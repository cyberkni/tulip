package net.demo.netty.http.spdy;

import io.netty.channel.ChannelInboundHandler;
import io.netty.handler.codec.spdy.SpdyOrHttpChooser;
import org.eclipse.jetty.npn.NextProtoNego;

import javax.net.ssl.SSLEngine;
import java.util.logging.Logger;

/**
 * Negotiates with the browser if SPDY or HTTP is going to be used. Once
 * decided, the Netty pipeline is setup with the correct handlers for the
 * selected protocol.
 */
public class SpdyOrHttpHandler extends SpdyOrHttpChooser {
	private static final Logger logger = Logger.getLogger(SpdyOrHttpHandler.class.getName());
	private static final int MAX_CONTENT_LENGTH = 1024 * 100;

	public SpdyOrHttpHandler() {
		this(MAX_CONTENT_LENGTH, MAX_CONTENT_LENGTH);
	}

	public SpdyOrHttpHandler(int maxSpdyContentLength, int maxHttpContentLength) {
		super(maxSpdyContentLength, maxHttpContentLength);
	}

	@Override
	protected SelectedProtocol getProtocol(SSLEngine engine) {
		SpdyServerProvider provider = (SpdyServerProvider) NextProtoNego.get(engine);
		SelectedProtocol selectedProtocol = provider.getSelectedProtocol();

		logger.info("Selected Protocol is " + selectedProtocol);
		return selectedProtocol;
	}

	@Override
	protected ChannelInboundHandler createHttpRequestHandlerForHttp() {
		return new SpdyServerHandler();
	}

}
