package com.tlemceni;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.data.envers.repository.support.EnversRevisionRepositoryFactoryBean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.hateoas.client.LinkDiscoverer;
import org.springframework.hateoas.client.LinkDiscoverers;
import org.springframework.hateoas.mediatype.collectionjson.CollectionJsonLinkDiscoverer;
import org.springframework.plugin.core.SimplePluginRegistry;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import net.sf.ehcache.config.CacheConfiguration;
import net.sf.ehcache.config.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@EnableJpaRepositories(repositoryFactoryBeanClass = EnversRevisionRepositoryFactoryBean.class)
@EnableTransactionManagement
@EnableCaching
public class CarApplication extends SpringBootServletInitializer{

	public static void main(String[] args) {
		SpringApplication.run(CarApplication.class, args);
	}
	
	@Bean
	 public Docket productApi() {
	    return new Docket(DocumentationType.SWAGGER_2).select()
	          .apis(RequestHandlerSelectors.basePackage("com.tlemceni")).build();
	 }
	
	@Bean
    public LinkDiscoverers discoverers() {
        List<LinkDiscoverer> plugins = new ArrayList<>();
        plugins.add(new CollectionJsonLinkDiscoverer());
        return new LinkDiscoverers(SimplePluginRegistry.create(plugins));

    }
	
//	@Bean
//	public 	net.sf.ehcache.CacheManager ehCacheManager(){
//		CacheConfiguration tenSecondCache = new CacheConfiguration();
//		tenSecondCache.setName("ten-second-cache");
//		tenSecondCache.setMemoryStoreEvictionPolicy("LRU");
//		tenSecondCache.setMaxEntriesLocalHeap(1000);
//		tenSecondCache.internalSetTimeToLive(10);
//		
//		Configuration config = new Configuration();
//		config.addCache(tenSecondCache);
//		
//		
//		return net.sf.ehcache.CacheManager.newInstance(config);
//	}
//	
//	@Bean
//	@Override
//	public CacheManager cacheManager() {
//		return new EhCacheCacheManager(ehCacheManager());
//	}
//	
	
	

}
