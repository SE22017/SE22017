package com.cn.swm.driver;

import com.cn.swm.model.activiti.ActIdGroup;
import com.cn.swm.model.activiti.ActIdUser;
import com.cn.swm.service.ActUserRoleService;
import com.cn.swm.service.impl.ActUserRoleServiceIml;
import org.apache.commons.io.output.CloseShieldOutputStream;

import javax.sound.midi.Soundbank;


public class ActUserRoleServiceDriver {
    ActUserRoleService actUserRoleService = new ActUserRoleServiceIml();

    public void testAddActUser(){
        ActIdUser user = new ActIdUser();
        user.setId("User1");
        user.setPwd("abcdefg");
        System.out.println(actUserRoleService.addActUser(user));
    }

    public void testDeleteUser(){
        ActIdUser user = new ActIdUser();
        String id = user.getId();
        String id2 = "name";
        if(id==id2){
            System.out.println(actUserRoleService.deleteUser(id));
        }else{
            System.out.println("null");
        }
    }

    public void testReUser(){
        ActIdUser user = new ActIdUser();
        user.setId("User1");
        user.setPwd("abcdefg");
        System.out.println(actUserRoleService.reUser(user));
    }

    public void testGetActUser(){
        ActIdUser user = new ActIdUser();
        String id = user.getId();
        System.out.println(actUserRoleService.getActUser(id));
    }
}
