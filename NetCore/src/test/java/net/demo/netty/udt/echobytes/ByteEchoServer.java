//package net.demo.netty.udt.echobytes;
//
//import io.netty.bootstrap.ServerBootstrap;
//import io.netty.channel.ChannelFuture;
//import io.netty.channel.ChannelInitializer;
//import io.netty.channel.ChannelOption;
//import io.netty.channel.nio.NioEventLoopGroup;
//import io.netty.channel.udt.UdtChannel;
//import io.netty.channel.udt.nio.NioUdtProvider;
//import io.netty.handler.logging.LogLevel;
//import io.netty.handler.logging.LoggingHandler;
//
//import java.util.concurrent.ThreadFactory;
//import java.util.logging.Logger;
//
///**
// * UDT Byte Stream Server
// * <p>
// * Echoes back any received data from a client.
// */
//public class ByteEchoServer {
//
//	private static final Logger log = Logger.getLogger(ByteEchoServer.class.getName());
//
//	private final int port;
//
//	public ByteEchoServer(final int port) {
//		this.port = port;
//	}
//
//	public void run() throws Exception {
//		final ThreadFactory acceptFactory = new ThreadFactory() {
//			@Override
//			public Thread newThread(Runnable r) {
//				Thread thread = new Thread(r);
//				thread.setName("accept");
//				return thread;
//			}
//		};
//		final ThreadFactory connectFactory = new ThreadFactory() {
//			@Override
//			public Thread newThread(Runnable r) {
//				Thread thread = new Thread(r);
//				thread.setName("connect");
//				return thread;
//			}
//		};
//		final NioEventLoopGroup acceptGroup = new NioEventLoopGroup(1, acceptFactory, NioUdtProvider.BYTE_PROVIDER);
//		final NioEventLoopGroup connectGroup = new NioEventLoopGroup(1, connectFactory, NioUdtProvider.BYTE_PROVIDER);
//		// Configure the server.
//		try {
//			final ServerBootstrap boot = new ServerBootstrap();
//			boot.group(acceptGroup, connectGroup).channelFactory(NioUdtProvider.BYTE_ACCEPTOR)
//					.option(ChannelOption.SO_BACKLOG, 10).handler(new LoggingHandler(LogLevel.INFO))
//					.childHandler(new ChannelInitializer<UdtChannel>() {
//						@Override
//						public void initChannel(final UdtChannel ch) throws Exception {
//							ch.pipeline().addLast(new LoggingHandler(LogLevel.INFO), new ByteEchoServerHandler());
//						}
//					});
//			// Start the server.
//			final ChannelFuture future = boot.bind(port).sync();
//			// Wait until the server socket is closed.
//			future.channel().closeFuture().sync();
//		} finally {
//			// Shut down all event loops to terminate all threads.
//			acceptGroup.shutdownGracefully();
//			connectGroup.shutdownGracefully();
//		}
//	}
//
//	public static void main(final String[] args) throws Exception {
//		log.info("init");
//
//		final int port = 1234;
//
//		new ByteEchoServer(port).run();
//
//		log.info("done");
//	}
//
//}