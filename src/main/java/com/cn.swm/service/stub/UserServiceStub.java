package com.cn.swm.service.stub;

import com.cn.swm.model.User;
import com.cn.swm.model.sys.Menu;
import com.cn.swm.service.UserServiceI;

import javax.swing.event.MenuListener;
import java.util.List;

public class UserServiceStub implements UserServiceI {
    @Override
    public User getUserById(String id) {
        User user = new User();
        user.setId(id);
        return user;
    }

    @Override
    public int addUser(User record) {
        return 233;
    }

    @Override
    public User findUserLogin(User user) {
        User newuser = new User();
        String userid = user.getId();
        String newid = newuser.getId();
        if(userid==newid){
            return user;
        }else{
            return user;
        }
    }

    @Override
    public List<User> getUserList(User user) {
        List<User> userlist = null;
        userlist.add(user);
        return userlist;
    }

    @Override
    public List<Menu> getUserMenu(String parentId, String userId) {
        List<Menu> menulist = null;
        Menu menu1 = new Menu();
        menu1.setParentId(parentId);
        menulist.add(menu1);
        return menulist;
    }

    @Override
    public String getMenuResult(List<Menu> list, String path, User user) {
        String password = user.getPassWd();
        String parents = path;
        if(password==parents){
            return password;
        }
        else{
            return parents;
        }
    }
}
