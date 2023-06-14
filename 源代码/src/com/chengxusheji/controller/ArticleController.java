package com.chengxusheji.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.chengxusheji.utils.ExportExcelUtil;
import com.chengxusheji.utils.UserException;
import com.chengxusheji.service.ArticleService;
import com.chengxusheji.po.Article;
import com.chengxusheji.service.ArticleClassService;
import com.chengxusheji.po.ArticleClass;

//Article管理控制层
@Controller
@RequestMapping("/Article")
public class ArticleController extends BaseController {

    /*业务层对象*/
    @Resource ArticleService articleService;

    @Resource ArticleClassService articleClassService;
	@InitBinder("articleClassObj")
	public void initBinderarticleClassObj(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("articleClassObj.");
	}
	@InitBinder("article")
	public void initBinderArticle(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("article.");
	}
	/*跳转到添加Article视图*/
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add(Model model,HttpServletRequest request) throws Exception {
		model.addAttribute(new Article());
		/*查询所有的ArticleClass信息*/
		List<ArticleClass> articleClassList = articleClassService.queryAllArticleClass();
		request.setAttribute("articleClassList", articleClassList);
		return "Article_add";
	}

	/*客户端ajax方式提交添加文章信息*/
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public void add(@Validated Article article, BindingResult br,
			Model model, HttpServletRequest request,HttpServletResponse response) throws Exception {
		String message = "";
		boolean success = false;
		if (br.hasErrors()) {
			message = "输入信息不符合要求！";
			writeJsonResponse(response, success, message);
			return ;
		}
		try {
			article.setArticlePhoto(this.handlePhotoUpload(request, "articlePhotoFile"));
		} catch(UserException ex) {
			message = "图片格式不正确！";
			writeJsonResponse(response, success, message);
			return ;
		}
        articleService.addArticle(article);
        message = "文章添加成功!";
        success = true;
        writeJsonResponse(response, success, message);
	}
	/*ajax方式按照查询条件分页查询文章信息*/
	@RequestMapping(value = { "/list" }, method = {RequestMethod.GET,RequestMethod.POST})
	public void list(String title,@ModelAttribute("articleClassObj") ArticleClass articleClassObj,String addTime,Integer page,Integer rows, Model model, HttpServletRequest request,HttpServletResponse response) throws Exception {
		if (page==null || page == 0) page = 1;
		if (title == null) title = "";
		if (addTime == null) addTime = "";
		if(rows != 0)articleService.setRows(rows);
		List<Article> articleList = articleService.queryArticle(title, articleClassObj, addTime, page);
	    /*计算总的页数和总的记录数*/
	    articleService.queryTotalPageAndRecordNumber(title, articleClassObj, addTime);
	    /*获取到总的页码数目*/
	    int totalPage = articleService.getTotalPage();
	    /*当前查询条件下总记录数*/
	    int recordNumber = articleService.getRecordNumber();
        response.setContentType("text/json;charset=UTF-8");
		PrintWriter out = response.getWriter();
		//将要被返回到客户端的对象
		JSONObject jsonObj=new JSONObject();
		jsonObj.accumulate("total", recordNumber);
		JSONArray jsonArray = new JSONArray();
		for(Article article:articleList) {
			JSONObject jsonArticle = article.getJsonObject();
			jsonArray.put(jsonArticle);
		}
		jsonObj.accumulate("rows", jsonArray);
		out.println(jsonObj.toString());
		out.flush();
		out.close();
	}

	/*ajax方式按照查询条件分页查询文章信息*/
	@RequestMapping(value = { "/listAll" }, method = {RequestMethod.GET,RequestMethod.POST})
	public void listAll(HttpServletResponse response) throws Exception {
		List<Article> articleList = articleService.queryAllArticle();
        response.setContentType("text/json;charset=UTF-8"); 
		PrintWriter out = response.getWriter();
		JSONArray jsonArray = new JSONArray();
		for(Article article:articleList) {
			JSONObject jsonArticle = new JSONObject();
			jsonArticle.accumulate("articleId", article.getArticleId());
			jsonArticle.accumulate("title", article.getTitle());
			jsonArray.put(jsonArticle);
		}
		out.println(jsonArray.toString());
		out.flush();
		out.close();
	}

	/*前台按照查询条件分页查询文章信息*/
	@RequestMapping(value = { "/frontlist" }, method = {RequestMethod.GET,RequestMethod.POST})
	public String frontlist(String title,@ModelAttribute("articleClassObj") ArticleClass articleClassObj,String addTime,Integer currentPage, Model model, HttpServletRequest request) throws Exception  {
		if (currentPage==null || currentPage == 0) currentPage = 1;
		if (title == null) title = "";
		if (addTime == null) addTime = "";
		List<Article> articleList = articleService.queryArticle(title, articleClassObj, addTime, currentPage);
	    /*计算总的页数和总的记录数*/
	    articleService.queryTotalPageAndRecordNumber(title, articleClassObj, addTime);
	    /*获取到总的页码数目*/
	    int totalPage = articleService.getTotalPage();
	    /*当前查询条件下总记录数*/
	    int recordNumber = articleService.getRecordNumber();
	    request.setAttribute("articleList",  articleList);
	    request.setAttribute("totalPage", totalPage);
	    request.setAttribute("recordNumber", recordNumber);
	    request.setAttribute("currentPage", currentPage);
	    request.setAttribute("title", title);
	    request.setAttribute("articleClassObj", articleClassObj);
	    request.setAttribute("addTime", addTime);
	    List<ArticleClass> articleClassList = articleClassService.queryAllArticleClass();
	    request.setAttribute("articleClassList", articleClassList);
		return "Article/article_frontquery_result"; 
	}

     /*前台查询Article信息*/
	@RequestMapping(value="/{articleId}/frontshow",method=RequestMethod.GET)
	public String frontshow(@PathVariable Integer articleId,Model model,HttpServletRequest request) throws Exception {
		/*根据主键articleId获取Article对象*/
        Article article = articleService.getArticle(articleId);

        List<ArticleClass> articleClassList = articleClassService.queryAllArticleClass();
        request.setAttribute("articleClassList", articleClassList);
        request.setAttribute("article",  article);
        return "Article/article_frontshow";
	}

	/*ajax方式显示文章修改jsp视图页*/
	@RequestMapping(value="/{articleId}/update",method=RequestMethod.GET)
	public void update(@PathVariable Integer articleId,Model model,HttpServletRequest request,HttpServletResponse response) throws Exception {
        /*根据主键articleId获取Article对象*/
        Article article = articleService.getArticle(articleId);

        response.setContentType("text/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
		//将要被返回到客户端的对象 
		JSONObject jsonArticle = article.getJsonObject();
		out.println(jsonArticle.toString());
		out.flush();
		out.close();
	}

	/*ajax方式更新文章信息*/
	@RequestMapping(value = "/{articleId}/update", method = RequestMethod.POST)
	public void update(@Validated Article article, BindingResult br,
			Model model, HttpServletRequest request,HttpServletResponse response) throws Exception {
		String message = "";
    	boolean success = false;
		if (br.hasErrors()) { 
			message = "输入的信息有错误！";
			writeJsonResponse(response, success, message);
			return;
		}
		String articlePhotoFileName = this.handlePhotoUpload(request, "articlePhotoFile");
		if(!articlePhotoFileName.equals("upload/NoImage.jpg"))article.setArticlePhoto(articlePhotoFileName); 


		try {
			articleService.updateArticle(article);
			message = "文章更新成功!";
			success = true;
			writeJsonResponse(response, success, message);
		} catch (Exception e) {
			e.printStackTrace();
			message = "文章更新失败!";
			writeJsonResponse(response, success, message); 
		}
	}
    /*删除文章信息*/
	@RequestMapping(value="/{articleId}/delete",method=RequestMethod.GET)
	public String delete(@PathVariable Integer articleId,HttpServletRequest request) throws UnsupportedEncodingException {
		  try {
			  articleService.deleteArticle(articleId);
	            request.setAttribute("message", "文章删除成功!");
	            return "message";
	        } catch (Exception e) { 
	            e.printStackTrace();
	            request.setAttribute("error", "文章删除失败!");
				return "error";

	        }

	}

	/*ajax方式删除多条文章记录*/
	@RequestMapping(value="/deletes",method=RequestMethod.POST)
	public void delete(String articleIds,HttpServletRequest request,HttpServletResponse response) throws IOException, JSONException {
		String message = "";
    	boolean success = false;
        try { 
        	int count = articleService.deleteArticles(articleIds);
        	success = true;
        	message = count + "条记录删除成功";
        	writeJsonResponse(response, success, message);
        } catch (Exception e) { 
            //e.printStackTrace();
            message = "有记录存在外键约束,删除失败";
            writeJsonResponse(response, success, message);
        }
	}

	/*按照查询条件导出文章信息到Excel*/
	@RequestMapping(value = { "/OutToExcel" }, method = {RequestMethod.GET,RequestMethod.POST})
	public void OutToExcel(String title,@ModelAttribute("articleClassObj") ArticleClass articleClassObj,String addTime, Model model, HttpServletRequest request,HttpServletResponse response) throws Exception {
        if(title == null) title = "";
        if(addTime == null) addTime = "";
        List<Article> articleList = articleService.queryArticle(title,articleClassObj,addTime);
        ExportExcelUtil ex = new ExportExcelUtil();
        String _title = "Article信息记录"; 
        String[] headers = { "文章标题","文章类别","文章主图","作者","来源","添加时间"};
        List<String[]> dataset = new ArrayList<String[]>(); 
        for(int i=0;i<articleList.size();i++) {
        	Article article = articleList.get(i); 
        	dataset.add(new String[]{article.getTitle(),article.getArticleClassObj().getClassName(),article.getArticlePhoto(),article.getAuthor(),article.getComeFrom(),article.getAddTime()});
        }
        /*
        OutputStream out = null;
		try {
			out = new FileOutputStream("C://output.xls");
			ex.exportExcel(title,headers, dataset, out);
		    out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		*/
		OutputStream out = null;//创建一个输出流对象 
		try { 
			out = response.getOutputStream();//
			response.setHeader("Content-disposition","attachment; filename="+"Article.xls");//filename是下载的xls的名，建议最好用英文 
			response.setContentType("application/msexcel;charset=UTF-8");//设置类型 
			response.setHeader("Pragma","No-cache");//设置头 
			response.setHeader("Cache-Control","no-cache");//设置头 
			response.setDateHeader("Expires", 0);//设置日期头  
			String rootPath = request.getSession().getServletContext().getRealPath("/");
			ex.exportExcel(rootPath,_title,headers, dataset, out);
			out.flush();
		} catch (IOException e) { 
			e.printStackTrace(); 
		}finally{
			try{
				if(out!=null){ 
					out.close(); 
				}
			}catch(IOException e){ 
				e.printStackTrace(); 
			} 
		}
    }
}
