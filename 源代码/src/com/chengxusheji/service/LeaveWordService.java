package com.chengxusheji.service;

import java.util.ArrayList;
import javax.annotation.Resource; 
import org.springframework.stereotype.Service;
import com.chengxusheji.po.UserInfo;
import com.chengxusheji.po.LeaveWord;

import com.chengxusheji.mapper.LeaveWordMapper;
@Service
public class LeaveWordService {

	@Resource LeaveWordMapper leaveWordMapper;
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

    /*添加留言建议记录*/
    public void addLeaveWord(LeaveWord leaveWord) throws Exception {
    	leaveWordMapper.addLeaveWord(leaveWord);
    }

    /*按照查询条件分页查询留言建议记录*/
    public ArrayList<LeaveWord> queryLeaveWord(String title,String leaveTime,UserInfo userObj,int currentPage) throws Exception { 
     	String where = "where 1=1";
    	if(!title.equals("")) where = where + " and t_leaveWord.title like '%" + title + "%'";
    	if(!leaveTime.equals("")) where = where + " and t_leaveWord.leaveTime like '%" + leaveTime + "%'";
    	if(null != userObj &&  userObj.getUser_name() != null  && !userObj.getUser_name().equals(""))  where += " and t_leaveWord.userObj='" + userObj.getUser_name() + "'";
    	int startIndex = (currentPage-1) * this.rows;
    	return leaveWordMapper.queryLeaveWord(where, startIndex, this.rows);
    }

    /*按照查询条件查询所有记录*/
    public ArrayList<LeaveWord> queryLeaveWord(String title,String leaveTime,UserInfo userObj) throws Exception  { 
     	String where = "where 1=1";
    	if(!title.equals("")) where = where + " and t_leaveWord.title like '%" + title + "%'";
    	if(!leaveTime.equals("")) where = where + " and t_leaveWord.leaveTime like '%" + leaveTime + "%'";
    	if(null != userObj &&  userObj.getUser_name() != null && !userObj.getUser_name().equals(""))  where += " and t_leaveWord.userObj='" + userObj.getUser_name() + "'";
    	return leaveWordMapper.queryLeaveWordList(where);
    }

    /*查询所有留言建议记录*/
    public ArrayList<LeaveWord> queryAllLeaveWord()  throws Exception {
        return leaveWordMapper.queryLeaveWordList("where 1=1");
    }

    /*当前查询条件下计算总的页数和记录数*/
    public void queryTotalPageAndRecordNumber(String title,String leaveTime,UserInfo userObj) throws Exception {
     	String where = "where 1=1";
    	if(!title.equals("")) where = where + " and t_leaveWord.title like '%" + title + "%'";
    	if(!leaveTime.equals("")) where = where + " and t_leaveWord.leaveTime like '%" + leaveTime + "%'";
    	if(null != userObj &&  userObj.getUser_name() != null && !userObj.getUser_name().equals(""))  where += " and t_leaveWord.userObj='" + userObj.getUser_name() + "'";
        recordNumber = leaveWordMapper.queryLeaveWordCount(where);
        int mod = recordNumber % this.rows;
        totalPage = recordNumber / this.rows;
        if(mod != 0) totalPage++;
    }

    /*根据主键获取留言建议记录*/
    public LeaveWord getLeaveWord(int leaveId) throws Exception  {
        LeaveWord leaveWord = leaveWordMapper.getLeaveWord(leaveId);
        return leaveWord;
    }

    /*更新留言建议记录*/
    public void updateLeaveWord(LeaveWord leaveWord) throws Exception {
        leaveWordMapper.updateLeaveWord(leaveWord);
    }

    /*删除一条留言建议记录*/
    public void deleteLeaveWord (int leaveId) throws Exception {
        leaveWordMapper.deleteLeaveWord(leaveId);
    }

    /*删除多条留言建议信息*/
    public int deleteLeaveWords (String leaveIds) throws Exception {
    	String _leaveIds[] = leaveIds.split(",");
    	for(String _leaveId: _leaveIds) {
    		leaveWordMapper.deleteLeaveWord(Integer.parseInt(_leaveId));
    	}
    	return _leaveIds.length;
    }
}
