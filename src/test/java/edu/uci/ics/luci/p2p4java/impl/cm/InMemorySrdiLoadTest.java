package edu.uci.ics.luci.p2p4java.impl.cm;

import org.junit.Ignore;

import edu.uci.ics.luci.p2p4java.impl.cm.InMemorySrdi;

//Make sure your don;t use the default JUNIT VM args to run this test or you'll get OOM
//I use -XX:MaxPermSize=256m -Xms256m -Xmx512m
/* FIXME (2010/07/06 iainmcg): due to extremely poor memory and time performance on these tests,
* I've opted to disable this entire test suite for now. The in memory implementation needs
* further review to see what it is that is making it so slow for these tests.
*/
@Ignore
public class InMemorySrdiLoadTest extends
		AbstractSrdiIndexBackendLoadTest {

	@Override
	protected String getSrdiIndexBackendClassname() {
		return InMemorySrdi.class.getName();
	}

}
