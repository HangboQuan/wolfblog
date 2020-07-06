package com.tencent.controller.home;
import java.util.*;
import com.tencent.entity.Article;
import com.tencent.entity.Link;
import com.tencent.enums.LinkStatus;
import com.tencent.service.IArticleService;
import com.tencent.service.ILinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeLinkController {

    @Autowired
    private IArticleService articleService;

    @Autowired
    private ILinkService linkService;

    @RequestMapping("/applyLink")
    public String applyLinkView(Model model){
        //获得热评文章
        List<Article> mostCommentArticleList = articleService.listArticleByCommentCount(8);
        model.addAttribute("mostCommentArticleList",mostCommentArticleList);
        return "home/page/applyLink";
    }

    @RequestMapping(value = "/applyLinkSubmit",method = {RequestMethod.POST})
    @ResponseBody
    public void applyLinkSubmit(Link link){
        link.setLinkStatus(LinkStatus.HIDDEN.getValue());
        link.setLinkCreateTime(new Date());
        link.setLinkUpdateTime(new Date());
        linkService.insertLink(link);
    }
}
