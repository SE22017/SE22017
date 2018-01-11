package com.cn.swm.service.stub;

import com.cn.swm.model.activiti.ActIdGroup;
import com.cn.swm.service.ActIdGroupService;


public class ActIdGroupServiceStub implements ActIdGroupService {
    @Override
    public int addActGroup(ActIdGroup group) {
        return 234;
    }

    @Override
    public int deleteGroup(String id) {
        return 222;
    }

    @Override
    public int reGroup(ActIdGroup group) {
        return 111;
    }

    @Override
    public ActIdGroup getActGroup(String id) {
        ActIdGroup ac = null;
        ac.setId(id);
        return ac;
    }
}
