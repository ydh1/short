package com.leederedu.educhat.service;

import com.github.pagehelper.PageHelper;
import com.leederedu.educhat.db.mappers.TestMapper;
import com.leederedu.educhat.model.TestModel;
import com.leederedu.educhat.utils.MyLog;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class TestService {

    @Resource
    TestMapper testMapper;

    public int addTest(TestModel test){
        return testMapper.testAdd(test);
    }

    public List<TestModel> selectTest(TestModel test,int page,int pageSize){
        PageHelper.startPage(page,pageSize);
        return testMapper.testSelect(test);
    }

    public TestModel getById(int id){
        return  testMapper.getById(id);
    }

    public  String func(){
        String result = null;
        try{


        }catch (Exception e){
            MyLog.error(e);
        }
        return result;
    }
}
