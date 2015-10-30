/**
 * 
 */
package com.cache;

import java.util.Map;
import java.util.Set;


/**
 * @author NAYAKDUR
 *
 */
public interface Cache<K, V> {

	/**
	   * Gets an entry from the cache.
	   * <p>
	   * If the cache is configured to use read-through, and get would return null
	   * because the entry is missing from the cache, the Cache's {@link CacheLoader}
	   * is called in an attempt to load the entry.
	   *
	   * @param key the key whose associated value is to be returned
	   * @return the element, or null, if it does not exist.
	   * @throws IllegalStateException if the cache is {@link #isClosed()}
	   * @throws NullPointerException  if the key is null
	   * @throws CacheException        if there is a problem fetching the value
	   * @throws ClassCastException    if the implementation is configured to perform
	   *                               runtime-type-checking, and the key or value
	   *                               types are incompatible with those that have been
	   *                               configured for the {@link Cache}
	   */
	  V get(K key);
	  /**
	   * Gets a collection of entries from the {@link Cache}, returning them as
	   * {@link Map} of the values associated with the set of keys requested.
	   * <p>
	   * If the cache is configured read-through, and a get for a key would
	   * return null because an entry is missing from the cache, the Cache's
	   * {@link CacheLoader} is called in an attempt to load the entry. If an
	   * entry cannot be loaded for a given key, the key will not be present in
	   * the returned Map.
	   *
	   * @param keys The keys whose associated values are to be returned.
	   * @return A map of entries that were found for the given keys. Keys not found
	   *         in the cache are not in the returned map.
	   * @throws NullPointerException  if keys is null or if keys contains a null
	   * @throws IllegalStateException if the cache is {@link #isClosed()}
	   * @throws CacheException        if there is a problem fetching the values
	   * @throws ClassCastException    if the implementation is configured to perform
	   *                               runtime-type-checking, and the key or value
	   *                               types are incompatible with those that have been
	   *                               configured for the {@link Cache}
	   */
	  Map<K, V> getAll(Set<? extends K> keys);

	  /**
	   * Determines if the {@link Cache} contains an entry for the specified key.
	   * <p>
	   * More formally, returns <tt>true</tt> if and only if this cache contains a
	   * mapping for a key <tt>k</tt> such that <tt>key.equals(k)</tt>.
	   * (There can be at most one such mapping.)
	   *
	   * @param key key whose presence in this cache is to be tested.
	   * @return <tt>true</tt> if this map contains a mapping for the specified key
	   * @throws NullPointerException  if key is null
	   * @throws IllegalStateException if the cache is {@link #isClosed()}
	   * @throws CacheException        it there is a problem checking the mapping
	   * @throws ClassCastException    if the implementation is configured to perform
	   *                               runtime-type-checking, and the key or value
	   *                               types are incompatible with those that have been
	   *                               configured for the {@link Cache}
	   */
	  boolean containsKey(K key);
	  
	  /**
	   * Associates the specified value with the specified key in the cache.
	   * <p>
	   * If the {@link Cache} previously contained a mapping for the key, the old
	   * value is replaced by the specified value.  (A cache <tt>c</tt> is said to
	   * contain a mapping for a key <tt>k</tt> if and only if {@link
	   * #containsKey(Object) c.containsKey(k)} would return <tt>true</tt>.)
	   *
	   * @param key   key with which the specified value is to be associated
	   * @param value value to be associated with the specified key
	   * @throws NullPointerException  if key is null or if value is null
	   * @throws IllegalStateException if the cache is {@link #isClosed()}
	   * @throws CacheException        if there is a problem doing the put
	   * @throws ClassCastException    if the implementation is configured to perform
	   *                               runtime-type-checking, and the key or value
	   *                               types are incompatible with those that have been
	   *                               configured for the {@link Cache}
	   * @see java.util.Map#put(Object, Object)
	   * @see #getAndPut(Object, Object)
	   * @see #getAndReplace(Object, Object)
	   * @see CacheWriter#write
	   */
	  void put(K key, V value);
	  /**
	   * Copies all of the entries from the specified map to the {@link Cache}.
	   * <p>
	   * The effect of this call is equivalent to that of calling
	   * {@link #put(Object, Object) put(k, v)} on this cache once for each mapping
	   * from key <tt>k</tt> to value <tt>v</tt> in the specified map.
	   * <p>
	   * The order in which the individual puts occur is undefined.
	   * <p>
	   * The behavior of this operation is undefined if entries in the cache
	   * corresponding to entries in the map are modified or removed while this
	   * operation is in progress. or if map is modified while the operation is in
	   * progress.
	   * <p>
	   * In Default Consistency mode, individual puts occur atomically but not
	   * the entire putAll.  Listeners may observe individual updates.
	   *
	   * @param map mappings to be stored in this cache
	   * @throws NullPointerException  if map is null or if map contains null keys
	   *                               or values.
	   * @throws IllegalStateException if the cache is {@link #isClosed()}
	   * @throws CacheException        if there is a problem doing the put.
	   * @throws ClassCastException    if the implementation is configured to perform
	   *                               runtime-type-checking, and the key or value
	   *                               types are incompatible with those that have been
	   *                               configured for the {@link Cache}
	   */
	  void putAll(java.util.Map<? extends K, ? extends V> map);
	  /**
	   * Removes the mapping for a key from this cache if it is present.
	   * <p>
	   * More formally, if this cache contains a mapping from key <tt>k</tt> to
	   * value <tt>v</tt> such that
	   * <code>(key==null ?  k==null : key.equals(k))</code>, that mapping is removed.
	   * (The cache can contain at most one such mapping.)
	   * 
	   * <p>Returns <tt>true</tt> if this cache previously associated the key,
	   * or <tt>false</tt> if the cache contained no mapping for the key.
	   * <p>
	   * The cache will not contain a mapping for the specified key once the
	   * call returns.
	   *
	   * @param key key whose mapping is to be removed from the cache
	   * @return returns false if there was no matching key
	   * @throws NullPointerException  if key is null
	   * @throws IllegalStateException if the cache is {@link #isClosed()}
	   * @throws CacheException        if there is a problem doing the remove
	   * @throws ClassCastException    if the implementation is configured to perform
	   *                               runtime-type-checking, and the key or value
	   *                               types are incompatible with those that have been
	   *                               configured for the {@link Cache}
	   */
	  boolean remove(K key);

	  /**
	   * Atomically removes the mapping for a key only if currently mapped to the
	   * given value.
	   * <p>
	   * This is equivalent to:
	   * <pre><code>
	   * if (cache.containsKey(key) &amp;&amp; equals(cache.get(key), oldValue) {
	   *   cache.remove(key);
	   *   return true;
	   * } else {
	   *   return false;
	   * }
	   * </code></pre>
	   * except that the action is performed atomically.
	   *
	   * @param key      key whose mapping is to be removed from the cache
	   * @param oldValue value expected to be associated with the specified key
	   * @return returns false if there was no matching key
	   * @throws NullPointerException  if key is null
	   * @throws IllegalStateException if the cache is {@link #isClosed()}
	   * @throws CacheException        if there is a problem doing the remove
	   * @throws ClassCastException    if the implementation is configured to perform
	   *                               runtime-type-checking, and the key or value
	   *                               types are incompatible with those that have been
	   *                               configured for the {@link Cache}
	   */
	  boolean remove(K key, V oldValue);

	  /**
	   * Atomically replaces the entry for a key only if currently mapped to a
	   * given value.
	   * <p>
	   * This is equivalent to:
	   * <pre><code>
	   * if (cache.containsKey(key) &amp;&amp; equals(cache.get(key), oldValue)) {
	   *  cache.put(key, newValue);
	   * return true;
	   * } else {
	   *  return false;
	   * }
	   * </code></pre>
	   * except that the action is performed atomically.
	   *
	   * @param key      key with which the specified value is associated
	   * @param oldValue value expected to be associated with the specified key
	   * @param newValue value to be associated with the specified key
	   * @return <tt>true</tt> if the value was replaced
	   * @throws NullPointerException  if key is null or if the values are null
	   * @throws IllegalStateException if the cache is {@link #isClosed()}
	   * @throws CacheException        if there is a problem during the replace
	   * @throws ClassCastException    if the implementation is configured to perform
	   *                               runtime-type-checking, and the key or value
	   *                               types are incompatible with those that have been
	   *                               configured for the {@link Cache}
	   */
	  boolean replace(K key, V oldValue, V newValue);

	  /**
	   * Atomically replaces the entry for a key only if currently mapped to some
	   * value.
	   * <p>
	   * This is equivalent to
	   * <pre><code>
	   * if (cache.containsKey(key)) {
	   *   cache.put(key, value);
	   *   return true;
	   * } else {
	   *   return false;
	   * }</code></pre>
	   * except that the action is performed atomically.
	   *
	   * @param key  the key with which the specified value is associated
	   * @param value the value to be associated with the specified key
	   * @return <tt>true</tt> if the value was replaced
	   * @throws NullPointerException  if key is null or if value is null
	   * @throws IllegalStateException if the cache is {@link #isClosed()}
	   * @throws CacheException        if there is a problem during the replace
	   * @throws ClassCastException    if the implementation is configured to perform
	   *                               runtime-type-checking, and the key or value
	   *                               types are incompatible with those that have been
	   *                               configured for the {@link Cache}
	   */
	  boolean replace(K key, V value);
	  
	  /**
	   * Removes entries for the specified keys.
	   * <p>
	   * The order in which the individual entries are removed is undefined.
	   * <p>
	   * For every entry in the key set, the following are called:
	   *
	   * @param keys the keys to remove
	   * @throws NullPointerException  if keys is null or if it contains a null key
	   * @throws IllegalStateException if the cache is {@link #isClosed()}
	   * @throws CacheException        if there is a problem during the remove
	   * @throws ClassCastException    if the implementation is configured to perform
	   *                               runtime-type-checking, and the key or value
	   *                               types are incompatible with those that have been
	   *                               configured for the {@link Cache}
	   */
	  void removeAll(Set<? extends K> keys);

	  /**
	   * Removes all of the mappings from this cache.
	   * <p>
	   * The order that the individual entries are removed is undefined.
	   * <p>
	   * For every mapping that exists the following are called:
	   * <p>
	   * This is potentially an expensive operation as listeners are invoked.
	   * Use {@link #clear()} to avoid this.
	   * 
	   * @throws IllegalStateException if the cache is {@link #isClosed()}
	   * @throws CacheException        if there is a problem during the remove
	   * @see #clear()
	   */
	  void removeAll();
	  

	  /**
	   * Clears the contents of the cache, without notifying listeners.
	   *
	   * @throws IllegalStateException if the cache is {@link #isClosed()}
	   * @throws CacheException        if there is a problem during the clear
	   */
	  void clear();
	  
	  /**
	   * Return the name of the cache.
	   *
	   * @return the name of the cache.
	   */
	  String getName();
	  
	  /**
	   * Closing a {@link Cache} signals to the {@link CacheManager} that produced or
	   * owns the {@link Cache} that it should no longer be managed. At this
	   * point in time the {@link CacheManager}:
	   * <ul>
	   * <li>must close and release all resources being coordinated on behalf of the
	   * Cache by the {@link CacheManager}. 
	   * </ul>
	   * Once closed any attempt to use an operational method on a Cache will throw an
	   * {@link IllegalStateException}.
	   *
	   * @throws SecurityException when the operation could not be performed
	   *                           due to the current security settings
	   */
	  void close();

	  /**
	   * Determines whether this Cache instance has been closed. A Cache is
	   * considered closed if;
	   * <ol>
	   * <li>the {@link #close()} method has been called</li>
	   * <li>the associated {@link #getCacheManager()} has been closed, or</li>
	   * <li>the Cache has been removed from the associated
	   * {@link #getCacheManager()}</li>
	   * </ol>
	   * <p>
	   * This method generally cannot be called to determine whether a Cache instance
	   * is valid or invalid. A typical client can determine that a Cache is invalid
	   * by catching any exceptions that might be thrown when an operation is
	   * attempted.
	   *
	   * @return true if this Cache instance is closed; false if it is still open
	   */
	  boolean isClosed();
}
