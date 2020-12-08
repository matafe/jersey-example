package com.matafe;

import javax.inject.Inject;

import org.glassfish.hk2.api.ServiceLocator;
import org.glassfish.jersey.server.ResourceConfig;
import org.jvnet.hk2.guice.bridge.api.GuiceBridge;
import org.jvnet.hk2.guice.bridge.api.GuiceIntoHK2Bridge;

import com.google.inject.Injector;
import com.matafe.person.PersonResource;

public class ApplicationConfig extends ResourceConfig {

	@Inject
	public ApplicationConfig(ServiceLocator serviceLocator) {
		// register(new GuiceContainerLifecycleListener());
		// property("jersey.config.server.provider.packages", "com.matafe");
		packages("com.matafe");

		register(new GoogleInjectResolver.Binder());

		initGuiceIntoHK2Bridge(serviceLocator, ClassUtils.getInjector());
		//register(PersonResource.class);

	}

	private void initGuiceIntoHK2Bridge(ServiceLocator serviceLocator, Injector injector) {
		GuiceBridge.getGuiceBridge().initializeGuiceBridge(serviceLocator);
		GuiceIntoHK2Bridge guiceBridge = serviceLocator.getService(GuiceIntoHK2Bridge.class);
		guiceBridge.bridgeGuiceInjector(injector);
	}

}
