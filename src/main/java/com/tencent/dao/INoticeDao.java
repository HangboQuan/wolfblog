package com.tencent.dao;

import com.tencent.entity.Notice;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.*;
@Mapper
public interface INoticeDao {

    void insert(Notice notice);

    void delete(Integer noticeId);

    void update(Notice notice);

    Notice getNoticeById(Integer noticeId);

    List<Notice>listNotice(@Param("status")Integer status);

    Integer countNotice(@Param("status")Integer status);
}
