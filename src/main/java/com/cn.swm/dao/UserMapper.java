package com.cn.swm.dao;

import java.util.List;

import com.cn.swm.model.User;
import com.cn.swm.model.UserNum;


public interface UserMapper {
    int insert(User record);
    int insertSelective(User record);
    User getUserById(String name);
    User findUserLogin(User user);
    List<User> getUserList(User user);
    List<UserNum> getUserNum();
}