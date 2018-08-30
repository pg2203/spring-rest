package com.impetus.rest.config;

import java.util.Collections;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.impetus.rest.common.AbstractSpringProperties;

@Component
public class ElasticSearchProperties extends AbstractSpringProperties{

	public ElasticSearchProperties() {
		super("elastic");
	}
	
	@PostConstruct
	public void init() {
		getClusterName();
		getMaxHeapThreshold();
	}
	
	@Autowired
	private ElasticSearchConfigurationProperties delegate;
	
	public interface Host {
		public String getName();
		public Integer getPort();
	}
	
	public interface IndexConfig {
		public String getIndexName();
		public String getIndexAlias();
	}
	
	public String getClusterName() {
		return getRequiredProperty("cluster-name");
	}
	
	public Integer getMaxHeapThreshold() {
		return getRequiredProperty("max-heap-threshold", Integer.class);
	}
	
	public List<Host> getHosts() {
		return Collections.unmodifiableList(delegate.getHosts());
	}

	public List<IndexConfig> getIndexConfig() {
		return Collections.unmodifiableList(delegate.getIndexConfig());
	}
}
