package com.tencent.dao;

import com.tencent.entity.Tag;
import org.apache.ibatis.annotations.Mapper;

import java.util.*;
@Mapper
public interface ITagDao {

    int insert(Tag tag);

    int update(Tag tag);

    int deleteById(Integer tagId);

    Tag getTagById(Integer tagId);

    Tag getTagByName(String tagName);

    Integer countTag();//统计标签数量

    List<Tag> getTagList();//获取标签列表
}
