/**
 * 
 */
package com.cache.gemfire;

import org.springframework.data.gemfire.support.GemfireCacheManager;

import com.cache.AbstractCacheManager;
import com.cache.Cache;
import com.gemstone.gemfire.cache.Region;

/**
 * @author NAYAKDUR
 *
 */
public class GemfireCacheManagerImpl extends AbstractCacheManager{
	
	private GemfireCacheManager gemfireCacheManager;
	
	public void setGemfireCacheManager(GemfireCacheManager gemfireCacheManager) {
		this.gemfireCacheManager = gemfireCacheManager;
	}

	public <K, V> Cache<K, V> createCache(String cacheName)
			throws IllegalArgumentException {
		GemfireCache<K, V> gemfireCache = new GemfireCache<K, V>();
		Region<?,?> region = ((org.springframework.data.gemfire.support.GemfireCache)gemfireCacheManager.getCache(cacheName)).getNativeCache();
		gemfireCache.setRegion(region);
		this.addCache(gemfireCache);
		return gemfireCache;
	}

	public <K, V> Cache<K, V> loadCache(String cacheName)
			throws IllegalArgumentException {
		return null;
	}

}
