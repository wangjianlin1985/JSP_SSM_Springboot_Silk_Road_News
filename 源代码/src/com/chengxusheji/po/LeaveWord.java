package com.chengxusheji.po;

import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotEmpty;
import org.json.JSONException;
import org.json.JSONObject;

public class LeaveWord {
    /*留言id*/
    private Integer leaveId;
    public Integer getLeaveId(){
        return leaveId;
    }
    public void setLeaveId(Integer leaveId){
        this.leaveId = leaveId;
    }

    /*留言标题*/
    @NotEmpty(message="留言标题不能为空")
    private String title;
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    /*留言内容*/
    @NotEmpty(message="留言内容不能为空")
    private String leaveContent;
    public String getLeaveContent() {
        return leaveContent;
    }
    public void setLeaveContent(String leaveContent) {
        this.leaveContent = leaveContent;
    }

    /*留言时间*/
    @NotEmpty(message="留言时间不能为空")
    private String leaveTime;
    public String getLeaveTime() {
        return leaveTime;
    }
    public void setLeaveTime(String leaveTime) {
        this.leaveTime = leaveTime;
    }

    /*留言人*/
    private UserInfo userObj;
    public UserInfo getUserObj() {
        return userObj;
    }
    public void setUserObj(UserInfo userObj) {
        this.userObj = userObj;
    }

    /*回复内容*/
    private String replyContent;
    public String getReplyContent() {
        return replyContent;
    }
    public void setReplyContent(String replyContent) {
        this.replyContent = replyContent;
    }

    /*回复时间*/
    private String replyTime;
    public String getReplyTime() {
        return replyTime;
    }
    public void setReplyTime(String replyTime) {
        this.replyTime = replyTime;
    }

    public JSONObject getJsonObject() throws JSONException {
    	JSONObject jsonLeaveWord=new JSONObject(); 
		jsonLeaveWord.accumulate("leaveId", this.getLeaveId());
		jsonLeaveWord.accumulate("title", this.getTitle());
		jsonLeaveWord.accumulate("leaveContent", this.getLeaveContent());
		jsonLeaveWord.accumulate("leaveTime", this.getLeaveTime().length()>19?this.getLeaveTime().substring(0,19):this.getLeaveTime());
		jsonLeaveWord.accumulate("userObj", this.getUserObj().getName());
		jsonLeaveWord.accumulate("userObjPri", this.getUserObj().getUser_name());
		jsonLeaveWord.accumulate("replyContent", this.getReplyContent());
		jsonLeaveWord.accumulate("replyTime", this.getReplyTime());
		return jsonLeaveWord;
    }}