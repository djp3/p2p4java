package edu.uci.ics.luci.p2p4java.impl.endpoint.netty;

import edu.uci.ics.luci.p2p4java.endpoint.Message;

/**
 * The interface which is used for dispatching the messages and connection related information
 * out of the Netty channel pipeline to the JXTA environment.
 * 
 * @author Iain McGinniss (iain.mcginniss@onedrum.com)
 */
public interface MessageArrivalListener {

    public void messageArrived(Message m);
    public void connectionDied();
    public void channelSaturated(boolean saturated);
    public void connectionDisposed();
    
}
