package com.yc.snacknet.dao;

import java.util.List;
import java.util.Map;

public class ReportInfoDao {
	/**
	 * 年统计
	 * @return
	 */
	public List<Map<String, String>> reportByYear() {
		DBHelper db = new DBHelper();
		String sql = "select year(odate) year, sum(price) price from orderinfo group by year(odate) order by year asc";
		return db.finds(sql);
	}
	
	public List<Map<String, String>> reportByMonth(String year) {
		DBHelper db = new DBHelper();
		String sql = "select month(odate) year, sum(price) price from orderinfo where year(odate) = ? group by month(odate) order by year asc";
		return db.finds(sql, year);
	}
	
	public List<Map<String, String>> findByType() {
		DBHelper db = new DBHelper();
		String sql = "select year(odate) year, tname, sum(i.price) price  from orderiteminfo i, orderinfo o, goodsinfo g, "
				+ "goodstype t where i.gno = g.gno and i.ono=o.ono and g.tno=t.tno group by year(odate), t.tname order by year asc";
		return db.finds(sql);
	}
	
	public List<Map<String, String>> findByType(String year) {
		DBHelper db = new DBHelper();
		String sql = "select month(odate) year, tname, sum(i.price) price from orderiteminfo i, orderinfo o, goodsinfo g, goodstype t "
				+ "where i.gno = g.gno and i.ono=o.ono and g.tno=t.tno and year(odate) = ? group by month(odate), t.tname order by year asc";
		return db.finds(sql, year);
	}
}
