package com.tencent.service;

import com.tencent.entity.Category;

import java.util.List;

public interface ICategoryService {
    //添加
    void insertCategory(Category category);
    //更新
    void udpateCategory(Category category);
    //删除
    void deleteCategory(int categoryId);
    //根据分类id获得分类信息
    Category getCategoryById(Integer categoryId);
    //根据标签名获取标签
    Category getCategoryByName(String categoryName);
    //查询分类总数
    Integer countCategory();
    //获取分类列表
    List<Category> listCategory();
    //获取分类列表
    List<Category> listCategoryWithCount();

}
