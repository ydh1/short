package com.leederedu.educhat.db.mappers;


import com.leederedu.educhat.model.User;
import com.leederedu.educhat.utils.Obj;

import java.util.Map;

public interface UserMapper {

    int addUser(User user);

    User getUser(int userId);
}
