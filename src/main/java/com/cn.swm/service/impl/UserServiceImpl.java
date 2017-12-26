package com.cn.swm.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cn.swm.dao.UserMapper;
import com.cn.swm.dao.sys.MenuMapper;
import com.cn.swm.model.User;
import com.cn.swm.model.sys.Menu;
import com.cn.swm.service.UserServiceI;

@Service("userService") 
public class UserServiceImpl implements UserServiceI {
	    @Resource  
	    private UserMapper userDao;  
	    @Resource  
	    private MenuMapper menuDao;  
	    @Override  
	    public User getUserById(String userId) {  
	        // TODO Auto-generated method stub  
	        return this.userDao.getUserById(userId);  
	    }  
	    @Override  
	    public int addUser(User user) {  
	        // TODO Auto-generated method stub  
	        return this.userDao.insert(user);
	    }  
	    @Override
	    public User findUserLogin(User user){
	    	return this.userDao.findUserLogin(user);
	    }
	    @Override
	    public List<User> getUserList(User user){
	    	return this.userDao.getUserList(user);
	    }
	    @Override
	    public List<Menu> getUserMenu(String parentId,String userId){
	    	Map<String,Object> param = new HashMap<String,Object>();
			param.put("parentId", parentId);
			param.put("userId",userId);
			return menuDao.findUserMenu(param);
	    	
	    }
	    @Override
		public String getMenuResult(List<Menu> list, String path, User user) {
			StringBuilder result = new StringBuilder("");
			for(Menu menu:list){
				 result.append("<h2><span class=\"icon-caret-right\" ></span>"+menu.getName()+"</h2>") ;
				 result.append("<ul style=\"display:block\">");
				  List<Menu> secMenus = getUserMenu(menu.getId(),user.getId());
				  for(Menu menu_i:secMenus){
					result.append("<li><a href="+path+menu_i.getRequest()+" target=\"right\"><span ></span>"+menu_i.getName()+"</a></li>");
					
					}
				  result.append("</ul>");
				}
			return result.toString();
		}  
}
