package com.tencent.controller.home;

import cn.hutool.http.HtmlUtil;
import com.tencent.dto.JsonResult;
import com.tencent.entity.Article;
import com.tencent.entity.Comment;
import com.tencent.enums.ArticleStatus;
import com.tencent.enums.Role;
import com.tencent.service.IArticleService;
import com.tencent.service.ICommentService;
import com.tencent.utils.MyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@RestController //这个注解的作用相当是requestBody+controller注解
public class HomeCommentController {

    @Autowired(required = false)
    private ICommentService commentService;

    @Autowired(required = false)
    private IArticleService articleService;

    @RequestMapping(value = "/comment",method = RequestMethod.POST)
    public JsonResult insertComment(HttpServletRequest request, Comment comment){
        comment.setCommentCreateTime(new Date());
        comment.setCommentIp(MyUtils.getIpAddr(request));
        if(request.getSession().getAttribute("user")!=null){
            comment.setCommentRole(Role.ADMIN.getValue());
        }else{
            comment.setCommentRole(Role.VISITOR.getValue());
        }
        comment.setCommentAuthorAvatar(MyUtils.getGravatar(comment.getCommentAuthorEmail()));

        //这里的作用是:过滤掉字符，防止xss攻击
        comment.setCommentContent(HtmlUtil.escape(comment.getCommentContent()));
        comment.setCommentAuthorName(HtmlUtil.escape(comment.getCommentAuthorName()));
        comment.setCommentAuthorEmail(HtmlUtil.escape(comment.getCommentAuthorEmail()));
        comment.setCommentAuthorUrl(HtmlUtil.escape(comment.getCommentAuthorUrl()));


        try{
            commentService.insertComment(comment);
            //更新文章的评论数
            Article article = articleService.getArticleByStatusAndId(ArticleStatus.PUBLISH.getValue(),comment.getCommentArticleId());
            articleService.updateCommentCount(article.getArticleId());
        }catch(Exception e){
            e.printStackTrace();
            return new JsonResult().fail();
        }
        return new JsonResult().ok();
    }

}
