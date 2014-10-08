package net.demo.netty.advanced.portunification;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * Serves two protocols (HTTP and Factorial) using only one port, enabling
 * either SSL or GZIP dynamically on demand.
 * <p>
 * Because SSL and GZIP are enabled on demand, 5 combinations per protocol are
 * possible: none, SSL only, GZIP only, SSL + GZIP, and GZIP + SSL.
 */
public class PortUnificationServer {

	private final int port;

	public PortUnificationServer(int port) {
		this.port = port;
	}

	public void run() throws Exception {
		EventLoopGroup bossGroup = new NioEventLoopGroup();
		EventLoopGroup workerGroup = new NioEventLoopGroup();
		try {
			ServerBootstrap b = new ServerBootstrap();
			b.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class)
					.childHandler(new ChannelInitializer<SocketChannel>() {
						@Override
						public void initChannel(SocketChannel ch) throws Exception {
							ch.pipeline().addLast(new PortUnificationServerHandler());
						}
					});

			// Bind and start to accept incoming connections.
			b.bind(port).sync().channel().closeFuture().sync();
		} finally {
			bossGroup.shutdownGracefully();
			workerGroup.shutdownGracefully();
		}
	}

	public static void main(String[] args) throws Exception {
		int port;
		if (args.length > 0) {
			port = Integer.parseInt(args[0]);
		} else {
			port = 8080;
		}
		new PortUnificationServer(port).run();
	}
}
