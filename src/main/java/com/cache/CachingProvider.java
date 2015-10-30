/**
 * 
 */
package com.cache;

import java.net.URI;
import java.util.Properties;



/**
 * @author NAYAKDUR
 *
 */
public interface CachingProvider {
	
	 /**
	   * Requests a {@link CacheManager} configured according to the implementation
	   * specific {@link URI} be made available
	   * <p>
	   * Properties are used in construction of a {@link CacheManager} and do not form
	   * part of the identity of the CacheManager. i.e. if a second call is made to
	   * with the same {@link URI} and {@link ClassLoader} but different properties,
	   * the {@link CacheManager} created in the first call is returned.
	   *
	   * @param uri         an implementation specific URI for the
	   *                    {@link CacheManager} (null means use
	   *                    {@link #getDefaultURI()})
	   * @param properties  the {@link Properties} for the {@link CachingProvider}
	   *                    to create the {@link CacheManager} (null means no
	   *                    implementation specific Properties are required)
	   * @throws CacheException    when a {@link CacheManager} for the
	   *                           specified arguments could not be produced
	   * @throws SecurityException when the operation could not be performed
	   *                           due to the current security settings
	   */
	  CacheManager getCacheManager(URI uri, Properties properties);
	 /**
	   * Obtains the default {@link URI} for the {@link CachingProvider}.
	   * <p>
	   * Use this method to obtain a suitable {@link URI} for the
	   * {@link CachingProvider}.
	   *
	   * @return the default {@link URI} for the {@link CachingProvider}
	   */
	  URI getDefaultURI();

	  /**
	   * Obtains the default {@link Properties} for the {@link CachingProvider}.
	   * <p>
	   * Use this method to obtain suitable {@link Properties} for the
	   * {@link CachingProvider}.
	   *
	   * @return the default {@link Properties} for the {@link CachingProvider}
	   */
	  Properties getDefaultProperties();
	 /**
	   * Requests a {@link CacheManager} configured according to the implementation
	   * specific {@link URI}.
	   * @param uri         an implementation specific {@link URI} for the
	   *                    {@link CacheManager} 
	   * @throws CacheException    when a {@link CacheManager} for the
	   *                           specified arguments could not be produced
	   * @throws SecurityException when the operation could not be performed
	   *                           due to the current security settings
	   */
	  CacheManager getCacheManager(URI uri);

	  /**
	   * Requests a {@link CacheManager} configured according to the
	   * {@link #getDefaultURI()} and {@link #getDefaultProperties()}.
	   * <p>
	   * Multiple calls to this method must return the same {@link CacheManager}
	   * instance, except if a previously returned {@link CacheManager} has been
	   * closed.
	   *
	   * @throws SecurityException when the operation could not be performed
	   *                           due to the current security settings
	   */
	  CacheManager getCacheManager();

	  /**
	   * Closes all of the {@link CacheManager} instances and associated resources
	   * created and maintained by the {@link CachingProvider}.
	   * <p>
	   * After closing the {@link CachingProvider} will still be operational.  It
	   * may still be used for acquiring {@link CacheManager} instances, though
	   * those will now be new.
	   *
	   * @throws SecurityException when the operation could not be performed
	   *                           due to the current security settings
	   */
	  void close();
}
