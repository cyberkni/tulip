/*
 * Created on 26.10.2006
 */
package net.demo.http;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

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
 *
 */
public class Header {

    OutputStream      os          = null;

    ArrayList<String> headerLines = new ArrayList<String>();

    public Header(OutputStream os) {
        this.os = os;
    }

    public void addHeaderLine(String line) {
        headerLines.add(line);
    }

    public boolean sendHeader() throws IOException {
        // add our servers signature
        this.addHeaderLine("Server: " + Config.SIGNATURE);
        for (int i = 0; i < headerLines.size(); i++) {
            os.write((headerLines.get(i) + Config.CRLF).getBytes());
        }
        // Send a blank line to indicate the end of the header lines.
        os.write(Config.CRLF.getBytes());
        return true;
    }

}
