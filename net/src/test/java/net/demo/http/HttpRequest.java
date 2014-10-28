package net.demo.http;

import java.io.*;
import java.net.*;
import java.util.*;

/**
 * <pre>
 *  Copyright (c) 2006 Dominik Schulz
 *  Copyright (c) 2006 Florian Lindner
 *  Copyright (c) 2006 Philip Hartmann
 *  
 *  This file is part of jHTTPd.
 *
 *  jHTTPd is free software; you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation; either version 2 of the License, or
 *  (at your option) any later version.
 *
 *  jHTTPd is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *  
 *  You should have received a copy of the GNU General Public License
 *  along with jHTTPd; if not, write to the Free Software
 *  Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 *  </pre>
 *  
 *  @author Dominik
 *  @author Philip
 *  @author Florian
 */
public final class HttpRequest implements Runnable {

    /**
     * This method implements our own readline which we were advised to use
     * instead of the readers readline. the difference is simple: readline looks
     * for a New-Line (\n) character. We look for a combination of
     * Carriage-Return and New-Line (\r\n) because a New-Line can occur within a
     * line.
     * 
     * @param br
     *            the BufferedReader to read from
     * @return the line which was read from the reader
     * @throws IOException
     */
    private static String crlfReadline(BufferedReader br) throws IOException {
        int buffer = -1;
        String currentLine = "";
        while ((buffer = br.read()) != -1) {
            if (buffer != "\r".toCharArray()[0]) {
                currentLine += (char) buffer;
            } else {
                if (br.read() == "\n".toCharArray()[0]) {
                    return currentLine;
                } else {
                    currentLine += "\n";
                }
            }
        }
        return currentLine;
    }

    private static void sendBytes(FileInputStream fis, OutputStream os) throws IOException {
        // Construct a 1K buffer to hold bytes on their way to the socket.
        byte[] buffer = new byte[1024];
        int bytes = 0;
        // Copy requested file into the socket's output stream.

        while ((bytes = fis.read(buffer)) != -1)
            os.write(buffer, 0, bytes);
    }



    private Socket socket;

    private Config config;

    /**
     * The value of the Content-Length: header field
     */
    private int    contentLength = 0;

    /**
     * The value of the Host: header field
     */
    @SuppressWarnings("unused")
    private String host          = "";

    /*
     * private static String contentType(String fileName) { // just handle some
     * basic types here if (fileName.endsWith(".htm") ||
     * fileName.endsWith(".html")) return "text/html"; if
     * (fileName.endsWith(".txt")) return "text/plain"; if
     * (fileName.endsWith(".jpg") || fileName.endsWith(".jpeg") ||
     * fileName.endsWith(".JPG") || fileName.endsWith(".JPEG")) return
     * "image/jpeg"; if (fileName.endsWith(".png")) return "image/png"; if
     * (fileName.endsWith(".gif")) return "image/gif"; return ""; }
     */

    public HttpRequest(Socket socket, Config config) throws Exception {
        this.socket = socket;
        this.config = config;
    }

    private String contentType(String fileName) {
        StringTokenizer tokens = new StringTokenizer(fileName, ".");
        String currentToken = "";
        while (tokens.hasMoreTokens()) {
            currentToken = tokens.nextToken(); // we are only interested in the
            // last token
        }
        return this.config.getMimeType(currentToken);
    }

    private void processRequest() throws Exception {

        InputStream is = null;
        OutputStream os = null;

        InputStreamReader isr = null;
        BufferedReader br = null;

        OutputStreamWriter osw = null;
        BufferedWriter bw = null;

        try {
            config.countUp();

            is = socket.getInputStream();
            os = socket.getOutputStream();

            isr = new InputStreamReader(is);
            br = new BufferedReader(isr);

            osw = new OutputStreamWriter(os);
            bw = new BufferedWriter(osw);

            String requestLine = crlfReadline(br);

            String headerLine = null; // read the request header
            while ((headerLine = crlfReadline(br)).length() != 0) {
                System.out.println(headerLine);
                if (headerLine.startsWith("Content-Length:")) {
                    StringTokenizer tokens = new StringTokenizer(headerLine);
                    // String: Content-Length:
                    tokens.nextToken();
                    // the content-length
                    this.contentLength = Integer.parseInt(tokens.nextToken());
                }
                if (headerLine.startsWith("Host:")) {
                    StringTokenizer tokens = new StringTokenizer(headerLine);
                    // String: Content-Length:
                    tokens.nextToken();
                    // the content-length
                    this.host = tokens.nextToken();
                }
            }

            StringTokenizer tokens = new StringTokenizer(requestLine);
            String httpMethod = tokens.nextToken(); // GET, HEAD or POST
            String fileName = tokens.nextToken();
            @SuppressWarnings("unused")
            String httpVersion = tokens.nextToken();

            // build the header
            Header header = new Header(os);

            // Split filename
            StringTokenizer tokens1 = new StringTokenizer(fileName, "?");
            String realFileName = "";
            @SuppressWarnings("unused")
            String arguments = "";
            if (tokens1.hasMoreTokens()) {
                realFileName = tokens1.nextToken();
            }
            if (tokens1.hasMoreTokens()) {
                arguments = tokens1.nextToken();
            }

            // construct filename, will be checked later
            File file = new File(config.getServerRoot() + realFileName);

            String entityBody = null;

            if (httpMethod.equals(Config.HTTP_METHOD_GET)) {
                if (file.exists() && !file.isDirectory() && file.canRead()) {
                    // the requested object is a REAL file, send status 200
                    header.addHeaderLine(Config.HTTP_STATUS_200);
                    header.addHeaderLine("Content-type: " + contentType(fileName) + "; " + config.getDefaultCharset());
                    header.addHeaderLine("Content-Length: " + file.length());
                    header.sendHeader();
                    FileInputStream fis = new FileInputStream(file);
                    sendBytes(fis, os);
                    fis.close();
                } else if (file.exists() && file.isDirectory() && file.canRead()) {
                    // the requested object is a directory
                    // 302 index.* if found, 200 listview if listview = true,
                    // 403 if
                    // else fails
                    File[] files = file.listFiles();
                    // The first step is to look for a index.* file
                    for (int i = 0; i < files.length; i++) {
                        if (files[i].getName().contains("index.")) {
                            header.addHeaderLine(Config.HTTP_STATUS_302);
                            header.addHeaderLine("Location: " + files[i].getName());
                            header.sendHeader();
                            break;
                        }
                    }
                    if (config.isDirectoryListView()) {
                        // the next option is to try showing a list-view
                        header.addHeaderLine(Config.HTTP_STATUS_200);
                        header.addHeaderLine("Content-type: text/html" + "; " + config.getDefaultCharset());
                        header.sendHeader();
                        entityBody = "<html><head><title>Directory Index</title></head>\n";
                        entityBody += "<body><h2>Directory: " + file.getName() + "</h2><hr>";
                        // entityBody += "<a href=\"/" + file.getName() +
                        // "\">.<br
                        // />";
                        // entityBody += "<a href=\"/"
                        // + file.getParentFile().getName() + "\">..<br />";
                        for (int i = 0; i < files.length; i++) {
                            if (files[i].isDirectory()) {
                                entityBody += "<a href=\"" + file.getName() + "/" + files[i].getName() + "\">" + files[i].getName() + "</a><br />";
                            } else {
                                entityBody += "<a href=\"" + files[i].getName() + "\">" + files[i].getName() + "</a><br />";
                            }
                        }
                        entityBody += "<br /><hr />\n" + Config.SIGNATURE + "</body>\n</html>";
                        os.write(entityBody.getBytes());
                    } else { // the last option is to send a 403
                        header.addHeaderLine(Config.HTTP_STATUS_403);
                        header.addHeaderLine("Content-type: text/html" + "; " + config.getDefaultCharset());
                        entityBody = "<html><head><title>Forbidden</title></head>" + "<body><h1>Error 403 - Forbidden</h1>" + Config.SIGNATURE + "</body></html>";
                        header.sendHeader();
                        os.write(entityBody.getBytes());
                    }
                } else {
                    // 404 we cant read
                    header.addHeaderLine(Config.HTTP_STATUS_404);
                    header.addHeaderLine("Content-type: text/html" + "; " + config.getDefaultCharset());
                    entityBody = "<html><head><title>Not Found</title></head>" + "<body><h1>Error 404 - Not Found</h1>" + Config.SIGNATURE + "</body></html>";
                    header.sendHeader();
                    os.write(entityBody.getBytes());
                }
            } else if (httpMethod.equals(Config.HTTP_METHOD_HEAD)) {
                if (file.exists() && !file.isDirectory() && file.canRead()) {
                    // the requested object is a REAL file 200
                    header.addHeaderLine(Config.HTTP_STATUS_200);
                    header.addHeaderLine("Content-type: " + contentType(fileName) + "; " + config.getDefaultCharset());
                    header.addHeaderLine("Content-Length: " + file.length());
                    header.sendHeader();
                } else if (file.exists() && file.isDirectory() && file.canRead()) {
                    // the requested object is a directory
                    // 302 index.* if found, 200 listview if listview = true,
                    // 403 if
                    // else fails
                    File[] files = file.listFiles();
                    // The first step is to look for a index.* file
                    for (int i = 0; i < files.length; i++) {
                        if (files[i].getName().contains("index")) {
                            header.addHeaderLine(Config.HTTP_STATUS_302);
                            header.addHeaderLine("Location: " + files[i].getName());
                            header.sendHeader();
                            break;
                        }
                    }
                    if (config.isDirectoryListView()) {
                        // the next option is to try showing a list-view
                        header.addHeaderLine(Config.HTTP_STATUS_200);
                        header.sendHeader();
                    } else { // the last option is to send a 403
                        header.addHeaderLine(Config.HTTP_STATUS_403);
                        header.addHeaderLine("Content-type: text/html" + "; " + config.getDefaultCharset());
                        header.sendHeader();
                    }
                }
            } else if (httpMethod.equals(Config.HTTP_METHOD_POST)) {
                String reqEntityBody = "";
                int buffer = -1;
                while ((reqEntityBody.length() < this.contentLength) && ((buffer = br.read()) != -1)) {
                    reqEntityBody += (char) buffer;
                }
                header.addHeaderLine(Config.HTTP_STATUS_200);
                entityBody = "<html><head><title>POST Request</title></head>";
                entityBody += "<body>";
                StringTokenizer token = new StringTokenizer(reqEntityBody, "&");
                // split string into key=value pairs
                StringTokenizer tokenInner = null; // to split the key=value
                // pairs
                // into key and value
                String innerToken = "";
                while (token.hasMoreTokens()) {
                    innerToken = token.nextToken();
                    tokenInner = new StringTokenizer(innerToken, "=");
                    entityBody += "<strong>" + tokenInner.nextToken() + "</strong> " + tokenInner.nextToken() + "<br />";
                }
                entityBody += "</body></html>";
                header.sendHeader();
                os.write(entityBody.getBytes());
            } else {
                header.addHeaderLine(Config.HTTP_STATUS_500);
                header.sendHeader();
            }

        }
        catch (Exception e) {
            throw e;
        }
        finally {
            bw.close();
            os.close();
            br.close();

            socket.close();

            config.countDown();
        }
    }

    public void run() {
        try {
            processRequest();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

}
