package com.yc.snacknet.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yc.snacknet.dao.AddrInfoDao;
import com.yc.snacknet.util.StringUtil;

@WebServlet("/addr")
public class AddrInfoController extends BasicController{
	private static final long serialVersionUID = 1541343297567029693L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String op = request.getParameter("op");

		if ("findByMno".equals(op)) { // 获取购物车信息
			findByMno(request, response);
		} else if ("add".equals(op)) { // 添加地址
			add(request, response);
		} else if ("update".equals(op)) { // 修改默认收货地址
			update(request, response);
		}

	}

	private void update(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String anos = request.getParameter("anos");
		if (StringUtil.checkNull(anos) || anos.contains(" or ")) {
			this.send(response, -1);
			return;
		}

		AddrInfoDao addrInfoDao = new AddrInfoDao();
		this.send(response, addrInfoDao.update(anos));
	}

	private void add(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String ano = request.getParameter("ano");
		String mno = request.getParameter("mno");
		String name = request.getParameter("name");
		String tel = request.getParameter("tel");
		String province = request.getParameter("province");
		String city = request.getParameter("city");
		String area = request.getParameter("area");
		String addr = request.getParameter("addr");

		if (StringUtil.checkNull(ano, mno, name, tel, province, city, area, addr)) {
			this.send(response, -2);
			return;
		}
		AddrInfoDao addrInfoDao = new AddrInfoDao();
		this.send(response, addrInfoDao.add(ano, mno, name, tel, province, city, area, addr));
	}

	@SuppressWarnings("unchecked")
	private void findByMno(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Object obj = request.getSession().getAttribute("currentLoginMember");
		if (obj == null) { // 说明没有登录
			this.send(response, 100, null);
			return;
		} 
		Map<String, String> mf = (Map<String, String>) obj;
		AddrInfoDao addrInfoDao = new AddrInfoDao();
		this.send(response, 200, addrInfoDao.finds(mf.get("mno")));
	}
}
