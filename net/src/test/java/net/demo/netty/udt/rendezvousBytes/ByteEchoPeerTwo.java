//package net.demo.netty.udt.rendezvousBytes;
//
//import java.net.InetSocketAddress;
//import java.net.SocketAddress;
//import java.util.concurrent.TimeUnit;
//import java.util.logging.Logger;
//
//import net.demo.netty.udt.rendezvous.Config;
//
///**
// * UDT Byte Stream Peer
// * <p/>
// * Sends one message when a connection is open and echoes back any received data
// * to the server. Simply put, the echo client initiates the ping-pong traffic
// * between the echo client and server by sending the first message to the
// * server.
// * <p/>
// */
//public class ByteEchoPeerTwo extends ByteEchoPeerBase0 {
//	private static final Logger log = Logger.getLogger(ByteEchoPeerTwo.class.getName());
//
//	public ByteEchoPeerTwo(int messageSize, SocketAddress myAddress, SocketAddress peerAddress) {
//		super(messageSize, myAddress, peerAddress);
//	}
//
//	public static void main(String[] args) throws Exception {
//		log.info("init");
//		// peer two is reporting metrics
////		UtilConsoleReporter.enable(3, TimeUnit.SECONDS);
//		final int messageSize = 64 * 1024;
//		final InetSocketAddress myAddress = new InetSocketAddress(Config.hostTwo, Config.portTwo);
//		final InetSocketAddress peerAddress = new InetSocketAddress(Config.hostOne, Config.portOne);
//
//		new ByteEchoPeerTwo(messageSize, myAddress, peerAddress).run();
//	}
//}
