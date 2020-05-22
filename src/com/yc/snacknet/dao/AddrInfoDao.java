package com.yc.snacknet.dao;

import java.util.List;
import java.util.Map;

public class AddrInfoDao {
	/**
	 * 添加收货地址
	 * @param ano
	 * @param mno
	 * @param name
	 * @param tel
	 * @param province
	 * @param city
	 * @param area
	 * @param addr
	 * @return
	 */
	public int add(String ano, String mno, String name, String tel, String province, String city, String area, String addr) {
		DBHelper db = new DBHelper();
		String sql = "insert into addrinfo values(?, ?, ?, ?, ?, ?, ?, ?, 0, 1)";
		return db.update(sql, ano, mno, name, tel, province, city, area, addr);
	}
	
	/**
	 * 根据会员编号查询收货地址
	 * @param mno
	 * @return
	 */
	public List<Map<String, String>> finds(String mno) {
		DBHelper db = new DBHelper();
		String sql = "select ano, mno, name, tel, province, city, area, addr, flag from addrinfo where status=1 and mno=?";
		return db.finds(sql, mno);
	}
	
	public int update(String anos) {
		DBHelper db = new DBHelper();
		String sql = "update addrinfo set flag = (flag + 1) % 2 where ano in(" + anos + ")";
		return db.update(sql);
	}
}
