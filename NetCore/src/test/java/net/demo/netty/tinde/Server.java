package net.demo.netty.tinde;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;
import org.jboss.netty.handler.codec.http.HttpChunkAggregator;
import org.jboss.netty.handler.codec.http.HttpContentCompressor;
import org.jboss.netty.handler.codec.string.StringDecoder;
import org.jboss.netty.handler.codec.string.StringEncoder;
import org.jboss.netty.handler.execution.ExecutionHandler;
import org.jboss.netty.handler.execution.OrderedMemoryAwareThreadPoolExecutor;
import org.jboss.netty.handler.stream.ChunkedWriteHandler;
import org.jboss.netty.handler.timeout.ReadTimeoutHandler;
import org.jboss.netty.util.HashedWheelTimer;

/**
 * 
 * @author 刘飞 E-mail:liufei_it@126.com
 * @version 1.0
 * @since 2014年2月13日 下午1:44:50
 */
public class Server {
	private Channel channel = null;
	private IoSession session;

	public static void main(String[] args) throws Exception {
		Server server = new Server();
		server.start();
	}

	public void start() {
		try {
			ServerBootstrap bootstrap = new ServerBootstrap(new NioServerSocketChannelFactory(
					Executors.newCachedThreadPool(), Executors.newCachedThreadPool()));
			session = new IoSession();
			final ServerHandler handler = new ServerHandler(session);
			bootstrap.setPipelineFactory(new ChannelPipelineFactory() {
				private OrderedMemoryAwareThreadPoolExecutor memoryAwareThreadPoolExecutor = new OrderedMemoryAwareThreadPoolExecutor(
						50, 10000, 1000000);
				private HashedWheelTimer hashedWheelTimer = new HashedWheelTimer();
				private ExecutionHandler executionHandler = new ExecutionHandler(memoryAwareThreadPoolExecutor);

				@Override
				public ChannelPipeline getPipeline() throws Exception {
					ChannelPipeline pipeline = Channels.pipeline();
					pipeline.addLast("executor", executionHandler);
					pipeline.addLast("timeout", new ReadTimeoutHandler(hashedWheelTimer, 3000));
					pipeline.addLast("decoder", new StringDecoder());
					pipeline.addLast("aggregator", new HttpChunkAggregator(1024000));
					pipeline.addLast("encoder", new StringEncoder());
					pipeline.addLast("deflater", new HttpContentCompressor());
					pipeline.addLast("chunkedWriter", new ChunkedWriteHandler());
					pipeline.addLast("handler", handler);
					return pipeline;
				}
			});
			bootstrap.setOption("child.tcpNoDelay", true);  
	        bootstrap.setOption("child.keepAlive", true);
			channel = bootstrap.bind(new InetSocketAddress(2001));
			session.add(channel);
			System.out.println("Server start success!!!");
		} catch (Exception e) {
			e.printStackTrace(System.err);
		}
	}

	public void shutdown() {
		try {
			channel.unbind();
			channel.disconnect();
			channel.close();
		} catch (Exception e) {
			e.printStackTrace(System.err);
		}
	}
}
