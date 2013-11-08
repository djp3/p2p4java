package examples;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.util.Date;

import edu.uci.ics.luci.p2p4java.document.MimeMediaType;
import edu.uci.ics.luci.p2p4java.endpoint.Message;
import edu.uci.ics.luci.p2p4java.endpoint.Message.ElementIterator;
import edu.uci.ics.luci.p2p4java.endpoint.MessageElement;
import edu.uci.ics.luci.p2p4java.endpoint.WireFormatMessage;
import edu.uci.ics.luci.p2p4java.endpoint.WireFormatMessageFactory;
import edu.uci.ics.luci.p2p4java.peergroup.PeerGroup;
import edu.uci.ics.luci.p2p4java.pipe.InputPipe;
import edu.uci.ics.luci.p2p4java.pipe.PipeMsgEvent;
import edu.uci.ics.luci.p2p4java.pipe.PipeMsgListener;
import edu.uci.ics.luci.p2p4java.pipe.PipeService;
import edu.uci.ics.luci.p2p4java.platform.NetworkConfigurator;
import edu.uci.ics.luci.p2p4java.platform.NetworkManager;
import edu.uci.ics.luci.p2p4java.protocol.PipeAdvertisement;
import edu.uci.ics.luci.p2p4java.util.CountingOutputStream;
import edu.uci.ics.luci.p2p4java.util.DevNullOutputStream;


public class SinkServer implements PipeMsgListener{
	
	transient NetworkManager manager = null;
    private PipeService pipeService = null;
    private PipeAdvertisement pipeAdv = null;
    private InputPipe inputPipe = null;
	
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
			
			manager = localManager;
			
			PeerGroup netPeerGroup = null;
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
	
	
	 /**
     * Creates the input pipe with this as the message listener
     */
    public void start() {

        try {
            System.out.println("Creating input pipe");
            // Create the InputPipe and register this for message arrival
            // notification call-back
            inputPipe = pipeService.createInputPipe(pipeAdv, this);
        } catch (IOException io) {
            io.printStackTrace();
            return;
        }
        if (inputPipe == null) {
            System.out.println(" cannot open InputPipe");
            System.exit(-1);
        }
        System.out.println("Waiting for msgs on input pipe");
    }
    
    /**
     * Closes the output pipe and stops the platform
     */
    public void stop() {
        // Close the input pipe
        inputPipe.close();
        // Stop JXTA
        manager.stopNetwork();
    }
    
    /**
     * Dumps the message content to stdout
     *
     * @param msg     the message
     * @param verbose dumps message element content if true
     */
    public static void printMessageStats(Message msg, boolean verbose) {
        try {
            CountingOutputStream cnt;
            ElementIterator it = msg.getMessageElements();

            System.out.println("------------------Begin Message---------------------");
            WireFormatMessage serialed = WireFormatMessageFactory.toWire(msg, new MimeMediaType("application/x-jxta-msg"), null);

            System.out.println("Message Size :" + serialed.getByteLength());
            while (it.hasNext()) {
                MessageElement el = it.next();
                String eName = el.getElementName();

                cnt = new CountingOutputStream(new DevNullOutputStream());
                el.sendToStream(cnt);
                long size = cnt.getBytesWritten();

                System.out.println("Element " + eName + " : " + size);
                if (verbose) {
                    System.out.println("[" + el + "]");
                }
            }
            System.out.println("-------------------End Message----------------------");
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    
    
    /**
     * PipeMsgListener interface for asynchronous message arrival notification
     *
     * @param event the message event
     */
    public void pipeMsgEvent(PipeMsgEvent event) {

        Message msg;
        try {
            // Obtain the message from the event
            msg = event.getMessage();
            if (msg == null) {
                System.out.println("Received an empty message");
                return;
            }
            // dump the message content to screen
            printMessageStats(msg, true);
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

        // get all the message elements
        Message.ElementIterator en = msg.getMessageElements();

        if (!en.hasNext()) {
            return;
        }

        // get the message element in the name space PipeClient.MESSAGE_NAME_SPACE
        MessageElement msgElement = msg.getMessageElement(null, SourceServer.MESSAGE_NAME_SPACE);

        // Get message
        if (msgElement.toString() == null) {
            System.out.println("null msg received");
        } else {
            Date date = new Date(System.currentTimeMillis());
            System.out.println("Message received at :" + date.toString());
            System.out.println("Message  created at :" + msgElement.toString());
        }
    }

    
    
	
	public static void main(String[] args) {
		SinkServer sinkServer = new SinkServer();
		sinkServer.start();

	}

}
