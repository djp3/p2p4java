
package edu.uci.ics.luci.p2p4java.impl.endpoint.cbjx;

import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import edu.uci.ics.luci.p2p4java.endpoint.EndpointAddress;
import edu.uci.ics.luci.p2p4java.endpoint.Message;
import edu.uci.ics.luci.p2p4java.endpoint.MessageFilterListener;
import edu.uci.ics.luci.p2p4java.endpoint.WireFormatMessageFactory;
import edu.uci.ics.luci.p2p4java.impl.endpoint.EndpointServiceImpl;

/**
 *
 */
public class CbjxFilter implements MessageFilterListener
{
	
    public Message filterMessage(Message paramMsg, EndpointAddress paramSrcAddr, EndpointAddress paramDstAddr)
    {
        if(WireFormatMessageFactory.CBJX_DISABLE)
        {
            return paramMsg;
        }
        boolean tempLoopback = (Boolean)paramMsg.getMessageProperty(EndpointServiceImpl.MESSAGE_LOOPBACK);
        if(tempLoopback)
        {
            return paramMsg;
        }

        Set<EndpointAddress> tempSetEA = (Set)paramMsg.getMessageProperty(EndpointServiceImpl.VERIFIED_ADDRESS_SET);
        if(tempSetEA.contains(paramSrcAddr))
        {
            return paramMsg;
        }
        else
        {
            Logger.getLogger(CbjxFilter.class.getName()).log(Level.SEVERE, "Address spoofing: wrong address="+paramSrcAddr);
            Logger.getLogger(CbjxFilter.class.getName()).log(Level.SEVERE, "                  verified set ="+tempSetEA);
            return null;
        }
    }

}
