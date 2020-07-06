package com.tencent.controller.home;

import com.alibaba.fastjson.JSON;
import com.tencent.entity.Article;
import com.tencent.entity.Comment;
import com.tencent.entity.Tag;
import com.tencent.entity.User;
import com.tencent.enums.ArticleStatus;
import com.tencent.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;
@Controller
public class HomeArticleController {

    @Autowired(required = false)
    private IArticleService articleService;

    @Autowired(required = false)
    private ICommentService commentService;

    @Autowired(required = false)
    private IUserService userService;

    @Autowired(required = false)
    private ITagService tagService;

    @Autowired(required = false)
    private ICategoryService categoryService;


    @RequestMapping(value = "article/{articleId}")
    public String getArticleDetailPage(@PathVariable("articleId")Integer articleId, Model model){
        //文章信息，分类，标签，作者，评论 表示已发布
        Article article = articleService.getArticleByStatusAndId(ArticleStatus.PUBLISH.getValue(),articleId);
        if(null == article){
            return "home/error/404";
        }

        //用户信息
        User user = userService.getUserById(article.getArticleUserId());
        article.setUser(user);

        //文章信息
        model.addAttribute("article",article);

        //评论信息
        List<Comment> commentList = commentService.listCommentByArticleId(articleId);
        model.addAttribute("commentList",commentList);

        //相关文章
        List<Integer> categoryIds = articleService.listCategoryByArticleId(articleId);
        List<Article> similarArticleList = articleService.listArticleByCategoryIds(categoryIds,5);
        model.addAttribute("similarArticleList",similarArticleList);

        //猜你喜欢
        List<Article> mostViewArticleList = articleService.listArticleByViewCount(5);
        model.addAttribute("mostViewArticleList",mostViewArticleList);

        //获取上一篇文章
        Article preArticle = articleService.getPreArticle(articleId);
        model.addAttribute("preArticle",preArticle);
        //获取下一篇文章
        Article afterArticle = articleService.getAfterArticle(articleId);
        model.addAttribute("afterArticle",afterArticle);

        //标签列表显示
        List<Tag> allTagList = tagService.getTagList();
        model.addAttribute("allTagList",allTagList);

        //获得随机文章
        List<Article> randomArticleList = articleService.listRandomArticle(8);
        model.addAttribute("randomArticleList",randomArticleList);

        //获得热评文章
        List<Article> mostCommentArticleList = articleService.listArticleByCommentCount(8);
        model.addAttribute("mostCommentArticleList",mostCommentArticleList);

        String sortNumer = "O";
        model.addAttribute("num", sortNumer);

        String randNumber = "R";
        model.addAttribute("random", randNumber);

        int articleIds = article.getArticleId();
        System.out.println(articleIds);
        model.addAttribute("articleId",articleIds);


        return "home/page/articleDetail";
    }

    @RequestMapping(value = "/article/like/{id}",method = {RequestMethod.POST})
    @ResponseBody
    public String increaseLikeCount(@PathVariable("id")Integer id){
        Article article = articleService.getArticleByStatusAndId(ArticleStatus.PUBLISH.getValue(),id);
        Integer articleCount = article.getArticleLikeCount()+1;
        article.setArticleLikeCount(articleCount);
        articleService.updateArticle(article);

        return JSON.toJSONString(articleCount);
    }

    //文章访问量增加
    @RequestMapping(value = "/article/view/{id}",method = {RequestMethod.POST})
    @ResponseBody
    public String increaseViewCount(@PathVariable("id")Integer id){
        Article article = articleService.getArticleByStatusAndId(ArticleStatus.PUBLISH.getValue(),id);
        Integer articleCount = article.getArticleViewCount()+1;
        article.setArticleViewCount(articleCount);
        articleService.updateArticle(article);
        return JSON.toJSONString(articleCount);
    }

}
