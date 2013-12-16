package edu.uci.ics.luci.p2pinterface;

import java.util.Map;

public interface P2PSink {
	
	void incoming(Map<String, byte[]> map);
}
