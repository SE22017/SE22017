package com.cn.swm.service;

import com.cn.swm.model.activiti.ActIdGroup;

public interface ActIdGroupService {
	public int addActGroup(ActIdGroup group);
	public int deleteGroup(String  id);
	public int reGroup(ActIdGroup group);
	public ActIdGroup getActGroup(String id);
}
