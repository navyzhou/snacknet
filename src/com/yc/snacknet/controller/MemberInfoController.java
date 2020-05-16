package com.yc.snacknet.controller;

import java.io.IOException;
import java.util.Map;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yc.snacknet.dao.MemberInfoDao;
import com.yc.snacknet.util.SendMailUtil;
import com.yc.snacknet.util.StringUtil;

@WebServlet("/member")
public class MemberInfoController extends BasicController{
	private static final long serialVersionUID = 1541343297567029693L;
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String op = request.getParameter("op");
		
		if ("sendCode".equals(op)) { // 说明是发送验证码
			sendCode(request, response);
		} else if ("reg".equals(op)) { // 说明是会员注册
			reg(request, response);
		} else if ("login".equals(op)) { // 说明是会员登录
			login(request, response);
		} else if ("check".equals(op)) { // 说明是检查登录的方法
			check(request, response);
		}
	
	}

	/**
	 * 检查登录的方法
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	private void check(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Object obj = request.getSession().getAttribute("currentLoginMember");
		if (obj == null) { // 说明没有登录
			this.send(response, 100, null);
			return;
		} 
		this.send(response, 200, obj);
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
		
		MemberInfoDao memberInfoDao = new MemberInfoDao();
		Map<String, String> map = memberInfoDao.login(account, pwd);
		if (map != null && !map.isEmpty()) {
			request.getSession().setAttribute("currentLoginMember", map);
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
	private void reg(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String nickName = request.getParameter("nickName"); // 昵称
		String pwd = request.getParameter("pwd"); // 密码
		String email = request.getParameter("email"); // 邮箱
		String tel = request.getParameter("tel"); // 手机
		String code = request.getParameter("code"); // 验证码
		
		if (StringUtil.checkNull(nickName, pwd, email, tel, code)) {
			this.send(response, 100); // 信息不完整
			return;
		}
		
		HttpSession session = request.getSession();
		Object vcode = session.getAttribute("vcode"); // 获取验证码
		if (vcode == null) {
			this.send(response, 101); // 验证码过期
			return;
		}
		
		if (!code.equals(String.valueOf(vcode))) {
			this.send(response, 102); // 验证码错误
			return;
		}
		
		MemberInfoDao memberInfoDao = new MemberInfoDao();
		int result = memberInfoDao.reg(nickName, pwd, email, tel);
		if (result > 0) {
			this.send(response, 200); // 成功
			return;
		}
		this.send(response, 103); // 注册失败
	}

	/**
	 * 发送验证码的方法
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	private void sendCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String receive = request.getParameter("receive"); // 收件箱
		String name = request.getParameter("name"); // 收件人

		SendMailUtil sendMailUtil = new SendMailUtil();
		String code = "";
		Random rd = new Random();
		while( code.length() < 6) {
			code += rd.nextInt(10);
		}

		if (sendMailUtil.sendQQmail("1293580602@qq.com", "dihpepdwtahlgefh", receive, code, name)) { // 如果发送成功
			HttpSession session = request.getSession();
			session.setAttribute("vcode", code);

			TimerTask task = new TimerTask() {
				@Override
				public void run() {
					session.removeAttribute("vcode"); // 清空验证码
				}
			};

			Timer timer = new Timer(); // 实例化一个定时器
			timer.schedule(task, 180000); // 3分钟后执行任务task一次
			this.send(response, 1);
			return;
		}
		this.send(response, 0);
	}
}
