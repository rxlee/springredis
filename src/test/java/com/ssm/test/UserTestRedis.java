package com.ssm.test;

import com.ssm.pojo.User;
import com.ssm.service.IUserService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import java.util.List;

public class UserTestRedis extends BaseJunit4Test {
    @Autowired
    private IUserService userService;

    @Test
    public void add() {
        User user = new User();
        user.setAge(22);
        user.setUserName("张三");
        user.setSex("1");
        userService.insertUser(user);
    }

    @Test
    @Rollback(false)
    public void query() {
        List<User> allUser = userService.getAllUser();
        for (User user : allUser) {
            System.out.println(user.toString());
        }
    }

}
