package com.cn.swm.driver;

import com.cn.swm.model.activiti.ActIdGroup;
import com.cn.swm.service.ActIdGroupService;
import com.cn.swm.service.impl.ActIdGroupServiceIml;


public class ActIdGroupSericeDriver {
    ActIdGroupService actIdGroupService = new ActIdGroupServiceIml();

    public void testAddActGroup(){
        ActIdGroup group = new ActIdGroup();
        group.setId("G1");
        group.setName("adb");
        System.out.println(actIdGroupService.addActGroup(group));
    }

    public void testDeleteGroup(){
        String id = "Type1";
        ActIdGroup group = new ActIdGroup();
        group.setId(id);
        System.out.println(actIdGroupService.deleteGroup(id));
    }

    public void testReGroup(){
        ActIdGroup group = new ActIdGroup();
        group.setId("G1");
        group.setName("WAHAHA");
        System.out.println(actIdGroupService.reGroup(group));
    }

    public void testGetActGroup(){
        ActIdGroup group = new ActIdGroup();
        String groupid = group.getId();
        if(groupid==null){
            System.out.println("null");
        }else{
            System.out.println(actIdGroupService.getActGroup(groupid));
        }
    }
}
