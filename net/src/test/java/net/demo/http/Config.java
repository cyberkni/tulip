/*
 * Created on 25.10.2006
 */
package net.demo.http;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.StringTokenizer;

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
 * 
 * This class stores our webserver config. Since we will have more configuration
 * options than a simple port I think this would make sense.
 * 
 * @link http://www.faqs.org/rfcs/rfc1945.html
 * @author Dominik
 */
public class Config {

    private int                     runningThreads    = 0;

    /**
     * The port to bind the listen socket to
     */
    private int                     port              = 0;

    private String                  serverRoot        = ".";

    private String                  defaultCharset    = "ISO-8859-1";

    private String                  defaultType       = "text/plain";

    private boolean                 directoryListView = false;

    private HashMap<String, String> mimeTypes         = new HashMap<String, String>();

    public static final String      CRLF              = "\r\n";

    public static final String      CMD_QUIT          = "QUIT";

    public static final String      CMD_MIME          = "MIME";

    public static final String      CMD_CONFIG        = "CONFIG";

    public static final String      CONFIG_FILE       = "jhttpd.conf";

    public static final String      HTTP_METHOD_GET   = "GET";

    public static final String      HTTP_METHOD_HEAD  = "HEAD";

    public static final String      HTTP_METHOD_POST  = "POST";

    public static final String      PRODUCT_NAME      = "jHTTPd";

    public static final String      PRODUCT_VERSION   = "0.1a";

    public static final String      SIGNATURE         = PRODUCT_NAME + " " + PRODUCT_VERSION + " HTTP 1.0 Server";

    /**
     * Successful 2xx This class of status code indicates that the client's
     * request was successfully received, understood, and accepted. 200 OK The
     * request has succeeded. The information returned with the response is
     * dependent on the method used in the request, as follows: GET an entity
     * corresponding to the requested resource is sent in the response; HEAD the
     * response must only contain the header information and no Entity-Body;
     * POST an entity describing or containing the result of the action.
     */
    public static final String      HTTP_STATUS_200   = "HTTP/1.0 200 OK";

    /**
     * 201 Created The request has been fulfilled and resulted in a new resource
     * being created. The newly created resource can be referenced by the URI(s)
     * returned in the entity of the response. The origin server should create
     * the resource before using this Status-Code. If the action cannot be
     * carried out immediately, the server must include in the response body a
     * description of when the resource will be available; otherwise, the server
     * should respond with 202 (accepted). Of the methods defined by this
     * specification, only POST can create a resource.
     */
    public static final String      HTTP_STATUS_201   = "HTTP/1.0 201 Created";

    /**
     * 202 Accepted The request has been accepted for processing, but the
     * processing has not been completed. The request may or may not eventually
     * be acted upon, as it may be disallowed when processing actually takes
     * place. There is no facility for re-sending a status code from an
     * asynchronous operation such as this. The 202 response is intentionally
     * non-committal. Its purpose is to allow a server to accept a request for
     * some other process (perhaps a batch-oriented process that is only run
     * once per day) without requiring that the user agent's connection to the
     * server persist until the process is completed. The entity returned with
     * this response should include an indication of the request's current
     * status and either a pointer to a status monitor or some estimate of when
     * the user can expect the request to be fulfilled.
     */
    public static final String      HTTP_STATUS_202   = "HTTP/1.0 202 Accepted";

    /**
     * 204 No Content The server has fulfilled the request but there is no new
     * information to send back. If the client is a user agent, it should not
     * change its document view from that which caused the request to be
     * generated. This response is primarily intended to allow input for scripts
     * or other actions to take place without causing a change to the user
     * agent's active document view. The response may include new
     * metainformation in the form of entity headers, which should apply to the
     * document currently in the user agent's active view.
     */
    public static final String      HTTP_STATUS_204   = "HTTP/1.0 204 No Content";

    /**
     * 300 Multiple Choices This response code is not directly used by HTTP/1.0
     * applications, but serves as the default for interpreting the 3xx class of
     * responses. The requested resource is available at one or more locations.
     * Unless it was a HEAD request, the response should include an entity
     * containing a list of resource characteristics and locations from which
     * the user or user agent can choose the one most appropriate. If the server
     * has a preferred choice, it should include the URL in a Location field;
     * user agents may use this field value for automatic redirection.
     */
    public static final String      HTTP_STATUS_300   = "HTTP/1.0 300 Multiple Choices";

    /**
     * 301 Moved Permanently The requested resource has been assigned a new
     * permanent URL and any future references to this resource should be done
     * using that URL. Clients with link editing capabilities should
     * automatically relink references to the Request-URI to the new reference
     * returned by the server, where possible. The new URL must be given by the
     * Location field in the response. Unless it was a HEAD request, the
     * Entity-Body of the response should contain a short note with a hyperlink
     * to the new URL. If the 301 status code is received in response to a
     * request using the POST method, the user agent must not automatically
     * redirect the request unless it can be confirmed by the user, since this
     * might change the conditions under which the request was issued. Note:
     * When automatically redirecting a POST request after receiving a 301
     * status code, some existing user agents will erroneously change it into a
     * GET request.
     */
    public static final String      HTTP_STATUS_301   = "HTTP/1.0 301 Moved Permanently";

    /**
     * 302 Moved Temporarily The requested resource resides temporarily under a
     * different URL. Since the redirection may be altered on occasion, the
     * client should continue to use the Request-URI for future requests. The
     * URL must be given by the Location field in the response. Unless it was a
     * HEAD request, the Entity-Body of the response should contain a short note
     * with a hyperlink to the new URI(s). If the 302 status code is received in
     * response to a request using the POST method, the user agent must not
     * automatically redirect the request unless it can be confirmed by the
     * user, since this might change the conditions under which the request was
     * issued. Note: When automatically redirecting a POST request after
     * receiving a 302 status code, some existing user agents will erroneously
     * change it into a GET request.
     */
    public static final String      HTTP_STATUS_302   = "HTTP/1.0 302 Moved Temporarily";

    /**
     * 304 Not Modified If the client has performed a conditional GET request
     * and access is allowed, but the document has not been modified since the
     * date and time specified in the If-Modified-Since field, the server must
     * respond with this status code and not send an Entity-Body to the client.
     * Header fields contained in the response should only include information
     * which is relevant to cache managers or which may have changed
     * independently of the entity's Last-Modified date. Examples of relevant
     * header fields include: Date, Server, and Expires. A cache should update
     * its cached entity to reflect any new field values given in the 304
     * response.
     */

    public static final String      HTTP_STATUS_304   = "HTTP/1.0 304 Not Modified";

    /**
     * 9.4 Client Error 4xx The 4xx class of status code is intended for cases
     * in which the client seems to have erred. If the client has not completed
     * the request when a 4xx code is received, it should immediately cease
     * sending data to the server. Except when responding to a HEAD request, the
     * server should include an entity containing an explanation of the error
     * situation, and whether it is a temporary or permanent condition. These
     * status codes are applicable to any request method. Note: If the client is
     * sending data, server implementations on TCP should be careful to ensure
     * that the client acknowledges receipt of the packet(s) containing the
     * response prior to closing the input connection. If the client continues
     * sending data to the server after the close, the server's controller will
     * send a reset packet to the client, which may erase the client's
     * unacknowledged input buffers before they can be read and interpreted by
     * the HTTP application. 400 Bad Request The request could not be understood
     * by the server due to malformed syntax. The client should not repeat the
     * request without modifications.
     */
    public static final String      HTTP_STATUS_400   = "HTTP/1.0 400 Bad Request";

    /**
     * 401 Unauthorized The request requires user authentication. The response
     * must include a WWW-Authenticate header field (Section 10.16) containing a
     * challenge applicable to the requested resource. The client may repeat the
     * request with a suitable Authorization header field (Section 10.2). If the
     * request already included Authorization credentials, then the 401 response
     * indicates that authorization has been refused for those credentials. If
     * the 401 response contains the same challenge as the prior response, and
     * the user agent has already attempted authentication at least once, then
     * the user should be presented the entity that was given in the response,
     * since that entity may include relevant diagnostic information. HTTP
     * access authentication is explained in Section 11.
     */
    public static final String      HTTP_STATUS_401   = "HTTP/1.0 401 Unauthorized";

    /**
     * 403 Forbidden The server understood the request, but is refusing to
     * fulfill it. Authorization will not help and the request should not be
     * repeated. If the request method was not HEAD and the server wishes to
     * make public why the request has not been fulfilled, it should describe
     * the reason for the refusal in the entity body. This status code is
     * commonly used when the server does not wish to reveal exactly why the
     * request has been refused, or when no other response is applicable.
     */
    public static final String      HTTP_STATUS_403   = "HTTP/1.0 403 Forbidden";

    /**
     * 404 Not Found The server has not found anything matching the Request-URI.
     * No indication is given of whether the condition is temporary or
     * permanent. If the server does not wish to make this information available
     * to the client, the status code 403 (forbidden) can be used instead.
     */
    public static final String      HTTP_STATUS_404   = "HTTP/1.0 404 Not Found";

    /**
     * 9.5 Server Error 5xx Response status codes beginning with the digit "5"
     * indicate cases in which the server is aware that it has erred or is
     * incapable of performing the request. If the client has not completed the
     * request when a 5xx code is received, it should immediately cease sending
     * data to the server. Except when responding to a HEAD request, the server
     * should include an entity containing an explanation of the error
     * situation, and whether it is a temporary or permanent condition. These
     * response codes are applicable to any request method and there are no
     * required header fields. 500 Internal Server Error The server encountered
     * an unexpected condition which prevented it from fulfilling the request.
     */
    public static final String      HTTP_STATUS_500   = "HTTP/1.0 500 Internal Server Error";

    /**
     * 501 Not Implemented The server does not support the functionality
     * required to fulfill the request. This is the appropriate response when
     * the server does not recognize the request method and is not capable of
     * supporting it for any resource.
     */
    public static final String      HTTP_STATUS_501   = "HTTP/1.0 501 Not Implemented";

    /**
     * 502 Bad Gateway The server, while acting as a gateway or proxy, received
     * an invalid response from the upstream server it accessed in attempting to
     * fulfill the request.
     */
    public static final String      HTTP_STATUS_502   = "HTTP/1.0 502 Bad Gateway";

    /**
     * 503 Service Unavailable The server is currently unable to handle the
     * request due to a temporary overloading or maintenance of the server. The
     * implication is that this is a temporary condition which will be
     * alleviated after some delay. Note: The existence of the 503 status code
     * does not imply that a server must use it when becoming overloaded. Some
     * servers may wish to simply refuse the connection.
     */
    public static final String      HTTP_STATUS_503   = "HTTP/1.0 503 Service Unavailable";

    public static final String      CMD_STATUS        = "STATUS";

    public void setPort(int port) {
        this.port = port;
    }

    public void setPort(String port) {
        this.port = Integer.parseInt(port);
    }

    public int getPort() {
        return this.port;
    }

    public String getMimeType(String extension) {
        // System.err.print("Config#getMimeType extension: " + extension);
        if (mimeTypes.containsKey(extension)) {
            // System.err.println(" corresponding MIME-Type: " +
            // mimeTypes.get(extension));
            return mimeTypes.get(extension);
        } else {
            // System.err.println(" corresponding MIME-Type: " +
            // this.defaultType);
            return this.defaultType;
        }
    }

    public String getMimeType() {
        return this.defaultType;
    }

    public boolean readConfig(String configFile) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader(configFile));
        String currentLine = "";
        while ((currentLine = in.readLine()) != null) {
            if (!currentLine.startsWith("#")) { // skip comments
                StringTokenizer tokens = new StringTokenizer(currentLine);
                String currentToken = tokens.nextToken();
                if (currentToken.equals("AddType")) {
                    String value = tokens.nextToken();
                    while (tokens.hasMoreTokens()) {
                        String key = tokens.nextToken();
                        mimeTypes.put(key, value);
                    }
                } else if (currentToken.equals("ServerRoot")) {
                    this.serverRoot = tokens.nextToken();
                } else if (currentToken.equals("Listen")) {
                    this.port = Integer.parseInt(tokens.nextToken());
                } else if (currentToken.equals("AddDefaultCharset")) {
                    this.defaultCharset = tokens.nextToken();
                } else if (currentToken.equals("DefaultType")) {
                    this.defaultType = tokens.nextToken();
                } else if (currentToken.equals("DirectoryListView")) {
                    if (tokens.nextToken().toLowerCase().equals("yes")) {
                        this.directoryListView = true;
                    } else {
                        this.directoryListView = false;
                    }
                }
            }
        }
        return true;
    }

    /**
     * @return the directoryListView
     */
    public boolean isDirectoryListView() {
        return directoryListView;
    }

    /**
     * @return the defaultCharset
     */
    public String getDefaultCharset() {
        return defaultCharset;
    }

    /**
     * @return the serverRoot
     */
    public String getServerRoot() {
        return serverRoot;
    }

    public synchronized void countDown() {
        runningThreads--;
    }

    public synchronized void countUp() {
        runningThreads++;
    }

    /**
     * @return the runningThreads
     */
    public int getRunningThreads() {
        return runningThreads;
    }

    /**
     * @return the mimeTypes
     */
    public HashMap<String, String> getMimeTypes() {
        return mimeTypes;
    }

}
