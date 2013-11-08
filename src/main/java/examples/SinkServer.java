package examples;
import java.io.File;
import java.net.URI;

import edu.uci.ics.luci.p2p4java.peergroup.PeerGroup;
import edu.uci.ics.luci.p2p4java.pipe.InputPipe;
import edu.uci.ics.luci.p2p4java.pipe.PipeService;
import edu.uci.ics.luci.p2p4java.platform.NetworkConfigurator;
import edu.uci.ics.luci.p2p4java.platform.NetworkManager;
import edu.uci.ics.luci.p2p4java.protocol.PipeAdvertisement;


public class SinkServer {
	
	public SinkServer() {
		try{
			File file = new File(".cache");
			File file2 = new File(file, "SinkServer");
			URI uri = file2.toURI();
		
			NetworkManager localManager = null;
			localManager = new NetworkManager(NetworkManager.ConfigMode.EDGE, "SinkServer",uri);
			NetworkConfigurator configurator = null;
			configurator = localManager.getConfigurator();
		
			URI TheSeed = URI.create(RelayRendezvousServer.SUPER_URI);
			configurator.addSeedRendezvous(TheSeed);
			configurator.addSeedRelay(TheSeed);
		
			localManager.startNetwork();
		
			PeerGroup netPeerGroup = null;
			PipeService pipeService = null;
			PipeAdvertisement pipeAdv = null;
			InputPipe inputPipe = null;
			// Get the NetPeerGroup
			netPeerGroup = localManager.getNetPeerGroup();
			// get the pipe service, and discovery
			pipeService = netPeerGroup.getPipeService();
			// create the pipe advertisement
			pipeAdv = SourceServer.getPipeAdvertisement();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		SinkServer sinkServer = new SinkServer();

	}

}
