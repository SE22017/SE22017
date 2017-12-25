package com.cn.swm.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cn.swm.dao.activiti.ActIdGroupMapper;
import com.cn.swm.model.activiti.ActIdGroup;
import com.cn.swm.service.ActIdGroupService;
@Service("actIdGroupService") 
public class ActIdGroupServiceIml implements ActIdGroupService {
	    @Resource  
	    private ActIdGroupMapper actGroupDao;
	    @Override
	    public int addActGroup(ActIdGroup group){
	    	
	    	return this.actGroupDao.addActGroup(group) ;
	    }
	    @Override
		public int deleteGroup(String  id){
	    	return this.actGroupDao.deleteGroup(id);
	    }
	    @Override
		public int reGroup(ActIdGroup group){
	    	return this.actGroupDao.reUser(group);
	    }
	    @Override
		public ActIdGroup getActGroup(String id){
	    	return this.actGroupDao.getActGroup(id);
	    }
}
