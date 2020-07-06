package com.tencent.controller.admin;

import com.tencent.entity.Menu;
import com.tencent.enums.MenuLevel;
import com.tencent.service.IMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import java.util.*;
@Controller
@RequestMapping("/admin/menu")
public class MenuController {

    @Autowired(required = false)
    private IMenuService menuService;

    @RequestMapping("")
    public String menuList(){
        ModelAndView modelAndView = new ModelAndView();
        List<Menu> menuList = menuService.listMenu();
        modelAndView.addObject("menuList",menuList);
        return "admin/menu/index";
    }

    @RequestMapping(value = "/insertSubmit",method = RequestMethod.POST)
    public String insertMenuSubmit(Menu menu){
        if(menu.getMenuOrder()==null){
            menu.setMenuOrder(MenuLevel.TOP_MENU.getValue());
        }
        menuService.insertMenu(menu);
        return "redirect:/admin/menu";
    }

    @RequestMapping("/edit/{id}")
    public ModelAndView editMenuView(@PathVariable("id")Integer id){
        ModelAndView modelAndView = new ModelAndView();
        Menu menu = menuService.getMenuById(id);
        modelAndView.addObject("menu",menu);

        List<Menu> menuList = menuService.listMenu();
        modelAndView.addObject("menuList",menuList);
        modelAndView.setViewName("/admin/menu/edit");
        return modelAndView;
    }

    @RequestMapping(value = "/editSubmit",method = RequestMethod.POST)
    public String editMenuSubmit(Menu menu){
        menuService.updateMenu(menu);
        return "redirect:/admin/menu";
    }

    @RequestMapping("/delete/{id}")
    public String deleteMenu(@PathVariable("id") Integer id){
        menuService.deleteMenuById(id);
        return "redirect:/admin/menu";
    }

}
