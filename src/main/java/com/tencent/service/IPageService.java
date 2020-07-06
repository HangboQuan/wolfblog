package com.tencent.service;

import com.tencent.entity.Page;
import java.util.*;
public interface IPageService {

    void insertPage(Page page);

    void updatePage(Page page);

    void deletePage(Integer pageId);

    Page getPageById(Integer pageId);

    Page getPageByKey(Integer status,String key);

    List<Page> listPage(Integer status);
}
