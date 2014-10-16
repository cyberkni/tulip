package net.demo.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
<pre>
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
 */
public class ControlThread implements Runnable {

    private Config config = null;

    public ControlThread(Config config) {
        this.config = config;
    }

    public void run() {
        for (;;) {
            // open a reader on the standard input
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

            String input = "";

            try {
                input = br.readLine();
            }
            catch (IOException ioe) {
                ;
            }

            if (input.toLowerCase().equals(Config.CMD_QUIT.toLowerCase())) {
                System.out.println("Shutting down ...");
                System.exit(0);
            } else if (input.toLowerCase().equals(Config.CMD_STATUS.toLowerCase())) {
                System.out.println("Running Threads: " + config.getRunningThreads());
            } else if (input.toLowerCase().equals(Config.CMD_CONFIG.toLowerCase())) {
                System.out.println("Current Configuration:");
                System.out.println("Port: " + config.getPort());
                System.out.println("ServerRoot: " + config.getServerRoot());
                System.out.println("Default Charset: " + config.getDefaultCharset());
                System.out.println("Default MIME-Type: " + config.getMimeType());
                System.out.println("Directory List View: " + config.isDirectoryListView());
            } else if (input.toLowerCase().equals(Config.CMD_MIME.toLowerCase())) {
                // TODO get mime-types
            }

            // Sleep
            try {
                Thread.sleep(100);
            }
            catch (InterruptedException e) {
                ;
            }
        }
    }

}
