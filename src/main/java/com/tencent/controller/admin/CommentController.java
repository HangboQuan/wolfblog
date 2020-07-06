package com.tencent.controller.admin;

import com.github.pagehelper.PageInfo;
import com.tencent.entity.Article;
import com.tencent.entity.Comment;
import com.tencent.service.IArticleService;
import com.tencent.service.ICommentService;
import com.tencent.utils.MyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Controller
@RequestMapping("/admin/comment")
public class CommentController {

    @Autowired(required = false)
    private ICommentService commentService;

    @Autowired(required = false)
    private IArticleService articleService;

    @RequestMapping("")
    public String commentListView(@RequestParam(required = false,defaultValue = "1")Integer pageIndex,
                                  @RequestParam(required = false,defaultValue = "10")Integer pageSize,
                                  Model model){
        PageInfo<Comment> commentPageInfo = commentService.listCommentByPage(pageIndex,pageSize);
        model.addAttribute("pageInfo",commentPageInfo);
        model.addAttribute("pageUrlPrefix","/admin/comment?pageIndex");
        return "admin/comment/index";
    }

    @RequestMapping(value = "/insert",method = RequestMethod.POST)
    @ResponseBody
    public void insertComment(Comment comment, HttpServletRequest request){
        //添加评论
        comment.setCommentIp(MyUtils.getIpAddr(request));
        comment.setCommentCreateTime(new Date());
        commentService.insertComment(comment);

        //更新文章评论
        Article article = articleService.getArticleByStatusAndId(null,comment.getCommentArticleId());
        articleService.updateCommentCount(article.getArticleId());
    }

    @RequestMapping("/delete/{id}")
    public void deleteComment(@PathVariable("id")Integer id){
        Comment comment = commentService.getCommentById(id);
        //删除评论
        commentService.deleteComment(id);
        //删除子评论
        List<Comment> listChildComment = commentService.listChildComment(id);
        for(int i = 0; i < listChildComment.size(); i++){
            commentService.deleteComment(listChildComment.get(i).getCommentId());
        }
        //更新文章的评论数
        Article article = articleService.getArticleByStatusAndId(null,comment.getCommentArticleId());
        articleService.updateCommentCount(article.getArticleId());
    }

    //编辑评论页面显示
    @RequestMapping("/edit/{id}")
    public String editCommentView(@PathVariable("id")Integer id,Model model){
        Comment comment = commentService.getCommentById(id);
        model.addAttribute("comment",comment);
        return "admin/comment/edit";

    }

    //编辑评论提交
    @RequestMapping(value = "/editSubmit",method = RequestMethod.POST)
    public String editCommentSubmit(Comment comment){
        commentService.updateComment(comment);
        return "redirect:/admin/comment";
    }

    //编辑回复页面显示
    @RequestMapping("/reply/{id}")
    public String replyCommentView(@PathVariable("id")Integer id,Model model){
        Comment comment  =  commentService.getCommentById(id);
        model.addAttribute("comment",comment);
        return "admin/comment/reply";
    }

    //回复评论提交
    @RequestMapping(value = "/replySubmit",method = RequestMethod.POST)
    public String replyCommentSubmit(HttpServletRequest request,Comment comment){
        Article article = articleService.getArticleByStatusAndId(null,comment.getCommentArticleId());
        article.setArticleCommentCount(article.getArticleCommentCount()+1);
        articleService.updateArticle(article);

        //添加评论
        comment.setCommentCreateTime(new Date());
        comment.setCommentIp(MyUtils.getIpAddr(request));
        commentService.insertComment(comment);
        return "redirect:/admin/comment";
    }






}
