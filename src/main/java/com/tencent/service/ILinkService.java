package com.tencent.service;
import com.tencent.entity.Link;
import java.util.*;
public interface ILinkService {

    void insertLink(Link link);

    void updateLink(Link link);

    void deleteLink(Integer linkId);

    Link getLinkById(Integer linkId);

    Integer countLink(Integer status);

    List<Link> listLink(Integer status);

}
