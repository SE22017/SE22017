package com.cn.swm.service.stub;

import com.cn.swm.model.activiti.ActIdUser;
import com.cn.swm.service.ActUserRoleService;


public class ActUserRoleServiceStub implements ActUserRoleService {
    @Override
    public int addActUser(ActIdUser user) {
        return 111;
    }

    @Override
    public int deleteUser(String id) {
        return 120;
    }

    @Override
    public int reUser(ActIdUser user) {
        return 343;
    }

    @Override
    public ActIdUser getActUser(String id) {
        ActIdUser aid = new ActIdUser();
        aid.setId(id);
        return aid;
    }
}
