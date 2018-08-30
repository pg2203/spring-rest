package com.impetus.rest.config;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.Validate;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "elastic")
public class ElasticSearchConfigurationProperties {

	/**
	 * 2-tuple for representing host/port
	 */
	public static class Host implements ElasticSearchProperties.Host {
		private String name;
		private Integer port;

		@Override
		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		@Override
		public Integer getPort() {
			return port;
		}

		public void setPort(Integer port) {
			this.port = port;
		}

		@Override
		public boolean equals(Object h) {
			return EqualsBuilder.reflectionEquals(this, h);
		}

		@Override
		public int hashCode() {
			return HashCodeBuilder.reflectionHashCode(this);
		}

	}

	private List<Host> hosts;

	public List<Host> getHosts() {
		return hosts;
	}

	public void setHosts(List<Host> hosts) {
		this.hosts = hosts;
	}

	public static class IndexConfig implements ElasticSearchProperties.IndexConfig {
		private String indexName;
		private String indexAlias;

		@Override
		public String getIndexName() {
			return indexName;
		}

		public void setIndexName(String indexName) {
			this.indexName = indexName;
		}

		@Override
		public String getIndexAlias() {
			return indexAlias;
		}

		public void setIndexAlias(String indexAlias) {
			this.indexAlias = indexAlias;
		}
		
		@Override
		public boolean equals(Object h) {
			return EqualsBuilder.reflectionEquals(this, h);
		}

		@Override
		public int hashCode() {
			return HashCodeBuilder.reflectionHashCode(this);
		}

	}
	
	private List<IndexConfig> indexConfig;
	
	public List<IndexConfig> getIndexConfig() {
		return indexConfig;
	}
	
	public void setIndexConfig(List<IndexConfig> indexConfig) {
		this.indexConfig = indexConfig;
	}

	@PostConstruct
	public void init() {
		hosts = hosts.stream().distinct().collect(Collectors.toList());
		checkForDuplicates(indexConfig, "indexName", IndexConfig::getIndexName);
		checkForDuplicates(indexConfig, "indexAlias", IndexConfig::getIndexAlias);
	}
	
	private <T> void checkForDuplicates(List<T> elements, String fieldName, Function<T, ?> fieldGetter) {
		Validate.notEmpty(elements);
		if(elements.size() != elements.stream().map(fieldGetter).distinct().count()) {
			throw new IllegalArgumentException(""
					+ "found more than one element of type["+ elements.get(0).getClass().getSimpleName()
					+"] sharing the same value for property ["
					+fieldName+"]; must be unique");
		}
	}
}
