package com.tencent.controller.home;

import com.github.pagehelper.PageInfo;
import com.tencent.entity.*;
import com.tencent.enums.*;
import com.tencent.service.*;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;
import java.util.HashMap;

@Controller
public class HomeIndexController {

    @Autowired
    private IArticleService articleService;

    @Autowired
    private ILinkService linkService;

    @Autowired
    private INoticeService noticeService;

    @Autowired
    private ICommentService commentService;

    @Autowired
    private ITagService tagService;

    @Autowired
    private IOptionsService optionsService;

    @Autowired
    private IMenuService menuService;


    @RequestMapping(value = {"","/article"})
    public String index(@RequestParam(required = false,defaultValue = "1")Integer pageIndex,
                        @RequestParam(required = false,defaultValue = "10")Integer pageSize, Model model){
        HashMap<String,Object> criteria = new HashMap<>(1);
        criteria.put("status", ArticleStatus.PUBLISH.getValue());

        //文章列表
        PageInfo<Article> articleList = articleService.pageArticle(pageIndex,pageSize,criteria);
        model.addAttribute("pageInfo",articleList);

        //公告 ,此处的normal是不显示的意思
        List<Notice> noticeList = noticeService.listNotice(NoticeStatus.NORMAL.getValue());
        model.addAttribute("noticeList",noticeList);

        //获得菜单
        List<Menu> menuList = menuService.listMenu();
        model.addAttribute("menuList",menuList);

        //友情链接
        List<Link> linkList = linkService.listLink(LinkStatus.NORMAL.getValue());
        model.addAttribute("linkList",linkList);

        List<Tag> allTagList = tagService.getTagList();
        model.addAttribute("allTagList",allTagList);


        List<Comment> recentCommentList = commentService.listRecentComment(10);
        model.addAttribute("recentCommentList",recentCommentList);
        model.addAttribute("pageUrlPrefix","/article?pageIndex");
        return "home/index";
    }

    @RequestMapping(value = "/search")
    public String search(@RequestParam("keywords")String keywords,
                         @RequestParam(required = false,defaultValue = "1")Integer pageIndex,
                         @RequestParam(required = false,defaultValue = "20")Integer pageSize,Model model){
        //文章列表
        HashMap<String,Object> criteria = new HashMap<>(2);
        criteria.put("status",ArticleStatus.PUBLISH.getValue());
        criteria.put("keywords",keywords);
        //这里添加了ES的操作，如果开启了ES
        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(
                        new HttpHost("192.168.0.41",9200,"http"))
        );
        PageInfo<Article> articlePageInfo = null;

        if(client != null){
            articlePageInfo = articleService.findArticleByEs(client,criteria,pageIndex,pageSize);
        }
        else{
           articlePageInfo =  articleService.pageArticle(pageIndex,pageSize,criteria);
        }
        //articlePageInfo = articleService.pageArticle(pageIndex,pageSize,criteria);
        System.out.println(articlePageInfo);
        model.addAttribute("pageInfo",articlePageInfo);


        //标签列表显示
        List<Tag> allTagList = tagService.getTagList();
        model.addAttribute("allTagList",allTagList);

        List<Article> randomArticleList = articleService.listRandomArticle(8);
        model.addAttribute("randomArticleList",randomArticleList);



        //获得热评文章
        List<Article> mostCommentArticleList = articleService.listArticleByCommentCount(8);
        model.addAttribute("mostCommentArticleList",mostCommentArticleList);

        //最新评论
        List<Comment> recentCommentList = commentService.listRecentComment(10);
        model.addAttribute("recentCommentList",recentCommentList);
        model.addAttribute("pageUrlPrefix","/search?pageIndex");


        return "home/page/search";
    }

    @RequestMapping("/404")
    public String notFound(@RequestParam(required = false) String message,Model model){
        model.addAttribute("message",message);
        return "/home/error/404";
    }

    @RequestMapping("/500")
    public String serverError(@RequestParam(required = false)String message,Model model){
        model.addAttribute("message",message);
        return "home/error/500";
    }

}
