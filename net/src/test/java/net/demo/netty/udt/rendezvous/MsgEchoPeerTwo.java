//package net.demo.netty.udt.rendezvous;
//
//import java.net.InetSocketAddress;
//import java.util.concurrent.TimeUnit;
//import java.util.logging.Logger;
//
///**
// * UDT Message Flow Peer
// * <p>
// * Sends one message when a connection is open and echoes back any received data
// * to the other peer.
// */
//public class MsgEchoPeerTwo extends MsgEchoPeerBase {
//
//	private static final Logger log = Logger.getLogger(MsgEchoPeerTwo.class.getName());
//
//	public MsgEchoPeerTwo(final InetSocketAddress self, final InetSocketAddress peer, final int messageSize) {
//		super(self, peer, messageSize);
//	}
//
//	public static void main(final String[] args) throws Exception {
//		log.info("init");
//
//		// peer two is reporting metrics
////		UtilConsoleReporter.enable(3, TimeUnit.SECONDS);
//
//		final int messageSize = 64 * 1024;
//
//		final InetSocketAddress self = new InetSocketAddress(Config.hostTwo, Config.portTwo);
//
//		final InetSocketAddress peer = new InetSocketAddress(Config.hostOne, Config.portOne);
//
//		new MsgEchoPeerTwo(self, peer, messageSize).run();
//
//		log.info("done");
//	}
//
//}
