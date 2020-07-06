package com.tencent.controller.admin;

import com.tencent.entity.Tag;
import com.tencent.service.IArticleService;
import com.tencent.service.ITagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import java.util.*;
@Controller
@RequestMapping("/admin/tag")
public class TagController {
     @Autowired(required = false)
     private IArticleService articleService;
     @Autowired(required = false)
     private ITagService tagService;
     //后台标签页面展示
     @RequestMapping("")
     public ModelAndView index(){
         ModelAndView modelAndView = new ModelAndView();
         List<Tag> tagList = tagService.listTagWithCount();
         modelAndView.addObject("tagList",tagList);

         modelAndView.setViewName("admin/tag/index");
         return modelAndView;
     }
     //后台添加标签
    @RequestMapping(value = "/insertSubmit",method = RequestMethod.POST)
    public String insertTagSubmit(Tag tag){
         tagService.insertTag(tag);
         return "redirect:/admin/tag";
    }
    //编辑标签页
    @RequestMapping("/edit/{id}")
    public ModelAndView editTag(@PathVariable("id")Integer id){
         ModelAndView modelAndView = new ModelAndView();
         Tag tag = tagService.getTagById(id);
         modelAndView.addObject("tag",tag);

         List<Tag> tagList = tagService.listTagWithCount();
         modelAndView.addObject("tagList",tagList);

         modelAndView.setViewName("admin/tag/edit");
         return modelAndView;
    }

    @RequestMapping(value = "/editSubmit",method = RequestMethod.POST)
    public String editSubmit(Tag tag){
         tagService.updateTag(tag);
         return "redirect:/admin/tag";
    }

    //删除标签
    @RequestMapping(value = "/delete/{id}",method = RequestMethod.GET)
    public String deleteTag(@PathVariable("id")Integer id){
         System.out.println(id);
         Integer count = articleService.countArticleByTagId(id);
         System.out.println("count:"+count);
         //如果在该标签下没有任何文章的话，那么就可以删除该标签了
         if(count == 0){
             tagService.deleteById(id);
         }
         return "redirect:/admin/tag";
    }
}
