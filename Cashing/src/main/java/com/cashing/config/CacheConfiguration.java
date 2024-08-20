package com.cashing.config;

import org.springframework.boot.autoconfigure.cache.CacheManagerCustomizer;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;

public class CacheConfiguration {

	@Bean
	CacheManagerCustomizer<ConcurrentMapCacheManager> customizer(){
		return new CacheManager();
	}
	private class CacheManager implements CacheManagerCustomizer<ConcurrentMapCacheManager>{

		@Override
		public void customize(ConcurrentMapCacheManager cacheManager) {
			cacheManager.setAllowNullValues(false);
			System.out.println("cache config Enabled.....");
		}
	}
}
