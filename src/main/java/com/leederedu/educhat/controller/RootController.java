package com.leederedu.educhat.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping("/")
public class RootController {

    @RequestMapping(value = "error/{msg}")
    public String error(@PathVariable String msg, HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setAttribute("msg", msg);
        return "/common/error";
    }


    @RequestMapping(value = "exception")
    public String exception(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setAttribute("msg", "exception");
        return "/common/error";
    }
}
