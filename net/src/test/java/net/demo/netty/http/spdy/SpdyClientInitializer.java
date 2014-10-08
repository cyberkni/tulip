package net.demo.netty.http.spdy;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.spdy.SpdyFrameDecoder;
import io.netty.handler.codec.spdy.SpdyFrameEncoder;
import io.netty.handler.codec.spdy.SpdyHttpDecoder;
import io.netty.handler.codec.spdy.SpdyHttpEncoder;
import io.netty.handler.codec.spdy.SpdySessionHandler;
import io.netty.handler.ssl.SslHandler;
import net.demo.netty.textprotocols.securechat.SecureChatSslContextFactory;

import org.eclipse.jetty.npn.NextProtoNego;

import javax.net.ssl.SSLEngine;

import static io.netty.handler.codec.spdy.SpdyVersion.*;
import static io.netty.util.internal.logging.InternalLogLevel.*;

public class SpdyClientInitializer extends ChannelInitializer<SocketChannel> {

	private final HttpResponseClientHandler httpResponseHandler;

	public SpdyClientInitializer(HttpResponseClientHandler httpResponseHandler) {
		this.httpResponseHandler = httpResponseHandler;
	}

	private static final int MAX_SPDY_CONTENT_LENGTH = 1024 * 1024; // 1 MB

	@Override
	public void initChannel(SocketChannel ch) throws Exception {
		SSLEngine engine = SecureChatSslContextFactory.getClientContext().createSSLEngine();
		engine.setUseClientMode(true);
		NextProtoNego.put(engine, new SpdyClientProvider());
		NextProtoNego.debug = true;

		ChannelPipeline pipeline = ch.pipeline();

		pipeline.addLast("ssl", new SslHandler(engine));
		pipeline.addLast("spdyEncoder", new SpdyFrameEncoder(SPDY_3_1));
		pipeline.addLast("spdyDecoder", new SpdyFrameDecoder(SPDY_3_1));
		pipeline.addLast("spdyFrameLogger", new SpdyFrameLogger(INFO));
		pipeline.addLast("spdySessionHandler", new SpdySessionHandler(SPDY_3_1, false));
		pipeline.addLast("spdyHttpEncoder", new SpdyHttpEncoder(SPDY_3_1));
		pipeline.addLast("spdyHttpDecoder", new SpdyHttpDecoder(SPDY_3_1, MAX_SPDY_CONTENT_LENGTH));
		pipeline.addLast("spdyStreamIdHandler", new SpdyClientStreamIdHandler());
		pipeline.addLast("httpHandler", httpResponseHandler);
	}
}
