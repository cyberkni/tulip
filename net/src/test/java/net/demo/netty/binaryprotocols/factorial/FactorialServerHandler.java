package net.demo.netty.binaryprotocols.factorial;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.math.BigInteger;
import java.util.Formatter;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Handler for a server-side channel. This handler maintains stateful
 * information which is specific to a certain channel using member variables.
 * Therefore, an instance of this handler can cover only one channel. You have
 * to create a new handler instance whenever you create a new channel and insert
 * this handler to avoid a race condition.
 */
public class FactorialServerHandler extends SimpleChannelInboundHandler<BigInteger> {

	private static final Logger logger = Logger.getLogger(FactorialServerHandler.class.getName());

	private BigInteger lastMultiplier = new BigInteger("1");
	private BigInteger factorial = new BigInteger("1");

	@Override
	public void channelRead0(ChannelHandlerContext ctx, BigInteger msg) throws Exception {
		// Calculate the cumulative factorial and send it to the client.
		lastMultiplier = msg;
		factorial = factorial.multiply(msg);
		ctx.writeAndFlush(factorial);
	}

	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		Formatter fmt = new Formatter();
		logger.info(fmt.format("Factorial of %,d is: %,d", lastMultiplier, factorial).toString());
		fmt.close();
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		logger.log(Level.WARNING, "Unexpected exception from downstream.", cause);
		ctx.close();
	}
}
