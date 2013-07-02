package net.jxta.impl.endpoint.netty;

import edu.uci.ics.luci.p2p4java.endpoint.MessengerEvent;
import edu.uci.ics.luci.p2p4java.endpoint.MessengerEventListener;

public class FakeMessengerEventListener implements MessengerEventListener {

    public FakeEndpointService owner;
    
    public FakeMessengerEventListener(FakeEndpointService owner) {
        this.owner = owner;
    }

    public boolean messengerReady(MessengerEvent event) {
        owner.messengers.add(event.getMessenger());
        return true;
    }
}
