package com.chengxusheji.mapper;

import java.util.ArrayList;
import org.apache.ibatis.annotations.Param;
import com.chengxusheji.po.Article;

public interface ArticleMapper {
	/*添加文章信息*/
	public void addArticle(Article article) throws Exception;

	/*按照查询条件分页查询文章记录*/
	public ArrayList<Article> queryArticle(@Param("where") String where,@Param("startIndex") int startIndex,@Param("pageSize") int pageSize) throws Exception;

	/*按照查询条件查询所有文章记录*/
	public ArrayList<Article> queryArticleList(@Param("where") String where) throws Exception;

	/*按照查询条件的文章记录数*/
	public int queryArticleCount(@Param("where") String where) throws Exception; 

	/*根据主键查询某条文章记录*/
	public Article getArticle(int articleId) throws Exception;

	/*更新文章记录*/
	public void updateArticle(Article article) throws Exception;

	/*删除文章记录*/
	public void deleteArticle(int articleId) throws Exception;

}
