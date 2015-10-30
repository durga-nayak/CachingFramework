/**
 * 
 */
package com.cache;

import java.util.Map;


/**
 * @author NAYAKDUR
 *
 */
public interface ClassLoader<K, V> {
	  /**
	   * Loads an object. Application developers should implement this
	   * method to customize the loading of a value for a cache entry. This method
	   * is called by a cache when a requested entry is not in the cache. If
	   * the object can't be loaded <code>null</code> should be returned.
	   *
	   * @param key the key identifying the object being loaded
	   * @return The value for the entry that is to be stored in the cache or
	   *         <code>null</code> if the object can't be loaded
	   * @throws CacheLoaderException if there is problem executing the loader.
	   */
	  V load(K key) throws CacheLoaderException;



	  /**
	   * Loads multiple objects. Application developers should implement this
	   * method to customize the loading of cache entries. This method is called
	   * when the requested object is not in the cache. If an object can't be loaded,
	   * it is not returned in the resulting map.
	   *
	   * @param keys keys identifying the values to be loaded
	   * @return A map of key, values to be stored in the cache.
	   * @throws CacheLoaderException if there is problem executing the loader.
	   */
	  Map<K, V> loadAll(Iterable<? extends K> keys) throws CacheLoaderException;
}
