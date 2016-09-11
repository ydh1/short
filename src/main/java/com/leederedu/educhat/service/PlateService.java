package com.leederedu.educhat.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.leederedu.educhat.db.mappers.PlateMapper;
import com.leederedu.educhat.utils.EhcacheUtil;
import com.leederedu.educhat.utils.SpringUtils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 板块service
 *
 */
@Service
public class PlateService<T> {
	
	@Resource
	public PlateMapper plateMapper;
	
	/**
	 * 获取指定区域的板块信息 ,先从缓存里取，取不到再查数据库
	 * @param custId
	 * @param dcode
	 * @return
	 */
	public JSONArray getList(long custId,String dcode) {
		List<T> list = null;
		//先查缓存
		Object cacheObj = EhcacheUtil.get(EhcacheUtil.CACHE_PLATE, dcode);
		if(cacheObj != null && !"".equals(cacheObj)) {
			//命中，直接返回缓存数据
			list = (List<T>) cacheObj;
		}else {
			//否则查询数据库，然后把数据加入缓存
			list = plateMapper.getList(custId, dcode);
			
			EhcacheUtil.put(EhcacheUtil.CACHE_PLATE, dcode, list);
		}
		
		JSONArray json = null;
		if(list != null && list.size() > 0) {
			json = JSONArray.fromObject(list);
		}else {
			json = new JSONArray();
		}
		return json;
	}
}
