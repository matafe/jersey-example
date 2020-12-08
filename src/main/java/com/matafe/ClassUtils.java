package com.matafe;

import com.google.inject.Guice;
import com.google.inject.Injector;

/**
 * Class Utils - Simple Helper for DI Injection
 * 
 * @author ferrazm
 */
public final class ClassUtils {

	private static Injector injector = Guice.createInjector(new WebInjectBindingModule());

	private ClassUtils() {
	}

	public static <T> T createInstance(Class<T> type) {
		return injector.getInstance(type);
	}

	public static Injector getInjector() {
		return injector;
	}

}
