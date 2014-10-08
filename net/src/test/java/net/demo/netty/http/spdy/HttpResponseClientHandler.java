package net.demo.netty.http.spdy;

import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.HttpContent;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpObject;
import io.netty.handler.codec.http.HttpResponse;
import io.netty.handler.codec.http.LastHttpContent;
import io.netty.util.CharsetUtil;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import net.demo.netty.http.snoop.HttpSnoopClientHandler;

/**
 * This is a modified version of {@link HttpSnoopClientHandler} that uses a
 * {@link BlockingQueue} to wait until an HTTPResponse is received.
 */
public class HttpResponseClientHandler extends SimpleChannelInboundHandler<HttpObject> {

	private final BlockingQueue<ChannelFuture> queue = new LinkedBlockingQueue<ChannelFuture>();

	@Override
	public void channelRead0(ChannelHandlerContext ctx, HttpObject msg) throws Exception {
		if (msg instanceof HttpResponse) {
			HttpResponse response = (HttpResponse) msg;

			System.out.println("STATUS: " + response.getStatus());
			System.out.println("VERSION: " + response.getProtocolVersion());
			System.out.println();

			if (!response.headers().isEmpty()) {
				for (String name : response.headers().names()) {
					for (String value : response.headers().getAll(name)) {
						System.out.println("HEADER: " + name + " = " + value);
					}
				}
				System.out.println();
			}

			if (HttpHeaders.isTransferEncodingChunked(response)) {
				System.out.println("CHUNKED CONTENT {");
			} else {
				System.out.println("CONTENT {");
			}
		}
		if (msg instanceof HttpContent) {
			HttpContent content = (HttpContent) msg;

			System.out.print(content.content().toString(CharsetUtil.UTF_8));
			System.out.flush();

			if (content instanceof LastHttpContent) {
				System.out.println("} END OF CONTENT");
				queue.add(ctx.channel().newSucceededFuture());
			}
		}
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		queue.add(ctx.channel().newFailedFuture(cause));
		cause.printStackTrace();
		ctx.close();
	}

	public BlockingQueue<ChannelFuture> queue() {
		return queue;
	}

}
