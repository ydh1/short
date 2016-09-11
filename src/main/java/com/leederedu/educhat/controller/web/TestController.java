package com.leederedu.educhat.controller.web;

import com.leederedu.educhat.frame.annotation.ApiLimit;
import com.leederedu.educhat.model.TestModel;
import com.leederedu.educhat.service.TestService;
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
@RequestMapping("/test")
public class TestController {

    @Resource
    TestService testService;

    // 获取页面
    @RequestMapping(value = "/add",method = RequestMethod.GET)
    String testAddPage(HttpServletRequest request, HttpServletResponse response){
        return "/test/add";
    }

    // 提交
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    String testAdd(@ModelAttribute TestModel model, HttpServletRequest request, HttpServletResponse response) throws IOException{
        int result = 0;
        if(model.getTextField() != null && model.getIntField() != 0){
            result  = testService.addTest(model);
        }
        if(result > 0){
            request.setAttribute("test",model);
            return "/test/show";
        }else{
            request.setAttribute("msg","数据不能为空");
            return "/common/error";
        }
    }

    // 显示
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    String testShow(@PathVariable int id, HttpServletRequest request, HttpServletResponse response) throws IOException{
        TestModel result = testService.getById(id);
        if(result != null){
            request.setAttribute("test",result);
            return "/test/show";
        }else{
            request.setAttribute("msg","没找到!");
            return "/common/error";
        }
    }
}
