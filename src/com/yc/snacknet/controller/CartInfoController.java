package com.yc.snacknet.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yc.snacknet.dao.CartInfoDao;
import com.yc.snacknet.util.StringUtil;

@WebServlet("/cart")
public class CartInfoController extends BasicController{
	private static final long serialVersionUID = 1541343297567029693L;
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String op = request.getParameter("op");
		
		 if ("get".equals(op)) { // 获取购物车信息
			getByMmo(request, response);
		} else if ("update".equals(op)) { // 修改数量
			update(request, response);
		} else if ("add".equals(op)) { // 添加
			add(request, response);
		}
	
	}

	private void add(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String cno = UUID.randomUUID().toString().replace("-", "");
		
		String mno = request.getParameter("mno");
		String num = request.getParameter("num");
		String gno = request.getParameter("gno");
		
		if (StringUtil.checkNull(mno, gno, num)) {
			this.send(response, 100, null);
			return;
		}
		CartInfoDao cartInfoDao = new CartInfoDao();
		int result = cartInfoDao.add(cno, gno, Integer.parseInt(mno), Integer.parseInt(num));
		if (result > 0) { // 说明是成功
			this.send(response, 200, cno);
			return;
		}
		this.send(response, 100, null);
		return;
	}

	private void update(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String num = request.getParameter("num");
		String cno = request.getParameter("cno");
		
		if (StringUtil.checkNull(cno, num)) {
			this.send(response, 100, null);
			return;
		}
		CartInfoDao cartInfoDao = new CartInfoDao();
		int result = cartInfoDao.updateStore(cno, Integer.parseInt(num));
		if (result > 0) { // 说明是成功
			this.send(response, 200, null);
			return;
		}
		this.send(response, 100, null);
		return;
	}

	private void getByMmo(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String mno = request.getParameter("mno");
		CartInfoDao cartInfoDao = new CartInfoDao();
		List<Map<String, String>> list = cartInfoDao.getByMno(mno);
		if (list == null || list.isEmpty()) {
			this.send(response, 100, null);
			return;
		}
		this.send(response, 200, list);
	}
}
