package com.leederedu.educhat.frame;

import java.sql.ResultSet;
import java.util.Date;
import java.util.HashMap;

import com.leederedu.educhat.db.JDBCDao;
import com.leederedu.educhat.utils.Obj;
import com.leederedu.educhat.utils.MyLog;


public class GKSetting {
	public static boolean IS_DEBUG = true;

	private static HashMap<String, String> map ;
	static long lastUpdate = 0;
	
	public static String getString(String key){
		String result = null;
		try {
			init();
			result = map.get(key);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public static Integer getInt(String key){
		Integer result = null;
		try {			
			init();
			String value = map.get(key);
			if(value != null){
				result = Integer.parseInt(value);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public static Double getDouble(String key){
		Double result = null;
		try {			
			init();
			String value = map.get(key);
			if(value != null){
				result = Double.parseDouble(value);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	} 
	
	public static Float getFloat(String key){
		Float result = null;
		try {			
			init();
			String value = map.get(key);
			if(value != null){
				result = Float.parseFloat(value);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	} 
	
	public static boolean Contains(String key){
		return map.containsKey(key);
	}
	
	static {
		init();
	}
	
	public  static void init(){
		if(map != null && map.size() > 0 /* && (new Date().getTime() - lastUpdate) < 20*60000 */){
			return;
		}
		lastUpdate = new Date().getTime();
		JDBCDao dao = new JDBCDao();
		try {
			
			//properties 暂不存在这表，故先注释 
			/*String sql = "SELECT `key`,`value` FROM properties";
			ResultSet set = dao.query(sql);
			map = new HashMap<String, String>();
			while (set.next()) {
				map.put(set.getString("key"), set.getString("value"));
			}*/
		} catch (Exception e) {
			MyLog.error(e);
		}
		dao.close();
	}

	public static HashMap<String, String> getMap() {
		init();
		return map;
	}

    public static void reload(){
        map = null;
        init();
    }
}
