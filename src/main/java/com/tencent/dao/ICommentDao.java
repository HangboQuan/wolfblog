package com.tencent.dao;

import com.tencent.entity.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.*;

@Mapper
public interface ICommentDao {

    int insert(Comment comment);

    int update(Comment comment);

    int delete(Integer commentId);

    /**
     * 根据Id查询
     * @param commentId
     * @return
     */
    Comment getCommentById(Integer commentId);

    //根据文章Id获取评论列表
    List<Comment> listCommentByArticleId(@Param(value = "id")Integer id);

    //获得评论列表
    List<Comment> listComment();

    //统计评论数
    Integer countComment();

    //获得最近评论
    List<Comment> listRecentComment(@Param(value = "limit")Integer limit);

    //获得评论的子评论
    List<Comment> listChildComment(@Param(value = "id") Integer id);
}
