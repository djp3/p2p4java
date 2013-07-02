package net.jxta.impl.cm;

import org.junit.Ignore;

import edu.uci.ics.luci.p2p4java.impl.cm.InMemorySrdi;
import edu.uci.ics.luci.p2p4java.impl.cm.SrdiAPI;
import edu.uci.ics.luci.p2p4java.peergroup.PeerGroup;

@Ignore("Should not override default constructor in junit")
public class InMemorySrdiConcurrencyTest extends AbstractSrdiIndexBackendConcurrencyTest {

	@Override
	protected SrdiAPI createBackend(PeerGroup group, String indexName) {
		return new InMemorySrdi(group, indexName);
	}

}
