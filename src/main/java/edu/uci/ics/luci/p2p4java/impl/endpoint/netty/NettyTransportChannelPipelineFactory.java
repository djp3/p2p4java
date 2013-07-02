package edu.uci.ics.luci.p2p4java.impl.endpoint.netty;

import java.util.concurrent.atomic.AtomicBoolean;


import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.util.Timer;

import edu.uci.ics.luci.p2p4java.endpoint.EndpointAddress;
import edu.uci.ics.luci.p2p4java.peer.PeerID;
import edu.uci.ics.luci.p2p4java.peergroup.PeerGroup;

public class NettyTransportChannelPipelineFactory implements ChannelPipelineFactory {

	private PeerID localPeerId;
	private Timer timeoutTimer;
	private NettyChannelRegistry registry;
	private AtomicBoolean acceptConnectionFlag;
    private AddressTranslator addrTranslator;
    private EndpointAddress returnAddress;
    private EndpointAddress remoteAddress;

    private PeerGroup peerGroup;
	
	public NettyTransportChannelPipelineFactory(PeerGroup peerGroup, PeerID localPeerId, Timer timeoutTimer, NettyChannelRegistry registry, AddressTranslator addrTranslator, EndpointAddress remoteAddress, EndpointAddress returnAddress) {
		this(peerGroup, localPeerId, timeoutTimer, registry, addrTranslator, new AtomicBoolean(true), remoteAddress, returnAddress);
	}
	
	public NettyTransportChannelPipelineFactory(PeerGroup peerGroup, PeerID localPeerId, Timer timeoutTimer, NettyChannelRegistry registry, AddressTranslator addrTranslator, AtomicBoolean acceptConnectionFlag, EndpointAddress remoteAddress, EndpointAddress returnAddress) {
		this.peerGroup = peerGroup;
		this.localPeerId = localPeerId;
		this.timeoutTimer = timeoutTimer;
		this.registry = registry;
		this.acceptConnectionFlag = acceptConnectionFlag;
		this.addrTranslator = addrTranslator;
		this.remoteAddress = remoteAddress;
		this.returnAddress = returnAddress;
	}

	public ChannelPipeline getPipeline() throws Exception {
		ChannelPipeline pipeline = Channels.pipeline();
		pipeline.addFirst(ConnectionRejector.NAME, new ConnectionRejector(acceptConnectionFlag));
		pipeline.addLast(JxtaProtocolHandler.NAME, new JxtaProtocolHandler(addrTranslator, localPeerId, timeoutTimer, remoteAddress, returnAddress));
		pipeline.addLast(JxtaMessageEncoder.NAME, new JxtaMessageEncoder(peerGroup));
		pipeline.addLast(JxtaMessageDecoder.NAME, new JxtaMessageDecoder(peerGroup));
		pipeline.addLast(MessageDispatchHandler.NAME, new MessageDispatchHandler(registry));
		
		return pipeline;
	}
}
