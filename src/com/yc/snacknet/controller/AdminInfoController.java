package com.yc.snacknet.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yc.snacknet.dao.AdminInfoDao;
import com.yc.snacknet.util.StringUtil;

@WebServlet("/admin")
public class AdminInfoController extends BasicController{
	private static final long serialVersionUID = 1541343297567029693L;
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String op = request.getParameter("op");
		
		 if ("add".equals(op)) { // 添加后台管理员
			add(request, response);
		} else if ("login".equals(op)) { // 管理员登录
			login(request, response);
		} else if ("finds".equals(op)) { // 查询所有管理员信息
			finds(request, response);
		} else if ("update".equals(op)) { // 冻结解冻账号
			update(request, response);
		}
	
	}

	/**
	 * 冻结解冻账号
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	private void update(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String aid = request.getParameter("aid");
		String status = request.getParameter("stauts");
		AdminInfoDao adminInfoDao = new AdminInfoDao();
		this.send(response, adminInfoDao.update(aid, status));
	}

	/**
	 * 查询所有管理员信息
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	private void finds(HttpServletRequest request, HttpServletResponse response) throws IOException {
		AdminInfoDao adminInfoDao = new AdminInfoDao();
		this.send(response, adminInfoDao.finds());
	}

	/**
	 * 会员登录的方法
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	private void login(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String account = request.getParameter("account"); // 账号
		String pwd = request.getParameter("pwd"); // 密码
		
		if (StringUtil.checkNull(account, pwd)) {
			this.send(response, 100); // 信息不完整
			return;
		}
		
		AdminInfoDao adminInfoDao = new AdminInfoDao();
		Map<String, String> map = adminInfoDao.login(account, pwd);
		if (map != null && !map.isEmpty()) {
			request.getSession().setAttribute("currentLoginAdmin", map);
			this.send(response, 200); // 成功
			return;
		}
		this.send(response, 101); // 注账或密码错误
	}

	/**
	 * 会员注册方法
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	private void add(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String aname = request.getParameter("aname"); // 昵称
		String pwd = request.getParameter("pwd"); // 密码
		String tel = request.getParameter("tel"); // 手机
		if (StringUtil.checkNull(aname, pwd, tel)) {
			this.send(response, 100); // 信息不完整
			return;
		}
		
		AdminInfoDao adminInfoDao = new AdminInfoDao();
		int result = adminInfoDao.add(aname, pwd, tel);
		if (result > 0) {
			this.send(response, 200); // 成功
			return;
		}
		this.send(response, 101); // 注册失败
	}
}
