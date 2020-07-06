package com.tencent.service.impl;

import com.tencent.dao.IArticleDao;
import com.tencent.dao.IUserDao;
import com.tencent.entity.User;
import com.tencent.service.IUserService;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("userService")
public class UserServiceImpl implements IUserService {
    @Autowired(required = false)//required的作用是，默认是true必须有bean存在才能被注入,如果没有bean则自动跳过
    private IUserDao userDao;

    @Autowired(required = false)
    private IArticleDao articleDao;

    @Override
    public User insertUser(User user) {
        user.setUserRegisterTime(new Date());
        userDao.insert(user);
        return user;
    }

    @Override
    public void updateUser(User user) {
        userDao.update(user);
    }

    @Override
    public void deleteUser(Integer userId) {
        userDao.delete(userId);
    }

    @Override
    public User getUserById(Integer userId) {
        return userDao.getUserById(userId);
    }

    @Override
    public User getUserByName(String username) {
        return userDao.getUserByName(username);
    }

    @Override
    public User getUserByNameOrEmail(String str) {
        return userDao.getUserByNameOrEmail(str);
    }

    @Override
    public User getUserByEmail(String useremail) {
        return userDao.getUserByEmail(useremail);
    }

    @Override
    public List<User> listUser() {
        List<User> userList = userDao.listUser();
        for (int i = 0; i < userList.size(); i++) {
            Integer articleCount = articleDao.countArticleByUser(userList.get(i).getUserId());
            userList.get(i).setArticleCount(articleCount);
        }
        return userList;
    }
}
