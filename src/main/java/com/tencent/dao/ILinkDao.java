package com.tencent.dao;
import com.tencent.entity.Link;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.*;
@Mapper
public interface ILinkDao {

    void insert(Link link);

    void update(Link link);

    void delete(Integer linkId);

    Link getLinkById(Integer linkId);

    Integer countLink(@Param(value = "status")Integer status);

    List<Link> listLink(@Param(value = "status") Integer status);
}
