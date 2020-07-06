package com.tencent.service.impl;

import com.tencent.dao.IMenuDao;
import com.tencent.entity.Menu;
import com.tencent.service.IMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service("menuService")
public class MenuServiceImpl implements IMenuService {

    @Autowired(required = false)
    private IMenuDao menuDao;

    @Override
    public void insertMenu(Menu menu) {
        menuDao.insert(menu);
    }

    @Override
    public void updateMenu(Menu menu) {
        menuDao.update(menu);
    }

    @Override
    public void deleteMenuById(Integer menuId) {
        menuDao.delete(menuId);
    }

    @Override
    public Menu getMenuById(Integer menuId) {
        return menuDao.getMenuById(menuId);
    }

    @Override
    public List<Menu> listMenu() {
        return menuDao.listMenu();
    }
}
