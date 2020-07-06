package com.tencent.interceptor;

import com.tencent.entity.Article;
import com.tencent.entity.Category;
import com.tencent.entity.Menu;
import com.tencent.entity.Options;
import com.tencent.enums.ArticleStatus;
import com.tencent.enums.LinkStatus;
import com.tencent.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import java.util.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@Component
public class HomeResourceInterceptor implements HandlerInterceptor {

    @Autowired(required = false)
    private IArticleService articleService;

    @Autowired(required = false)
    private ICategoryService categoryService;

    @Autowired(required = false)
    private ITagService tagService;

    @Autowired(required = false)
    private ILinkService linkService;

    @Autowired(required = false)
    private IOptionsService optionsService;

    @Autowired(required = false)
    private IMenuService menuService;


    /**
     *在请求处理执行，该方法主要是用于准备资源数据的，然后可以把他们当做请求属性放到webRequest中
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //菜单显示
        List<Menu> menuList = menuService.listMenu();
        request.setAttribute("menuList",menuList);

        //分类显示
        List<Category> categoryList = categoryService.listCategory();
        request.setAttribute("allCategoryList",categoryList);

        //获得网站概况
        List<String> siteBasicStatistics = new ArrayList<String>();
        siteBasicStatistics.add(articleService.countArticle(ArticleStatus.PUBLISH.getValue())+"");
        siteBasicStatistics.add(articleService.countArticleComment()+"");
        siteBasicStatistics.add(categoryService.countCategory()+"");
        siteBasicStatistics.add(tagService.countTag()+"");
        siteBasicStatistics.add(linkService.countLink(LinkStatus.NORMAL.getValue())+"");
        siteBasicStatistics.add(articleService.countArticleView()+"");
        request.setAttribute("siteBasicStatistics",siteBasicStatistics);

        //最后更新的文章
        Article lastUpdateArticle = articleService.getLastUpdateArticle();
        request.setAttribute("lastUpdateArticle",lastUpdateArticle);

        //页脚显示
        //博客基本信息显示(Options)
        Options options = optionsService.getOptions();
        request.setAttribute("options",options);
        return true;

    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
