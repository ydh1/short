package com.leederedu.educhat.controller.api;

import com.leederedu.educhat.frame.annotation.ApiLimit;
import com.leederedu.educhat.model.User;
import com.leederedu.educhat.model.api.ApiResult;
import com.leederedu.educhat.service.UserService;
import com.leederedu.educhat.utils.Obj;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@ResponseBody
@RequestMapping("/api/user")
public class UserApi {

    @Resource
    UserService userService;

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    @ApiLimit(limit = "login")
    public Object show(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int userId = Obj.toInt(request.getParameter("userId"));
        if(userId > 0){
            User user = userService.getUser(userId);
            if(user != null){
               return ApiResult.success().put("user",user);
            }else{
                return ApiResult.error("没找到");
            }
        }else{
            return ApiResult.error("参数错误");
        }
    }
}
