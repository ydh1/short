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

import com.leederedu.educhat.service.PlateService;
import com.leederedu.educhat.util.GenericsUtils;
import com.leederedu.educhat.util.StringUtil;

import net.sf.json.JSONArray;

/**
 * 板块controller
 *
 */
@Controller
@RequestMapping("/plate")
public class PlateController {
	
	@Resource
	private PlateService plateService;
	
	@RequestMapping(value = "/list",method = RequestMethod.GET)
	public void list(long custId,@RequestParam(value = "dcode",defaultValue = "000000") String dcode,HttpServletRequest request,HttpServletResponse response) {
		PrintWriter writer = null;
		String result = "";
		try {
			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/json; charset=utf-8");
			writer = response.getWriter();
			if(custId <= 0) {
				result = GenericsUtils.returnJsonString(-1, "42A1107017", "输入数据无效", "");
			}
			if(StringUtil.isEmpty(dcode)) {
				result = GenericsUtils.returnJsonString(-1, "42A1107017", "输入数据无效", "");
			}
			//获取板块列表
			JSONArray resultJson = plateService.getList(custId, dcode);
			if(resultJson != null && resultJson.size() > 0) {
				result = GenericsUtils.returnJsonString(1, null, null, resultJson.toString());
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
