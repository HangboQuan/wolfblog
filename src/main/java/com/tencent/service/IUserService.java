package com.tencent.service;

import com.tencent.entity.User;

import java.util.List;

public interface IUserService {
    User insertUser(User user);
    void updateUser(User user);
    void deleteUser(Integer userId);
    User getUserById(Integer userId);
    User getUserByName(String username);
    User getUserByNameOrEmail(String str);
    User getUserByEmail(String useremail);
    List<User> listUser();
}
