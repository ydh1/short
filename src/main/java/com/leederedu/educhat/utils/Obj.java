package com.leederedu.educhat.utils;

import java.util.HashMap;
import java.util.Map;

public class Obj {
	public static Map<String, Object> loadMap(String[] name, Object[] val){
		Map<String, Object> map = new HashMap<String, Object>();
		for (int i = 0; i < name.length; i++) {
			map.put(name[i], val[i]);
		}
		return map;
	}
	
	public static Map<String, Object> asMap(String[] keys, Object ... values) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		for (int i = 0; i < keys.length; i++) {
			map.put(keys[i], values[i]);
		}
		return map;
	}
	
	public static Map<String, Object> asMap(Object ... values){
		HashMap<String, Object> map = new HashMap<String, Object>();
		int center = values.length / 2;
		for (int i = 0; i < center; i++) {
			map.put(values[i].toString(), values[center + i]);
		}
		return map;
	}

	public static String[] toAr(String... values) {
		return values;
	}

	public static Object[] toAr(Object... values) {
		return values;
	}
	
	public static int getPageNum(int page, int rows){
		int sr = (page-1) * rows;
		return sr;
	}
	
	public static String toStr(double d)
	{
	    if(d == (long) d)
	        return String.format("%d",(long)d);
	    else
	        return String.format("%s",d);
	}
	
	public static int toInt(Object value){
		if(value != null){
			try {
				return Integer.parseInt(value.toString());
			} catch (Exception e) {
			}
		}
		return -1;
	}


	public static int toInt(Object value,int defaultValue){
		if(value != null){
			try {
				return Integer.parseInt(value.toString());
			} catch (Exception e) {
			}
		}
		return defaultValue;
	}

	public static float toFloat(String value){
		if(value != null){
			try {
				return Float.parseFloat(value);
			} catch (Exception e) {
			}
		}
		return -1;
	}
}
