package com.yc.snacknet.dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class OrderInfoDao {
	/**
	 * 添加订单
	 * @param cnos 购物车编号
	 * @param ano 收货地址编号
	 * @param totalPrice 总金额
	 * @return 返回订单编号
	 */
	public String add(String cnos, String ano, String totalPrice) {
		// 生成订单
		String ono = new Date().getTime() + "" + new Random().nextInt(10000);
		List<String> sqls = new ArrayList<String>();
		List<List<Object>> params = new ArrayList<List<Object>>();
		
		// 添加订单
		String sql1 = "insert into orderinfo values(?, now(), ?, null, null, 1, ?, 0)";
		List<Object> param1 = new ArrayList<Object>();
		Collections.addAll(param1, ono, ano, totalPrice); 
		
		// 添加订单详细
		String sql2 = "insert into orderiteminfo select 0, ?, cf.gno, num, price, 1 from cartinfo cf, goodsinfo gf where cf.gno=gf.gno and cno in(";
		List<Object> param2 = new ArrayList<Object>();
		String[] arrs = cnos.split(",");
		param2.add(ono);
		for (String cno : arrs) {
			sql2 += "?, ";
			param2.add(cno);
		}
		sql2 = sql2.substring(0, sql2.lastIndexOf(",")) + ")";
		
		// 修改库存
		String sql3 = "update goodsinfo set balance=balance-case ";
		List<Object> param3 = new ArrayList<Object>();
		
		for (String cno : arrs) {
			sql3 += " when gno=(select gno from cartinfo where cno=?) then (select num from cartinfo where cno=?)";
			param3.add(cno);
			param3.add(cno);
		}
		sql3 += " end where gno in(select gno from cartinfo where cno in(";
		
		// 删除购物车
		String sql4 = "delete from cartinfo where cno in(";
		List<Object> param4 = new ArrayList<Object>();
		for (String cno : arrs) {
			sql3 += "?, ";
			sql4 += "?, ";
			param3.add(cno);
			param4.add(cno);
		}
		sql3 = sql3.substring(0, sql3.lastIndexOf(",")) + "))";
		sql4 = sql4.substring(0, sql4.lastIndexOf(",")) + ")";
		
		Collections.addAll(sqls, sql1, sql2, sql3, sql4);
		Collections.addAll(params, param1, param2, param3, param4);
		
		DBHelper db = new DBHelper();
		if (db.update(sqls, params) > 0) {
			return ono;
		}
		return null;
	}

	/**
	 * 修改订单状态
	 * @param ono
	 * @param status
	 * @return
	 */
	public int update(String ono, Integer status) {
		DBHelper db = new DBHelper();
		String sql = "update orderinfo set status = ? where ono = ?";
		return db.update(sql, status, ono);
	}

	/**
	 * 根据会员编号查询订单
	 * @param mno
	 * @return
	 */
	public List<Map<String, String>> findByMno(String mno) {
		DBHelper db = new DBHelper();
		String sql = "select o.ono, date_format(odate, '%Y-%m-%d %H:%i') odate, o.status, o.price amount, i.gno, i.nums, i.price, gname, pics, weight, unit " 
				+ " from orderinfo o, orderiteminfo i, goodsinfo g, addrinfo a where o.ono=i.ono and i.gno=g.gno and o.ano=a.ano and a.mno=? order by o.ono desc";
		return db.finds(sql, mno);
	}
}
