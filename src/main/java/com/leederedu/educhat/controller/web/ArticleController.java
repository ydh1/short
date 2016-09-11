package com.leederedu.educhat.controller.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 文章信息controller
 *
 */

@Controller
@RequestMapping("/article")
public class ArticleController {
	
	/**
	 * 
	 * @param sortType
	 * @param pageIndex
	 * @param pageCount
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/getListOfAllSpecialists",method = RequestMethod.GET)
	public String getListOfAllSpecialists(int sortType,int pageIndex,int pageCount,HttpServletRequest request,HttpServletResponse response) {
		
		return "";
	}
	
	/**
	 * 获取专家最近更新的文章列表
	 * @param custId
	 * @param pageIndex
	 * @param pageCount
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/getList",method = RequestMethod.GET)
	public String getList(long custId,int pageIndex,int pageCount,HttpServletRequest request,HttpServletResponse response) {
		
		return "";
	}
	
	/**
	 * 根据文章ID获取文章信息
	 * @param id
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/getEntity",method = RequestMethod.GET)
	public String getEntity(long id,HttpServletRequest request,HttpServletResponse response) {
		return "";
	}
}
