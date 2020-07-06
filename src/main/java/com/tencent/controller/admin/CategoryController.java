package com.tencent.controller.admin;

import com.tencent.entity.Category;
import com.tencent.service.IArticleService;
import com.tencent.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import java.util.*;

@Controller
@RequestMapping("/admin/category")
public class CategoryController {
    @Autowired
    private IArticleService articleService;
    @Autowired
    private ICategoryService categoryService;

    /**
     * 后台列表分类展示
     * @return
     */
    @RequestMapping("")
    public ModelAndView categoryList(){
        ModelAndView modelAndView = new ModelAndView();
        List<Category> categoryList = categoryService.listCategoryWithCount();
        modelAndView.addObject("categoryList",categoryList);
        modelAndView.setViewName("admin/category/index");
        return modelAndView;
    }

    @RequestMapping("/insertSubmit")
    public String insertCategorySubmit(Category category){
        categoryService.insertCategory(category);
        //注意:这里的请求路径，/admin/category表示的是：localhost:8888/admin/category;
        return "redirect:/admin/category";
    }

    //删除分类,@PathVariable的作用是接受请求路径占位符的值
    @RequestMapping("/delete/{id}")
    public String deleteCategory(@PathVariable("id")Integer id){
        System.out.println(id);
        //根据分类id统计文章数目
        int count = articleService.countArticleByCategoryId(id);
        System.out.println(count);
        //不删除有文章的分类
        if(count == 0){
            categoryService.deleteCategory(id);
        }
        return "redirect:/admin/category";
    }
    //编辑
    @RequestMapping("/edit/{id}")
    public ModelAndView editCategoryView(@PathVariable("id") Integer id){
        ModelAndView modelAndView = new ModelAndView();
        Category category = categoryService.getCategoryById(id);
        modelAndView.addObject("category",category);

        List<Category> categoryList = categoryService.listCategoryWithCount();
        modelAndView.addObject("categoryList",categoryList);
        modelAndView.setViewName("/admin/category/edit");
        return modelAndView;
    }
    //编辑后提交
    @RequestMapping(value = "/editSubmit",method = RequestMethod.POST)
    public String editCategorySubmit(Category category){
        categoryService.udpateCategory(category);
        return "redirect:/admin/category";
    }
}
