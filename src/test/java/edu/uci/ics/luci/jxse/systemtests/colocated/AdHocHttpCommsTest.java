package edu.uci.ics.luci.jxse.systemtests.colocated;


import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import edu.uci.ics.luci.jxse.systemtests.colocated.configs.PeerConfigurator;
import edu.uci.ics.luci.p2p4java.platform.NetworkManager;

/**
 * Tests ad-hoc mode communication using the HTTP (legacy) transport. This test exists within
 * {@link AdHocCommsTest} however due to peer isolation issues it must currently
 * be run separately - placing it in it's own class ensures it is run in a forked
 * VM.
 */
public class AdHocHttpCommsTest {

	@Rule
	public TemporaryFolder tempStorage = new TemporaryFolder();
	
	private NetworkManager aliceManager;
	private NetworkManager bobManager;
	
	@Before
	public void createPeers() throws Exception {
		aliceManager = PeerConfigurator.createHttpAdhocPeer("alice", 58000, tempStorage);
		bobManager = PeerConfigurator.createHttpAdhocPeer("bob", 58001, tempStorage);
		
		aliceManager.startNetwork();
		bobManager.startNetwork();
		
		// XXX: give the network managers time to stabilise
		Thread.sleep(5000L);
	}
	
	@Test
	public void testComms() throws Exception {
		SystemTestUtils.testPeerCommunication(aliceManager, bobManager);
	}
	
	@After
	public void killAlice() throws Exception {
		aliceManager.stopNetwork();
	}
	
	@After
	public void killBob() throws Exception {
		bobManager.stopNetwork();
	}
	
}
