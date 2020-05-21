package com.yc.snacknet.dao;

import java.util.List;
import java.util.Map;

/**
 * 商品类型
 * 源辰信息
 * @author navy
 * @date 2020年5月16日
 */
public class GoodsTypeInfoDao {
	/**
	 * 查询所有商品类型信息
	 * @return
	 */
	public List<Map<String, String>> finds() {
		DBHelper db = new DBHelper();
		String sql = "select tno, tname, status from goodstype order by tno";
		return db.finds(sql);
	}

	/**
	 * 添加商品类型信息
	 * @param tname
	 * @return
	 */
	public int add(String tname) {
		DBHelper db = new DBHelper();
		String sql = "insert into goodstype values(0, ?, 1)";
		return db.update(sql, tname);
	}

	/**
	 * 修改商品类型状态
	 * @param mno
	 * @param flag
	 * @return
	 */
	public int update(int tno, int flag) {
		DBHelper db = new DBHelper();
		String sql = "update goodstype set status = ? where tno = ?";
		return db.update(sql, flag, tno);
	}
}
