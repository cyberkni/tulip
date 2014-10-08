package net.demo.netty.http.spdy;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.ssl.SslHandler;
import net.demo.netty.textprotocols.securechat.SecureChatSslContextFactory;

import org.eclipse.jetty.npn.NextProtoNego;

import javax.net.ssl.SSLEngine;

/**
 * Sets up the Netty pipeline
 */
public class SpdyServerInitializer extends ChannelInitializer<SocketChannel> {
	@Override
	public void initChannel(SocketChannel ch) throws Exception {
		ChannelPipeline p = ch.pipeline();

		SSLEngine engine = SecureChatSslContextFactory.getServerContext().createSSLEngine();
		engine.setUseClientMode(false);
		p.addLast("ssl", new SslHandler(engine));

		// Setup NextProtoNego with our server provider
		NextProtoNego.put(engine, new SpdyServerProvider());
		NextProtoNego.debug = true;

		// Negotiates with the browser if SPDY or HTTP is going to be used
		p.addLast("handler", new SpdyOrHttpHandler());
	}
}
