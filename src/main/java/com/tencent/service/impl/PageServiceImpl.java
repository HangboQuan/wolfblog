package com.tencent.service.impl;

import com.tencent.dao.IPageDao;
import com.tencent.entity.Page;
import com.tencent.service.IPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("pageService")
public class PageServiceImpl implements IPageService {

    @Autowired(required = false)
    private IPageDao pageDao;
    @Override
    public void insertPage(Page page) {
        pageDao.insert(page);
    }

    @Override
    public void updatePage(Page page) {
        pageDao.update(page);
    }

    @Override
    public void deletePage(Integer pageId) {
        pageDao.deleteById(pageId);
    }

    @Override
    public Page getPageById(Integer pageId) {
        return pageDao.getPageById(pageId);
    }

    @Override
    public Page getPageByKey(Integer status, String key) {
        return pageDao.getPageByKey(status,key);
    }

    @Override
    public List<Page> listPage(Integer status) {
        return pageDao.listPage(status);
    }
}
