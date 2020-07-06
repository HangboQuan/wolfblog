package com.tencent.service;

import com.tencent.entity.Menu;
import java.util.*;
public interface IMenuService {

    void insertMenu(Menu menu);

    void updateMenu(Menu menu);

    void deleteMenuById(Integer menuId);

    Menu getMenuById(Integer menuId);

    List<Menu> listMenu();

}
