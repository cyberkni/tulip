package net.demo.netty.udt.echobytes;

import com.yammer.metrics.Metrics;
import com.yammer.metrics.core.Meter;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.udt.nio.NioUdtProvider;

import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Handler implementation for the echo client. It initiates the ping-pong
 * traffic between the echo client and server by sending the first message to
 * the server on activation.
 */
public class ByteEchoClientHandler extends SimpleChannelInboundHandler<ByteBuf> {

	private static final Logger log = Logger.getLogger(ByteEchoClientHandler.class.getName());

	private final ByteBuf message;

	final Meter meter = Metrics.newMeter(ByteEchoClientHandler.class, "rate", "bytes", TimeUnit.SECONDS);

	public ByteEchoClientHandler(final int messageSize) {
		super(false);

		message = Unpooled.buffer(messageSize);

		for (int i = 0; i < message.capacity(); i++) {
			message.writeByte((byte) i);
		}
	}

	@Override
	public void channelActive(final ChannelHandlerContext ctx) throws Exception {
//		log.info("ECHO active " + NioUdtProvider.socketUDT(ctx.channel()).toStringOptions());
		ctx.writeAndFlush(message);
	}

	@Override
	public void channelRead0(ChannelHandlerContext ctx, ByteBuf msg) throws Exception {
		meter.mark(msg.readableBytes());

		ctx.write(msg);
	}

	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		ctx.flush();
	}

	@Override
	public void exceptionCaught(final ChannelHandlerContext ctx, final Throwable cause) {
		log.log(Level.WARNING, "close the connection when an exception is raised", cause);
		ctx.close();
	}

}
