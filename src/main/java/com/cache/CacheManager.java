/**
 * 
 */
package com.cache;

import javax.security.auth.login.Configuration;

/**
 * * A {@link CacheManager} provides a means of establishing, configuring,
 * acquiring, closing and destroying uniquely named {@link Cache}s.
 * <p>
 * {@link Cache}s produced and owned by a {@link CacheManager} typically share
 * common infrastructure.
 * @author NAYAKDUR
 *
 */
public interface CacheManager {
	
		<K, V> Cache<K, V> addCache(Cache<K, V> cache) throws IllegalArgumentException;
		<K, V> Cache<K, V> loadCache(String cacheName) throws IllegalArgumentException;
	 /**
	   * Creates a named {@link Cache} at runtime.
	   * <p>
	   * If a {@link Cache} with the specified name is known to the {@link
	   * CacheManager}, a CacheException is thrown.
	   * <p>
	   * If a {@link Cache} with the specified name is unknown the {@link
	   * CacheManager}, one is created according to the provided {@link Configuration}
	   * after which it becomes managed by the {@link CacheManager}.
	   * <p>
	   * Prior to a {@link Cache} being created, the provided {@link Configuration}s is
	   * validated within the context of the {@link CacheManager} properties and
	   * implementation.
	   * <p>
	   * Implementers should be aware that the {@link Configuration} may be used to
	   * configure other {@link Cache}s.
	   * <p>
	   * There's no requirement on the part of a developer to call this method for
	   * each {@link Cache} an application may use.  Implementations may support
	   * the use of declarative mechanisms to pre-configure {@link Cache}s, thus
	   * removing the requirement to configure them in an application.  In such
	   * circumstances a developer may simply call either the
	   * {@link #getCache(String)} or {@link #getCache(String, Class, Class)}
	   * methods to acquire a previously established or pre-configured {@link Cache}.
	   *
	   * @param cacheName     the name of the {@link Cache}
	   * @throws IllegalStateException         if the {@link CacheManager}
	   *                                       {@link #isClosed()}
	   * @throws CacheException                if there was an error configuring the
	   *                                       {@link Cache}, which includes trying
	   *                                       to create a cache that already exists.
	   * @throws IllegalArgumentException      if the configuration is invalid
	   * @throws UnsupportedOperationException if the configuration specifies
	   *                                       an unsupported feature
	   * @throws NullPointerException          if the cache configuration or name
	   *                                       is null
	   * @throws SecurityException             when the operation could not be performed
	   *                                       due to the current security settings
	   */
	  <K, V> Cache<K, V> createCache(String cacheName) throws IllegalArgumentException;
	  /**
	   * Looks up a managed {@link Cache} given its name.
	   * <p>
	   * This method must be used for {@link Cache}s that were configured with
	   * runtime key and value types. Use {@link #getCache(String)} for
	   * {@link Cache}s where these were not specified.
	   * <p>
	   * Implementations must ensure that the key and value types are the same as
	   * those configured for the {@link Cache} prior to returning from this method.
	   * <p>
	   * Implementations may further perform type checking on mutative cache operations
	   * and throw a {@link ClassCastException} if these checks fail.
	   * <p>
	   * Implementations that support declarative mechanisms for pre-configuring
	   * {@link Cache}s may return a pre-configured {@link Cache} instead of
	   * <code>null</code>.
	   *
	   * @param cacheName the name of the managed {@link Cache} to acquire
	   * @param keyType   the expected {@link Class} of the key
	   * @param valueType the expected {@link Class} of the value
	   * @return the Cache or null if it does exist or can't be pre-configured
	   * @throws IllegalStateException    if the {@link CacheManager}
	   *                                  is {@link #isClosed()}
	   * @throws IllegalArgumentException if the specified key and/or value types are
	   *                                  incompatible with the configured cache.
	   * @throws SecurityException        when the operation could not be performed
	   *                                  due to the current security settings
	   */
	  <K, V> Cache<K, V> getCache(String cacheName, Class<K> keyType,
	                              Class<V> valueType);

	  /**
	   * Looks up a managed {@link Cache} given its name.
	   * <p>
	   * This method may only be used to acquire {@link Cache}s that were
	   * configured without runtime key and value types, or were configured
	   * to use Object.class key and value types.
	   * <p>
	   * Use the {@link #getCache(String, Class, Class)} method to acquire
	   * {@link Cache}s that were configured with specific runtime types.
	   * <p>
	   * Implementations must check if key and value types were configured
	   * for the requested {@link Cache}. If either the keyType or valueType of the
	   * configured {@link Cache} were specified (other than <code>Object.class</code>)
	   * an {@link IllegalArgumentException} will be thrown.
	   * <p>
	   * Implementations that support declarative mechanisms for pre-configuring
	   * {@link Cache}s may return a pre-configured {@link Cache} instead of
	   * <code>null</code>.
	   *
	   * @param cacheName the name of the cache to look for
	   * @return the Cache or null if it does exist or can't be pre-configured
	   * @throws IllegalStateException    if the CacheManager is {@link #isClosed()}
	   * @throws IllegalArgumentException if the {@link Cache} was configured with
	   *                                  specific types, this method cannot be used
	   * @throws SecurityException        when the operation could not be performed
	   *                                  due to the current security settings
	   * @see #getCache(String, Class, Class)
	   */
	  <K, V> Cache<K, V> getCache(String cacheName);

	  /**
	   * Obtains an {@link Iterable} over the names of {@link Cache}s managed by the
	   * {@link CacheManager}.
	   * <p>
	   * {@link java.util.Iterator}s returned by the {@link Iterable} are immutable.
	   * Any modification of the {@link java.util.Iterator}, including remove, will
	   * raise an {@link IllegalStateException}.  If the {@link Cache}s managed by
	   * the {@link CacheManager} change, the {@link Iterable} and
	   * associated {@link java.util.Iterator}s are not affected.
	   * <p>
	   * {@link java.util.Iterator}s returned by the {@link Iterable} may not provide
	   * all of the {@link Cache}s managed by the {@link CacheManager}.  For example:
	   * Internally defined or platform specific {@link Cache}s that may be accessible
	   * by a call to {@link #getCache(String)} or {@link #getCache(String, Class,
	   * Class)} may not be present in an iteration.
	   *
	   * @return an {@link Iterable} over the names of managed {@link Cache}s.
	   * @throws IllegalStateException if the {@link CacheManager}
	   *                               is {@link #isClosed()}
	   * @throws SecurityException     when the operation could not be performed
	   *                               due to the current security settings
	   */
	  Iterable<String> getCacheNames();

	  /**
	   * Destroys a specifically named and managed {@link Cache}.
	   * <p>
	   * This is equivalent to the following sequence of method calls:
	   * <ol>
	   * <li>{@link Cache#clear()}</li>
	   * <li>{@link Cache#close()}</li>
	   * </ol>
	   * followed by allowing the name of the {@link Cache} to be used for other
	   * {@link Cache} configurations.
	   * <p>
	   *
	   * @param cacheName the cache to destroy
	   * @throws IllegalStateException if the {@link CacheManager}
	   *                               {@link #isClosed()}
	   * @throws NullPointerException  if cacheName is null
	   * @throws SecurityException     when the operation could not be performed
	   *                               due to the current security settings
	   */
	  void destroyCache(String cacheName);
	  
	  /**
	   * Closes the {@link CacheManager}.
	   * <p>
	   * For each {@link Cache} managed by the {@link CacheManager}, the
	   * {@link Cache#close()} method will be invoked, in no guaranteed order.
	   * <p>
	   * If a {@link Cache#close()} call throws an exception, the exception will be
	   * ignored.
	   * <p>
	   * After executing this method, the {@link #isClosed()} method will return
	   * <code>true</code>.
	   * <p>
	   * All attempts to close a previously closed {@link CacheManager} will be
	   * ignored.
	   *
	   * @throws SecurityException when the operation could not be performed due to the
	   *         current security settings
	   */
	  void close();

	  /**
	   * Determines whether the {@link CacheManager} instance has been closed. A
	   * {@link CacheManager} is considered closed if;
	   * <p>
	   * This method generally cannot be called to determine whether the
	   * {@link CacheManager} is valid or invalid. A typical client can determine
	   * that a {@link CacheManager} is invalid by catching any exceptions that
	   * might be thrown when an operation is attempted.
	   *
	   * @return true if this {@link CacheManager} instance is closed; false if it
	   *         is still open
	   */
	  boolean isClosed();
}
