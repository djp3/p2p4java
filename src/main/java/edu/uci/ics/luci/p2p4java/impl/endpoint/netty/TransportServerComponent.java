package edu.uci.ics.luci.p2p4java.impl.endpoint.netty;

import java.util.Iterator;
import java.util.List;

import edu.uci.ics.luci.p2p4java.endpoint.EndpointAddress;


public interface TransportServerComponent extends TransportComponent {

    /**
     * @return the public addresses for this component - those which will be included in the
     * peer advertisement.
     */
    public Iterator<EndpointAddress> getPublicAddresses();

    /**
     * @return the physically bound addresses for this component, as opposed to those which are
     * broadcasted to external peers.
     */
    public List<EndpointAddress> getBoundAddresses();

}
