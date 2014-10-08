package net.demo.netty.udt.rendezvous;

import com.yammer.metrics.Metrics;
import com.yammer.metrics.core.Meter;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.udt.UdtMessage;
import io.netty.channel.udt.nio.NioUdtProvider;

import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Handler implementation for the echo peer. It initiates the ping-pong traffic
 * between the echo peers by sending the first message to the other peer on
 * activation.
 */
public class MsgEchoPeerHandler extends SimpleChannelInboundHandler<UdtMessage> {

	private static final Logger log = Logger.getLogger(MsgEchoPeerHandler.class.getName());

	private final UdtMessage message;

	public MsgEchoPeerHandler(final int messageSize) {
		super(false);
		final ByteBuf byteBuf = Unpooled.buffer(messageSize);
		for (int i = 0; i < byteBuf.capacity(); i++) {
			byteBuf.writeByte((byte) i);
		}
		message = new UdtMessage(byteBuf);
	}

	final Meter meter = Metrics.newMeter(MsgEchoPeerHandler.class, "rate", "bytes", TimeUnit.SECONDS);

	@Override
	public void channelActive(final ChannelHandlerContext ctx) throws Exception {
//		log.info("ECHO active " + NioUdtProvider.socketUDT(ctx.channel()).toStringOptions());
		ctx.writeAndFlush(message);
	}

	@Override
	public void exceptionCaught(final ChannelHandlerContext ctx, final Throwable cause) {
		log.log(Level.WARNING, "close the connection when an exception is raised", cause);
		ctx.close();
	}

	@Override
	public void channelRead0(ChannelHandlerContext ctx, UdtMessage message) throws Exception {
		meter.mark(message.content().readableBytes());

		ctx.write(message);
	}

	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		ctx.flush();
	}
}
