package com.leederedu.educhat.controller.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.leederedu.educhat.service.CommendService;
import com.leederedu.educhat.util.GenericsUtils;

import net.sf.json.JSONObject;

/**
 * 推荐页controller
 *
 */

@Controller
@RequestMapping("/commends")
public class CommendController {
	
	@Resource
	private CommendService commendService;
	
	@RequestMapping(value = "getHomepage",method = RequestMethod.GET)
	public void getHomepage(long custId,@RequestParam(value = "plateCode",required = false) int plateCode,@RequestParam(value = "cnt",defaultValue = "10") int cnt,HttpServletRequest request,HttpServletResponse response) {
		PrintWriter writer = null;
		String result = "";
		try {
			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/json; charset=utf-8");
			writer = response.getWriter();
			if(custId <= 0) {
				result = GenericsUtils.returnJsonString(-1, "42A1107017", "输入数据无效", "");
			}
			
			JSONObject jsonObj = commendService.getHomepage(custId, plateCode, cnt);
			if(jsonObj != null) {
				result = GenericsUtils.returnJsonString(1, null, null, jsonObj.toString());
			}else {
				result = GenericsUtils.returnJsonString(-1, "41A1107016", "未查询到数据", "");
			}
			writer.write(result);
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			if(writer != null) {
				writer.flush();
				writer.close();
			}
		}
	}
}
