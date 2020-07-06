package com.tencent.entity;
/**
 * 文章分类关联表
 */


import java.io.Serializable;

public class ArticleCategoryRef implements Serializable {
    private Integer articleId;
    private Integer categoryId;
    public ArticleCategoryRef(){

    }
    public ArticleCategoryRef(Integer articleId,Integer categoryId){
        this.articleId = articleId;
        this.categoryId = categoryId;
    }

    public Integer getArticleId() {
        return articleId;
    }

    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    @Override
    public String toString() {
        return "ArticleCategoryRef{" +
                "articleId=" + articleId +
                ", categoryId=" + categoryId +
                '}';
    }
}
