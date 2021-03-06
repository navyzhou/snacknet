package com.yc.snacknet.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CartInfoDao {
	/**
	 * 获取购物车信息，取购物车编号、商品编号和数量
	 * @param mno
	 * @return
	 */
	public List<Map<String, String>> getByMno(String mno) {
		DBHelper db = new DBHelper();
		String sql = "select cno, gno, num from cartinfo where mno = ?";
		return db.finds(sql, mno);
	}
	
	/**
	 * 加入购物车
	 * @param cno
	 * @param gno
	 * @param mno
	 * @param num
	 * @return
	 */
	public int add(String cno, String gno, int mno, int num) {
		DBHelper db = new DBHelper();
		String sql = "insert into cartinfo values(?, ?, ?, ?)";
		return db.update(sql, cno, mno, gno, num);
	}
	
	/**
	 * 修改购买的数量
	 * @param cno
	 * @param num
	 * @return
	 */
	public int updateStore(String cno, int num) {
		DBHelper db = new DBHelper();
		String sql = "update cartinfo set num = num + ? where cno = ?";
		return db.update(sql, num, cno);
	}
	
	/**
	 * 根据会员编号，查询购物车信息
	 * @param mno
	 * @return
	 */
	public List<Map<String, String>> finds(String mno) {
		DBHelper db = new DBHelper();
		String sql = "select cno, cf.gno, num, pics, price, gname, unit, weight from cartinfo cf, goodsinfo gf where cf.gno=gf.gno and mno=?";
		return db.finds(sql, mno);
	}
	
	public List<Map<String, String>> findByCnos(String cnos) {
		DBHelper db = new DBHelper();
		String sql = "select cno, cf.gno, num, pics, price, gname, unit, weight from cartinfo cf, goodsinfo gf where cf.gno=gf.gno and cno in(";
		List<Object> params = new ArrayList<Object>();
		String[] arrs = cnos.split(",");
		for (String arr :arrs) {
			sql += "?, ";
			params.add(arr);
		}
		sql = sql.substring(0, sql.lastIndexOf(","));
		sql += ")";
		return db.finds(sql, params);
	}
}
