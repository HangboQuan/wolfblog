package com.tencent.dao;

import com.tencent.entity.Category;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.*;

@Mapper
public interface ICategoryDao {
    //添加
    int insert(Category category);
    //更新
    int update(Category category);
    //删除
    int delete(int categoryId);
    //根据分类id获得分类信息
    Category getCategoryById(Integer categoryId);
    //查询分类总数
    Integer countCategory();
    //获取分类列表
    List<Category> listCategory();
    //根据父分类找子分类
    List<Category> findChildCategory(@Param(value="id")Integer id);
    //根据标签名获取标签
    Category getCategoryByName(String categoryName);
}
