package edu.uci.ics.luci.p2p4java.util.luci;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class P2P4Java {
	
	private static String runtime = System.getProperty("java.runtime.name");
	
	public static boolean isAndroid(){
		return runtime.equals("Android Runtime");
	}

	private static Object context = null;
	
	public static void setContext(Object _context){
		if(!isAndroid()){
			throw new RuntimeException("There is no need to set Context if you aren't running on Android");
		}
		else{
			context = _context;
		}
	}

	public static File getCacheDirectory() {
		if(isAndroid()){
			if(context == null){
				throw new RuntimeException("Initialize Android Context before calling getCacheDirectory");
			}
			else{
				try{
					Method method = context.getClass().getMethod("getCacheDir", (Class<?>[]) null);
					if(method != null){
						return (File) method.invoke(context,(Object []) null);
					}
					else{
						throw new RuntimeException("Problem with Android Context, getCacheDir method not present");
					}
				} catch (NoSuchMethodException e) {
					throw new RuntimeException("Problem with Android Context "+e);
				} catch (IllegalArgumentException e) {
					throw new RuntimeException("Problem with Android Context "+e);
				} catch (IllegalAccessException e) {
					throw new RuntimeException("Problem with Android Context "+e);
				} catch (InvocationTargetException e) {
					throw new RuntimeException("Problem with Android Context "+e);
				}
			}
		}
		else{
            return new File(".cache");
		}
	}

	

	public static List<String> getResources(String interfaceName) throws IOException {
		List<String> ret = new ArrayList<String>();
		if (interfaceName.equals("META-INF/services/" + edu.uci.ics.luci.p2p4java.content.ContentProviderSPI.class.getCanonicalName())){
			// The list of Content Provider Instance Classes included with this distribution.
			// Used by ContentServiceImpl
			ret.add(edu.uci.ics.luci.p2p4java.impl.content.defprovider.DefaultContentProvider.class.getCanonicalName());
			ret.add(edu.uci.ics.luci.p2p4java.impl.content.srdisocket.SRDISocketContentProvider.class.getCanonicalName());
		}
		else if (interfaceName.equals("META-INF/services/" + edu.uci.ics.luci.p2p4java.document.Advertisement.class.getCanonicalName())){
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
		else if (interfaceName.equals("META-INF/services/" + edu.uci.ics.luci.p2p4java.document.StructuredDocument.class.getCanonicalName())){
			// The list of StructuredDocument Instance Classes included in this distribution.
			// Used by StructuredDocumentFactory.
			ret.add(edu.uci.ics.luci.p2p4java.impl.document.PlainTextDocument.class.getCanonicalName());
			ret.add(edu.uci.ics.luci.p2p4java.impl.document.LiteXMLDocument.class.getCanonicalName());
		}
		else if (interfaceName.equals("META-INF/services/" + edu.uci.ics.luci.p2p4java.endpoint.WireFormatMessage.class.getCanonicalName())){
			// The list of MessageWireFormat Instance Classes included in this distribution.
			// Used by MessageWireFormatFactory.
			ret.add(edu.uci.ics.luci.p2p4java.impl.endpoint.WireFormatMessageBinary.class.getCanonicalName());
		}
		else if (interfaceName.equals("META-INF/services/" + edu.uci.ics.luci.p2p4java.id.ID.class.getCanonicalName())){
			// List of ID types supported.
			ret.add(edu.uci.ics.luci.p2p4java.impl.id.UUID.IDFormat.class.getCanonicalName());
			ret.add(edu.uci.ics.luci.p2p4java.impl.id.CBID.IDFormat.class.getCanonicalName());
			ret.add(edu.uci.ics.luci.p2p4java.impl.id.binaryID.IDFormat.class.getCanonicalName());
		}
		else if (interfaceName.equals("META-INF/services/" + edu.uci.ics.luci.p2p4java.platform.Module.class.getCanonicalName())){
			//The list of Builtin Module Implementation Classes included with this distribution.
			//Used by PeerGroup Implementation.

			ret.add("urn:jxta:uuid-DEADBEEFDEAFBABAFEEDBABE000000020106 "+edu.uci.ics.luci.p2p4java.impl.resolver.ResolverServiceImpl.class.getCanonicalName()+" Reference Implementation of the Resolver service");
			ret.add("urn:jxta:uuid-DEADBEEFDEAFBABAFEEDBABE000000030106 "+edu.uci.ics.luci.p2p4java.impl.discovery.DiscoveryServiceImpl.class.getCanonicalName()+" Reference Implementation of the Discovery service");
			ret.add("urn:jxta:uuid-DEADBEEFDEAFBABAFEEDBABE000000040106 "+edu.uci.ics.luci.p2p4java.impl.pipe.PipeServiceImpl.class.getCanonicalName()+" Reference Implementation of the Pipe Service");
			//ret.add("urn:jxta:uuid-DEADBEEFDEAFBABAFEEDBABE000000050106 "+edu.uci.ics.luci.p2p4java.impl.membership.none.NoneMembershipService.class.getCanonicalName()+" None Membership Service");
			//ret.add("urn:jxta:uuid-DEADBEEFDEAFBABAFEEDBABE000000050206 "+edu.uci.ics.luci.p2p4java.impl.membership.passwd.PasswdMembershipService.class.getCanonicalName()+" Password Membership Service");
			ret.add("urn:jxta:uuid-DEADBEEFDEAFBABAFEEDBABE000000050306 "+edu.uci.ics.luci.p2p4java.impl.membership.pse.PSEMembershipService.class.getCanonicalName()+" PSE Membership Service");
			ret.add("urn:jxta:uuid-DEADBEEFDEAFBABAFEEDBABE000000060106 "+edu.uci.ics.luci.p2p4java.impl.rendezvous.RendezVousServiceImpl.class.getCanonicalName()+" Reference Implementation of the Rendezvous Service");
			ret.add("urn:jxta:uuid-DEADBEEFDEAFBABAFEEDBABE000000070106 "+edu.uci.ics.luci.p2p4java.impl.peer.PeerInfoServiceImpl.class.getCanonicalName()+" Reference Implementation of the Peerinfo Service");
			ret.add("urn:jxta:uuid-DEADBEEFDEAFBABAFEEDBABE000000080106 "+edu.uci.ics.luci.p2p4java.impl.endpoint.EndpointServiceImpl.class.getCanonicalName()+" Reference Implementation of the Endpoint service");
			ret.add("urn:jxta:uuid-DEADBEEFDEAFBABAFEEDBABE000000090106 "+edu.uci.ics.luci.p2p4java.impl.endpoint.netty.NettyTransport.class.getCanonicalName()+" Reference Implementation of the TCP Message Transport");
			ret.add("urn:jxta:uuid-E549DB3BCBCF4789A392B6100B78CC55F127AD1AADF0443ABF6FBDFD7909876906 "+edu.uci.ics.luci.p2p4java.impl.endpoint.netty.http.NettyHttpTunnelTransport.class.getCanonicalName()+" HTTP tunneling message transport");
			ret.add("urn:jxta:uuid-DEADBEEFDEAFBABAFEEDBABE0000000A0106 "+edu.uci.ics.luci.p2p4java.impl.endpoint.servlethttp.ServletHttpTransport.class.getCanonicalName()+" Reference Implementation of the HTTP Message Transport");
			ret.add("urn:jxta:uuid-DEADBEEFDEAFBABAFEEDBABE0000000B0106 "+edu.uci.ics.luci.p2p4java.impl.endpoint.router.EndpointRouter.class.getCanonicalName()+" Reference Implementation of the Router Message Transport");
			ret.add("urn:jxta:uuid-DEADBEEFDEAFBABAFEEDBABE0000000D0106 "+edu.uci.ics.luci.p2p4java.impl.endpoint.tls.TlsTransport.class.getCanonicalName()+" Reference Implementation of the TLS Message Transport");
			//ret.add("urn:jxta:uuid-DEADBEEFDEAFBABAFEEDBABE0000000E0106 "+edu.uci.ics.luci.p2p4java.impl.proxy.ProxyService.class.getCanonicalName()+" Reference Implementation of the JXME Proxy Service");
			ret.add("urn:jxta:uuid-DEADBEEFDEAFBABAFEEDBABE0000000F0106 "+edu.uci.ics.luci.p2p4java.impl.endpoint.relay.RelayTransport.class.getCanonicalName()+" Reference Implementation of the Relay Message Transport");
			ret.add("urn:jxta:uuid-DEADBEEFDEAFBABAFEEDBABE000000100106 "+edu.uci.ics.luci.p2p4java.impl.access.always.AlwaysAccessService.class.getCanonicalName()+" Always Access Service");
			ret.add("urn:jxta:uuid-DEADBEEFDEAFBABAFEEDBABE000000100206 "+edu.uci.ics.luci.p2p4java.impl.access.simpleACL.SimpleACLAccessService.class.getCanonicalName()+" Simple ACL Access Service");
			ret.add("urn:jxta:uuid-DEADBEEFDEAFBABAFEEDBABE000000100306 "+edu.uci.ics.luci.p2p4java.impl.access.pse.PSEAccessService.class.getCanonicalName()+" PSE Access Service");
			//ret.add("urn:jxta:uuid-DEADBEEFDEAFBABAFEEDBABE000000110106 "+edu.uci.ics.luci.p2p4java.impl.endpoint.cbjx.CbJxTransport.class.getCanonicalName()+" Reference Implementation of the Cryptobased-ID Message Transport");
			ret.add("urn:jxta:uuid-0C801F65D38F421C9884D706B337B8110106 "+edu.uci.ics.luci.p2p4java.impl.endpoint.mcast.McastTransport.class.getCanonicalName()+" Reference Implementation of the IP Multicast Message Transport");
			ret.add("urn:jxta:uuid-DDC5CA55578E4AB99A0AA81D2DC6EF3F3F7E9F18B5D84DD58D21CE9E37E19E6C06 "+edu.uci.ics.luci.p2p4java.impl.content.ContentServiceImpl.class.getCanonicalName()+" Reference Implementation of the Content Service");

			// Since the peer groups refer to other modules in their ModuleImplAdvertisements it's necessary to load them last.

			ret.add("urn:jxta:uuid-DEADBEEFDEAFBABAFEEDBABE000000010106 "+edu.uci.ics.luci.p2p4java.impl.peergroup.Platform.class.getCanonicalName()+" Standard World PeerGroup Reference Implementation");
			ret.add("urn:jxta:uuid-DEADBEEFDEAFBABAFEEDBABE000000010206 "+edu.uci.ics.luci.p2p4java.impl.peergroup.ShadowPeerGroup.class.getCanonicalName()+" Default Network PeerGroup Reference Implementation");
			ret.add("urn:jxta:uuid-DEADBEEFDEAFBABAFEEDBABE000000010306 "+edu.uci.ics.luci.p2p4java.impl.peergroup.StdPeerGroup.class.getCanonicalName()+" General Purpose Peer Group Implementation");
		}
		return(ret);
	}
	
	/**
	 *
	 * JXTA Distribution Configuration Properties File port
	 * 
	 * This object contains properties which configure several basic defaults for JXTA distributions.
	 *  Normally these values do not need to be changed by applications. 
	 *  (Most of the values have remained been unchanged since they were originally defined).
	 */
	static final Object[][] edu_uci_ics_luci_p2p4java_impl_config = new Object[][]{
			// ID of the net peer group as a URI
			{"NetPeerGroupID","jxta-NetGroup"},
			// Name of the Net Peer Group
			{"NetPeerGroupName","NetPeerGroup"},
			// Description of the Net Peer Group
			{"NetPeerGroupDesc","default Net Peer Group"},
			// Default type of ID to use when creating an ID
			{"IDNewInstances","cbid"},
	};
	
	/**
	 *
	 * JXTA Distribution User Properties File port
	 * 
	 */
	static final Object[][] edu_uci_ics_luci_p2p4java_user = new Object[][]{
			// Amount of time in minutes after which a connection is considered idle and may 
			// be scavenged expressed in minutes
			{"impl.endpoint.tls.connection.idletimeout","5"},

			// Amount of time in minutes a connection must be idle before a reconnection 
			// attempt will be considered expressed in minutes
			{"impl.endpoint.tls.connection.minidlereconnect","1"},

			// Amount if time in minutes which retries may remain queued for retrasmission.
			// expressed in minutes
			{"impl.endpoint.tls.connection.maxretryage","2"},

			// Cache Manager Deffered checkpoint
			// a setting of true deffers commit checkpoint to the Filer
			// this setting increases performance, with a caveat of possible
			// data corruption in unexpected process termination due to kernel
			// crash, or power loss
			{"impl.cm.defferedcheckpoint","true"},

			//Discovery provides the ability to disable discovery network traffic
			//when defined, it renders discovery a local cache
			{"impl.discovery.localonly","false"},

			//Metering code configuration
			{"edu.uci.ics.luci.p2p4java.meter.conditionalTransportMetering","OFF"},
			{"edu.uci.ics.luci.p2p4java.meter.conditionalMetering","OFF"},
			{"edu.uci.ics.luci.p2p4java.meter.conditionalEndpointMetering","OFF"},
			{"edu.uci.ics.luci.p2p4java.meter.conditionalResolverMetering","OFF"},
			{"edu.uci.ics.luci.p2p4java.meter.conditionalRendezvousMetering","OFF"},
	};
	
	public static CompatibleListResourceBundle getBundle(String resource){
		if(resource.equals("edu.uci.ics.luci.p2p4java.impl.config")){
			return new CompatibleListResourceBundle(edu_uci_ics_luci_p2p4java_impl_config);
		}
		else if(resource.equals("edu.uci.ics.luci.p2p4java.user")){
			return new CompatibleListResourceBundle(edu_uci_ics_luci_p2p4java_user);
		}
		else{
			System.err.println("Undealt with resource bundle porting: "+resource);
			System.exit(1);
		}
		return null;
	}

}
