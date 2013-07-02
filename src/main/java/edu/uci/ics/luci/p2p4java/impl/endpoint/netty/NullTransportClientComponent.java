package edu.uci.ics.luci.p2p4java.impl.endpoint.netty;

import edu.uci.ics.luci.p2p4java.endpoint.EndpointAddress;

/**
 * A "null object" implementation of the TransportClientComponent implementation, used when the client
 * end of a transport is disabled by the configuration.
 * 
 * @author Iain McGinniss (iain.mcginniss@onedrum.com)
 */
public class NullTransportClientComponent extends NullTransportComponent implements TransportClientComponent {

    public EndpointAddress getPublicAddress() {
        return null;
    }
}
