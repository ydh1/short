package com.leederedu.educhat.service;

import org.springframework.cache.ehcache.EhCacheCacheManager;

import com.leederedu.educhat.utils.SpringUtils;

import net.sf.ehcache.Ehcache;

public class BaseService {
	
	public static String EHCACHE_PLATE = "ehcache_plate";
	
	protected Ehcache getEhcache() {
		return null;
	}
}
