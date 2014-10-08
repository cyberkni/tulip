//package net.demo.netty.udt.rendezvousBytes;
//
//import java.net.InetSocketAddress;
//import java.net.SocketAddress;
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
//public class ByteEchoPeerOne extends ByteEchoPeerBase0 {
//	private static final Logger log = Logger.getLogger(ByteEchoPeerOne.class.getName());
//
//	public ByteEchoPeerOne(int messageSize, SocketAddress myAddress, SocketAddress peerAddress) {
//		super(messageSize, myAddress, peerAddress);
//	}
//
//	public static void main(String[] args) throws Exception {
//		log.info("init");
//		final int messageSize = 64 * 1024;
//		final InetSocketAddress myAddress = new InetSocketAddress(Config.hostOne, Config.portOne);
//		final InetSocketAddress peerAddress = new InetSocketAddress(Config.hostTwo, Config.portTwo);
//		new ByteEchoPeerOne(messageSize, myAddress, peerAddress).run();
//	}
//}
