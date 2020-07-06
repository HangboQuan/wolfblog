package com.tencent.controller.admin;

import cn.hutool.http.HtmlUtil;
import com.github.pagehelper.PageInfo;
import com.tencent.dto.ArticleParam;
import com.tencent.entity.Article;
import com.tencent.entity.Category;
import com.tencent.entity.Tag;
import com.tencent.entity.User;
import com.tencent.service.IArticleService;
import com.tencent.service.ICategoryService;
import com.tencent.service.ITagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.*;

import java.util.HashMap;
@Controller
@RequestMapping("/admin/article")
public class ArticleController {

    @Autowired(required = false)
    private IArticleService articleService;
    @Autowired(required = false)
    private ITagService tagService;
    @Autowired(required = false)
    private ICategoryService categoryService;

    //后台文章列表显示
    @RequestMapping("")
    public String index(@RequestParam(required = false,defaultValue = "1")Integer pageIndex,
                        @RequestParam(required = false,defaultValue = "10")Integer pageSize,
                        @RequestParam(required = false) String status, Model model){
        HashMap<String, Object> criteria = new HashMap<>(1);
        if(null == status){
            model.addAttribute("pageUrlPrefix","/admin/article?pageIndex");
        }else{
            criteria.put("status",status);
            model.addAttribute("pageUrlPrefix","/admin/article?status="+status+"&pageIndex");
        }
        PageInfo<Article> articlePageInfo = articleService.pageArticle(pageIndex,pageSize,criteria);
        model.addAttribute("pageInfo",articlePageInfo);
        return "admin/article/index";
    }

    //添加文章
    @RequestMapping("/insert")
    public String insertArticleView(Model model){
        List<Category> categoryList = categoryService.listCategory();
        List<Tag> tagList = tagService.getTagList();
        model.addAttribute("categoryList",categoryList);
        model.addAttribute("tagList",tagList);
        return "admin/article/insert";
    }

    @RequestMapping(value = "/insertSubmit",method = RequestMethod.POST)
    public String insertSubmitView(HttpSession session, ArticleParam articleParam){
        Article article = new Article();
        //用户Id
        User user = (User)session.getAttribute("user");
        if(null != user){
            article.setArticleUserId(user.getUserId());
        }
        article.setArticleTitle(articleParam.getArticleTitle());
        //文章摘要
        int summaryLength = 150;
        //这里是过滤html中的tag标签
        String summaryText = HtmlUtil.cleanHtmlTag(articleParam.getArticleContent());
        if(summaryText.length()>summaryLength){
            String summary = summaryText.substring(0,summaryLength);
            article.setArticleSummary(summary);
        }else{
            article.setArticleSummary(summaryText);
        }
        article.setArticleContent(articleParam.getArticleContent());
        article.setArticleStatus(articleParam.getArticleStatus());
        //填充分类
        List<Category> categoryList = new ArrayList<>();
        if(articleParam.getArticleChildCategoryId() != null){
            categoryList.add(new Category(articleParam.getArticleParentCategoryId()));
        }
        if(articleParam.getArticleChildCategoryId() != null){
            categoryList.add(new Category(articleParam.getArticleChildCategoryId()));
        }
        article.setCategoryList(categoryList);

        //填充标签
        List<Tag> tagList = new ArrayList<>();
        if(articleParam.getArticleTagIds()!=null){
            for(int i = 0; i < articleParam.getArticleTagIds().size(); i++){
                Tag tag = new Tag(articleParam.getArticleTagIds().get(i));
                tagList.add(tag);
            }
        }
        article.setTagList(tagList);

        articleService.insertArticle(article);
        return "redirect:/admin/article";
    }

    //删除文章
    @RequestMapping(value = "/delete/{id}")
    public void deleteArticle(@PathVariable("id")Integer id){
        articleService.deleteArticle(id);
    }

    //编辑文章页面显示
    @RequestMapping("/edit/{id}")
    public ModelAndView editArticleView(@PathVariable("id")Integer id){
        ModelAndView modelAndView = new ModelAndView();
        Article article = articleService.getArticleByStatusAndId(null,id);
        modelAndView.addObject("article",article);

        List<Category> categoryList = categoryService.listCategory();
        modelAndView.addObject("categoryList",categoryList);

        List<Tag> tagList = tagService.getTagList();
        modelAndView.addObject("tagList",tagList);

        modelAndView.setViewName("admin/article/edit");
        return modelAndView;
    }

    @RequestMapping(value = "/editSubmit",method = RequestMethod.POST)
    public String editArticleSubmit(ArticleParam articleParam){
        Article article = new Article();
        article.setArticleId(articleParam.getArticleId());
        article.setArticleTitle(articleParam.getArticleTitle());
        article.setArticleContent(articleParam.getArticleContent());
        article.setArticleStatus(articleParam.getArticleStatus());
        //文章摘要
        int summaryLength = 150;
        String summaryText = HtmlUtil.cleanHtmlTag(article.getArticleContent());
        if(summaryText.length() > summaryLength){
            String summary = summaryText.substring(0,summaryLength);
            article.setArticleSummary(summary);
        }else{
            article.setArticleSummary(summaryText);
        }

        List<Category> categoryList = new ArrayList<>();
        if(null != articleParam.getArticleChildCategoryId()){
            categoryList.add(new Category(articleParam.getArticleParentCategoryId()));

        }
        if(null != articleParam.getArticleChildCategoryId()){
            categoryList.add(new Category(articleParam.getArticleChildCategoryId()));
        }

        article.setCategoryList(categoryList);

        List<Tag> tagList = new ArrayList<>();
        if(null != articleParam.getArticleTagIds()){
            for(int i = 0; i < articleParam.getArticleTagIds().size(); i++){
                Tag tag  = new Tag(articleParam.getArticleTagIds().get(i));
                tagList.add(tag);
            }
        }
        article.setTagList(tagList);
        articleService.updateArticleDetail(article);
        return "redirect:/admin/article";

    }

}
