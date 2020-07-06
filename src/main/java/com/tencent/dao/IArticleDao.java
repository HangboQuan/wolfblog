package com.tencent.dao;
import java.util.*;
import com.tencent.entity.Article;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;


@Mapper//添加了@Mapper注解之后这个接口在编译时会生成相应的实现了类
public interface IArticleDao {
    //添加文章
    Integer insert(Article article);
    //根据id删除文章
    Integer deleteById(Integer articleId);
    //更新文章
    Integer update(Article article);
    //获取所有的文章,其中criteria表示是查询条件，他的中文意思是标准
    List<Article> findAll(HashMap<String,Object> criteria);
    //文章归档
    List<Article> listAllNotWithContent();
    //获取文章总数
    //用注解来简化xml配置的时候，@param注解的作用是给参数命名，参数命名后就能根据名字得到参数值，正确得将参数传入到sql语句中
    //简化的就是xml中的传入参数类型，paramType就可以省略了
    Integer countArticle(@Param(value="status")Integer status);

    //获取文章的作者id

    Integer getArticleUserId(Integer articleId);

    //更新文章的点赞数
    //@Update("update article set article_like_count = #{articleLikeCount} where article_id = {articleId}")
    Integer updateLikeCount(@Param(value="articleId") Integer articleId, @Param(value="articleLikeCount") Integer articleLikeCount);
    //获取留言总数
    Integer countArticleComment();
    //获取浏览量总数
    Integer countArticleView();
    //获取所有文章的归档
    List<Article>listArticle();
    //根据id或者status查询文章信息
    Article getArticleByStatusAndId(@Param(value="status")Integer status,@Param(value="id")Integer id);
    //分页操作,参数列表代表状态 ， 从第几页开始，每页数量
    @Deprecated//该注解的作用是：加上该注解之后，表示此方法或类不再建议使用,调用时也会出现删除线，但是不代表不能用，只是说不推荐使用
    List<Article> pageArticle(@Param(value="status")Integer status,@Param(value="pageIndex")Integer pageIndex,@Param(value="pageSize")Integer pageSize);
    //获得访问最多的文章（猜你喜欢） limit表示查询数量
    List<Article> listArticleByViewCount(@Param(value="limit")Integer limit);
    //获得上一篇文章
    Article getPreArticle(@Param(value="id")Integer id);
    //获得下一篇文章
    Article getAfterArticle(@Param(value="id")Integer id);
    //获得随机文章,limit表示的是查询数量
    List<Article> listRandomArticle(@Param(value="limit")Integer limit);
    //获得热评文章
    List<Article> listArticleByCommentCount(@Param(value="limit")Integer limit);
    //更新文章的评论数
    void updateCommentCount(@Param(value="articleId")Integer articleId);
    //获得最后更新的记录
    Article getLastUpdateArticle();
    //用户的文章数,id表示用户id
    Integer countArticleByUser(@Param(value="id")Integer id);
    //获得最新文章
    List<Article> listArticleByLimit(Integer limit);
    //批量删除文章
    Integer deleteBatch(@Param("ids")List<Integer>ids);
    //注意下面里那个方法由于文章分类的功能暂时还没写
    //根据分类id，categoryId表示的是分类id
    List<Article> findArticleByCategoryId(@Param("categoryId")Integer categoryId,@Param("limit")Integer limit);
    //根据分类id，categoryIds表示的是分类ids
    List<Article> findArticleByCategoryIds(@Param("categoryIds")List<Integer> categoryIds,@Param("limit")Integer limit);
}
