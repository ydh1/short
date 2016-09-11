package com.leederedu.educhat.frame.intceptor;

import com.leederedu.educhat.frame.annotation.ApiLimit;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EnterIntceptor implements HandlerInterceptor {

    @Override
    public void afterCompletion(HttpServletRequest arg0,
                                HttpServletResponse arg1, Object arg2, Exception arg3)
            throws Exception {

    }

    @Override
    public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
                           Object arg2, ModelAndView arg3) throws Exception {
        // TODO Auto-generated method stub

    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object obj) throws Exception {
        if (obj instanceof ResourceHttpRequestHandler) {//资源
            return true;
        }

        //User user = ServerUtils.checkLogin(request, response, userService);


        // 权限
        ApiLimit wikiLimit = ((HandlerMethod) obj).getMethodAnnotation(ApiLimit.class);
        if (wikiLimit != null) {
            return checkLimit(wikiLimit, request, response);
        }

        return true;
    }


    private boolean checkLimit(ApiLimit limit, HttpServletRequest request, HttpServletResponse response) {
        try {
            String[] limits = limit.limit();
            if (limits != null) {
//                if (user == null) {
//                    request.getRequestDispatcher("/error/请登陆后再操作").forward(request, response);
//                    return false;
//                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return true;
    }

}
