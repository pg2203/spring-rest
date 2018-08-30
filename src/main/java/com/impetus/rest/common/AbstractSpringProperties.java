package com.impetus.rest.common;

import org.apache.commons.lang3.Validate;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;

public abstract class AbstractSpringProperties implements EnvironmentAware {

	/**
	 * spring environment priving access to all environment properties including
	 * system env variables and application properties
	 */
	protected Environment springEvn;
	/**
	 * prefix under which all proerties for this instace are found
	 */
	private String prefix;

	public AbstractSpringProperties(String prefix) {
		Validate.notEmpty(prefix);
		this.prefix = prefix + ".";
	}

	@Override
	public void setEnvironment(Environment springEnv) {
		this.springEvn = springEnv;
	}

	protected String getProperty(String key) {
		return getProperty(key, String.class);
	}

	protected <T> T getProperty(String key, Class<T> targetType) {
		return targetType.cast(springEvn.getProperty(getQualifiedName(key), targetType));
	}

	protected String getRequiredProperty(String key) {
		return getRequiredProperty(key, String.class);
	}
	
	protected <T> T getRequiredProperty(String key, Class<T> targetType) {
		return targetType.cast(springEvn.getProperty(getQualifiedName(key), targetType));
	}

	private String getQualifiedName(String key) {
		return prefix + key;
	}
}
