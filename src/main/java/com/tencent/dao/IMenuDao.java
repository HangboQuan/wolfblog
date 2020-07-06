package com.tencent.dao;

import com.tencent.entity.Menu;
import org.apache.ibatis.annotations.Mapper;

import java.util.*;
@Mapper
public interface IMenuDao {

    void insert(Menu menu);

    void update(Menu menu);

    void delete(Integer menuId);

    Menu getMenuById(Integer menuId);

    List<Menu> listMenu();
}
