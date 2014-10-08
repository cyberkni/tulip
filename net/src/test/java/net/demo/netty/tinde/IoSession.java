package net.demo.netty.tinde;

import org.jboss.netty.channel.group.DefaultChannelGroup;

/**
 * 
 * @author 刘飞 E-mail:liufei_it@126.com
 * @version 1.0
 * @since 2014年2月18日 上午11:03:13
 */
public class IoSession extends DefaultChannelGroup {

	public IoSession() {
		super("channel-x0-session");
	}

	public IoSession(String name) {
		super(name);
	}
}