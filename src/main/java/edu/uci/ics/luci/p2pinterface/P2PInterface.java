package edu.uci.ics.luci.p2pinterface;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.TreeMap;

import org.apache.log4j.Logger;

import edu.uci.ics.luci.p2p4java.document.AdvertisementFactory;
import edu.uci.ics.luci.p2p4java.document.MimeMediaType;
import edu.uci.ics.luci.p2p4java.endpoint.ByteArrayMessageElement;
import edu.uci.ics.luci.p2p4java.endpoint.Message;
import edu.uci.ics.luci.p2p4java.endpoint.Message.ElementIterator;
import edu.uci.ics.luci.p2p4java.endpoint.MessageElement;
import edu.uci.ics.luci.p2p4java.endpoint.WireFormatMessage;
import edu.uci.ics.luci.p2p4java.endpoint.WireFormatMessageFactory;
import edu.uci.ics.luci.p2p4java.id.IDFactory;
import edu.uci.ics.luci.p2p4java.pipe.InputPipe;
import edu.uci.ics.luci.p2p4java.pipe.OutputPipe;
import edu.uci.ics.luci.p2p4java.pipe.OutputPipeEvent;
import edu.uci.ics.luci.p2p4java.pipe.OutputPipeListener;
import edu.uci.ics.luci.p2p4java.pipe.PipeID;
import edu.uci.ics.luci.p2p4java.pipe.PipeMsgEvent;
import edu.uci.ics.luci.p2p4java.pipe.PipeMsgListener;
import edu.uci.ics.luci.p2p4java.pipe.PipeService;
import edu.uci.ics.luci.p2p4java.platform.NetworkConfigurator;
import edu.uci.ics.luci.p2p4java.platform.NetworkManager;
import edu.uci.ics.luci.p2p4java.protocol.PipeAdvertisement;
import edu.uci.ics.luci.p2p4java.util.CountingOutputStream;
import edu.uci.ics.luci.p2p4java.util.DevNullOutputStream;
import examples.RelayRendezvousServer;

public class P2PInterface implements PipeMsgListener{
	
	private static transient volatile Logger log = null;
	public static Logger getLog(){
		if(log == null){
			log = Logger.getLogger(P2PInterface.class);
		}
		return log;
	}
	
	private Map<String, OutputPipe> outputPipes;

	transient NetworkManager manager = null;
    private PipeService pipeService = null;
    private PipeAdvertisement pipeAdv = null;
    private InputPipe inputPipe = null;
    
    private P2PSink sink = null;
    
    void fetchBootstrapList(Set<URI> relay,Set<URI>rendezvous){
    	
    	if(relay == null){
    		relay = new HashSet<URI>();
    	}
    	if(rendezvous == null){
    		rendezvous = new HashSet<URI>();
    	}
    	URI r = URI.create(RelayRendezvousServer.SUPER_URI);
    	relay.add(r);
    	rendezvous.add(r);
    	
    }
    
	
	private String create_p2p_urn(String nodename)
			throws NoSuchAlgorithmException, UnsupportedEncodingException {
		MessageDigest md = MessageDigest.getInstance("SHA-256");
		
		md.update(nodename.getBytes("UTF-8"));
		byte [] mdbytes = md.digest();
		
		//convert the byte to hex format method 1
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < mdbytes.length; i++) {
          sb.append(Integer.toString((mdbytes[i] & 0xff) + 0x100, 16).substring(1));
        }
		String urn = "urn:jxta:uuid-"+sb.toString();
		return urn;
	}


	private NetworkManager setupLocalNetworkManager(String urn)
			throws IOException {
		File file = new File(".cache");
		File file2 = new File(file, urn);
		URI uri = file2.toURI();
	
		NetworkManager localManager = null;
		localManager = new NetworkManager(NetworkManager.ConfigMode.EDGE, urn,uri);
		return localManager;
	}


	private void discoverP2PNetwork(NetworkConfigurator configurator) {
		Set<URI> relay = null;
		Set<URI> rendezvous = null;
		relay = new HashSet<URI>();
		rendezvous = new HashSet<URI>();
		fetchBootstrapList(relay,rendezvous);
	
		URI theSeed;
		try{
			theSeed = rendezvous.iterator().next();
			configurator.addSeedRendezvous(rendezvous.iterator().next());
		}
		catch(NoSuchElementException e){
		}
		
		try{
			theSeed = relay.iterator().next();
			configurator.addSeedRelay(theSeed);
		}
		catch(NoSuchElementException e){
		}
	}


	private PipeAdvertisement setupPipeAdvertisement(String nodeNamespace, String urn) {
		PipeID pipeID = null;
	
		try {
			byte[] b = urn.getBytes("UTF-8");
			pipeID = IDFactory.newPipeID(manager.getNetPeerGroup().getPeerGroupID(), b);
			//pipeID = (PipeID) IDFactory.fromURI(new URI(urn));
		} catch (UnsupportedEncodingException e) {
			getLog().fatal(e);
		}
			
		PipeAdvertisement advertisement = (PipeAdvertisement)
				AdvertisementFactory.newAdvertisement(PipeAdvertisement.getAdvertisementType());
	
		advertisement.setPipeID(pipeID);
		advertisement.setType(PipeService.UnicastType);
		advertisement.setName(nodeNamespace);
		return(advertisement);
	}


	public P2PInterface(String nodename, P2PSink mr) {
		try{
			this.sink = mr;
			
			outputPipes = (Map<String, OutputPipe>) Collections.synchronizedMap(new TreeMap<String,OutputPipe>());
			
			String urn = create_p2p_urn(nodename);
			
			NetworkManager localNetworkManager = setupLocalNetworkManager(urn);
			
			NetworkConfigurator configurator = null;
			configurator = localNetworkManager.getConfigurator();
			
			discoverP2PNetwork(configurator);
			
			localNetworkManager.registerShutdownHook();
		
			localNetworkManager.startNetwork();
			
			manager = localNetworkManager;
			
			// get the pipe service, and discovery
			pipeService = localNetworkManager.getNetPeerGroup().getPipeService();
			
			pipeAdv = setupPipeAdvertisement(nodename, urn);
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	public boolean sendMessage(String destinationNodename,String message){
		Map<String, byte[]> m = new HashMap<String,byte[]>();
		try {
			m.put(null,message.getBytes("UTF-8"));
			return(sendMessage(destinationNodename,m));
		} catch (UnsupportedEncodingException e) {
			getLog().error("Unable to send message because your lame computer doesn't know about UTF-8:\n"+e);
			return(false);
		}
	}
	
	public boolean sendMessage(final String destinationNodename,final Map<String,byte []> message){
		final Object lock = new Object();
		OutputPipe outputPipe = outputPipes.get(destinationNodename);
		if(outputPipe == null){
			try{
				PipeAdvertisement destinationPipeAdv = null;
				try {
					destinationPipeAdv = setupPipeAdvertisement(destinationNodename, create_p2p_urn(destinationNodename));
				} catch (NoSuchAlgorithmException e1) {
					getLog().error(e1);
				} catch (UnsupportedEncodingException e1) {
					getLog().error(e1);
				}
				try{
					pipeService.createOutputPipe(destinationPipeAdv,new OutputPipeListener(){
						public void outputPipeEvent(OutputPipeEvent event) {
							synchronized(lock){
								OutputPipe localOutputPipe = event.getOutputPipe();
								outputPipes.put(destinationNodename, localOutputPipe);
								lock.notifyAll();
							}
						}
					});
				} catch (IOException e) {
					getLog().debug(e);
				}
			
				synchronized(lock){
					outputPipe= outputPipes.get(destinationNodename);
					while(outputPipe == null){
						try {
							lock.wait(1000);
						} catch (InterruptedException e) {
						}
						outputPipe = outputPipes.get(destinationNodename);
					}
				}
			}
			catch(IllegalArgumentException e){
				
			}
		}
			
		boolean result = false;
		if(outputPipe == null){
			getLog().error("Unable to send message because I can't make an output pipe");
		}
		else{
			Message msg = new Message();
			for(Entry<String, byte[]> e: message.entrySet()){
				ByteArrayMessageElement bme = new ByteArrayMessageElement(e.getKey(), null, e.getValue(),null);
				msg.addMessageElement(bme);
			}
			
			try {
				result = outputPipe.send(msg);
			} catch (IOException e) {
			}
		}
		return(result);
	}


	/**
     * Creates the input pipe with this as the message listener
     */
    public void start() {
    	
   		getLog().debug("Waiting for Rendezvous Connection");
   		manager.waitForRendezvousConnection(0);
   		getLog().debug("Connected to Rendezvous");

        try {
        	getLog().debug("Creating receiving pipe");
            // Create the InputPipe and register this for message arrival
            // notification call-back
            inputPipe = pipeService.createInputPipe(pipeAdv, this);
        } catch (IOException io) {
            getLog().warn(io.toString());
            return;
        }
        if (inputPipe == null) {
        	getLog().fatal("Can't open receiving pipe");
        	throw new RuntimeException("Can't open receiving pipe");
        }
        getLog().debug("Waiting for msgs on input pipe");
        
    }
    
    /**
     * Closes the output pipe and stops the platform
     */
    public void stop() {
    	
    	pipeAdv = null;
    	
        // Close the input pipe
    	if(inputPipe != null){
    		inputPipe.close();
    		inputPipe = null;
    	}
        
        synchronized(outputPipes){
        	Set<String> m = outputPipes.keySet();
        	for(String s:m){
        		OutputPipe op = outputPipes.remove(s);
        		if(op != null){
        			op.close();
        		}
        	}
        	outputPipes.clear();
        }
        
        // Stop JXTA
        manager.stopNetwork();
        
    }
    
    
    /**
     *
     * @param msg     the message
     * @param verbose dumps message element content if true
     * @return a set of namespaces and the String associated with it.
     */
    public static Map<String,byte[]> extractMessages(Message msg, boolean verbose) {
    	
    	Map<String, byte[]> ret = new HashMap<String,byte[]>();
    	
        try {
            CountingOutputStream cnt;
            ElementIterator it = msg.getMessageElements();

            getLog().debug("------------------Begin Message---------------------");
            WireFormatMessage serialed = WireFormatMessageFactory.toWire(msg, new MimeMediaType("application/x-jxta-msg"), null);

            getLog().debug("Message Size :" + serialed.getByteLength());
            while (it.hasNext()) {
                MessageElement el = it.next();
                
            	StringBuffer sb = new StringBuffer();
                String eName = el.getElementName();

                cnt = new CountingOutputStream(new DevNullOutputStream());
                el.sendToStream(cnt);
                long size = cnt.getBytesWritten();
                getLog().debug("Element " + eName + " : " + size);
                if (verbose) {
                    getLog().debug("[" + el + "]");
                }
                
                ret.put(eName,el.getBytes(true));
            }
            getLog().debug("-------------------End Message----------------------");
        } catch (Exception e) {
            e.printStackTrace();

        }
        return ret;
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
            	getLog().warn("Received an empty message");
                return;
            }
            // dump the message content to screen
            sink.incoming(extractMessages(msg, true));
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

    }


}
