package com.chengxusheji.po;

import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotEmpty;
import org.json.JSONException;
import org.json.JSONObject;

public class Article {
    /*文章id*/
    private Integer articleId;
    public Integer getArticleId(){
        return articleId;
    }
    public void setArticleId(Integer articleId){
        this.articleId = articleId;
    }

    /*文章标题*/
    @NotEmpty(message="文章标题不能为空")
    private String title;
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    /*文章类别*/
    private ArticleClass articleClassObj;
    public ArticleClass getArticleClassObj() {
        return articleClassObj;
    }
    public void setArticleClassObj(ArticleClass articleClassObj) {
        this.articleClassObj = articleClassObj;
    }

    /*文章主图*/
    private String articlePhoto;
    public String getArticlePhoto() {
        return articlePhoto;
    }
    public void setArticlePhoto(String articlePhoto) {
        this.articlePhoto = articlePhoto;
    }

    /*文章内容*/
    @NotEmpty(message="文章内容不能为空")
    private String content;
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }

    /*作者*/
    private String author;
    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }

    /*来源*/
    private String comeFrom;
    public String getComeFrom() {
        return comeFrom;
    }
    public void setComeFrom(String comeFrom) {
        this.comeFrom = comeFrom;
    }

    /*添加时间*/
    private String addTime;
    public String getAddTime() {
        return addTime;
    }
    public void setAddTime(String addTime) {
        this.addTime = addTime;
    }

    public JSONObject getJsonObject() throws JSONException {
    	JSONObject jsonArticle=new JSONObject(); 
		jsonArticle.accumulate("articleId", this.getArticleId());
		jsonArticle.accumulate("title", this.getTitle());
		jsonArticle.accumulate("articleClassObj", this.getArticleClassObj().getClassName());
		jsonArticle.accumulate("articleClassObjPri", this.getArticleClassObj().getClassId());
		jsonArticle.accumulate("articlePhoto", this.getArticlePhoto());
		jsonArticle.accumulate("content", this.getContent());
		jsonArticle.accumulate("author", this.getAuthor());
		jsonArticle.accumulate("comeFrom", this.getComeFrom());
		jsonArticle.accumulate("addTime", this.getAddTime());
		return jsonArticle;
    }}