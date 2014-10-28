/*
 * Created on 25.10.2006
 */
package net.demo.http;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

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
 * @author Dominik
 * @author Philip
 *
 */
public class WebServer {

    /**
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) {
        Config config = new Config();
        ServerSocket listenSocket = null;
        try {
            // read the config
            config.readConfig(Config.CONFIG_FILE);
            // check command line arg count
            if (args.length > 0) {
                // parse args
                config.setPort(args[0]);
            }
            // Establish the listen socket
            listenSocket = new ServerSocket(config.getPort());
            System.out.println(Config.PRODUCT_NAME + " " + Config.PRODUCT_VERSION + " starting...");
            System.out.println("Using Port " + config.getPort());
            System.out.println("Type QUIT to stop the webserver.");
            // System.out.println("Type STATUS to get the number of running
            // threads.");
            System.out.println("Type CONFIG to get the current configuration.");
            // System.out.println("Type MIME to get the current configured
            // mime-types.");
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        // Start a control thread (for shutdown, etc.)
        Thread controlThread = new Thread(new ControlThread(config));
        controlThread.start();

        // Process HTTP service requests in an infinite loop
        for (;;) {
            // Listen for a TCP connection request
            Socket connectionSocket = null;
            try {
                connectionSocket = listenSocket.accept();
            }
            catch (IOException e) {
                e.printStackTrace();
            }

            // Construct an object to process the HTTP request message
            HttpRequest request = null;
            try {
                request = new HttpRequest(connectionSocket, config);
            }
            catch (Exception e) {
                e.printStackTrace();
            }

            // Create a new thread to process the request
            Thread thread = new Thread(request);

            // Start the thread
            thread.start();
        }
    }

}
