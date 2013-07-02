package net.jxta.impl.cm.sql;

import java.io.IOException;

import net.jxta.impl.cm.AbstractCmConcurrencyTest;
import org.junit.Ignore;

import edu.uci.ics.luci.p2p4java.impl.cm.AdvertisementCache;
import edu.uci.ics.luci.p2p4java.impl.cm.sql.H2AdvertisementCache;
import edu.uci.ics.luci.p2p4java.impl.util.threads.TaskManager;

@Ignore("Takes way too long for unit test")
public class H2CmConcurrencyTest extends AbstractCmConcurrencyTest {
	
	@Override
    protected AdvertisementCache createWrappedCache(String areaName, TaskManager taskManager) throws IOException {
         return new H2AdvertisementCache(testFileStore.getRoot().toURI(), areaName, taskManager);
    }


}
