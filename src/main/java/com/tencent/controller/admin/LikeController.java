package com.tencent.controller.admin;

import com.tencent.async.EventModel;
import com.tencent.async.EventProductor;
import com.tencent.async.EventType;
import com.tencent.entity.Article;
import com.tencent.entity.EntityType;
import com.tencent.entity.HostHolder;
import com.tencent.entity.User;
import com.tencent.service.IArticleService;
import com.tencent.service.ILikeService;
import com.tencent.service.IUserService;
import com.tencent.utils.RedisAdpeter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
public class LikeController {

    @Autowired
    RedisAdpeter redisAdpeter;

    @Autowired
    ILikeService likeService;

    @Autowired
    IUserService userService;

    @Autowired
    IArticleService articleService;

    @Autowired
    HostHolder hostHolder;

    @Autowired
    EventProductor eventProductor;



    @RequestMapping(value = "/admin/like/{articleId}", method = {RequestMethod.GET, RequestMethod.POST})
    //@ResponseBody
    public String like(@PathVariable("articleId") int articleId, HttpServletRequest request) {

        User user = (User)request.getSession().getAttribute("user");

        long articleLikeCount = likeService.like(user.getUserId(), articleId, EntityType.ENTITY_ARTICLE);

        Article article = articleService.getArticleByStatusAndId(1, articleId);
        int articleUserId = article.getArticleUserId();

        articleService.updateLikeCount(articleId, (int)articleLikeCount);
        eventProductor.fireEvent(new EventModel(EventType.LIKE).setEntityOwnerId(articleUserId).
                setActorId(user.getUserId()).setEntityId(articleId));

        return "redirect:/";
    }

}
