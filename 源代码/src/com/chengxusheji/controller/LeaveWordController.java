﻿package com.chengxusheji.controller;

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
import com.chengxusheji.service.LeaveWordService;
import com.chengxusheji.po.LeaveWord;
import com.chengxusheji.service.UserInfoService;
import com.chengxusheji.po.UserInfo;

//LeaveWord管理控制层
@Controller
@RequestMapping("/LeaveWord")
public class LeaveWordController extends BaseController {

    /*业务层对象*/
    @Resource LeaveWordService leaveWordService;

    @Resource UserInfoService userInfoService;
	@InitBinder("userObj")
	public void initBinderuserObj(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("userObj.");
	}
	@InitBinder("leaveWord")
	public void initBinderLeaveWord(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("leaveWord.");
	}
	/*跳转到添加LeaveWord视图*/
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add(Model model,HttpServletRequest request) throws Exception {
		model.addAttribute(new LeaveWord());
		/*查询所有的UserInfo信息*/
		List<UserInfo> userInfoList = userInfoService.queryAllUserInfo();
		request.setAttribute("userInfoList", userInfoList);
		return "LeaveWord_add";
	}

	/*客户端ajax方式提交添加留言建议信息*/
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public void add(@Validated LeaveWord leaveWord, BindingResult br,
			Model model, HttpServletRequest request,HttpServletResponse response) throws Exception {
		String message = "";
		boolean success = false;
		if (br.hasErrors()) {
			message = "输入信息不符合要求！";
			writeJsonResponse(response, success, message);
			return ;
		}
        leaveWordService.addLeaveWord(leaveWord);
        message = "留言建议添加成功!";
        success = true;
        writeJsonResponse(response, success, message);
	}
	
	
	/*客户端ajax方式提交添加留言建议信息*/
	@RequestMapping(value = "/userAdd", method = RequestMethod.POST)
	public void userAdd(LeaveWord leaveWord,
			Model model, HttpServletRequest request,HttpServletResponse response,HttpSession session) throws Exception {
		String message = "";
		boolean success = false;
		String user_name = (String)session.getAttribute("user_name");
		UserInfo userObj = new UserInfo();
		userObj.setUser_name(user_name);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		leaveWord.setLeaveTime(sdf.format(new java.util.Date()));
		leaveWord.setReplyContent("--");
		leaveWord.setReplyTime("--"); 
		leaveWord.setUserObj(userObj); 
        leaveWordService.addLeaveWord(leaveWord);
        message = "留言建议发布成功!";
        success = true;
        writeJsonResponse(response, success, message);
	}
	
	
	/*ajax方式按照查询条件分页查询留言建议信息*/
	@RequestMapping(value = { "/list" }, method = {RequestMethod.GET,RequestMethod.POST})
	public void list(String title,String leaveTime,@ModelAttribute("userObj") UserInfo userObj,Integer page,Integer rows, Model model, HttpServletRequest request,HttpServletResponse response) throws Exception {
		if (page==null || page == 0) page = 1;
		if (title == null) title = "";
		if (leaveTime == null) leaveTime = "";
		if(rows != 0)leaveWordService.setRows(rows);
		List<LeaveWord> leaveWordList = leaveWordService.queryLeaveWord(title, leaveTime, userObj, page);
	    /*计算总的页数和总的记录数*/
	    leaveWordService.queryTotalPageAndRecordNumber(title, leaveTime, userObj);
	    /*获取到总的页码数目*/
	    int totalPage = leaveWordService.getTotalPage();
	    /*当前查询条件下总记录数*/
	    int recordNumber = leaveWordService.getRecordNumber();
        response.setContentType("text/json;charset=UTF-8");
		PrintWriter out = response.getWriter();
		//将要被返回到客户端的对象
		JSONObject jsonObj=new JSONObject();
		jsonObj.accumulate("total", recordNumber);
		JSONArray jsonArray = new JSONArray();
		for(LeaveWord leaveWord:leaveWordList) {
			JSONObject jsonLeaveWord = leaveWord.getJsonObject();
			jsonArray.put(jsonLeaveWord);
		}
		jsonObj.accumulate("rows", jsonArray);
		out.println(jsonObj.toString());
		out.flush();
		out.close();
	}

	/*ajax方式按照查询条件分页查询留言建议信息*/
	@RequestMapping(value = { "/listAll" }, method = {RequestMethod.GET,RequestMethod.POST})
	public void listAll(HttpServletResponse response) throws Exception {
		List<LeaveWord> leaveWordList = leaveWordService.queryAllLeaveWord();
        response.setContentType("text/json;charset=UTF-8"); 
		PrintWriter out = response.getWriter();
		JSONArray jsonArray = new JSONArray();
		for(LeaveWord leaveWord:leaveWordList) {
			JSONObject jsonLeaveWord = new JSONObject();
			jsonLeaveWord.accumulate("leaveId", leaveWord.getLeaveId());
			jsonLeaveWord.accumulate("title", leaveWord.getTitle());
			jsonArray.put(jsonLeaveWord);
		}
		out.println(jsonArray.toString());
		out.flush();
		out.close();
	}

	/*前台按照查询条件分页查询留言建议信息*/
	@RequestMapping(value = { "/frontlist" }, method = {RequestMethod.GET,RequestMethod.POST})
	public String frontlist(String title,String leaveTime,@ModelAttribute("userObj") UserInfo userObj,Integer currentPage, Model model, HttpServletRequest request) throws Exception  {
		if (currentPage==null || currentPage == 0) currentPage = 1;
		if (title == null) title = "";
		if (leaveTime == null) leaveTime = "";
		List<LeaveWord> leaveWordList = leaveWordService.queryLeaveWord(title, leaveTime, userObj, currentPage);
	    /*计算总的页数和总的记录数*/
	    leaveWordService.queryTotalPageAndRecordNumber(title, leaveTime, userObj);
	    /*获取到总的页码数目*/
	    int totalPage = leaveWordService.getTotalPage();
	    /*当前查询条件下总记录数*/
	    int recordNumber = leaveWordService.getRecordNumber();
	    request.setAttribute("leaveWordList",  leaveWordList);
	    request.setAttribute("totalPage", totalPage);
	    request.setAttribute("recordNumber", recordNumber);
	    request.setAttribute("currentPage", currentPage);
	    request.setAttribute("title", title);
	    request.setAttribute("leaveTime", leaveTime);
	    request.setAttribute("userObj", userObj);
	    List<UserInfo> userInfoList = userInfoService.queryAllUserInfo();
	    request.setAttribute("userInfoList", userInfoList);
		return "LeaveWord/leaveWord_frontquery_result"; 
	}
	
	
	/*前台用户按照查询条件分页查询自己的留言建议信息*/
	@RequestMapping(value = { "/userFrontlist" }, method = {RequestMethod.GET,RequestMethod.POST})
	public String userFrontlist(String title,String leaveTime,@ModelAttribute("userObj") UserInfo userObj,Integer currentPage, Model model, HttpServletRequest request,HttpSession session) throws Exception  {
		if (currentPage==null || currentPage == 0) currentPage = 1;
		if (title == null) title = "";
		if (leaveTime == null) leaveTime = "";
		String userName = (String)session.getAttribute("user_name");
		userObj = new UserInfo();
		userObj.setUser_name(userName);
		List<LeaveWord> leaveWordList = leaveWordService.queryLeaveWord(title, leaveTime, userObj, currentPage);
	    /*计算总的页数和总的记录数*/
	    leaveWordService.queryTotalPageAndRecordNumber(title, leaveTime, userObj);
	    /*获取到总的页码数目*/
	    int totalPage = leaveWordService.getTotalPage();
	    /*当前查询条件下总记录数*/
	    int recordNumber = leaveWordService.getRecordNumber();
	    request.setAttribute("leaveWordList",  leaveWordList);
	    request.setAttribute("totalPage", totalPage);
	    request.setAttribute("recordNumber", recordNumber);
	    request.setAttribute("currentPage", currentPage);
	    request.setAttribute("title", title);
	    request.setAttribute("leaveTime", leaveTime);
	    request.setAttribute("userObj", userObj);
	    List<UserInfo> userInfoList = userInfoService.queryAllUserInfo();
	    request.setAttribute("userInfoList", userInfoList);
		return "LeaveWord/leaveWord_userFrontquery_result"; 
	}

	

     /*前台查询LeaveWord信息*/
	@RequestMapping(value="/{leaveId}/frontshow",method=RequestMethod.GET)
	public String frontshow(@PathVariable Integer leaveId,Model model,HttpServletRequest request) throws Exception {
		/*根据主键leaveId获取LeaveWord对象*/
        LeaveWord leaveWord = leaveWordService.getLeaveWord(leaveId);

        List<UserInfo> userInfoList = userInfoService.queryAllUserInfo();
        request.setAttribute("userInfoList", userInfoList);
        request.setAttribute("leaveWord",  leaveWord);
        return "LeaveWord/leaveWord_frontshow";
	}

	/*ajax方式显示留言建议修改jsp视图页*/
	@RequestMapping(value="/{leaveId}/update",method=RequestMethod.GET)
	public void update(@PathVariable Integer leaveId,Model model,HttpServletRequest request,HttpServletResponse response) throws Exception {
        /*根据主键leaveId获取LeaveWord对象*/
        LeaveWord leaveWord = leaveWordService.getLeaveWord(leaveId);

        response.setContentType("text/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
		//将要被返回到客户端的对象 
		JSONObject jsonLeaveWord = leaveWord.getJsonObject();
		out.println(jsonLeaveWord.toString());
		out.flush();
		out.close();
	}

	/*ajax方式更新留言建议信息*/
	@RequestMapping(value = "/{leaveId}/update", method = RequestMethod.POST)
	public void update(@Validated LeaveWord leaveWord, BindingResult br,
			Model model, HttpServletRequest request,HttpServletResponse response) throws Exception {
		String message = "";
    	boolean success = false;
		if (br.hasErrors()) { 
			message = "输入的信息有错误！";
			writeJsonResponse(response, success, message);
			return;
		}
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			leaveWord.setReplyTime(sdf.format(new java.util.Date()));
			leaveWordService.updateLeaveWord(leaveWord);
			message = "留言回复更新成功!";
			success = true;
			writeJsonResponse(response, success, message);
		} catch (Exception e) {
			e.printStackTrace();
			message = "留言建议更新失败!";
			writeJsonResponse(response, success, message); 
		}
	}
    /*删除留言建议信息*/
	@RequestMapping(value="/{leaveId}/delete",method=RequestMethod.GET)
	public String delete(@PathVariable Integer leaveId,HttpServletRequest request) throws UnsupportedEncodingException {
		  try {
			  leaveWordService.deleteLeaveWord(leaveId);
	            request.setAttribute("message", "留言建议删除成功!");
	            return "message";
	        } catch (Exception e) { 
	            e.printStackTrace();
	            request.setAttribute("error", "留言建议删除失败!");
				return "error";

	        }

	}

	/*ajax方式删除多条留言建议记录*/
	@RequestMapping(value="/deletes",method=RequestMethod.POST)
	public void delete(String leaveIds,HttpServletRequest request,HttpServletResponse response) throws IOException, JSONException {
		String message = "";
    	boolean success = false;
        try { 
        	int count = leaveWordService.deleteLeaveWords(leaveIds);
        	success = true;
        	message = count + "条记录删除成功";
        	writeJsonResponse(response, success, message);
        } catch (Exception e) { 
            //e.printStackTrace();
            message = "有记录存在外键约束,删除失败";
            writeJsonResponse(response, success, message);
        }
	}

	/*按照查询条件导出留言建议信息到Excel*/
	@RequestMapping(value = { "/OutToExcel" }, method = {RequestMethod.GET,RequestMethod.POST})
	public void OutToExcel(String title,String leaveTime,@ModelAttribute("userObj") UserInfo userObj, Model model, HttpServletRequest request,HttpServletResponse response) throws Exception {
        if(title == null) title = "";
        if(leaveTime == null) leaveTime = "";
        List<LeaveWord> leaveWordList = leaveWordService.queryLeaveWord(title,leaveTime,userObj);
        ExportExcelUtil ex = new ExportExcelUtil();
        String _title = "LeaveWord信息记录"; 
        String[] headers = { "留言标题","留言时间","留言人","回复时间"};
        List<String[]> dataset = new ArrayList<String[]>(); 
        for(int i=0;i<leaveWordList.size();i++) {
        	LeaveWord leaveWord = leaveWordList.get(i); 
        	dataset.add(new String[]{leaveWord.getTitle(),leaveWord.getLeaveTime(),leaveWord.getUserObj().getName(),leaveWord.getReplyTime()});
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
			response.setHeader("Content-disposition","attachment; filename="+"LeaveWord.xls");//filename是下载的xls的名，建议最好用英文 
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
