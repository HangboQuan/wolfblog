package com.tencent.service;

import com.github.pagehelper.PageInfo;
import com.tencent.entity.Article;
import org.elasticsearch.client.RestHighLevelClient;

import java.util.*;
import java.util.HashMap;


public interface IArticleService {
    //添加文章
    void insertArticle(Article article);

    //根据id删除文章
    void deleteArticle(Integer articleId);

    //批量删除文章
    void deleteArticleBatch(List<Integer> ids);

    //更新文章
    void updateArticle(Article article);

    //修改文章详细信息
    void updateArticleDetail(Article article);

    //获取文章的作者ID
    Integer getArticleUserId(Integer articleId);

    //获取文章总数
    Integer countArticle(Integer status);

    //更新文章的点赞数
    Integer updateLikeCount(Integer articleId, Integer likeCount);

    //获取评论总数
    Integer countArticleComment();

    //获取文章浏览总数
    Integer countArticleView();

    //统计有这个分类的文章数
    Integer countArticleByCategoryId(Integer categoryId);

    //统计有这个标签的文章数
    Integer countArticleByTagId(Integer tagId);

    //获得所有文章不分页
    List<Article> listArticle(HashMap<String, Object> criteria);

    //获得最新的文章
    List<Article> listRecentArticle(Integer limit);

    //分页显示
    PageInfo<Article> pageArticle(Integer pageIndex,Integer pageSize,HashMap<String,Object> criteria);


    //ES查询
    PageInfo<Article> findArticleByEs(RestHighLevelClient client, HashMap<String,Object> criteria, Integer pageIndex, Integer pageSize);
    //文章详情页面显示
    Article getArticleByStatusAndId(Integer status,Integer id);

    //获得访问量较多的文章
    List<Article> listArticleByViewCount(Integer limit);

    //获得上一篇文章
    Article getPreArticle(Integer articleId);

    //获得下一篇文章
    Article getAfterArticle(Integer articleId);

    //获得随机文章
    List<Article> listRandomArticle(Integer limit);

    //获得评论数较多的文章
    List<Article> listArticleByCommentCount(Integer limit);

    //更新文章的评论数
    void updateCommentCount(Integer articleId);

    //获得最后更新记录
    Article getLastUpdateArticle();

    //获得相关文章
    List<Article> listArticleByCategoryId(Integer cateId,Integer limit);

    //获得相关的文章
    List<Article> listArticleByCategoryIds(List<Integer>cateIds,Integer limit);

    //根据文章ID获得分类Id列表
    List<Integer> listCategoryByArticleId(Integer articleId);

    //获取所有文章
    List<Article> listAllNotWithContent();
}
