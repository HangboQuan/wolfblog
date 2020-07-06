package com.tencent.dao;

import com.tencent.entity.ArticleTagRef;
import com.tencent.entity.Tag;
import java.util.*;

public interface IArticleTagRefDao {
    /**
     * 添加文章和标签关联记录
     * @param record 关联对象
     * @return
     */
    int insert(ArticleTagRef record);

    /**
     * 根据标签id删除记录
     * @param tagId
     * @return
     */
    int deleteByTagId(Integer tagId);

    /**
     * 根据文章id删除记录
     * @param articleId
     * @return
     */
    int deleteByArticleId(Integer articleId);

    /**
     * 根据标签id统计文章数
     * @param tagId
     * @return
     */
    int countArticleByTagId(Integer tagId);

    /**
     * 根据文章获得标签列表
     * @param articleId
     * @return
     */
    List<Tag> listTagByArticleId(Integer articleId);
}
