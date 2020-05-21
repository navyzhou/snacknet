package com.yc.snacknet.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.yc.snacknet.util.StringUtil;

/**
 * 商品类型
 * 源辰信息
 * @author navy
 * @date 2020年5月16日
 */
public class GoodsInfoDao {
	/**
	 * 添加商品信息
	 * @param map
	 * @return
	 */
	public int add(Map<String, String> map) {
		DBHelper db = new DBHelper();
		String sql = "insert into goodsinfo values(0, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, 1)";
		return db.update(sql, map.get("gname"), map.get("tno"), map.get("price"), map.get("intro"), map.get("balance"),
				map.get("pic"), map.get("unit"), map.get("qperied"), map.get("weight"), map.get("descr"));
	}
	
	/**
	 * 分页查询
	 * @param page
	 * @param rows
	 * @param tno
	 * @param gname
	 * @return
	 */
	public List<Map<String, String>> findByPage(int page, int rows, String tno, String gname) {
		DBHelper db = new DBHelper();
		String sql = "select gno, gname, g.tno, tname, price, balance, unit, qperied, weight, g.status from goodsinfo g, goodstype t where g.tno=t.tno";
		List<Object> params = new ArrayList<Object>();
		if (!StringUtil.checkNull(tno)) {
			sql += " and g.tno = ?";
			params.add(tno);
		}
		
		if (!StringUtil.checkNull(gname)) {
			sql += " and gname like concat('%', ?, '%')";
			params.add(gname);
		}
		sql += " order by gno desc limit ?, ?";
		params.add((page - 1) * rows);
		params.add(rows);
		
		return db.finds(sql, params);
	}
	
	public int total(String tno, String gname) {
		DBHelper db = new DBHelper();
		String sql = "select count(gno) from goodsinfo where 1=1";
		List<Object> params = new ArrayList<Object>();
		if (!StringUtil.checkNull(tno)) {
			sql += " and tno = ?";
			params.add(tno);
		}
		
		if (!StringUtil.checkNull(gname)) {
			sql += " and gname like concat('%', ?, '%')";
			params.add(gname);
		}
		return db.total(sql, params);
	}
	
	/**
	 * 查询商品详细
	 * @param gno
	 * @return
	 */
	public Map<String, String> findByGno(String gno) {
		DBHelper db = new DBHelper();
		String sql = "select gno, gname, g.tno, tname, price, balance, unit, qperied, weight, g.status, "
				+ "intro, pics, descr from goodsinfo g, goodstype t where g.tno=t.tno and gno = ?";
		return db.find(sql, gno);
	}
	
	/**
	 * 修改商品状态
	 * @param gno
	 * @param status
	 * @return
	 */
	public int update(String gno, int status) {
		DBHelper db = new DBHelper();
		String sql = "update goodsinfo set status = ? where gno = ?";
		return db.update(sql, status, gno);
	}
	
	/**
	 * 前台首页分页查询
	 * @param page 查第几页
	 * @param rows 每页多少条
	 * @param order 根据价格排序规则
	 * @param tno 商品类型
	 * @param sprice 起始价格
	 * @param eprice 结束价格
	 * @return
	 */
	public List<Map<String, String>> finds(int page, int rows, String tno, String gname, String sprice, String eprice, String order) {
		DBHelper db = new DBHelper();
		String sql = "select gno, gname, tno, price, weight, pics from goodsinfo where status != 0";
		List<Object> params = new ArrayList<Object>();
		if (!StringUtil.checkNull(tno)) {
			sql += " and tno = ?";
			params.add(tno);
		}
		
		if (!StringUtil.checkNull(gname)) {
			sql += " and gname like concat('%', ?, '%')";
			params.add(gname);
		}
		
		if (!StringUtil.checkNull(sprice)) {
			sql += " and price >= ?";
			params.add(sprice);
		}
		
		if (!StringUtil.checkNull(eprice)) {
			sql += " and price <= ?";
			params.add(eprice);
		}

		if (!StringUtil.checkNull(order)) {
			sql += " order by price " + order;
		} else {
			sql += " order by gno desc";
		}
		sql += " limit ?, ?";
		params.add((page - 1) * rows);
		params.add(rows);
		return db.finds(sql, params);
	}
	
	public int total(String tno, String gname, String sprice, String eprice) {
		DBHelper db = new DBHelper();
		String sql = "select count(gno) from goodsinfo where status != 0";
		List<Object> params = new ArrayList<Object>();
		if (!StringUtil.checkNull(tno)) {
			sql += " and tno = ?";
			params.add(tno);
		}
		
		if (!StringUtil.checkNull(gname)) {
			sql += " and gname like concat('%', ?, '%')";
			params.add(gname);
		}
		
		if (!StringUtil.checkNull(sprice)) {
			sql += " and price >= ?";
			params.add(sprice);
		}
		
		if (!StringUtil.checkNull(eprice)) {
			sql += " and price <= ?";
			params.add(eprice);
		}
		return db.total(sql, params);
	}
}
