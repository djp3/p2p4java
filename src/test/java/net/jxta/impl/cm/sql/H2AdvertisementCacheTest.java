package net.jxta.impl.cm.sql;

import edu.uci.ics.luci.p2p4java.impl.cm.AdvertisementCache;
import edu.uci.ics.luci.p2p4java.impl.cm.sql.H2AdvertisementCache;
import net.jxta.impl.cm.AbstractCmTest;

public class H2AdvertisementCacheTest extends AbstractCmTest {

	@Override
	public AdvertisementCache createWrappedCache(String areaName) throws Exception {
		return new H2AdvertisementCache(testRootDir.toURI(), areaName, taskManager);
	}

	@Override
	public String getCacheClassName() {
		return H2AdvertisementCache.class.getName();
	}
}
