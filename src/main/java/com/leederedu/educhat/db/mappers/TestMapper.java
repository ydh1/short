package com.leederedu.educhat.db.mappers;


import com.leederedu.educhat.model.TestModel;

import java.util.List;

public interface TestMapper {
    int testAdd(TestModel model);
    List<TestModel> testSelect(TestModel model);
    TestModel getById(int id);
}
