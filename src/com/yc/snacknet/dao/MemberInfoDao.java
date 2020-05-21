package com.yc.snacknet.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.yc.snacknet.util.StringUtil;

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
		String sql = "select mno, nickName, photo from memberinfo where (nickName=? or tel=? or email=?) and pwd=md5(?)";
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
		String sql = "insert into memberinfo values(0, ?, '', md5(?), ?, ?, '', now(), 1)";
		return db.update(sql, nickName, pwd, tel, email);
	}
	
	/**
	 * 分页查询
	 * @param page 查第几页
	 * @param rows 每页显示多少条
	 * @return
	 */
	public List<Map<String, String>> findByPage(int page, int rows) {
		DBHelper db = new DBHelper();
		String sql = "select mno, nickName, realName, tel, email, date_format(regDate, '%Y-%m-%d %H:%i:%s') regDate, status from memberinfo order by mno desc limit ?,?";
		return db.finds(sql, (page - 1) * rows, rows);
	}
	
	public int total() {
		DBHelper db = new DBHelper();
		String sql = "select count(mno) from memberinfo";
		return db.total(sql);
	}
	
	/**
	 * 条件分页查询
	 * @param page 查第几页
	 * @param rows 每页显示多少条
	 * @return
	 */
	public List<Map<String, String>> findByCondition(String nickName, String tel, int page, int rows) {
		DBHelper db = new DBHelper();
		List<Object> params = new ArrayList<Object>();
		String sql = "select mno, nickName, IFNull(realName, '') realName, tel, email, date_format(regDate, '%Y-%m-%d %H:%i:%s') regDate, status from memberinfo where 1=1";
		if (!StringUtil.checkNull(nickName)) {
			sql += " and nickName like concat('%', ?, '%')";
			params.add(nickName);
		}
		
		if (!StringUtil.checkNull(tel)) {
			sql += " and tel = ?";
			params.add(tel);
		}
		sql += " order by mno desc limit ?, ?";
		params.add((page - 1) * rows);
		params.add(rows);
		return db.finds(sql, params);
	}
	
	public int total(String nickName, String tel) {
		DBHelper db = new DBHelper();
		List<Object> params = new ArrayList<Object>();
		String sql = "select count(mno) from memberinfo where 1=1";
		if (!StringUtil.checkNull(nickName)) {
			sql += " and nickName like concat('%', ?, '%')";
			params.add(nickName);
		}
		
		if (!StringUtil.checkNull(tel)) {
			sql += " and tel = ?";
			params.add(tel);
		}
		return db.total(sql, params);
	}
	
	/**
	 * 修改状态
	 * @param mno
	 * @param flag
	 * @return
	 */
	public int update(int mno, int flag) {
		DBHelper db = new DBHelper();
		String sql = "update memberinfo set status = ? where mno = ?";
		return db.update(sql, flag, mno);
	}
	
	/**
	 * 重置密码
	 * @param pwd
	 * @param mno
	 * @return
	 */
	public int resetPwd(int mno) {
		DBHelper db = new DBHelper();
		String sql = "update memberinfo set pwd = md5(right(tel, 6)) where mno = ?";
		return db.update(sql, mno);
	}
}
