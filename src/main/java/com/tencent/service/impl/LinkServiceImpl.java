package com.tencent.service.impl;

import com.tencent.dao.ILinkDao;
import com.tencent.entity.Link;
import com.tencent.service.ILinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("linkService")
public class LinkServiceImpl implements ILinkService {

    @Autowired
    private ILinkDao linkDao;

    @Override
    public void insertLink(Link link) {
        linkDao.insert(link);
    }

    @Override
    public void updateLink(Link link) {
        linkDao.update(link);
    }

    @Override
    public void deleteLink(Integer linkId) {
        linkDao.delete(linkId);
    }

    @Override
    public Link getLinkById(Integer linkId) {
        return linkDao.getLinkById(linkId);
    }

    @Override
    public Integer countLink(Integer status) {
        return linkDao.countLink(status);
    }

    @Override
    public List<Link> listLink(Integer status) {
        return linkDao.listLink(status);
    }
}
