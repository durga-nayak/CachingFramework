/**
 * 
 */
package com.cache.gemfire;

import java.util.Map;
import java.util.Set;

import com.cache.Cache;
import com.gemstone.gemfire.cache.Region;


/**
 * @author NAYAKDUR
 *
 */
public class GemfireCache<K,V> implements  Cache<K, V>{

	@SuppressWarnings("rawtypes")
	private Region region;

	public void setRegion(Region<?, ?> region) {
		this.region = region;
	}

	public GemfireCache() {
		
	}
	
	public V get(K key) {
		return (V) this.region.get(key);
	}
	public Map<K, V> getAll(Set<? extends K> keys) {
		return this.region.getAll(keys);
	}
	public boolean containsKey(K key) {
		return this.region.containsKey(key);
	}
	public void putAll(Map<? extends K, ? extends V> map) {
		this.region.putAll(map);		
	}
	public boolean remove(K key) {
		return this.region.remove(key)!=null;
	}
	public boolean remove(K key, V oldValue) {
		return this.region.remove(key, oldValue);
	}
	public boolean replace(K key, V oldValue, V newValue) {
		return this.region.replace(key, oldValue, newValue);
	}
	public boolean replace(K key, V value) {
		return this.region.replace(key, value) != null;
	}
	public void removeAll(Set<? extends K> keys) {
		//TODO:
		//this.region.remove(keys);
	}
	public void removeAll() {
		this.region.clear();
		
	}
	public void clear() {
		this.region.clear();		
	}
	public String getName() {
		return region.getName();
	}
	public void close() {
		this.region.close();
		
	}
	public boolean isClosed() {
		return this.region.isDestroyed();
	}

	public void put(K key, V value) {
		this.region.put(key, value);
		
	}

}
