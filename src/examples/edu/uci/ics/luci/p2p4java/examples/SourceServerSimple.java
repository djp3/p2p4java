package edu.uci.ics.luci.p2p4java.examples;

import java.util.Map;

import edu.uci.ics.luci.p2pinterface.P2PInterface;
import edu.uci.ics.luci.p2pinterface.P2PSink;

public class SourceServerSimple implements P2PSink{
	
	public void incoming(Map<String, byte[]> map) {
		throw new RuntimeException("I don't expect to get any messages");
	};
	

	public static void main(String[] args) {
		String nodename = "SourceServerSimple";
		
		SourceServerSimple sss = new SourceServerSimple();
		
		P2PInterface p2p = new P2PInterface(nodename,sss);
		p2p.start();
		
		p2p.sendMessage("SinkServerSimple", "Hello World");
		
		p2p.stop();
	}
}
