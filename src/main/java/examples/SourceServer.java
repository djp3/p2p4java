package examples;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Date;

import edu.uci.ics.luci.p2p4java.document.AdvertisementFactory;
import edu.uci.ics.luci.p2p4java.endpoint.Message;
import edu.uci.ics.luci.p2p4java.endpoint.StringMessageElement;
import edu.uci.ics.luci.p2p4java.exception.PeerGroupException;
import edu.uci.ics.luci.p2p4java.id.IDFactory;
import edu.uci.ics.luci.p2p4java.pipe.OutputPipe;
import edu.uci.ics.luci.p2p4java.pipe.OutputPipeEvent;
import edu.uci.ics.luci.p2p4java.pipe.OutputPipeListener;
import edu.uci.ics.luci.p2p4java.pipe.PipeID;
import edu.uci.ics.luci.p2p4java.pipe.PipeService;
import edu.uci.ics.luci.p2p4java.platform.NetworkConfigurator;
import edu.uci.ics.luci.p2p4java.platform.NetworkManager;
import edu.uci.ics.luci.p2p4java.protocol.PipeAdvertisement;


/**
 * This tutorial illustrates the use of JXTA Pipes to exchange messages.
 * <p/>
 * This peer is the pipe "client". It opens the pipe for output and when it
 * resolves (finds a listening peer) it sends a message to the "server".
 */
public class SourceServer implements OutputPipeListener {

    /**
     * The tutorial message name space
     */
    public final static String MESSAGE_NAME_SPACE = "SourceServer";
    private boolean waitForRendezvous = false;
    private PipeService pipeService;
    private PipeAdvertisement pipeAdv;
    private OutputPipe outputPipe;
    private final Object lock = new Object();

    /**
     * Network is JXTA platform wrapper used to configure, start, and stop the
     * the JXTA platform
     */
    private NetworkManager manager;
;

    /**
     * A pre-baked PipeID string
     */
    public final static String PIPEIDSTR = "urn:jxta:uuid-59616261646162614E50472050325033C0C1DE89719B456691A596B983BA0E1004";

    /**
     * Create this instance and starts the JXTA platform
     *
     * @param waitForRendezvous indicates whether to wait for a rendezvous connection
     */
    public SourceServer(boolean waitForRendezvous) {
    	
        this.waitForRendezvous = waitForRendezvous;
        
        try {
        	synchronized(lock){
        		manager = new NetworkManager(NetworkManager.ConfigMode.EDGE, "SourceServer",(new File(new File(".cache"), "SourceServer")).toURI());
        		
        		NetworkConfigurator configurator = manager.getConfigurator();
        		
        		URI TheSeed = URI.create(RelayRendezvousServer.SUPER_URI);
                configurator.addSeedRendezvous(TheSeed);
                configurator.addSeedRelay(TheSeed);
                
                manager.registerShutdownHook();
        		manager.startNetwork();
            
        		// get the pipe service, and discovery
        		pipeService = manager.getNetPeerGroup().getPipeService();
        		// create the pipe advertisement
        		pipeAdv = getPipeAdvertisement();
        	}
            
        } catch (RuntimeException e){
            e.printStackTrace();
            stop();
        } catch(IOException e){
            e.printStackTrace();
            stop();
        } catch(PeerGroupException e) {
            e.printStackTrace();
            stop();
        }
    }

   

    /**
     * Creates the pipe advertisement
     * pipe ID
     *
     * @return the pre-defined Pipe Advertisement
     */
    public static PipeAdvertisement getPipeAdvertisement() {
        PipeID pipeID = null;

        try {
            pipeID = (PipeID) IDFactory.fromURI(new URI(PIPEIDSTR));
        } catch (URISyntaxException use) {
            use.printStackTrace();
        }
        PipeAdvertisement advertisement = (PipeAdvertisement)
                AdvertisementFactory.newAdvertisement(PipeAdvertisement.getAdvertisementType());

        advertisement.setPipeID(pipeID);
        advertisement.setType(PipeService.UnicastType);
        advertisement.setName("Pipe tutorial");
        return advertisement;
    }

    /**
     * the thread which creates (resolves) the output pipe
     * and sends a message once it's resolved
     */
    public void start() {
    	
        try {
            if (waitForRendezvous) {
                System.out.println("Waiting for Rendezvous Connection");
                synchronized(lock){
                	// wait indefinitely until connected to a rendezvous
                	manager.waitForRendezvousConnection(0);
                	System.out.println("Connected to Rendezvous, attempting to create a OutputPipe");
                }
            }
            // issue a pipe resolution asynchronously. outputPipeEvent() is called
            // once the pipe has resolved
            pipeService.createOutputPipe(pipeAdv, this);
            try {
            	while(manager != null){
            		synchronized (lock) {
            			if(manager != null){
            				lock.wait();
            			}
            		}
            	}
            } catch (InterruptedException e) {
            	System.out.println("Thread interrupted");
            }
        } catch (IOException e) {
            System.out.println("OutputPipe creation failure");
            e.printStackTrace();
            stop();
        }
    }

    /**
     * by implementing OutputPipeListener we must define this method which
     * is called when the output pipe is created
     *
     * @param event event object from which to get output pipe object
     */
    public void outputPipeEvent(OutputPipeEvent event) {

        System.out.println("Received the output pipe resolution event");
        // get the output pipe object
        outputPipe = event.getOutputPipe();
        Thread t = new Thread(new Runnable(){
			public void run() {
				sendMessages();
			}
        });
        
        t.start();

    }



	private void sendMessages() {
		for(int count = 0; count < 25 ; count ++){
        	Message msg;

        	try {
        		// create the message
        		msg = new Message();
        		Date date = new Date(System.currentTimeMillis());
        		// add a string message element with the current date
        		String data = date.toString();
        		StringMessageElement sme = new StringMessageElement(MESSAGE_NAME_SPACE,"Hello World, This is message #"+count+", time:"+data , null);

        		msg.addMessageElement(null, sme);
        		// send the message
        		boolean result = false;
        		while (!result){
        			System.out.println("Sending message "+count);
        			result = outputPipe.send(msg);
        			if(result){
        				System.out.println("message sent");
        			}
        			else{
        				System.out.println("message not sent... retrying");
        				try {
        					Thread.sleep(1000);
        				} catch (InterruptedException e) {
        				}
        			}
        		}
        		try {
        			Thread.sleep(1000);
        		} catch (InterruptedException e) {
        		}
        	} catch (IOException e) {
        		System.out.println("failed to send message");
        		e.printStackTrace();
        	}
        }
        
        stop();
	}

    /**
     * Closes the output pipe and stops the platform
     */
    public void stop() {
   		synchronized (lock) {
   			if(manager != null){
    			manager.stopNetwork();
    			manager = null;
    		}
    	}
   		synchronized (lock) {
            lock.notifyAll();
        }
   		//System.exit(-1);
    }
    
    public static void main(String args[]) {
    	
		String c = java.nio.charset.Charset.defaultCharset().name();
		if(!c.equals("UTF-8")){
			throw new IllegalArgumentException("The character set is not UTF-8:"+c);
		}
		
        String value = System.getProperty("RDVWAIT", "true");
        boolean waitForRendezvous = Boolean.valueOf(value);
        SourceServer source = new SourceServer(waitForRendezvous);

        source.start();
       	while(source.manager != null){
       		synchronized(source.lock){
        		try {
        			if(source.manager != null){
        				source.lock.wait();
        			}
        		} catch (InterruptedException e) {
        		}
        	}
        }
        System.exit(-1);
    }
}
