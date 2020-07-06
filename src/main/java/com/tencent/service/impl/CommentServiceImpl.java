package com.tencent.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tencent.dao.IArticleDao;
import com.tencent.dao.ICommentDao;
import com.tencent.entity.Article;
import com.tencent.entity.Comment;
import com.tencent.service.ICommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service("commentService")
@Slf4j
public class CommentServiceImpl implements ICommentService {

    @Autowired(required = false)
    private ICommentDao commentDao;

    @Autowired(required = false)
    private IArticleDao articleDao;


    @Override
    public void insertComment(Comment comment) {
        try{
            commentDao.insert(comment);
        }catch(Exception e){
            e.printStackTrace();
            log.error("创建评论失败:comment:{},cause:{}",comment,e);
        }
    }

    @Override
    public void updateComment(Comment comment) {
        try{
            commentDao.update(comment);
        }catch (Exception e){
            e.printStackTrace();
            log.error("更新评论失败：comment:{},cause:{}",comment,e);
        }
    }

    @Override
    public void deleteComment(Integer commentId) {
        try{
            commentDao.delete(commentId);
        }catch(Exception e){
            e.printStackTrace();
            log.error("删除评论失败:commentId:{},cause:{}",commentId,e);
        }
    }

    @Override
    public Comment getCommentById(Integer commentId) {
        Comment comment = null;
        try{
            comment= commentDao.getCommentById(commentId);
        }catch(Exception e){
            e.printStackTrace();
            log.error("根据评论Id评论:commentId:{},cause:{}",commentId,e);
        }
        return comment;
    }

    @Override
    public List<Comment> listCommentByArticleId(Integer articleId) {
        List<Comment> commentList = null;
        try{
            commentList = commentDao.listCommentByArticleId(articleId);
        }catch(Exception e){
            e.printStackTrace();
            log.error("根据文章id获得评论列表失败:articleId:{},cause:{}",articleId,e);
        }
        return commentList;
    }

    @Override
    public PageInfo<Comment> listCommentByPage(Integer pageIndex, Integer pageSize) {
        PageHelper.startPage(pageIndex,pageSize);
        List<Comment> commentList = null;
        try{
            commentList = commentDao.listComment();
            for(int i = 0;i < commentList.size();i++){
                Article article = articleDao.getArticleByStatusAndId(0,0);
                commentList.get(i).setArticle(article);
            }
        }catch(Exception e){
            e.printStackTrace();
            log.error("分页获得评论失败，pageIndex:{},pageSize:{},cause:{}",pageIndex,pageSize,e);
        }
        return new PageInfo<>(commentList);
    }

    @Override
    public List<Comment> listComment() {
        List<Comment> commentList = null;
        try{
            commentList = commentDao.listComment();
        }catch(Exception e){
            e.printStackTrace();
            log.error("获得评论列表失败:cause:{}",e);
        }
        return commentList;
    }

    @Override
    public Integer countComment() {
        Integer count = null;
        try{
            count = commentDao.countComment();
        }catch(Exception e){
            e.printStackTrace();
            log.error("获得评论数量失败,cause:{}",e);
        }
        return count;
    }

    @Override
    public List<Comment> listRecentComment(Integer limit) {
        List<Comment> commentList = null;
        try{
            commentList = commentDao.listRecentComment(limit);
            for(int i = 0; i < commentList.size();i++){
                //注意这里参数没有传递，还没有ArticleStatus.PUBLISH
                Article article = articleDao.getArticleByStatusAndId(0,0);
                commentList.get(i).setArticle(article);
            }
        }catch(Exception e){
            e.printStackTrace();
            log.error("获得最新评论失败:limit:{},cause:{}",limit,e);

        }
        return commentList;
    }

    @Override
    public List<Comment> listChildComment(Integer id) {
        List<Comment> commentList = null;
        try{
            commentList = commentDao.listChildComment(id);
        }catch(Exception e){
            e.printStackTrace();
            log.error("获取子评论失败,cause:{}",e);
        }
        return commentList;
    }
}
