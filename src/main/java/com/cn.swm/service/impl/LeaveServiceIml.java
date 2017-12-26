package com.cn.swm.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cn.swm.dao.UserMapper;
import com.cn.swm.dao.leave.TleaveMapper;
import com.cn.swm.model.leave.Tleave;
import com.cn.swm.service.leave.LeaveService;
@Service("leaveService") 
public class LeaveServiceIml implements LeaveService {
	@Resource  
	private TleaveMapper leaveDao; 
	@Override
	public int addLeave(Tleave tleave) {
		// TODO Auto-generated method stub
		return leaveDao.insert(tleave);
	}

	@Override
	public int deleteLeave(String id) {
		// TODO Auto-generated method stub
		return leaveDao.deleteLeave(id);
	}

	@Override
	public int updateLeave(Tleave tleave) {
		// TODO Auto-generated method stub
		return leaveDao.updateLeave(tleave);
	}

	@Override
	public Tleave getLeave(String id) {
		// TODO Auto-generated method stub
		return leaveDao.getLeave(id);
	}
	public Tleave getLeaveByTask(String id){
		
		return leaveDao.selectByProIntenceId(id);
	}
	public List<Tleave> leaveList(Map<String, Object> param){
		
		return leaveDao.leaveList(param);
	}
}
