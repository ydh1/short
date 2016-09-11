package com.leederedu.educhat.controller.web;

import com.leederedu.educhat.model.User;
import com.leederedu.educhat.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping("/user")
public class UserController {

    @Resource
    UserService userService;

    @RequestMapping(value = "/signup",method = RequestMethod.GET)
    public String signup(HttpServletRequest request, HttpServletResponse response) throws IOException{
        return  "user/signup";
    }

    @RequestMapping(value = "/signup",method = RequestMethod.POST)
    public String signupSubmmit(@ModelAttribute User user, HttpServletRequest request, HttpServletResponse response) throws IOException{
        // String phone = request.getParameter("phone");
        if(userService.addUser(user) > 0){
            request.setAttribute("msg","成功");
            return "forward:user/"+user.getUserId();
        }else{
            request.setAttribute("msg","失败");
            return "/common/error";
        }
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public String show(@PathVariable int id, HttpServletRequest request, HttpServletResponse response) throws IOException{
        User user = userService.getUser(id);
        if(user != null){
            request.setAttribute("user",user);
            return "user/show";
        }
        request.setAttribute("msg","没找到...");
        return "/common/error";
    }
}
