package edu.uci.ics.luci.p2p4java.impl.cm.sql;

import org.junit.Ignore;

import edu.uci.ics.luci.p2p4java.impl.cm.AbstractCmTest;
import edu.uci.ics.luci.p2p4java.impl.cm.AdvertisementCache;
import edu.uci.ics.luci.p2p4java.impl.cm.sql.DerbyAdvertisementCache;

@Ignore("Very long test: 10 min")
public class DerbyAdvertisementCacheTest extends AbstractCmTest {

	@Override
	public AdvertisementCache createWrappedCache(String areaName) throws Exception {
		return new DerbyAdvertisementCache(testRootDir.toURI(), areaName, taskManager);
	}

	@Override
	public String getCacheClassName() {
		return DerbyAdvertisementCache.class.getName();
	}
}
