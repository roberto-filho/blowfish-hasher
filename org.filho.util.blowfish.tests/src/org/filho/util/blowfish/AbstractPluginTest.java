package org.filho.util.blowfish;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

public class AbstractPluginTest {
	
	protected BundleContext getPlugin() {
		return Activator.getContext();
	}
	
	protected <T> T getService(Class<T> c) {
		ServiceReference<T> ref = getPlugin().getServiceReference(c);
		return getPlugin().getService(ref);
	}
	
}
