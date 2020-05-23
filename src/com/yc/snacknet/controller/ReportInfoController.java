package com.yc.snacknet.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yc.snacknet.dao.ReportInfoDao;
import com.yc.snacknet.util.StringUtil;

@WebServlet("/report")
public class ReportInfoController extends BasicController{
	private static final long serialVersionUID = 1541343297567029693L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String op = request.getParameter("op");

		if ("findt".equals(op)) { // 营业额统计
			findt(request, response);
		} else if ("findByType".equals(op)) { // 根据类型统计
			findByType(request, response);
		}

	}

	private void findByType(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String year = request.getParameter("year");

		ReportInfoDao reportInfoDao = new ReportInfoDao();
		if (StringUtil.checkNull(year)) { // 说明是按年统计
			this.send(response, reportInfoDao.findByType());
			return;
		} 

		// 说明是按月统计
		this.send(response, reportInfoDao.findByType(year));
	}

	private void findt(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String year = request.getParameter("year");

		ReportInfoDao reportInfoDao = new ReportInfoDao();
		if (StringUtil.checkNull(year)) { // 说明是按年统计
			this.send(response, reportInfoDao.reportByYear());
			return;
		} 

		// 说明是按月统计
		this.send(response, reportInfoDao.reportByMonth(year));
	}
}
