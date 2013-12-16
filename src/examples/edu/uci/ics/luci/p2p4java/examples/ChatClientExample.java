package examples;

import java.awt.BorderLayout;
import processing.core.*;

import java.awt.Dimension;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.UnsupportedEncodingException;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.JFrame;
import javax.swing.JLabel;

import controlP5.ControlFont;
import controlP5.ControlP5;
import controlP5.Textlabel;

import edu.uci.ics.luci.p2pinterface.P2PInterface;
import edu.uci.ics.luci.p2pinterface.P2PSink;

public class ChatClientExample extends PApplet implements P2PSink {
	
	static Object lock = new Object();
	static boolean messageReceived = false;
	
	/** Graphics stuff **/
	ControlP5 cp5;

	PFont pfont;
	
	public void setup() {
		size(800,200,P2D);
		background(0);
		smooth();
		String[] list = PFont.list();
		
		//pfont = loadFont("/Users/djp3/Development/Mac/EclipseWorkspaceCacophony/p2p4java/lib/processing/Helvetica-48.vlw");
		pfont = loadFont("data/Helvetica-48.vlw");
		
		cp5 = new ControlP5(this);

	}

	public void draw() {
		smooth();
		textFont(pfont);
		text("word", 101, 101);
		if (mousePressed) {
			line(mouseX,mouseY,pmouseX,pmouseY);
		}
	}

	/**
	 *  This function decodes the incoming messages based on knowing the conventions of the sender.
	 *  Namely that a "null" message element type means the element is UTF-8 encoded bytes and
	 *  there are no other payloads.
	 * @return
	 */
	private String incomingHelper(Map<String, byte []> map){
		
		for(Entry<String, byte[]> e :map.entrySet()){
			String key = e.getKey();
			if((key == null) || (key.equals(""))){
				try {
					return new String(e.getValue(),"UTF-8");
				} catch (UnsupportedEncodingException e1) {
					e1.printStackTrace();
				}
			}
		}
		return null;
	}
	
	public void incoming(Map<String, byte[]> map) {
		String incomingMessage = incomingHelper(map);
		System.out.println(incomingMessage);
		
		synchronized(lock){
			messageReceived = true;
			lock.notifyAll();
		}
	}
	
	/*private static void launchWindows(final P2PInterface p2p){
		JFrame frame = new JFrame("p2p4java chat example");

		frame.addWindowListener(new WindowListener(){
			public void windowActivated(WindowEvent arg0) {
			}

			public void windowClosed(WindowEvent arg0) {
				//p2p.stop();
			}

			public void windowClosing(WindowEvent arg0) {
			}

			public void windowDeactivated(WindowEvent arg0) {
			}

			public void windowDeiconified(WindowEvent arg0) {
			}

			public void windowIconified(WindowEvent arg0) {
			}

			public void windowOpened(WindowEvent arg0) {
				//p2p.start();
			}});
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JLabel emptyLabel = new JLabel("");
        emptyLabel.setPreferredSize(new Dimension(175, 100));
        frame.getContentPane().add(emptyLabel, BorderLayout.CENTER);
        
        Label label1 = new Label("Name:");
        TextField textField = new TextField ();
        HBox hb = new HBox();
        hb.getChildren().addAll(label1, textField);
        hb.setSpacing(10);
 
        //Display the window.
        frame.pack();
        frame.setVisible(true);
	}*/

	public static void main(String[] args) {
		String nodeName = "SinkServerSimple";
		
		ChatClientExample sss = new ChatClientExample();
		
		P2PInterface p2p = null;
		//P2PInterface p2p = new P2PInterface(nodeName,sss);
		
		//launchWindows(p2p);
	}

	

}
