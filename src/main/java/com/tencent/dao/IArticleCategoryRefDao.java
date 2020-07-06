package com.tencent.dao;

import com.tencent.entity.ArticleCategoryRef;
import com.tencent.entity.Category;
import org.apache.ibatis.annotations.Mapper;

import java.util.*;
@Mapper
public interface IArticleCategoryRefDao {
    /**
     * 添加文章和分类关联记录
     * @param record 关联对象
     * @return 影响行数
     */
    int insert(ArticleCategoryRef record);

    /**
     * 根据分类Id删除记录
     * @param categoryId
     * @return
     */
    int deleteByCategoryId(Integer categoryId);

    /**
     * 根据文章Id删除记录
     * @param articleId
     * @return
     */
    int deleteByArticleId(Integer articleId);

    /**
     * 根据分类ID统计文章数
     * @param categoryId
     * @return
     */
    int countArticleByCategoryId(Integer categoryId);

    /**
     * 根据文章Id查询分类Id
     * @param articleId
     * @return
     */
    List<Integer> selectCategoryByArticleId(Integer articleId);

    /**
     * 根据分类id查询文章id
     * @param categoryId
     * @return
     */
    List<Integer> selectArticleIdByCategoryId(Integer categoryId);


    //根据文章id获得分类列表
    List<Category> listCategoryByArticleId(Integer articleId);

}
