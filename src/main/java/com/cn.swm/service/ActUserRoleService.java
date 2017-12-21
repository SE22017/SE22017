package com.cn.swm.service;

import com.cn.swm.model.activiti.ActIdUser;

public interface ActUserRoleService {
	public int addActUser(ActIdUser user);
	public int deleteUser(String  id);
	public int reUser(ActIdUser user);
	public ActIdUser getActUser(String id);
}
