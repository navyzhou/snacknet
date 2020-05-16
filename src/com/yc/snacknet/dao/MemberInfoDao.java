package com.yc.snacknet.dao;

import java.util.Map;

/**
 * 会员信息数据处理
 * 源辰信息
 * @author navy
 * @date 2020年5月16日
 */
public class MemberInfoDao {
	/**
	 * 会员登录查询
	 * @param account
	 * @param pwd
	 * @return
	 */
	public Map<String, String> login(String account, String pwd) {
		DBHelper db = new DBHelper();
		String sql = "select mno, nickName, photo from memberInfo where (nickName=? or tel=? or email=?) and pwd=md5(?)";
		return db.find(sql, account, account, account, pwd);
	}
	
	/**
	 * 会员注册处理
	 * @param nickName
	 * @param pwd
	 * @param email
	 * @param tel
	 * @return
	 */
	public int reg(String nickName,  String pwd, String email, String tel) {
		DBHelper db = new DBHelper();
		String sql = "insert into memberInfo values(0, ?, '', md5(?), ?, ?, '', now(), 1)";
		return db.update(sql, nickName, pwd, tel, email);
	}
}
