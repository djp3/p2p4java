/**
 * 
 */
package edu.uci.ics.luci.p2p4java.util.luci;

import java.util.ListResourceBundle;

/**
 * This is a data structure that I made to port the resources that were included
 * in JXTA as property files.  Android doesn't allow property files in jars.
 * @author djp3
 *
 */
public class CompatibleListResourceBundle extends ListResourceBundle {

	private Object[][] data;

	public CompatibleListResourceBundle(Object[][] data) {
		this.data = data;
	}

	@Override
	protected Object[][] getContents() {
		return data;
	}

}
