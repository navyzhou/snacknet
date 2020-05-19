package com.yc.snacknet.dao;

import java.util.List;
import java.util.Map;

/**
 * 后管理员数据处理
 * 源辰信息
 * @author navy
 * @date 2020年5月16日
 */
public class AdminInfoDao {
	/**
	 * 后台管理员登录查询
	 * @param account
	 * @param pwd
	 * @return
	 */
	public Map<String, String> login(String account, String pwd) {
		DBHelper db = new DBHelper();
		String sql = "select aid, aname, tel from admininfo where aname=? and pwd=md5(?) and status=1";
		return db.find(sql, account, pwd);
	}
	
	/**
	 * 添加管理员
	 * @param aname
	 * @param pwd
	 * @param tel
	 * @return
	 */
	public int add(String aname,  String pwd, String tel) {
		DBHelper db = new DBHelper();
		String sql = "insert into admininfo values(0, ?, ?, ?, 1)";
		return db.update(sql, aname, pwd, tel);
	}
	
	/**
	 * 查询所有管理员信息
	 * @return
	 */
	public List<Map<String, String>> finds() {
		DBHelper db = new DBHelper();
		String sql = "select aid, aname, tel, status from admininfo order by aid asc";
		return db.finds(sql);
	}
	
	/**
	 * 解锁冻结账号
	 * @param aid
	 * @param status
	 * @return
	 */
	public int update(String aid, String status) {
		DBHelper db = new DBHelper();
		String sql = "update admininfo set status = ? where aid = ?";
		return db.update(sql, status, aid);
	}
}
