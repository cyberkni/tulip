package net.demo.netty.tinde;

import java.util.Iterator;

import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelStateEvent;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelUpstreamHandler;

/**
 * 
 * @author 刘飞 E-mail:liufei_it@126.com
 * @version 1.0
 * @since 2014年2月13日 下午1:45:23
 */
public class ServerHandler extends SimpleChannelUpstreamHandler {
	private final IoSession session;

	public ServerHandler(IoSession session) {
		super();
		this.session = session;
	}

	@Override
	public void channelConnected(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
		session.add(ctx.getChannel());
		Iterator<Channel> channels = session.iterator();
		while(channels.hasNext()) {
			Channel channel = channels.next();
			System.out.println("===================================");
			System.out.println("class : " + channel.getClass());
			System.out.println("Id : " + channel.getId());
			System.out.println("InterestOps : " + channel.getInterestOps());
			System.out.println("RemoteAddress : " + channel.getRemoteAddress());
		}
	}

	@Override
	public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) throws Exception {
		System.out.println("Server messageReceived : " + e.getMessage());
		ctx.getChannel().write("hello client!!!");
		session.write("Message from session...");
	}
}