package com.tencent.service;

import com.github.pagehelper.PageInfo;
import com.tencent.entity.Comment;
import java.util.*;
public interface ICommentService {

    /**
     * 添加评论
     */
    void insertComment(Comment comment);

    /**
     * 修改评论
     */
    void updateComment(Comment comment);

    /**
     * 删除评论
     */
    void deleteComment(Integer commentId);

    /**
     * 根据Id获得评论
     */
    Comment getCommentById(Integer commentId);

    /**
     * 根据文章id获得评论
     */
    List<Comment> listCommentByArticleId(Integer articleId);

    /**
     * 获取所有评论列表
     * @param pageIndex：从第几页开始
     * @param pageSize：一页显示的数量
     * @return
     */
    PageInfo<Comment> listCommentByPage(Integer pageIndex,Integer pageSize);

    /**
     * 获取评论列表
     * @return
     */
    List<Comment> listComment();

    /**
     * 获取评论数
     */
    Integer countComment();

    /**
     * 获得最新评论
     */
    List<Comment> listRecentComment(Integer limit);

    /**
     * 获得评论的子评论
     */

    List<Comment> listChildComment(Integer Id);
}
