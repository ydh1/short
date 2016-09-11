package com.leederedu.educhat.utils;

import org.springframework.cache.ehcache.EhCacheCacheManager;

import com.leederedu.educhat.util.StringUtil;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

/**
 * 缓存工具类
 *
 */
public class EhcacheUtil {
	public static String CACHE_PLATE = "cache_plate";  //板块cache     key : region_code
	public static String CACHE_EXPERT = "cache_expert";  //专家cache
	public static String CACHE_PLATECONTENT = "cache_platecontent";   //资讯内容（新闻或专家文章）
	
	public static CacheManager cacheManager = ((EhCacheCacheManager)SpringUtils.getContext().getBean("cacheManager")).getCacheManager();
	
	//获取对应KEY的缓存数据
	public static Object get(String cacheName,String key) {
		Element element = getCache(cacheName).get(key);
		return element == null?"":element.getObjectValue();
	}
	
	public static void put(String cacheName,String key,Object value) {
		if(!StringUtil.isEmpty(key)) {
			Element element = new Element(key, value);
			getCache(cacheName).put(element);
		}
	}
	
	public static void remove(String cacheName,String key) {
		getCache(cacheName).remove(key);
	}
	
	public static Cache getCache(String cacheName) {
		Cache cache = cacheManager.getCache(cacheName);
		if(cache == null) {
			cacheManager.addCache(cacheName);
			cache = cacheManager.getCache(cacheName);
			cache.getCacheConfiguration().setEternal(true);
		}
		return cache;
	}
}
