package com.ssm.serviceImpl;

import com.ssm.dao.UserMapper;
import com.ssm.pojo.User;
import com.ssm.service.IUserService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;


/**
 *
 * UserService
 * 缓存机制说明：所有的查询结果都放进了缓存，也就是把MySQL查询的结果放到了redis中去，
 * 然后第二次发起该条查询时就可以从redis中去读取查询的结果，从而不与MySQL交互，从而达到优化的效果，
 * redis的查询速度之于MySQL的查询速度相当于 内存读写速度 /硬盘读写速度
 * @Cacheable("a")注解的意义就是把该方法的查询结果放到redis中去，下一次再发起查询就去redis中去取，存在redis中的数据的key就是a；
 * @CacheEvict(value={"a","b"},allEntries=true) 的意思就是执行该方法后要清除redis中key名称为a,b的数据；
 */
@Service("userService")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class UserServiceImpl implements IUserService {

    @Resource
    private UserMapper iUserDao;

    @Cacheable("getUserById")
    @Override
    public User getUserById(int userId) {
        return iUserDao.selectByPrimaryKey(userId);
    }

    @Cacheable("getAllUser")
    @Override
    public List<User> getAllUser() {
        return iUserDao.selectAllUser();
    }

    @Cacheable("findUsers")
    @Override
    public List<User> findUsers(String keyWords) {
        return iUserDao.findUsers(keyWords);
    }


    //清空缓存，变量allEntries=true表示所有对象的缓存都清除
    @CacheEvict(value = {"getAllUser", "getUserById", "findUsers"}, allEntries = true)
    @Override
    public void insertUser(User user) {
        iUserDao.insertUser(user);
    }

    @CacheEvict(value = {"getAllUser", "getUserById", "findUsers"}, allEntries = true)
    @Override
    public void deleteUser(int id) {
        iUserDao.deleteUser(id);
    }

    @CacheEvict(value = {"getAllUser", "getUserById", "findUsers"}, allEntries = true)
    @Override
    public void editUser(User user) {
        iUserDao.editUser(user);
    }
}
