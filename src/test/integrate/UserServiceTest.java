package test.integrate;

import com.cn.swm.model.User;
import com.cn.swm.service.UserServiceI;
import org.junit.Assert;
import org.junit.Test;
import test.BaseTest;

import javax.annotation.Resource;

/**
 * Created by CZB on 2018/1/13.
 */
public class UserServiceTest extends BaseTest {
    @Resource
    UserServiceI userServiceI;

    @Test
    public void testGetUserById(){
        Assert.assertEquals("name",userServiceI.getUserById("name"));
    }

    @Test
    public void testAddUser(){
        User user = new User();
        user.setName("name");
        Assert.assertEquals(user,userServiceI.addUser(new User()));
    }
    @Test
    public void testFindUserLogin(){
        User user = new User();
        user.setName("name");
        user.getId();
        Assert.assertEquals(user,userServiceI.findUserLogin(new User()));
    }

    @Test
    public void testGetUserList(){
        User user = new User();
        user.setName("name");
        user.getId();
        Assert.assertEquals(user,userServiceI.getUserList(new User()));
    }

    @Test
    public void testGetUserMenu(){

    }

    @Test
    public void testGetMenuResult(){

    }
}
