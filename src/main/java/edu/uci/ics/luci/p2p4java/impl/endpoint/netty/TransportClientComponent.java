package edu.uci.ics.luci.p2p4java.impl.endpoint.netty;

import edu.uci.ics.luci.p2p4java.endpoint.EndpointAddress;

public interface TransportClientComponent extends TransportComponent {

    /**
     * @return the public address for the client, i.e. the address the client will
     * declare as it's address when connected to remote peers.
     */
    public EndpointAddress getPublicAddress();
    
}
