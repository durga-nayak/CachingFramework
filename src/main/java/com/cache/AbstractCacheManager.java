/**
 * 
 */
package com.cache;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * @author NAYAKDUR
 *
 */
public abstract class AbstractCacheManager implements CacheManager {
	
	private final ConcurrentMap<String, Cache> cacheMap = new ConcurrentHashMap<String, Cache>();
	private Set<String> cacheNames = new LinkedHashSet<String>();
	
	
	/**
	 * 
	 */
	public AbstractCacheManager() {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see com.cache.CacheManager#createCache(java.lang.String)
	 */
	public <K, V> Cache<K, V> addCache(Cache<K, V> cache)
			throws IllegalArgumentException {
		cacheNames.add(cache.getName());
		return cacheMap.put(cache.getName(), cache);
	}

	/* (non-Javadoc)
	 * @see com.cache.CacheManager#getCache(java.lang.String, java.lang.Class, java.lang.Class)
	 */
	public <K, V> Cache<K, V> getCache(String cacheName, Class<K> keyType,
			Class<V> valueType) {
		return null;
	}

	/* (non-Javadoc)
	 * @see com.cache.CacheManager#getCache(java.lang.String)
	 */
	public <K, V> Cache<K, V> getCache(String cacheName) {
		// TODO Auto-generated method stub
		Cache<K, V> cache = cacheMap.get(cacheName);
		return cache;
	}

	/* (non-Javadoc)
	 * @see com.cache.CacheManager#getCacheNames()
	 */
	public Iterable<String> getCacheNames() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.cache.CacheManager#destroyCache(java.lang.String)
	 */
	public void destroyCache(String cacheName) {
//		this.getCache(cacheName).destoy();

	}

	/* (non-Javadoc)
	 * @see com.cache.CacheManager#close()
	 */
	public void close() {
//		this.getCache(cacheName).

	}

	/* (non-Javadoc)
	 * @see com.cache.CacheManager#isClosed()
	 */
	public boolean isClosed() {
		// TODO Auto-generated method stub
		return false;
	}

}
