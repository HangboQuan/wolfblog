package com.tencent.controller.home;

import com.tencent.entity.*;
import com.tencent.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
@Controller
public class HomePageController {

    private static final Logger logger = LoggerFactory.getLogger(HomePageController.class);

    @Autowired
    private IArticleService articleService;

    @Autowired
    private ICategoryService categoryService;

    @Autowired
    private ITagService tagService;

    @Autowired
    private IPageService pageService;

    @Autowired
    private IMessageService messageService;

    @RequestMapping(value ={"/key"})
    public String pageDetail(@PathVariable("key")String key, Model model){
        Page page = pageService.getPageByKey(1,key);
        if(null == page){
            return "redirect:/404";
        }
        model.addAttribute("page",page);

        List<Article> mostCommentArticleList = articleService.listArticleByCommentCount(8);
        model.addAttribute("mostCommentArticleList",mostCommentArticleList);
        return "home/page/page";
    }


    //文章页面显示
    @RequestMapping(value = "/articleFile")
    public String articleFile(Model model){
        List<Article> articleList = articleService.listAllNotWithContent();
        model.addAttribute("articleList",articleList);

        List<Article> mostCommentArticleList = articleService.listArticleByCommentCount(10);
        model.addAttribute("mostCommentArticleList",mostCommentArticleList);
        return "home/page/articleFile";
    }

    //站点地图显示
    @RequestMapping("/map")
    public String siteMap(Model model){
        List<Article> articleList = articleService.listAllNotWithContent();
        model.addAttribute("articleList",articleList);


        List<Category> categoryList = categoryService.listCategory();
        model.addAttribute("categoryList",categoryList);

        //标签显示
        List<Tag> tagList = tagService.getTagList();
        model.addAttribute("tagList",tagList);

        //获得热评文章

        List<Article> mostCommentArticleList = articleService.listArticleByCommentCount(10);
        model.addAttribute("mostCommentArticleList",mostCommentArticleList);
        return "home/page/siteMap";

    }

    @RequestMapping("/message")
    public String message(Model model, HttpServletRequest request){
        //获得热门评论
        List<Article> mostCommentArticleList = articleService.listArticleByCommentCount(10);
        model.addAttribute("mostCommentArticleList",mostCommentArticleList);

        User user = (User)request.getSession().getAttribute("user");
        if (null == user) {
            return "/admin/login";
        }
        else{

            int localUserId = user.getUserId();
            List<Message> conversationList = messageService.getConversationList(localUserId, 0, 10);

            model.addAttribute("conversations", conversationList);
        }
        return "/home/page/message";
    }

    @RequestMapping("/aboutSite")
    public String aboutSite(){
        return "home/page/aboutSite";
    }

    @RequestMapping(path = {"/msg/addMessage"}, method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public String addMessage(@RequestParam("fromId") int fromId,
                             @RequestParam("toId") int toId,
                             @RequestParam("content") String content) {
        Message msg = new Message();
        msg.setContent(content);
        msg.setCreatedDate(new Date());
        msg.setToId(toId);
        msg.setFromId(fromId);
        msg.setConversationId(fromId < toId ? String.format("%d_%d", fromId, toId) :
                String.format("%d_%d", toId, fromId));
        messageService.addMessage(msg);
        return "success";
    }


}
