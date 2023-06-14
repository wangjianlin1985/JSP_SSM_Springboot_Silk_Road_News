package com.chengxusheji.service;

import java.util.ArrayList;
import javax.annotation.Resource; 
import org.springframework.stereotype.Service;
import com.chengxusheji.po.ArticleClass;

import com.chengxusheji.mapper.ArticleClassMapper;
@Service
public class ArticleClassService {

	@Resource ArticleClassMapper articleClassMapper;
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

    /*添加文章分类记录*/
    public void addArticleClass(ArticleClass articleClass) throws Exception {
    	articleClassMapper.addArticleClass(articleClass);
    }

    /*按照查询条件分页查询文章分类记录*/
    public ArrayList<ArticleClass> queryArticleClass(int currentPage) throws Exception { 
     	String where = "where 1=1";
    	int startIndex = (currentPage-1) * this.rows;
    	return articleClassMapper.queryArticleClass(where, startIndex, this.rows);
    }

    /*按照查询条件查询所有记录*/
    public ArrayList<ArticleClass> queryArticleClass() throws Exception  { 
     	String where = "where 1=1";
    	return articleClassMapper.queryArticleClassList(where);
    }

    /*查询所有文章分类记录*/
    public ArrayList<ArticleClass> queryAllArticleClass()  throws Exception {
        return articleClassMapper.queryArticleClassList("where 1=1");
    }

    /*当前查询条件下计算总的页数和记录数*/
    public void queryTotalPageAndRecordNumber() throws Exception {
     	String where = "where 1=1";
        recordNumber = articleClassMapper.queryArticleClassCount(where);
        int mod = recordNumber % this.rows;
        totalPage = recordNumber / this.rows;
        if(mod != 0) totalPage++;
    }

    /*根据主键获取文章分类记录*/
    public ArticleClass getArticleClass(int classId) throws Exception  {
        ArticleClass articleClass = articleClassMapper.getArticleClass(classId);
        return articleClass;
    }

    /*更新文章分类记录*/
    public void updateArticleClass(ArticleClass articleClass) throws Exception {
        articleClassMapper.updateArticleClass(articleClass);
    }

    /*删除一条文章分类记录*/
    public void deleteArticleClass (int classId) throws Exception {
        articleClassMapper.deleteArticleClass(classId);
    }

    /*删除多条文章分类信息*/
    public int deleteArticleClasss (String classIds) throws Exception {
    	String _classIds[] = classIds.split(",");
    	for(String _classId: _classIds) {
    		articleClassMapper.deleteArticleClass(Integer.parseInt(_classId));
    	}
    	return _classIds.length;
    }
}
