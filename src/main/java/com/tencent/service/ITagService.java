package com.tencent.service;

import com.tencent.entity.Tag;

import java.util.List;

public interface ITagService {

    void insertTag(Tag tag);

    void updateTag(Tag tag);

    void deleteById(Integer tagId);

    Tag getTagById(Integer tagId);

    Tag getTagByName(String tagName);

    Integer countTag();//统计标签数量

    List<Tag> getTagList();//获取标签列表

    List<Tag> listTagWithCount();//获取标签列表

    List<Tag> listTagByArticleId(Integer articleId);//通过文章id获得标签列表
}
