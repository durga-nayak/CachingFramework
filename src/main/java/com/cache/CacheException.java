/**
 * 
 */
package com.cache;

/**
 * Thrown to indicate an exception has occurred in the Cache.
 * It is the base class for all cache custom exception.
 * @author NAYAKDUR
 *
 */
public class CacheException extends RuntimeException {
	private static final long serialVersionUID = -7499561891248873709L;

	  /**
	   * Constructs a new CacheException.
	   *
	   * @since 1.0
	   */
	  public CacheException() {
	    super();
	  }

	  /**
	   * Constructs a new CacheException with a message string.
	   *
	   * @param message the detail message. The detail message is saved for
	   *                later retrieval by the {@link #getMessage()} method.
	   */
	  public CacheException(String message) {
	    super(message);
	  }

	  /**
	   * Constructs a CacheException with a message string, and
	   * a base exception
	   *
	   * @param message the detail message. The detail message is saved for
	   *                later retrieval by the {@link #getMessage()} method.
	   * @param cause   the cause (that is saved for later retrieval by the
	   *                {@link #getCause()} method).  (A <tt>null</tt> value is
	   *                permitted, and indicates that the cause is nonexistent or
	   *                unknown.)
	   */
	  public CacheException(String message, Throwable cause) {
	    super(message, cause);
	  }

	  /**
	   * Constructs a new CacheException with the specified cause and a
	   * detail message of <tt>(cause==null ? null : cause.toString())</tt>
	   * (that typically contains the class and detail message of
	   * <tt>cause</tt>).  This constructor is useful for runtime exceptions
	   * that are little more than wrappers for other throwables.
	   *
	   * @param cause the cause (that is saved for later retrieval by the
	   *              {@link #getCause()} method).  (A <tt>null</tt> value is
	   *              permitted, and indicates that the cause is nonexistent or
	   *              unknown.)
	   */
	  public CacheException(Throwable cause) {
	    super(cause);
	  }

}
