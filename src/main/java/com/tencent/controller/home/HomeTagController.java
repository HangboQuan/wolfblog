package com.tencent.controller.home;

import com.github.pagehelper.PageInfo;
import com.tencent.entity.Article;
import com.tencent.entity.Tag;
import com.tencent.enums.ArticleStatus;
import com.tencent.service.IArticleService;
import com.tencent.service.ITagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.*;
import java.util.HashMap;

@Controller
public class HomeTagController {

    @Autowired
    private ITagService tagService;

    @Autowired
    private IArticleService articleService;

    @RequestMapping("/tag/{tagId}")
    public String getArticleListByTag(@PathVariable("tagId")Integer tagId,
                                      @RequestParam(required = false,defaultValue = "1")Integer pageIndex,
                                      @RequestParam(required = false,defaultValue = "10")Integer pageSize,
                                      Model model){
        Tag tag = tagService.getTagById(tagId);
        if(null == tag){
            return "redirect:/404";
        }
        model.addAttribute("tag",tag);

        //文章列表
        HashMap<String,Object> criteria = new HashMap<>(2);
        criteria.put("tagId",tagId);
        criteria.put("status", ArticleStatus.PUBLISH.getValue());
        PageInfo<Article> articlePageInfo = articleService.pageArticle(pageIndex,pageSize,criteria);
        model.addAttribute("pageInfo",articlePageInfo);

        //标签列表显示
        List<Tag> allTagList = tagService.getTagList();
        model.addAttribute("allTagList",allTagList);

        //获得随机文章
        List<Article> randomArticleList = articleService.listRandomArticle(8);
        model.addAttribute("randomArticleList",randomArticleList);

        //获得热评文章
        List<Article> mostCommentArticeList = articleService.listArticleByCommentCount(8);
        model.addAttribute("mostCommentArticleList",mostCommentArticeList);
        model.addAttribute("pageUrlPrefix","tag?pageIndex");
        return "home/page/articleListByTag";

    }
}
