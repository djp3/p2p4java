package net.jxse.systemtests.colocated;

import edu.uci.ics.luci.p2p4java.util.JxtaBiDiPipe;

/**
 * Simple class to store the two ends of an established pipe.
 */
public class PipeEnds {

	public JxtaBiDiPipe acceptedEnd;
	public JxtaBiDiPipe clientEnd;
	
	public PipeEnds(JxtaBiDiPipe acceptedEnd, JxtaBiDiPipe clientEnd) {
		this.acceptedEnd = acceptedEnd;
		this.clientEnd = clientEnd;
	}
	
}
