package examples;
import java.io.File;
import java.io.IOException;

import edu.uci.ics.luci.p2p4java.exception.PeerGroupException;
import edu.uci.ics.luci.p2p4java.platform.NetworkConfigurator;
import edu.uci.ics.luci.p2p4java.platform.NetworkManager;

public class RelayRendezvousServer {

	//public final static String SUPER_URI = "tcp://128.195.59.254:9701";
	public final static String SUPER_URI = "tcp://173.255.208.77:9701";

    transient NetworkManager manager = null;

    public RelayRendezvousServer() {
        try {
            manager = new NetworkManager(NetworkManager.ConfigMode.SUPER, "RelayServer",
                    new File(new File(".cache"), "RelayServer").toURI());
            
            NetworkConfigurator configurator = manager.getConfigurator();
            manager.startNetwork();
            
            String tcpInterfaceAddress = configurator.getTcpInterfaceAddress();
            int tcpPort = configurator.getTcpPort();
            String tcpPublicAddress = configurator.getTcpPublicAddress();
            System.out.println("TCP\n\t"+tcpInterfaceAddress+"\n\t"+tcpPort+"\n\t"+tcpPublicAddress);
        } catch (IOException e){
            e.printStackTrace();
            stop();
        } catch (RuntimeException e){
            e.printStackTrace();
            stop();
        } catch (PeerGroupException e) {
            e.printStackTrace();
            stop();
        }
    }

    public static void main(String args[]) {
    	/* Test that we are using UTF-8 as default */
		String c = java.nio.charset.Charset.defaultCharset().name();
		if(!c.equals("UTF-8")){
			throw new IllegalArgumentException("The character set is not UTF-8:"+c);
		}
		
        RelayRendezvousServer server = new RelayRendezvousServer();
        server.start();
    }

    public void start() {
    	System.out.println("Creating relay server");
    }

    public void stop() {
        manager.stopNetwork();
        manager = null;
    }
}


