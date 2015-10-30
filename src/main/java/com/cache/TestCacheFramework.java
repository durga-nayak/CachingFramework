/**
 * 
 */
package com.cache;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.gemfire.support.GemfireCacheManager;

import com.cache.gemfire.GemfireCacheManagerImpl;

/**
 * @author NAYAKDUR
 *
 */
public class TestCacheFramework {

	/**
	 * 
	 */
	public TestCacheFramework() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ClassPathXmlApplicationContext appCtx = new ClassPathXmlApplicationContext("gemfire-config.xml");
		String arr[] =appCtx.getBeanDefinitionNames();
		GemfireCacheManagerImpl gemfireCacheManager = (GemfireCacheManagerImpl) appCtx.getBean("gemfireCacheManagerImpl");
		Cache<String, String> cache = gemfireCacheManager.getCache("mtaxRegion");
		cache.put("Test", "Put");		
		System.out.println(gemfireCacheManager.getCache("mtaxRegion").get("Test"));
		System.out.println("End");

	}

}
