package com.ssm.service;


import com.ssm.pojo.User;

import java.util.List;

/**
 * user表的操作接口
 *
 * @Author rxlee
 *
 */
public interface IUserService {

    /**
     * 通过id查询user的方法
     * @param userId
     * @return
     */
    public User getUserById(int userId);

    /**
     * 查询所有的user
     * @return
     */
    public List<User> getAllUser();

    /**
     * 通过关键字查询user
     * @param keyWords
     * @return
     */
    public List<User> findUsers(String keyWords);


    /**
     * 添加一个user
     * @param user
     */
    public void insertUser(User user);

    /**
     * 通过ID删除user
     * @param id
     */
    public void deleteUser(int id);
    /**
     * 更新user
     * @param user
     */
    public void editUser(User user);

}
