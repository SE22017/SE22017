package com.cn.swm.driver;

import com.cn.swm.model.User;
import com.cn.swm.model.sys.Menu;
import com.cn.swm.service.UserServiceI;
import com.cn.swm.service.impl.UserServiceImpl;

import java.util.List;


public class UserServiceDriver {
    UserServiceI userServiceI = new UserServiceImpl();

    public void testGetUserById(){
        User user = new User();
        user.setId("ADB");
        user.getId();
        System.out.println(userServiceI.getUserById(user.getId()));
    }

    public void testaddUser(){
        User user = new User();
        user.setId("ADB");
        user.getId();
        System.out.println(userServiceI.addUser(user));
    }

    public void testFindUserLogin(){
        User user = new User();
        user.setId("ADB");
        user.getId();
        user.getPassWd();
        user.getActiveFlag();
        user.getUpBy();
        user.getEmail();
        System.out.println(userServiceI.findUserLogin(user));
    }

    public void testGetUserList(){
        User user = new User();
        user.getId();
        user.getPassWd();
        user.getActiveFlag();
        user.getUpBy();
        user.getEmail();
        System.out.println(userServiceI.getUserList(user));
    }

    public void testGetUserMenu(){
        User record = new User();
        record.getId();
        record.getAddDate();
        record.setEmail("Email");
        System.out.println(userServiceI.getUserMenu("parentid",record.getId()));
    }

    public void testGetMenuResult(){
        List<Menu> menulist = null;
        Menu m1 = new Menu();
        Menu m2 = new Menu();
        User u = new User();
        u.setId("ID");
        u.setName("a");
        String path = u.getAccount();
        menulist.add(m1);
        menulist.add(m2);
        System.out.println(userServiceI.getMenuResult(menulist,path,u));
    }
}
