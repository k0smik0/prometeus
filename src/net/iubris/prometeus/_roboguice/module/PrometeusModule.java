/*
 * Massimiliano Leone - k0smik0@logorroici.org
 * 
 * LGPL license
 */
package net.iubris.prometeus._roboguice.module;
/**
 * Module for (robo)guice
 * It must to be included in /res/values/roboguice.xml
 * with full package path: net.iubris.prometeus._roboguice.module.PrometeusModule
 */
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import com.google.inject.AbstractModule;

public class PrometeusModule extends AbstractModule {

	@Override
	protected void configure() {		
		bind(Serializer.class).to(Persister.class);
	}
}
