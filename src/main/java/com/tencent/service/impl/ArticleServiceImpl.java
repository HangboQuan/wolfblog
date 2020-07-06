package com.tencent.service.impl;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tencent.dao.IArticleCategoryRefDao;
import com.tencent.dao.IArticleDao;
import com.tencent.dao.IArticleTagRefDao;
import com.tencent.entity.*;
import com.tencent.enums.ArticleCommentStatus;
import com.tencent.service.IArticleService;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpHost;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.MatchPhraseQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.elasticsearch.search.sort.ScoreSortBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.elasticsearch.common.text.Text;
import java.util.*;
import java.util.concurrent.TimeUnit;

@Service("articleService")
@Slf4j
public class ArticleServiceImpl implements IArticleService {
    @Autowired(required = false)
    private IArticleDao articleDao;

    @Autowired(required = false)
    private RestHighLevelClient highLevelClient;

    @Autowired(required = false)
    private IArticleCategoryRefDao articleCategoryRefDao;

    @Autowired(required = false)
    private IArticleTagRefDao articleTagRefDao;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insertArticle(Article article) {
        //添加文章
        article.setArticleCreateTime(new Date());
        article.setArticleUpdateTime(new Date());
        article.setArticleIsComment(ArticleCommentStatus.ALLOW.getValue());
        article.setArticleViewCount(0);
        article.setArticleLikeCount(0);
        article.setArticleCommentCount(0);
        article.setArticleOrder(1);
        articleDao.insert(article);
        //添加分类和文章关联
        for(int i=0;i<article.getCategoryList().size();i++){
            ArticleCategoryRef articleCategoryRef = new ArticleCategoryRef(article.getArticleId(),article.getCategoryList().get(i).getCategoryId());
            articleCategoryRefDao.insert(articleCategoryRef);
        }

        //添加标签和文章关联
        for(int i=0;i<article.getTagList().size();i++){
            ArticleTagRef articleTagRef = new ArticleTagRef(article.getArticleId(),article.getTagList().get(i).getTagId());
            articleTagRefDao.insert(articleTagRef);
        }

    }

    @Override
    public void deleteArticle(Integer articleId) {
        articleDao.deleteById(articleId);
    }

    @Override
    public void deleteArticleBatch(List<Integer> ids) {
        articleDao.deleteBatch(ids);
    }

    @Override
    public void updateArticle(Article article) {
        articleDao.update(article);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateArticleDetail(Article article) {
        article.setArticleUpdateTime(new Date());
        articleDao.update(article);
        if(article.getTagList()!=null){
            //删除标签和文章关联
            articleTagRefDao.deleteByArticleId(article.getArticleId());
            //添加标签和文章关联
            for(int i=0;i<article.getTagList().size();i++){
                ArticleTagRef articleTagRef = new ArticleTagRef(article.getArticleId(),article.getTagList().get(i).getTagId());
                articleTagRefDao.insert(articleTagRef);
            }
        }

        if(article.getCategoryList()!=null){
            //删除分类和文章关联
            articleCategoryRefDao.deleteByArticleId(article.getArticleId());
            //添加分类和文章关联
            for(int i=0;i<article.getCategoryList().size();i++){
                ArticleCategoryRef articleCategoryRef = new ArticleCategoryRef(article.getArticleId(),article.getCategoryList().get(i).getCategoryId());
                articleCategoryRefDao.insert(articleCategoryRef);
            }
        }
    }

    @Override
    public Integer getArticleUserId(Integer articleId) {
        return articleDao.getArticleUserId(articleId);
    }

    @Override
    public Integer countArticle(Integer status) {
        Integer count = 0;
        try{
            count = articleDao.countArticle(status);
        }catch(Exception e){
            e.printStackTrace();
            log.error("根据状态统计文章数，status:{},cause:{}",status,e);
        }
        return count;
    }

    @Override
    public Integer updateLikeCount(Integer articleId, Integer likeCount) {
        return articleDao.updateLikeCount(articleId, likeCount);
    }

    @Override
    public Integer countArticleComment() {
        Integer count = 0;
        try{
            count = articleDao.countArticleComment();
        }catch(Exception e){
            e.printStackTrace();
            log.error("统计文章评论数失败，cause:{}",e);
        }
        return count;    }

    @Override
    public Integer countArticleView() {
        Integer count = 0;
        try{
            count = articleDao.countArticleView();
        }catch(Exception e){
            e.printStackTrace();
            log.error("统计文章访问量失败，cause:{}",e);
        }
        return count;
    }

    @Override
    public Integer countArticleByCategoryId(Integer categoryId) {
        Integer count = 0;
        try{
            count = articleCategoryRefDao.countArticleByCategoryId(categoryId);
        }catch (Exception e){
            e.printStackTrace();
            log.error("根据分类统计文章数量失败,categoryId:{},cause:{}",categoryId,e);
        }
        return count;
    }

    @Override
    public PageInfo<Article> findArticleByEs(RestHighLevelClient client,HashMap<String, Object> criteria, Integer pageIndex, Integer pageSize) {

        String keywords = null;
        for(String key : criteria.keySet()){
            keywords = criteria.get(key).toString();
            System.out.println(criteria.get(key));
            break;
        }
        SearchRequest searchRequest = new SearchRequest();
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        //设置搜索结果起始行数下标，默认从0开始。
        sourceBuilder.from(0);
        //设置搜索结果显示条数
        sourceBuilder.size(10);
        //设置搜索结果超时时间
        sourceBuilder.timeout(new TimeValue(60, TimeUnit.SECONDS));

        QueryBuilder queryBuilder = null;
        MatchPhraseQueryBuilder mpqBuilder1 = null;
        MatchPhraseQueryBuilder mpqBuilder2 = null;
        MatchPhraseQueryBuilder mpqBuilder3 = null;
        String keyword = keywords;
        //如果关键词中包含"+"，则默认将其拆分为2部分，+的左半部分用来标识查找范围，+的右半部分是关键词
        if(keywords.indexOf("+")>-1){
            String[] slist = keywords.split("\\+");
            String range = slist[0];
            keyword = slist[1];
            //
            if(range.equals("articleTitle")){
                mpqBuilder1 = new MatchPhraseQueryBuilder("article_title",keyword);
                queryBuilder = QueryBuilders.boolQuery().must(mpqBuilder1);
            }else if(range.equals("articleContent")){
                mpqBuilder2 = new MatchPhraseQueryBuilder("article_content",keyword);
                queryBuilder = QueryBuilders.boolQuery().must(mpqBuilder2);
            }else if(range.equals("articleSummary")){
                mpqBuilder3 = new MatchPhraseQueryBuilder("article_summary",keyword);
                queryBuilder = QueryBuilders.boolQuery().must(mpqBuilder3);
            }
        }else{
            mpqBuilder1 = new MatchPhraseQueryBuilder("article_title",keyword);
            mpqBuilder2 = new MatchPhraseQueryBuilder("article_content",keyword);
            mpqBuilder3 = new MatchPhraseQueryBuilder("article_summary",keyword);
            queryBuilder = QueryBuilders.boolQuery().should(mpqBuilder1).should(mpqBuilder2).should(mpqBuilder3);
        }

        List<Article> articleList = new ArrayList<>();
        try{
            sourceBuilder.query(queryBuilder);
            //将搜索构造器类sourceBuilder装入SearchRequest类
            searchRequest.source(sourceBuilder);
            //client的search方法执行搜索，结果返回到SearchResponse类
            SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
            //获取搜索结果
            SearchHits hits = searchResponse.getHits();
            //搜索到的结果数
            long totalHits = hits.getTotalHits().value;
            System.out.println(totalHits+"................");
            //处理搜索到的文档结果
            SearchHit[] searchHits = hits.getHits();
            System.out.println(searchHits);
            for (SearchHit hit : searchHits){
                String str = hit.getSourceAsString();
                Article esArticle = JSON.parseObject(str,Article.class);
                articleList.add(esArticle);
            }
        }catch (Exception f){
            System.out.println("\n-------------------------------------------搜索结果为空或异常-------------------------------------------\n"+f.toString());
        }
        /*SearchRequest searchRequest = new SearchRequest("article");

        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();

        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        for(String key:criteria.keySet()){
            boolQueryBuilder.must(QueryBuilders.matchQuery(key,criteria.get(key)));
        }
        sourceBuilder.query(boolQueryBuilder);
        sourceBuilder.from(pageIndex);
        sourceBuilder.size(pageSize);
        sourceBuilder.timeout(new TimeValue(60, TimeUnit.SECONDS));*/

        //sort
        //sourceBuilder.sort(new ScoreSortBuilder().order(SortOrder.DESC));

        //highlight
        HighlightBuilder highlightBuilder = new HighlightBuilder();
        HighlightBuilder.Field highlightTitle = new HighlightBuilder.Field("ArticleTitle");
        highlightTitle.preTags("<span class=\"highlight\">");
        highlightTitle.postTags("</span>");
        highlightBuilder.field(highlightTitle);
        sourceBuilder.highlighter(highlightBuilder);

        searchRequest.indices("article");
        searchRequest.source(sourceBuilder);


        System.out.println(searchRequest);
        SearchResponse searchResponse = null;
        List<Article> articleLists = new ArrayList<>();
        try{
            searchResponse = highLevelClient.search(searchRequest, RequestOptions.DEFAULT);
            SearchHits hits = searchResponse.getHits();

            long totalHits = hits.getTotalHits().value;
            System.out.println(totalHits);
            SearchHit[] searchHits = hits.getHits();
            for(SearchHit hit:searchHits){
                String str = hit.getSourceAsString();
                Article esArticle = JSON.parseObject(str,Article.class);
                System.out.println(esArticle);
                //高亮
                Map<String, HighlightField> highlightFields = hit.getHighlightFields();
                HighlightField highlight= highlightFields.get("articleTitle");
                if(highlight!=null){
                    Text[] fragments = highlight.fragments();
                    String fragmentString = fragments[0].string();
                    esArticle.setArticleTitle(fragmentString);
                }
                articleLists.add(esArticle);

            }



        }catch(Exception e){
            e.printStackTrace();
        }

        //return new PageInfo<>(articleLists);
        return new PageInfo<>(articleLists);
    }

    @Override
    public Integer countArticleByTagId(Integer tagId) {
        return articleTagRefDao.countArticleByTagId(tagId);
    }

    @Override
    public List<Article> listArticle(HashMap<String, Object> criteria) {
        return articleDao.findAll(criteria);
    }

    @Override
    public List<Article> listRecentArticle(Integer limit) {
        return articleDao.listArticleByLimit(limit);
    }

    @Override
    public PageInfo<Article> pageArticle(Integer pageIndex, Integer pageSize, HashMap<String, Object> criteria) {
        PageHelper.startPage(pageIndex,pageSize);
        List<Article> articleList = articleDao.findAll(criteria);
        for(int i=0;i<articleList.size();i++){
            //封装CategoryList
            List<Category> categoryList = articleCategoryRefDao.listCategoryByArticleId(articleList.get(i).getArticleId());
            if(categoryList == null || categoryList.size()==0){
                categoryList = new ArrayList<>();
                categoryList.add(Category.Default());
            }
            articleList.get(i).setCategoryList(categoryList);

        }
        return new PageInfo<>(articleList);
    }

    @Override
    public Article getArticleByStatusAndId(Integer status, Integer id) {

        Article article = articleDao.getArticleByStatusAndId(status,id);
        if(article!=null){
            List<Category> categoryList = articleCategoryRefDao.listCategoryByArticleId(article.getArticleId());
            List<Tag> tagList = articleTagRefDao.listTagByArticleId(article.getArticleId());
            article.setCategoryList(categoryList);
            article.setTagList(tagList);
        }
        return article;
    }

    @Override
    public List<Article> listArticleByViewCount(Integer limit) {
        return articleDao.listArticleByViewCount(limit);
    }

    @Override
    public Article getPreArticle(Integer articleId) {
        return articleDao.getPreArticle(articleId);
    }

    @Override
    public Article getAfterArticle(Integer articleId) {
        return articleDao.getAfterArticle(articleId);
    }

    @Override
    public List<Article> listRandomArticle(Integer limit) {
        return articleDao.listArticleByCommentCount(limit);
    }

    @Override
    public List<Article> listArticleByCommentCount(Integer limit) {
        return articleDao.listArticleByCommentCount(limit);
    }

    @Override
    public void updateCommentCount(Integer articleId) {
        articleDao.updateCommentCount(articleId);
    }

    @Override
    public Article getLastUpdateArticle() {
        return articleDao.getLastUpdateArticle();
    }

    @Override
    public List<Article> listArticleByCategoryId(Integer cateId, Integer limit) {
        return articleDao.findArticleByCategoryId(cateId,limit);
    }


    @Override
    public List<Article> listArticleByCategoryIds(List<Integer> cateIds, Integer limit) {
        if(cateIds == null || cateIds.size() == 0){
            return null;
        }
        return articleDao.findArticleByCategoryIds(cateIds,limit);
    }

    @Override
    public List<Integer> listCategoryByArticleId(Integer articleId) {
        return articleCategoryRefDao.selectCategoryByArticleId(articleId);
    }

    @Override
    public List<Article> listAllNotWithContent() {
        return articleDao.listAllNotWithContent();
    }
}
