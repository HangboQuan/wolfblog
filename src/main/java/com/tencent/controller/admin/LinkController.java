package com.tencent.controller.admin;

import com.tencent.entity.Link;
import com.tencent.service.ILinkService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;

@Controller
@RequestMapping("/admin/link")
@Slf4j
public class LinkController {

    @Autowired
    private ILinkService linkService;

    //后台链接显示
    @RequestMapping("")
    public ModelAndView linkList(){
        ModelAndView modelAndView = new ModelAndView();
        List<Link> linkList = linkService.listLink(null);
        modelAndView.addObject("linkList",linkList);
        modelAndView.setViewName("admin/link/index");
        return modelAndView;
    }

    @RequestMapping("/insert")
    public ModelAndView insert(){
        ModelAndView modelAndView = new ModelAndView();
        List<Link> linkList = linkService.listLink(null);

        modelAndView.addObject("linkList",linkList);
        modelAndView.setViewName("admin/link/insert");
        return modelAndView;
    }

    @RequestMapping(value = "/insertSubmit",method = RequestMethod.POST)
    public String insertSubmit(Link link){
        link.setLinkCreateTime(new Date());
        link.setLinkUpdateTime(new Date());
        link.setLinkStatus(1);
        linkService.insertLink(link);
        return "redirect:/admin/link/insert";
    }

    @RequestMapping("/edit/{id}")
    public ModelAndView editLinkView(@PathVariable("id")Integer id){
        ModelAndView modelAndView = new ModelAndView();
        Link linkCustom = linkService.getLinkById(id);

        modelAndView.addObject("linkCustom",linkCustom);
        List<Link> linkList = linkService.listLink(null);
        modelAndView.addObject("linkList",linkList);
        modelAndView.setViewName("admin/link/edit");

        return modelAndView;
    }

    @RequestMapping(value = "/editSubmit",method = RequestMethod.POST)
    public String editSubmit(Link link){
        link.setLinkUpdateTime(new Date());
        linkService.updateLink(link);
        return "redirect:/admin/link";
    }

    @RequestMapping("/delete/{id}")
    public String deleteLink(@PathVariable("id")Integer id){
        linkService.deleteLink(id);
        return "redirect:/admin/link";
    }

}

