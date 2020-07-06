package com.tencent.dao;

import com.tencent.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.*;
@Mapper
public interface IUserDao {

    //String INSERT_FIELDS = ""
    int insert(User user);
    int update(User user);
    int delete(Integer userId);
    User getUserById(Integer userId);
    User getUserByName(String username);


    //@Select({"select ", SELECT_FIELDS, " from ", TABLE_NAME, " where name=#{name}"})
    User getUserByNameOrEmail(String str);
    User getUserByEmail(String useremail);
    List<User> listUser();
}
