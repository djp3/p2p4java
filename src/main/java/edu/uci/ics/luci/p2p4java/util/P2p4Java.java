package edu.uci.ics.luci.p2p4java.util;

import java.util.ArrayList;
import java.util.List;

import edu.uci.ics.luci.p2p4java.content.ContentProviderSPI;
import edu.uci.ics.luci.p2p4java.impl.content.defprovider.DefaultContentProvider;

public class P2p4Java {

	public static List<String> getServices(String interfaceName) {
		List<String> ret = new ArrayList<String>();
		if (interfaceName.equals(edu.uci.ics.luci.p2p4java.content.ContentProviderSPI.class.getCanonicalName())){
			// The list of Content Provider Instance Classes included with this distribution.
			// Used by ContentServiceImpl
			ret.add(edu.uci.ics.luci.p2p4java.impl.content.defprovider.DefaultContentProvider.class.getCanonicalName());
			ret.add(edu.uci.ics.luci.p2p4java.impl.content.srdisocket.SRDISocketContentProvider.class.getCanonicalName());
		}
		else if (interfaceName.equals(edu.uci.ics.luci.p2p4java.document.Advertisement.class.getCanonicalName())){
			// The list of Advertisement Instance Classes included with this distribution.
			// Used by AdvertisementFactory
			ret.add(edu.uci.ics.luci.p2p4java.impl.protocol.PeerAdv.class.getCanonicalName());
			ret.add(edu.uci.ics.luci.p2p4java.impl.protocol.PlatformConfig.class.getCanonicalName());
			ret.add(edu.uci.ics.luci.p2p4java.impl.protocol.PeerGroupAdv.class.getCanonicalName());
			ret.add(edu.uci.ics.luci.p2p4java.impl.protocol.PeerGroupConfigAdv.class.getCanonicalName());
			ret.add(edu.uci.ics.luci.p2p4java.impl.protocol.TCPAdv.class.getCanonicalName());
			ret.add(edu.uci.ics.luci.p2p4java.impl.protocol.MulticastAdv.class.getCanonicalName());
			ret.add(edu.uci.ics.luci.p2p4java.impl.protocol.HTTPAdv.class.getCanonicalName());
			ret.add(edu.uci.ics.luci.p2p4java.impl.protocol.PSEConfigAdv.class.getCanonicalName());
			ret.add(edu.uci.ics.luci.p2p4java.impl.protocol.RdvConfigAdv.class.getCanonicalName());
			ret.add(edu.uci.ics.luci.p2p4java.impl.protocol.DiscoveryConfigAdv.class.getCanonicalName());
			ret.add(edu.uci.ics.luci.p2p4java.impl.protocol.PipeAdv.class.getCanonicalName());
			ret.add(edu.uci.ics.luci.p2p4java.impl.protocol.RelayConfigAdv.class.getCanonicalName());
			ret.add(edu.uci.ics.luci.p2p4java.impl.protocol.RdvAdv.class.getCanonicalName());
			ret.add(edu.uci.ics.luci.p2p4java.impl.protocol.ModuleImplAdv.class.getCanonicalName());
			ret.add(edu.uci.ics.luci.p2p4java.impl.protocol.ModuleSpecAdv.class.getCanonicalName());
			ret.add(edu.uci.ics.luci.p2p4java.impl.protocol.ModuleClassAdv.class.getCanonicalName());
			ret.add(edu.uci.ics.luci.p2p4java.impl.protocol.RouteAdv.class.getCanonicalName());
			ret.add(edu.uci.ics.luci.p2p4java.impl.protocol.AccessPointAdv.class.getCanonicalName());
			ret.add(edu.uci.ics.luci.p2p4java.impl.protocol.SignedAdv.class.getCanonicalName());
			ret.add(edu.uci.ics.luci.p2p4java.impl.protocol.GroupConfig.class.getCanonicalName());
			ret.add(edu.uci.ics.luci.p2p4java.impl.protocol.ContentAdvertisementImpl.class.getCanonicalName());
			ret.add(edu.uci.ics.luci.p2p4java.impl.protocol.ContentShareAdvertisementImpl.class.getCanonicalName());
			ret.add(edu.uci.ics.luci.p2p4java.impl.content.defprovider.DefaultContentShareAdvertisementImpl.class.getCanonicalName());
			ret.add(edu.uci.ics.luci.p2p4java.impl.content.srdisocket.SRDISocketContentShareAdvertisementImpl.class.getCanonicalName());
		}
		else if (interfaceName.equals(edu.uci.ics.luci.p2p4java.document.StructuredDocument.class.getCanonicalName())){
			// The list of StructuredDocument Instance Classes included in this distribution.
			// Used by StructuredDocumentFactory.
			ret.add(edu.uci.ics.luci.p2p4java.impl.document.PlainTextDocument.class.getCanonicalName());
			ret.add(edu.uci.ics.luci.p2p4java.impl.document.LiteXMLDocument.class.getCanonicalName());
		}
		else if
				(interfaceName.equals(edu.uci.ics.luci.p2p4java.endpoint.WireFormatMessage.class.getCanonicalName())){
			// The list of MessageWireFormat Instance Classes included in this distribution.
			// Used by MessageWireFormatFactory.
			ret.add(edu.uci.ics.luci.p2p4java.impl.endpoint.WireFormatMessageBinary.class.getCanonicalName());
		}
		else if
				(interfaceName.equals(edu.uci.ics.luci.p2p4java.id.ID.class.getCanonicalName())){
			// List of ID types supported.
			ret.add(edu.uci.ics.luci.p2p4java.impl.id.UUID.IDFormat.class.getCanonicalName());
			ret.add(edu.uci.ics.luci.p2p4java.impl.id.CBID.IDFormat.class.getCanonicalName());
			ret.add(edu.uci.ics.luci.p2p4java.impl.id.binaryID.IDFormat.class.getCanonicalName());
		}
		else if (interfaceName.equals(edu.uci.ics.luci.p2p4java.id.ID.class.getCanonicalName())){
			//The list of Builtin Module Implementation Classes included with this distribution.
			//Used by PeerGroup Implementation.

			ret.add("urn:jxta:uuid-deadbeefdeafbabafeedbabe000000020106 "+edu.uci.ics.luci.p2p4java.impl.resolver.ResolverServiceImpl.class.getCanonicalName()+" Reference Implementation of the Resolver service");
			ret.add("urn:jxta:uuid-deadbeefdeafbabafeedbabe000000030106 "+edu.uci.ics.luci.p2p4java.impl.discovery.DiscoveryServiceImpl.class.getCanonicalName()+" Reference Implementation of the Discovery service");
			ret.add("urn:jxta:uuid-deadbeefdeafbabafeedbabe000000040106 "+edu.uci.ics.luci.p2p4java.impl.pipe.PipeServiceImpl.class.getCanonicalName()+" Reference Implementation of the Pipe Service");
			//ret.add("urn:jxta:uuid-deadbeefdeafbabafeedbabe000000050106 "+edu.uci.ics.luci.p2p4java.impl.membership.none.NoneMembershipService.class.getCanonicalName()+" None Membership Service");
			//ret.add("urn:jxta:uuid-deadbeefdeafbabafeedbabe000000050206 "+edu.uci.ics.luci.p2p4java.impl.membership.passwd.PasswdMembershipService.class.getCanonicalName()+" Password Membership Service");
			ret.add("urn:jxta:uuid-deadbeefdeafbabafeedbabe000000050306 "+edu.uci.ics.luci.p2p4java.impl.membership.pse.PSEMembershipService.class.getCanonicalName()+" PSE Membership Service");
			ret.add("urn:jxta:uuid-deadbeefdeafbabafeedbabe000000060106 "+edu.uci.ics.luci.p2p4java.impl.rendezvous.RendezVousServiceImpl.class.getCanonicalName()+" Reference Implementation of the Rendezvous Service");
			ret.add("urn:jxta:uuid-deadbeefdeafbabafeedbabe000000070106 "+edu.uci.ics.luci.p2p4java.impl.peer.PeerInfoServiceImpl.class.getCanonicalName()+" Reference Implementation of the Peerinfo Service");
			ret.add("urn:jxta:uuid-deadbeefdeafbabafeedbabe000000080106 "+edu.uci.ics.luci.p2p4java.impl.endpoint.EndpointServiceImpl.class.getCanonicalName()+" Reference Implementation of the Endpoint service");
			ret.add("urn:jxta:uuid-deadbeefdeafbabafeedbabe000000090106 "+edu.uci.ics.luci.p2p4java.impl.endpoint.netty.NettyTransport.class.getCanonicalName()+" Reference Implementation of the TCP Message Transport");
			ret.add("urn:jxta:uuid-E549DB3BCBCF4789A392B6100B78CC55F127AD1AADF0443ABF6FBDFD7909876906 "+edu.uci.ics.luci.p2p4java.impl.endpoint.netty.http.NettyHttpTunnelTransport.class.getCanonicalName()+" HTTP tunneling message transport");
			ret.add("urn:jxta:uuid-deadbeefdeafbabafeedbabe0000000A0106 "+edu.uci.ics.luci.p2p4java.impl.endpoint.servlethttp.ServletHttpTransport.class.getCanonicalName()+" Reference Implementation of the HTTP Message Transport");
			ret.add("urn:jxta:uuid-deadbeefdeafbabafeedbabe0000000B0106 "+edu.uci.ics.luci.p2p4java.impl.endpoint.router.EndpointRouter.class.getCanonicalName()+" Reference Implementation of the Router Message Transport");
			ret.add("urn:jxta:uuid-deadbeefdeafbabafeedbabe0000000D0106 "+edu.uci.ics.luci.p2p4java.impl.endpoint.tls.TlsTransport.class.getCanonicalName()+" Reference Implementation of the TLS Message Transport");
			//ret.add("urn:jxta:uuid-deadbeefdeafbabafeedbabe0000000E0106 "+edu.uci.ics.luci.p2p4java.impl.proxy.ProxyService.class.getCanonicalName()+" Reference Implementation of the JXME Proxy Service");
			ret.add("urn:jxta:uuid-deadbeefdeafbabafeedbabe0000000F0106 "+edu.uci.ics.luci.p2p4java.impl.endpoint.relay.RelayTransport.class.getCanonicalName()+" Reference Implementation of the Relay Message Transport");
			ret.add("urn:jxta:uuid-deadbeefdeafbabafeedbabe000000100106 "+edu.uci.ics.luci.p2p4java.impl.access.always.AlwaysAccessService.class.getCanonicalName()+" Always Access Service");
			ret.add("urn:jxta:uuid-deadbeefdeafbabafeedbabe000000100206 "+edu.uci.ics.luci.p2p4java.impl.access.simpleACL.SimpleACLAccessService.class.getCanonicalName()+" Simple ACL Access Service");
			ret.add("urn:jxta:uuid-deadbeefdeafbabafeedbabe000000100306 "+edu.uci.ics.luci.p2p4java.impl.access.pse.PSEAccessService.class.getCanonicalName()+" PSE Access Service");
			//ret.add("urn:jxta:uuid-deadbeefdeafbabafeedbabe000000110106 "+edu.uci.ics.luci.p2p4java.impl.endpoint.cbjx.CbJxTransport.class.getCanonicalName()+" Reference Implementation of the Cryptobased-ID Message Transport");
			ret.add("urn:jxta:uuid-0C801F65D38F421C9884D706B337B8110106 "+edu.uci.ics.luci.p2p4java.impl.endpoint.mcast.McastTransport.class.getCanonicalName()+" Reference Implementation of the IP Multicast Message Transport");
			ret.add("urn:jxta:uuid-DDC5CA55578E4AB99A0AA81D2DC6EF3F3F7E9F18B5D84DD58D21CE9E37E19E6C06 "+edu.uci.ics.luci.p2p4java.impl.content.ContentServiceImpl.class.getCanonicalName()+" Reference Implementation of the Content Service");

			// Since the peer groups refer to other modules in their ModuleImplAdvertisements it's necessary to load them last.

			ret.add("urn:jxta:uuid-deadbeefdeafbabafeedbabe000000010106 "+edu.uci.ics.luci.p2p4java.impl.peergroup.Platform.class.getCanonicalName()+" Standard World PeerGroup Reference Implementation");
			ret.add("urn:jxta:uuid-deadbeefdeafbabafeedbabe000000010206 "+edu.uci.ics.luci.p2p4java.impl.peergroup.ShadowPeerGroup.class.getCanonicalName()+" Default Network PeerGroup Reference Implementation");
			ret.add("urn:jxta:uuid-deadbeefdeafbabafeedbabe000000010306 "+edu.uci.ics.luci.p2p4java.impl.peergroup.StdPeerGroup.class.getCanonicalName()+" General Purpose Peer Group Implementation");
		}
		return(ret);
	}

}
