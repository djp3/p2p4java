package edu.uci.ics.luci.p2p4java.util;

import java.util.ArrayList;
import java.util.List;

public class P2p4Android {

	public static List<String> getServices(String interfaceName) {
		List<String> ret = new ArrayList<String>();
		if (interfaceName.equals("edu.uci.ics.luci.p2p4android.content.ContentProviderSPI")){
			// The list of Content Provider Instance Classes included with this distribution.
			// Used by ContentServiceImpl
			ret.add("edu.uci.ics.luci.p2p4android.impl.content.defprovider.DefaultContentProvider");
			ret.add("edu.uci.ics.luci.p2p4android.impl.content.srdisocket.SRDISocketContentProvider");
		}
		else if (interfaceName.equals("edu.uci.ics.luci.p2p4android.document.Advertisement")){
			// The list of Advertisement Instance Classes included with this distribution.
			// Used by AdvertisementFactory
			ret.add("edu.uci.ics.luci.p2p4android.impl.protocol.PeerAdv");
			ret.add("edu.uci.ics.luci.p2p4android.impl.protocol.PlatformConfig");
			ret.add("edu.uci.ics.luci.p2p4android.impl.protocol.PeerGroupAdv");
			ret.add("edu.uci.ics.luci.p2p4android.impl.protocol.PeerGroupConfigAdv");
			ret.add("edu.uci.ics.luci.p2p4android.impl.protocol.TCPAdv");
			ret.add("edu.uci.ics.luci.p2p4android.impl.protocol.MulticastAdv");
			ret.add("edu.uci.ics.luci.p2p4android.impl.protocol.HTTPAdv");
			ret.add("edu.uci.ics.luci.p2p4android.impl.protocol.PSEConfigAdv");
			ret.add("edu.uci.ics.luci.p2p4android.impl.protocol.RdvConfigAdv");
			ret.add("edu.uci.ics.luci.p2p4android.impl.protocol.DiscoveryConfigAdv");
			ret.add("edu.uci.ics.luci.p2p4android.impl.protocol.PipeAdv");
			ret.add("edu.uci.ics.luci.p2p4android.impl.protocol.RelayConfigAdv");
			ret.add("edu.uci.ics.luci.p2p4android.impl.protocol.RdvAdv");
			ret.add("edu.uci.ics.luci.p2p4android.impl.protocol.ModuleImplAdv");
			ret.add("edu.uci.ics.luci.p2p4android.impl.protocol.ModuleSpecAdv");
			ret.add("edu.uci.ics.luci.p2p4android.impl.protocol.ModuleClassAdv");
			ret.add("edu.uci.ics.luci.p2p4android.impl.protocol.RouteAdv");
			ret.add("edu.uci.ics.luci.p2p4android.impl.protocol.AccessPointAdv");
			ret.add("edu.uci.ics.luci.p2p4android.impl.protocol.SignedAdv");
			ret.add("edu.uci.ics.luci.p2p4android.impl.protocol.GroupConfig");
			ret.add("edu.uci.ics.luci.p2p4android.impl.protocol.ContentAdvertisementImpl");
			ret.add("edu.uci.ics.luci.p2p4android.impl.protocol.ContentShareAdvertisementImpl");
			ret.add("edu.uci.ics.luci.p2p4android.impl.content.defprovider.DefaultContentShareAdvertisementImpl");
			ret.add("edu.uci.ics.luci.p2p4android.impl.content.srdisocket.SRDISocketContentShareAdvertisementImpl");
		}
		else if (interfaceName.equals("edu.uci.ics.luci.p2p4android.document.StructuredDocument")){
			// The list of StructuredDocument Instance Classes included in this distribution.
			// Used by StructuredDocumentFactory.
			ret.add("edu.uci.ics.luci.p2p4android.impl.document.PlainTextDocument");
			ret.add("edu.uci.ics.luci.p2p4android.impl.document.LiteXMLDocument");
		}
		else if (interfaceName.equals("edu.uci.ics.luci.p2p4android.endpoint.WireFormatMessage")){
			// The list of MessageWireFormat Instance Classes included in this distribution.
			// Used by MessageWireFormatFactory.
			ret.add("edu.uci.ics.luci.p2p4android.impl.endpoint.WireFormatMessageBinary");
		}
		else if (interfaceName.equals("edu.uci.ics.luci.p2p4android.id.ID")){
			// List of ID types supported.
			ret.add("edu.uci.ics.luci.p2p4android.impl.id.UUID.IDFormat");
			ret.add("edu.uci.ics.luci.p2p4android.impl.id.CBID.IDFormat");
			ret.add("edu.uci.ics.luci.p2p4android.impl.id.binaryID.IDFormat");
		}
		else if (interfaceName.equals("edu.uci.ics.luci.p2p4android.id.ID")){
			//The list of Builtin Module Implementation Classes included with this distribution.
			//Used by PeerGroup Implementation.

			ret.add("urn:jxta:uuid-deadbeefdeafbabafeedbabe000000020106 edu.uci.ics.luci.p2p4android.impl.resolver.ResolverServiceImpl Reference Implementation of the Resolver service");
			ret.add("urn:jxta:uuid-deadbeefdeafbabafeedbabe000000030106 edu.uci.ics.luci.p2p4android.impl.discovery.DiscoveryServiceImpl Reference Implementation of the Discovery service");
			ret.add("urn:jxta:uuid-deadbeefdeafbabafeedbabe000000040106 edu.uci.ics.luci.p2p4android.impl.pipe.PipeServiceImpl Reference Implementation of the Pipe Service");
			//urn:jxta:uuid-deadbeefdeafbabafeedbabe000000050106 edu.uci.ics.luci.p2p4android.impl.membership.none.NoneMembershipService None Membership Service
			//urn:jxta:uuid-deadbeefdeafbabafeedbabe000000050206 edu.uci.ics.luci.p2p4android.impl.membership.passwd.PasswdMembershipService Password Membership Service.
			ret.add("urn:jxta:uuid-deadbeefdeafbabafeedbabe000000050306 edu.uci.ics.luci.p2p4android.impl.membership.pse.PSEMembershipService PSE Membership Service");
			ret.add("urn:jxta:uuid-deadbeefdeafbabafeedbabe000000060106 edu.uci.ics.luci.p2p4android.impl.rendezvous.RendezVousServiceImpl Reference Implementation of the Rendezvous Service");
			ret.add("urn:jxta:uuid-deadbeefdeafbabafeedbabe000000070106 edu.uci.ics.luci.p2p4android.impl.peer.PeerInfoServiceImpl Reference Implementation of the Peerinfo Service");
			ret.add("urn:jxta:uuid-deadbeefdeafbabafeedbabe000000080106 edu.uci.ics.luci.p2p4android.impl.endpoint.EndpointServiceImpl Reference Implementation of the Endpoint service");
			ret.add("urn:jxta:uuid-deadbeefdeafbabafeedbabe000000090106 edu.uci.ics.luci.p2p4android.impl.endpoint.netty.NettyTransport Reference Implementation of the TCP Message Transport");
			ret.add("urn:jxta:uuid-E549DB3BCBCF4789A392B6100B78CC55F127AD1AADF0443ABF6FBDFD7909876906 edu.uci.ics.luci.p2p4android.impl.endpoint.netty.http.NettyHttpTunnelTransport HTTP tunneling message transport");
			ret.add("urn:jxta:uuid-deadbeefdeafbabafeedbabe0000000A0106 edu.uci.ics.luci.p2p4android.impl.endpoint.servlethttp.ServletHttpTransport Reference Implementation of the HTTP Message Transport");
			ret.add("urn:jxta:uuid-deadbeefdeafbabafeedbabe0000000B0106 edu.uci.ics.luci.p2p4android.impl.endpoint.router.EndpointRouter Reference Implementation of the Router Message Transport");
			ret.add("urn:jxta:uuid-deadbeefdeafbabafeedbabe0000000D0106 edu.uci.ics.luci.p2p4android.impl.endpoint.tls.TlsTransport Reference Implementation of the TLS Message Transport");
			ret.add("urn:jxta:uuid-deadbeefdeafbabafeedbabe0000000E0106 edu.uci.ics.luci.p2p4android.impl.proxy.ProxyService Reference Implementation of the JXME Proxy Service");
			ret.add("urn:jxta:uuid-deadbeefdeafbabafeedbabe0000000F0106 edu.uci.ics.luci.p2p4android.impl.endpoint.relay.RelayTransport Reference Implementation of the Relay Message Transport");
			ret.add("urn:jxta:uuid-deadbeefdeafbabafeedbabe000000100106 edu.uci.ics.luci.p2p4android.impl.access.always.AlwaysAccessService Always Access Service");
			ret.add("urn:jxta:uuid-deadbeefdeafbabafeedbabe000000100206 edu.uci.ics.luci.p2p4android.impl.access.simpleACL.SimpleACLAccessService Simple ACL Access Service");
			ret.add("urn:jxta:uuid-deadbeefdeafbabafeedbabe000000100306 edu.uci.ics.luci.p2p4android.impl.access.pse.PSEAccessService PSE Access Service");
			//urn:jxta:uuid-deadbeefdeafbabafeedbabe000000110106 edu.uci.ics.luci.p2p4android.impl.endpoint.cbjx.CbJxTransport Reference Implementation of the Cryptobased-ID Message Transport
			ret.add("urn:jxta:uuid-0C801F65D38F421C9884D706B337B8110106 edu.uci.ics.luci.p2p4android.impl.endpoint.mcast.McastTransport Reference Implementation of the IP Multicast Message Transport");
			ret.add("urn:jxta:uuid-DDC5CA55578E4AB99A0AA81D2DC6EF3F3F7E9F18B5D84DD58D21CE9E37E19E6C06 edu.uci.ics.luci.p2p4android.impl.content.ContentServiceImpl Reference Implementation of the Content Service");

			// Since the peer groups refer to other modules in their ModuleImplAdvertisements it's necessary to load them last.

			ret.add("urn:jxta:uuid-deadbeefdeafbabafeedbabe000000010106 edu.uci.ics.luci.p2p4android.impl.peergroup.Platform Standard World PeerGroup Reference Implementation");
			ret.add("urn:jxta:uuid-deadbeefdeafbabafeedbabe000000010206 edu.uci.ics.luci.p2p4android.impl.peergroup.ShadowPeerGroup Default Network PeerGroup Reference Implementation");
			ret.add("urn:jxta:uuid-deadbeefdeafbabafeedbabe000000010306 edu.uci.ics.luci.p2p4android.impl.peergroup.StdPeerGroup General Purpose Peer Group Implementation");
		}
		return(ret);
	}

}
