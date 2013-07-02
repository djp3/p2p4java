package net.jxta.impl.cm;

import java.io.IOException;

import org.junit.Ignore;

import edu.uci.ics.luci.p2p4java.impl.cm.AdvertisementCache;
import edu.uci.ics.luci.p2p4java.impl.cm.XIndiceAdvertisementCache;
import edu.uci.ics.luci.p2p4java.impl.util.threads.TaskManager;

@Ignore("Takes too long to run")
public class XIndiceCmConcurrencyTest extends AbstractCmConcurrencyTest {

    @Override
    protected AdvertisementCache createWrappedCache(String areaName, TaskManager taskManager) throws IOException {
        return new XIndiceAdvertisementCache(testFileStore.getRoot().toURI(), areaName, taskManager);
    }
}
