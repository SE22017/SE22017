package com.cn.swm.service.sys;

import java.util.List;
import java.util.Map;

import com.cn.swm.model.User;
import com.cn.swm.model.UserNum;
import com.cn.swm.model.sys.Menu;
import com.cn.swm.model.sys.Role;
import com.cn.swm.model.sys.Tcategory;


public interface SysServiceI {

	public List<Menu> findAll(Menu menu);
	public List<Map<String, Object>> listToMap(List<Menu> list);
	public int addMenu(Menu menu);
	public int update (Menu menu);
	public List<Role> findAll(Role role);
	public List<Menu> findAll(String roleid);
	public List<Menu> findAllByRole(String roleId);
	public boolean delRoleMenu(Role role);
	public boolean addRoleMenu(Role role, String[] menuIds);
	public List<Role> findAllu(String userId);
	public List<Map<String, Object>> listToMapu(List<Role> list);
	public List<Role> findAllByUser(String userId);
	public boolean delUserRole(User user);
	public boolean addUserRole(User user, String[] roleIds);
	public int addRole(Role role);
	public Menu getMenu(Menu menu);
	public List<Role> findRoleByMenu(String menuId);
	public int delMenu(String menuId);
	public List<UserNum> getUserNum();
	public List<Tcategory> findAll(Tcategory tcategory);
	public List<Map<String, Object>> listToMapCate(List<Tcategory> list);
	public int updateCate(Tcategory tcategory);
	public int addCategory(Tcategory tcategory);
	Tcategory getCate(Tcategory tcategory);
	int delCate(String CateId);
}
