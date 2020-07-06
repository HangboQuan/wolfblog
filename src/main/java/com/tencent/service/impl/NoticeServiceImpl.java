package com.tencent.service.impl;

import com.tencent.dao.INoticeDao;
import com.tencent.entity.Notice;
import com.tencent.service.INoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("noticeService")
public class NoticeServiceImpl implements INoticeService {

    @Autowired(required = false)
    private INoticeDao noticeDao;

    @Override
    public void insertNotice(Notice notice) {
        noticeDao.insert(notice);
    }

    @Override
    public void updateNotice(Notice notice) {
        noticeDao.update(notice);
    }

    @Override
    public void deleteNotice(Integer noticeId) {
        noticeDao.delete(noticeId);
    }

    @Override
    public Notice getNoticeById(Integer noticeId) {
        return noticeDao.getNoticeById(noticeId);
    }

    @Override
    public List<Notice> listNotice(Integer status) {
        return noticeDao.listNotice(status);
    }

    @Override
    public Integer countNotice(Integer status) {
        return noticeDao.countNotice(status);
    }
}
