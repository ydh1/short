package com.leederedu.educhat.frame;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.leederedu.educhat.utils.MyLog;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

public class ExceptionHandler implements HandlerExceptionResolver {

	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
		MyLog.error(ex);
		request.setAttribute("errorInfo", ex.getClass().getSimpleName());
		return new ModelAndView("/common/error");
	}
}