package com.leederedu.educhat.utils;

import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * JSON工具类
 * @author Administrator
 *
 */
public class JsonUtil {

	public static JSONArray obj2JSONArray(List<?> list,String[] itemKeys,String[] childItemKeys,String[][] childItems ) {
		JSONArray resultJA = new JSONArray();
		if(list != null && !list.isEmpty() && itemKeys != null && itemKeys.length > 0) {
			Object data = null;
			JSONObject resultJO = null;
			for (int i = 0; i < list.size(); i++) {
				data = list.get(i);
				if(data instanceof Map) {
					resultJO = obj2JSONObject((Map<String, Object>)data, itemKeys, childItemKeys, childItems);
					resultJA.add(resultJO);
				}else if(data instanceof Class) {
					resultJO = obj2JSONObject(data, itemKeys, childItemKeys, childItems);
					resultJA.add(resultJO);
				}
			}
		}
		return resultJA;
	}
	
	/**
	 * Map转JSONObject
	 * @param data  map数据
	 * @param itemKeys   JSON第一层KEY
	 * @param childItemKeys  JSON第二层KEY数组
	 * @param childItems	 JSON第二层KEY二维数组
	 * @return
	 */
	public static JSONObject obj2JSONObject(Map<String, Object> data,String[] itemKeys,String[] childItemKeys,String[][] childItems ) {
		JSONObject resultJO = new JSONObject();
		if(data != null && !data.isEmpty() && itemKeys != null && itemKeys.length > 0) {
			//JSONObject dataJson = JSONObject.fromObject(data);
			//开始拼接第一层JSON内容
			for (int i = 0; i < itemKeys.length; i++) {
				//非空的才拼到第一层JSON
				if(itemKeys[i] != null && !"".equals(itemKeys[i])) {
					resultJO.put(itemKeys[i], data.get(itemKeys[i]) == null?"":data.get(itemKeys[i]));
				}
			}
			
			//开始拼装第二层JSON内容
			JSONObject childItemJO = null;
			String[] childKeys = null;
			if(childItemKeys != null && childItemKeys.length > 0) {
				for (int i = 0; i < childItemKeys.length && i < childItems.length; i++) {
					childItemJO = new JSONObject();
					//第二层KEYS
					childKeys = childItems[i];
					//循环第二层内容KEY
					for (int ii = 0; ii < childKeys.length; ii++) {
						if(childKeys[ii] != null && !"".equals(childKeys[ii])) {
							childItemJO.put(childKeys[ii], data.get(childKeys[ii]) == null?"":data.get(childKeys[ii]));
						}
					}
					
					resultJO.put(childItemKeys[i], childItemJO);
				}
			}
		}
		return resultJO;
	}
	
	public static JSONObject obj2JSONObject(Object clazz,String[] itemKeys,String[] childItemKeys,String[][] childItems ) {
		JSONObject resultJO = new JSONObject();
		
		return resultJO;
	}
}
