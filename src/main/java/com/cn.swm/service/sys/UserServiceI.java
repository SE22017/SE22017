package com.cn.swm.service;

import java.util.List;

import com.cn.swm.model.User;
import com.cn.swm.model.sys.Menu;

public interface UserServiceI {
	public User getUserById(String id);
	int addUser(User record);
	public User findUserLogin(User user);
	public List<User> getUserList(User user);
	public List<Menu> getUserMenu(String parentId,String userId);
	public String getMenuResult(List<Menu> list, String path, User user);
}
