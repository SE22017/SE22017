package com.cn.swm.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cn.swm.dao.activiti.ActIdUserMapper;
import com.cn.swm.model.activiti.ActIdUser;
import com.cn.swm.service.ActUserRoleService;
@Service("actUserService") 
public class ActUserRoleServiceIml implements ActUserRoleService {
	    @Resource  
	    private ActIdUserMapper actUserDao;
	    @Override
	    public int addActUser(ActIdUser user){
	    	
	    	return this.actUserDao.addActUser(user) ;
	    }
	    @Override
		public int deleteUser(String  id){
	    	return this.actUserDao.deleteUser(id);
	    }
	    @Override
		public int reUser(ActIdUser user){
	    	return this.actUserDao.reUser(user);
	    }
	    @Override
		public ActIdUser getActUser(String id){
	    	return this.actUserDao.getActUser(id);
	    }
}
