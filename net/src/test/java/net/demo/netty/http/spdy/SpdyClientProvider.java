package net.demo.netty.http.spdy;

import org.eclipse.jetty.npn.NextProtoNego.ClientProvider;

import java.util.List;

import static io.netty.handler.codec.spdy.SpdyOrHttpChooser.SelectedProtocol.*;

/**
 * The Jetty project provides an implementation of the Transport Layer Security
 * (TLS) extension for Next Protocol Negotiation (NPN) for OpenJDK 7 or greater.
 * NPN allows the application layer to negotiate which protocol to use over the
 * secure connection.
 * <p>
 * This NPN service provider negotiates using SPDY.
 * <p>
 * To enable NPN support, start the JVM with:
 * {@code java -Xbootclasspath/p:<path_to_npn_boot_jar> ...}. The
 * "path_to_npn_boot_jar" is the path on the file system for the NPN Boot Jar
 * file which can be downloaded from Maven at coordinates
 * org.mortbay.jetty.npn:npn-boot. Different versions applies to different
 * OpenJDK versions.
 * 
 * @see <a
 *      href="http://www.eclipse.org/jetty/documentation/current/npn-chapter.html">Jetty
 *      documentation</a>
 */
public class SpdyClientProvider implements ClientProvider {

	private String selectedProtocol;

	@Override
	public String selectProtocol(List<String> protocols) {
		if (protocols.contains(SPDY_3_1.protocolName())) {
			return SPDY_3_1.protocolName();
		}
		return selectedProtocol;
	}

	@Override
	public boolean supports() {
		return true;
	}

	@Override
	public void unsupported() {
		selectedProtocol = HTTP_1_1.protocolName();
	}
}
