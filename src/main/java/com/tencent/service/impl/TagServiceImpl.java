package com.tencent.service.impl;

import com.tencent.dao.IArticleTagRefDao;
import com.tencent.dao.ITagDao;
import com.tencent.entity.Tag;
import com.tencent.service.ITagService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.List;
@Service("tagService")
@Slf4j
public class TagServiceImpl implements ITagService {
    @Autowired(required = false)
    private ITagDao tagDao;
    @Autowired(required = false)
    private IArticleTagRefDao articleTagRefDao;

    @Override
    public void insertTag(Tag tag) {
        try{
            tagDao.insert(tag);
        }catch(Exception e){
            e.printStackTrace();
            log.error("添加标签失败,cause{}",e);
        }
    }

    @Override
    public void updateTag(Tag tag) {
        try{
            tagDao.update(tag);
        }catch(Exception e){
            e.printStackTrace();
            log.error("更新标签失败,cause{}",e);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteById(Integer tagId) {
        try{
            tagDao.deleteById(tagId);
            articleTagRefDao.deleteByTagId(tagId);
        }catch(Exception e){
            e.printStackTrace();
            log.error("删除标签失败,cause{}",e);
            //但我们需要在事务控制的service层类中使用try catch去捕获异常后，就会是事务控制失效，因为
            //该类的异常没有抛出，就不是出发事务管理机制；要即使用try catch去捕捉异常，而又让异常出现后spring回滚就需要使用这种方法来手动回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
    }

    @Override
    public Tag getTagById(Integer tagId) {
        Tag tag = null;
        try{
            tag = tagDao.getTagById(tagId);
        }catch(Exception e){
            e.printStackTrace();
            log.error("根据id获得标签失败,id{},cause{}",tagId,e);
        }
        return tag;
    }

    @Override
    public Tag getTagByName(String tagName) {
        Tag tag = null;
        try{
            tagDao.getTagByName(tagName);
        }catch (Exception e){
            e.printStackTrace();
            log.error("根据name获得标签失败,name{},cause{}",tagName,e);
        }
        return tag;
    }

    @Override
    public Integer countTag() {
        Integer count = null;
        try{
            count = tagDao.countTag();
        }catch(Exception e){
            e.printStackTrace();
            log.error("统计标签数失败,cause{}",e);
        }
        return count;
    }

    @Override
    //获得所有标签列表
    public List<Tag> getTagList() {
        List<Tag> tagList = null;
        try{
            tagList = tagDao.getTagList();
        }catch(Exception e){
            e.printStackTrace();
            log.error("获得所有标签列表失败,cause{}",e);
        }
        return tagList;
    }

    @Override
    public List<Tag> listTagWithCount() {
        List<Tag> tagList = null;
        try{
            tagList = tagDao.getTagList();
            for(int i=0;i<tagList.size();i++){
                Integer count = articleTagRefDao.countArticleByTagId(tagList.get(i).getTagId());
                tagList.get(i).setArticleCount(count);
            }
        }catch(Exception e){
            e.printStackTrace();
            log.error("获得所有标签列表失败,cause{}",e);
        }
        return tagList;
    }

    @Override
    public List<Tag> listTagByArticleId(Integer articleId) {
        List<Tag> tagList = null;
        try{
            tagList = articleTagRefDao.listTagByArticleId(articleId);
        }catch (Exception e){
            e.printStackTrace();
            log.error("根据文章id获得标签列表失败,cause{}",e);
        }
        return tagList;
    }


}
