package edu.uci.ics.luci.p2p4java.examples;

import java.io.UnsupportedEncodingException;
import java.util.Map;
import java.util.Map.Entry;

import edu.uci.ics.luci.p2pinterface.P2PInterface;
import edu.uci.ics.luci.p2pinterface.P2PSink;

public class SinkServerSimple implements P2PSink{
	
	static Object lock = new Object();
	static boolean messageReceived = false;

	/**
	 *  This function decodes the incoming messages based on knowing the conventions of the sender.
	 *  Namely that a "null" message element type means the element is UTF-8 encoded bytes and
	 *  there are no other payloads.
	 * @return
	 */
	private String incomingHelper(Map<String, byte []> map){
		
		for(Entry<String, byte[]> e :map.entrySet()){
			String key = e.getKey();
			if((key == null) || (key.equals(""))){
				try {
					return new String(e.getValue(),"UTF-8");
				} catch (UnsupportedEncodingException e1) {
					e1.printStackTrace();
				}
			}
		}
		return null;
	}
	
	public void incoming(Map<String, byte[]> map) {
		String incomingMessage = incomingHelper(map);
		System.out.println(incomingMessage);
		
		synchronized(lock){
			messageReceived = true;
			lock.notifyAll();
		}
	}

	public static void main(String[] args) {
		String nodeName = "SinkServerSimple";
		
		SinkServerSimple sss = new SinkServerSimple();
		
		P2PInterface p2p = new P2PInterface(nodeName,sss);
		p2p.start();
		
		synchronized(lock){
			while(!messageReceived){
				try {
					lock.wait();
				} catch (InterruptedException e) {
				}
			}
		}
		
		p2p.stop();
	}

	

}
