package com.tencent.controller.admin;

import com.tencent.entity.Page;
import com.tencent.service.IPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import java.util.*;
@Controller
@RequestMapping("/admin/page")
public class PageController {

    @Autowired(required = false)
    private IPageService pageService;

    @RequestMapping("")
    public ModelAndView index(){
        ModelAndView modelAndView = new ModelAndView();
        List<Page> pageList = pageService.listPage(null);
        modelAndView.addObject("pageList",pageList);
        modelAndView.setViewName("admin/page/index");
        return modelAndView;
    }

    @RequestMapping("/insert")
    public ModelAndView insertPageView(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("admin/page/insert");
        return modelAndView;
    }

    @RequestMapping(value = "/insertSubmit",method = RequestMethod.POST)
    public String insertPageSubmit(Page page){
        Page checkPage = pageService.getPageByKey(null,page.getPageKey());
        if(checkPage == null){
            page.setPageCreateTime(new Date());
            page.setPageUpdateTime(new Date());
            //这里的参数没写
            page.setPageStatus(null);
            pageService.insertPage(page);
        }
        return "redirect:/admin/page";
    }

    @RequestMapping("/edit/{id}")
    public ModelAndView editPageView(@PathVariable("id")Integer id){
        ModelAndView modelAndView = new ModelAndView();
        Page page = pageService.getPageById(id);
        modelAndView.addObject("page",page);

        modelAndView.setViewName("admin/page/edit");
        return modelAndView;
    }

    //编辑页面提交
    @RequestMapping(value = "/editSubmit",method = RequestMethod.POST)
    public String editPageSubmit(Page page){
        Page checkPage = pageService.getPageByKey(null,page.getPageKey());
        //判断别名是否存在且不是这篇文章
        if(Objects.equals(checkPage.getPageId(),page.getPageId())){
            page.setPageUpdateTime(new Date());
            pageService.updatePage(page);
        }
        return "redirect:/admin/page";
    }

    @RequestMapping("/delete/{id}")
    public String deletePage(@PathVariable("id")Integer id){
        //调用service批量删除
        pageService.deletePage(id);
        return "redirect:/admin/page";
    }
}
