package com.yc.snacknet.controller;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspFactory;
import javax.servlet.jsp.PageContext;

import com.yc.snacknet.dao.GoodsInfoDao;
import com.yc.snacknet.util.FileUploadUtil;
import com.yc.snacknet.util.StringUtil;

@WebServlet("/goods")
public class GoodsInfoController extends BasicController{
	private static final long serialVersionUID = 1541343297567029693L;
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String op = request.getParameter("op");
		
		if ("upload".equals(op)) { // 图片上传
			upload(request, response);
		} else if ("add".equals(op)) { // 添加商品信息
			add(request, response);
		} else if ("findByPageFirst".equals(op)) { // 第一次分页查询
			findByPageFirst(request, response);
		} else if ("findByPage".equals(op)) { // 分页查询
			findByPage(request, response);
		} else if ("updates".equals(op)) { // 修改状态
			updates(request, response);
		} else if ("findByGno".equals(op)) { // 查询商品详细
			findByGno(request, response);
		} else if ("findFirst".equals(op)) { // 前台首页第一次查询
			findFirst(request, response);
		} else if ("finds".equals(op)) { // 前台首页分页查询
			finds(request, response);
		}
	}

	private void finds(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int page = Integer.parseInt(request.getParameter("page"));
		int rows = Integer.parseInt(request.getParameter("rows"));
		
		String tno = request.getParameter("tno");
		String order = request.getParameter("order");
		String sprice = request.getParameter("sprice");
		String eprice = request.getParameter("eprice");
		String gname = request.getParameter("gname");
		
		GoodsInfoDao goodsInfoDao = new GoodsInfoDao();
		this.send(response, goodsInfoDao.finds(page, rows, tno, gname, sprice, eprice, order));
	}

	private void findFirst(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int page = Integer.parseInt(request.getParameter("page"));
		int rows = Integer.parseInt(request.getParameter("rows"));
		
		String tno = request.getParameter("tno");
		String order = request.getParameter("order");
		String sprice = request.getParameter("sprice");
		String eprice = request.getParameter("eprice");
		String gname = request.getParameter("gname");
		
		GoodsInfoDao goodsInfoDao = new GoodsInfoDao();
		this.send(response, goodsInfoDao.total(tno, gname, sprice, eprice), goodsInfoDao.finds(page, rows, tno, gname, sprice, eprice, order));
	}

	private void findByGno(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String gno = request.getParameter("gno");
		GoodsInfoDao goodsInfoDao = new GoodsInfoDao();
		if (StringUtil.checkNull(gno)) {
			this.send(response, Collections.EMPTY_MAP);
			return;
		}
		this.send(response, goodsInfoDao.findByGno(gno));
	}

	private void updates(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int status = Integer.parseInt(request.getParameter("status"));
		String gno = request.getParameter("gno");
		GoodsInfoDao goodsInfoDao = new GoodsInfoDao();
		
		this.send(response, goodsInfoDao.update(gno, status));
	}

	private void findByPage(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int page = Integer.parseInt(request.getParameter("page"));
		int rows = Integer.parseInt(request.getParameter("rows"));
		
		String tno = request.getParameter("tno");
		String gname = request.getParameter("gname");
		GoodsInfoDao goodsInfoDao = new GoodsInfoDao();
		
		this.send(response, goodsInfoDao.findByPage(page, rows, tno, gname));
	}

	/**
	 * 第一次分页查询
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	private void findByPageFirst(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int page = Integer.parseInt(request.getParameter("page"));
		int rows = Integer.parseInt(request.getParameter("rows"));
		
		String tno = request.getParameter("tno");
		String gname = request.getParameter("gname");
		GoodsInfoDao goodsInfoDao = new GoodsInfoDao();
		
		this.send(response, goodsInfoDao.total(tno, gname), goodsInfoDao.findByPage(page, rows, tno, gname));
	}

	/**
	 * 添加商品信息
	 * @param request
	 * @param response
	 * @throws IOException 
	 */
	private void add(HttpServletRequest request, HttpServletResponse response) throws IOException {
		FileUploadUtil upload=new FileUploadUtil();
		PageContext pageContext=JspFactory.getDefaultFactory().getPageContext(this, request, response, null, true, 2048, true);
		int result = 0;
		try {
			Map<String, String> map = upload.uploads(pageContext);

			GoodsInfoDao goodsInfoDao = new GoodsInfoDao();
			result = goodsInfoDao.add(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.send(response, result);
	}

	/**
	 * 富文本编辑器图片上传
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	private void upload(HttpServletRequest request, HttpServletResponse response) throws IOException {
		FileUploadUtil upload=new FileUploadUtil();
		PageContext pageContext=JspFactory.getDefaultFactory().getPageContext(this, request, response, null, true, 2048, true);
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			Map<String, String> map = upload.upload(pageContext);
			result.put("fileName", map.get("fileName"));
			result.put("uploaded", 1);
			result.put("url", "../../"+ map.getOrDefault("upload", ""));
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.send(response, result);
	}
}
