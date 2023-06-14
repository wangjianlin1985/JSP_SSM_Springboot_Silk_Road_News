package com.chengxusheji.service;

import java.util.ArrayList;
import javax.annotation.Resource; 
import org.springframework.stereotype.Service;
import com.chengxusheji.po.ArticleClass;
import com.chengxusheji.po.Article;

import com.chengxusheji.mapper.ArticleMapper;
@Service
public class ArticleService {

	@Resource ArticleMapper articleMapper;
    /*每页显示记录数目*/
    private int rows = 10;;
    public int getRows() {
		return rows;
	}
	public void setRows(int rows) {
		this.rows = rows;
	}

    /*保存查询后总的页数*/
    private int totalPage;
    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }
    public int getTotalPage() {
        return totalPage;
    }

    /*保存查询到的总记录数*/
    private int recordNumber;
    public void setRecordNumber(int recordNumber) {
        this.recordNumber = recordNumber;
    }
    public int getRecordNumber() {
        return recordNumber;
    }

    /*添加文章记录*/
    public void addArticle(Article article) throws Exception {
    	articleMapper.addArticle(article);
    }

    /*按照查询条件分页查询文章记录*/
    public ArrayList<Article> queryArticle(String title,ArticleClass articleClassObj,String addTime,int currentPage) throws Exception { 
     	String where = "where 1=1";
    	if(!title.equals("")) where = where + " and t_article.title like '%" + title + "%'";
    	if(null != articleClassObj && articleClassObj.getClassId()!= null && articleClassObj.getClassId()!= 0)  where += " and t_article.articleClassObj=" + articleClassObj.getClassId();
    	if(!addTime.equals("")) where = where + " and t_article.addTime like '%" + addTime + "%'";
    	int startIndex = (currentPage-1) * this.rows;
    	return articleMapper.queryArticle(where, startIndex, this.rows);
    }

    /*按照查询条件查询所有记录*/
    public ArrayList<Article> queryArticle(String title,ArticleClass articleClassObj,String addTime) throws Exception  { 
     	String where = "where 1=1";
    	if(!title.equals("")) where = where + " and t_article.title like '%" + title + "%'";
    	if(null != articleClassObj && articleClassObj.getClassId()!= null && articleClassObj.getClassId()!= 0)  where += " and t_article.articleClassObj=" + articleClassObj.getClassId();
    	if(!addTime.equals("")) where = where + " and t_article.addTime like '%" + addTime + "%'";
    	return articleMapper.queryArticleList(where);
    }

    /*查询所有文章记录*/
    public ArrayList<Article> queryAllArticle()  throws Exception {
        return articleMapper.queryArticleList("where 1=1");
    }

    /*当前查询条件下计算总的页数和记录数*/
    public void queryTotalPageAndRecordNumber(String title,ArticleClass articleClassObj,String addTime) throws Exception {
     	String where = "where 1=1";
    	if(!title.equals("")) where = where + " and t_article.title like '%" + title + "%'";
    	if(null != articleClassObj && articleClassObj.getClassId()!= null && articleClassObj.getClassId()!= 0)  where += " and t_article.articleClassObj=" + articleClassObj.getClassId();
    	if(!addTime.equals("")) where = where + " and t_article.addTime like '%" + addTime + "%'";
        recordNumber = articleMapper.queryArticleCount(where);
        int mod = recordNumber % this.rows;
        totalPage = recordNumber / this.rows;
        if(mod != 0) totalPage++;
    }

    /*根据主键获取文章记录*/
    public Article getArticle(int articleId) throws Exception  {
        Article article = articleMapper.getArticle(articleId);
        return article;
    }

    /*更新文章记录*/
    public void updateArticle(Article article) throws Exception {
        articleMapper.updateArticle(article);
    }

    /*删除一条文章记录*/
    public void deleteArticle (int articleId) throws Exception {
        articleMapper.deleteArticle(articleId);
    }

    /*删除多条文章信息*/
    public int deleteArticles (String articleIds) throws Exception {
    	String _articleIds[] = articleIds.split(",");
    	for(String _articleId: _articleIds) {
    		articleMapper.deleteArticle(Integer.parseInt(_articleId));
    	}
    	return _articleIds.length;
    }
}
