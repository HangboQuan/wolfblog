package com.tencent.service;

import com.tencent.entity.Notice;
import java.util.*;
public interface INoticeService {

    void insertNotice(Notice notice);

    void updateNotice(Notice notice);

    void deleteNotice(Integer noticeId);

    Notice getNoticeById(Integer noticeId);

    List<Notice> listNotice(Integer status);

    Integer countNotice(Integer status);
}
