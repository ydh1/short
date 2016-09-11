package com.leederedu.educhat.service;

import com.leederedu.educhat.db.mappers.UserMapper;
import com.leederedu.educhat.frame.GKSetting;
import com.leederedu.educhat.frame.annotation.ApiLimit;
import com.leederedu.educhat.model.User;
import com.leederedu.educhat.utils.Obj;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserService {

    @Resource
    UserMapper userMapper;

    public int addUser(User user){
        return  userMapper.addUser(user);
    }

    @ApiLimit(limit = "login")
    public User getUser(int userId){
        return userMapper.getUser(userId);
    }

}
