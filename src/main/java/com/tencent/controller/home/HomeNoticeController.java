package com.tencent.controller.home;

import com.tencent.entity.Article;
import com.tencent.entity.Notice;
import com.tencent.service.IArticleService;
import com.tencent.service.INoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.*;
@Controller
public class HomeNoticeController {

    @Autowired(required = false)
    private IArticleService articleService;

    @Autowired(required = false)
    private INoticeService noticeService;

    @RequestMapping(value = {"notice/{noticeId}"})
    public String NoticeDetailView(@PathVariable("noticeId")Integer noticeId, Model model){
        Notice notice = noticeService.getNoticeById(noticeId);
        model.addAttribute("notice",notice);

        //获得热评文章
        List<Article> mostCommentArticleList = articleService.listArticleByCommentCount(8);
        model.addAttribute("mostCommentArticleList",mostCommentArticleList);
        return "home/page/noticeDetail";
    }

}
