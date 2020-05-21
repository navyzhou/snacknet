package com.yc.snacknet.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yc.snacknet.dao.GoodsTypeInfoDao;
import com.yc.snacknet.util.StringUtil;

@WebServlet("/type")
public class GoodsTypeController extends BasicController{
	private static final long serialVersionUID = 1541343297567029693L;
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String op = request.getParameter("op");
		
		if ("finds".equals(op)) { // 查询所有
			finds(request, response);
		} else if ("add".equals(op)) { // 添加类型
			add(request, response);
		} else if ("update".equals(op)) { // 修改状态
			update(request, response);
		}
	
	}


	private void update(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int tno = Integer.parseInt(request.getParameter("tno"));
		int flag = Integer.parseInt(request.getParameter("flag"));
		
		GoodsTypeInfoDao typeInfoDao = new GoodsTypeInfoDao();
		this.send(response, typeInfoDao.update(tno, flag));
	}

	/**
	 * 查询所有
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	private void finds(HttpServletRequest request, HttpServletResponse response) throws IOException {
		GoodsTypeInfoDao typeInfoDao = new GoodsTypeInfoDao();
		this.send(response, typeInfoDao.finds());
	}
	

	/**
	 * 添加类型
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	private void add(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String tname = request.getParameter("tname"); // 密码
		
		if (StringUtil.checkNull(tname)) {
			this.send(response, -1);
			return;
		}
		
		GoodsTypeInfoDao typeInfoDao = new GoodsTypeInfoDao();
		this.send(response, typeInfoDao.add(tname));
	}
}
