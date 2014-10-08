package net.demo.netty.http.spdy;

import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;
import io.netty.handler.codec.spdy.SpdyFrame;
import io.netty.util.internal.logging.InternalLogLevel;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;

/**
 * Logs SPDY frames for debugging purposes.
 */
public class SpdyFrameLogger extends ChannelDuplexHandler {

	private enum Direction {
		INBOUND, OUTBOUND
	}

	protected final InternalLogger logger;
	private final InternalLogLevel level;

	public SpdyFrameLogger(InternalLogLevel level) {
		if (level == null) {
			throw new NullPointerException("level");
		}

		logger = InternalLoggerFactory.getInstance(getClass());
		this.level = level;
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		if (acceptMessage(msg)) {
			log((SpdyFrame) msg, Direction.INBOUND);
		}
		super.channelRead(ctx, msg);
	}

	@Override
	public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
		if (acceptMessage(msg)) {
			log((SpdyFrame) msg, Direction.OUTBOUND);
		}
		super.write(ctx, msg, promise);
	}

	private static boolean acceptMessage(Object msg) throws Exception {
		return msg instanceof SpdyFrame;
	}

	private void log(SpdyFrame msg, Direction d) {
		if (logger.isEnabled(level)) {
			StringBuilder b = new StringBuilder("\n----------------").append(d.name()).append("--------------------\n");
			b.append(msg);
			b.append("\n------------------------------------");
			logger.log(level, b.toString());
		}
	}
}
