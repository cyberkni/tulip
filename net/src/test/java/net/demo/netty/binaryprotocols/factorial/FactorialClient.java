package net.demo.netty.binaryprotocols.factorial;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * Sends a sequence of integers to a {@link FactorialServer} to calculate the
 * factorial of the specified integer.
 */
public class FactorialClient {

	private final String host;
	private final int port;
	private final int count;

	public FactorialClient(String host, int port, int count) {
		this.host = host;
		this.port = port;
		this.count = count;
	}

	public void run() throws Exception {
		EventLoopGroup group = new NioEventLoopGroup();
		try {
			Bootstrap b = new Bootstrap();
			b.group(group).channel(NioSocketChannel.class).handler(new FactorialClientInitializer(count));

			// Make a new connection.
			ChannelFuture f = b.connect(host, port).sync();

			// Get the handler instance to retrieve the answer.
			FactorialClientHandler handler = (FactorialClientHandler) f.channel().pipeline().last();

			// Print out the answer.
			System.err.format("Factorial of %,d is: %,d", count, handler.getFactorial());
		} finally {
			group.shutdownGracefully();
		}
	}

	public static void main(String[] args) throws Exception {
		// Print usage if no argument is specified.
		if (args.length != 3) {
			System.err.println("Usage: " + FactorialClient.class.getSimpleName() + " <host> <port> <count>");
			return;
		}

		// Parse options.
		String host = args[0];
		int port = Integer.parseInt(args[1]);
		int count = Integer.parseInt(args[2]);
		if (count <= 0) {
			throw new IllegalArgumentException("count must be a positive integer.");
		}

		new FactorialClient(host, port, count).run();
	}
}
