package com.tencent.dao;

import com.tencent.entity.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.*;
@Mapper
public interface IPageDao {

    void insert(Page page);

    void update(Page page);

    void deleteById(Integer pageId);

    Page getPageById(Integer pageId);

    /**
     * 根据key获得页面
     * @param status
     * @param key
     * @return
     */
    Page getPageByKey(@Param(value = "status")Integer status,@Param(value = "key")String key);

    List<Page> listPage(@Param(value = "status")Integer status);
}
