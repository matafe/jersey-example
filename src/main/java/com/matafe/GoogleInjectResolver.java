package com.matafe;

import java.lang.reflect.AnnotatedElement;

import javax.inject.Inject;
import javax.inject.Named;

import org.glassfish.hk2.api.Injectee;
import org.glassfish.hk2.api.InjectionResolver;
import org.glassfish.hk2.api.ServiceHandle;
import org.glassfish.hk2.api.TypeLiteral;
import org.glassfish.hk2.utilities.binding.AbstractBinder;

/**
 * Google Inject Resolver
 * 
 * @author ferram
 */
public class GoogleInjectResolver implements InjectionResolver<com.google.inject.Inject> {

	/**
	 * Google injection resolver binder.
	 */
	public static final class Binder extends AbstractBinder {

		@Override
		protected void configure() {
			bind(GoogleInjectResolver.class).to(new TypeLiteral<InjectionResolver<com.google.inject.Inject>>() {
			}).in(javax.inject.Singleton.class);
		}
	}

	@Inject
	@Named(InjectionResolver.SYSTEM_RESOLVER_NAME)
	private InjectionResolver<Inject> systemInjectionResolver;

	@Override
	public Object resolve(Injectee injectee, ServiceHandle<?> handle) {
		final AnnotatedElement parent = injectee.getParent();
		if (parent != null && parent.getAnnotation(com.google.inject.Inject.class) != null) {
			return systemInjectionResolver.resolve(injectee, handle);
		}
		return null;
	}

	@Override
	public boolean isConstructorParameterIndicator() {
		return false;
	}

	@Override
	public boolean isMethodParameterIndicator() {
		return false;
	}

	@Override
	public String toString() {
		return "GoogleInjectResolver(" + System.identityHashCode(this) + ")";
	}
}
