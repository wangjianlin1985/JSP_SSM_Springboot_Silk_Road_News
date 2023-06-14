package com.chengxusheji.mapper;

import java.util.ArrayList;
import org.apache.ibatis.annotations.Param;
import com.chengxusheji.po.ArticleClass;

public interface ArticleClassMapper {
	/*添加文章分类信息*/
	public void addArticleClass(ArticleClass articleClass) throws Exception;

	/*按照查询条件分页查询文章分类记录*/
	public ArrayList<ArticleClass> queryArticleClass(@Param("where") String where,@Param("startIndex") int startIndex,@Param("pageSize") int pageSize) throws Exception;

	/*按照查询条件查询所有文章分类记录*/
	public ArrayList<ArticleClass> queryArticleClassList(@Param("where") String where) throws Exception;

	/*按照查询条件的文章分类记录数*/
	public int queryArticleClassCount(@Param("where") String where) throws Exception; 

	/*根据主键查询某条文章分类记录*/
	public ArticleClass getArticleClass(int classId) throws Exception;

	/*更新文章分类记录*/
	public void updateArticleClass(ArticleClass articleClass) throws Exception;

	/*删除文章分类记录*/
	public void deleteArticleClass(int classId) throws Exception;

}
