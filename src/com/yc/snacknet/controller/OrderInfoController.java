package com.yc.snacknet.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yc.snacknet.dao.OrderInfoDao;
import com.yc.snacknet.util.StringUtil;

@WebServlet("/order")
public class OrderInfoController extends BasicController{
	private static final long serialVersionUID = 1541343297567029693L;
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String op = request.getParameter("op");
		
		if ("add".equals(op)) { // 获取购物车信息
			 add(request, response);
		} else if ("find".equals(op)) { // 根据会员编号查询订单
			findByMno(request, response);
		}
	
	}

	@SuppressWarnings("unchecked")
	private void findByMno(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Object obj = request.getSession().getAttribute("currentLoginMember");
		if (obj == null) { // 说明没有登录
			this.send(response, 100, null);
			return;
		} 
		OrderInfoDao orderInfoDao = new OrderInfoDao();
		Map<String, String> map = (Map<String, String>) obj;
		this.send(response, 200, orderInfoDao.findByMno(map.get("mno")));
	}

	private void add(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String cnos = request.getParameter("cnos");
		String ano = request.getParameter("ano");
		String price = request.getParameter("price");
		
		if (StringUtil.checkNull(cnos, ano, price)) {
			this.send(response, 101, null);
			return;
		}
		
		OrderInfoDao orderInfoDao = new OrderInfoDao();
		String result = orderInfoDao.add(cnos, ano, price);
		if (!StringUtil.checkNull(result)) {
			this.send(response, 200, result);
			return;
		}
		this.send(response, 100, null);
	}
}
