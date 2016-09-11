package com.leederedu.educhat.controller.api;


import com.github.pagehelper.Page;
import com.leederedu.educhat.model.TestModel;
import com.leederedu.educhat.model.api.ApiResult;
import com.leederedu.educhat.service.TestService;
import com.leederedu.educhat.utils.MyLog;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
@ResponseBody
@RequestMapping("/api/test")
public class TestApi {

	@Resource
    TestService testService;

	@RequestMapping(value = "/get")
	Object get(@ModelAttribute TestModel model, HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            List<TestModel> result = testService.selectTest(model,1,10);
            if(result.size() > 0){
                return ApiResult.success().put("testData",result);
            }else{
                return ApiResult.error("没有数据");
            }
        } catch (Exception e) {
            MyLog.error(e);
            return ApiResult.error(e.getMessage());
        }
    }
}
