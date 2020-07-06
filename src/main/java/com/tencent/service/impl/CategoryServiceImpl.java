package com.tencent.service.impl;

import com.tencent.dao.IArticleCategoryRefDao;
import com.tencent.dao.ICategoryDao;
import com.tencent.entity.Category;
import com.tencent.service.ICategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.List;

@Service("categoryService")
@Slf4j
class CategoryServiceImpl implements ICategoryService {

    @Autowired(required = false)
    private ICategoryDao categoryDao;
    @Autowired(required = false)
    private IArticleCategoryRefDao articleCategoryRefDao;

    @Override
    public void insertCategory(Category category) {
        try {
            categoryDao.insert(category);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("创建分类失败,category:{},cause:{}", category, e);
        }
    }

    @Override
    public void udpateCategory(Category category) {
        try {
            categoryDao.update(category);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("更新分类失败,category:{},cause:{}", category, e);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)//作用是：如果这个方法发生异常，数据库就会发生回滚
    public void deleteCategory(int categoryId) {
        try {
            categoryDao.delete(categoryId);
            articleCategoryRefDao.deleteByArticleId(categoryId);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("删除分类失败,id{},cause:{}", categoryId, e);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
    }

    @Override
    public Category getCategoryById(Integer categoryId) {
        Category category = null;
        try {
            category = categoryDao.getCategoryById(categoryId);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("查询id分类获取分类,id{},cause{}", categoryId, e);
        }
        return category;
    }

    @Override
    public Category getCategoryByName(String categoryName) {
        Category category = null;
        try {
            category = categoryDao.getCategoryByName(categoryName);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("查询name获取分类,name{},cause{}", categoryName, e);
        }
        return category;
    }

    @Override
    public Integer countCategory() {
        Integer count = 0;
        try {
            count = categoryDao.countCategory();
        } catch (Exception e) {
            e.printStackTrace();
            log.error("统计分类失败,cause", e);
        }
        return count;
    }

    @Override
    public List<Category> listCategory() {
        List<Category> categoryList = null;
        try {
            categoryList = categoryDao.listCategory();
        } catch (Exception e) {
            log.error("获得文章分类列表失败,cause{}", e);
        }
        return categoryList;
    }

    @Override
    public List<Category> listCategoryWithCount() {
        List<Category> categoryList = null;
        try {
            categoryList = categoryDao.listCategory();
            for (int i = 0; i < categoryList.size(); i++) {
                Integer count = articleCategoryRefDao.countArticleByCategoryId(categoryList.get(i).getCategoryId());
                categoryList.get(i).setArticleCount(count);
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("根据文章获取分类列表失败,cause{}",e);
        }
        return categoryList;
    }
}
