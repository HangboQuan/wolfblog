package com.tencent.entity;


/**
 * 把文章和标签进行关联
 */
import java.io.Serializable;

public class ArticleTagRef implements Serializable {
    private Integer articleId;
    private Integer tagId;
    public ArticleTagRef(){

    }
    public ArticleTagRef(Integer articleId,Integer tagId){
        this.articleId = articleId;
        this.tagId = tagId;
    }

    public Integer getArticleId() {
        return articleId;
    }

    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }

    public Integer getTagId() {
        return tagId;
    }

    public void setTagId(Integer tagId) {
        this.tagId = tagId;
    }

    @Override
    public String toString() {
        return "ArticleTagRef{" +
                "articleId=" + articleId +
                ", tagId=" + tagId +
                '}';
    }
}
