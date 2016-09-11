package com.leederedu.educhat.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Service;

import com.leederedu.educhat.db.mappers.CommendMapper;

/**
 * 推荐服务类
 *
 */
@Service
public class CommendService<T> {
	
	@Resource
	private CommendMapper<T> commendMapper;
	
	public JSONObject getHomepage(long custId,int plateCode,int cnt){
		JSONObject resultJO = new JSONObject();
		//获取轮播图信息
		JSONArray banners = getBanners(custId, plateCode);
		if(banners != null && banners.size() > 0) {
			resultJO.put("banners", banners);
		}else {
			resultJO.put("banners", new JSONArray());
		}
		
		//获取推荐信息
		
		return resultJO;
	}

	/**
	 * 获取板块对应的轮播图
	 * @param custId
	 * @param plateCode
	 * @return
	 */
	private JSONArray getBanners(long custId,int plateCode) {
		JSONArray ja = null;
		List banners = commendMapper.getBanners(custId,plateCode);
		if(banners != null && banners.size() > 0) {
			ja = new JSONArray();
			JSONObject tempJO = null;
			Map<String, Object> tempMap = null;
			for (int i = 0; i < banners.size(); i++) {
				tempMap = (Map<String, Object>) banners.get(i);
				tempJO = new JSONObject();
				tempJO.put("bannerId", tempMap.get("bannerId"));
				
				int bannerType = Integer.valueOf(tempMap.get("bannerType").toString());
				switch(bannerType) {
					case 1: //图片
						tempJO.put("bannerType", bannerType);
						tempJO.put("photoUrl", tempMap.get("file_url")); //图片链接地址
						tempJO.put("hyperlink", tempMap.get("hyperlink")); //图片超链接地址
					break;
					
					case 2: //视频
						JSONObject video = new JSONObject();
						video.put("videoUrl", tempMap.get("file_url"));
						video.put("duration", tempMap.get("duration"));
						tempJO.put("video", video);
					break;
					
					case 3: //音频
						JSONObject voice = new JSONObject();
						voice.put("voiceUrl", tempMap.get("file_url"));
						voice.put("duration", tempMap.get("duration"));
						tempJO.put("video", voice);
						break;
					default:
						break;
				}
				ja.add(tempJO);
			}
		}
		return ja;
	}
	
	/**
	 * 分页获取推荐信息 
	 * @param custId  当前客户ID
	 * @param plateCode	板块编码
	 * @param idx	页码
	 * @param cnt	每页条数
	 * @return
	 */
	private JSONArray getCommends(long custId,int plateCode,int idx,int cnt) {
		JSONArray jo = null;
		
		return jo;
	}
}
