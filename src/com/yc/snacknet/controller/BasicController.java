package com.yc.snacknet.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class BasicController extends HttpServlet{
	private static final long serialVersionUID = -3808609982371106118L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
	
	protected void send(HttpServletResponse response, int result) throws IOException {
		PrintWriter out = response.getWriter();
		out.print(result);
		out.flush();
		out.close();
	}
	
	protected void send(HttpServletResponse response, String result) throws IOException {
		PrintWriter out = response.getWriter();
		out.print(result);
		out.flush();
		out.close();
	}
	
	protected void send(HttpServletResponse response, Object obj) throws IOException {
		PrintWriter out = response.getWriter();
		Gson gson = new GsonBuilder().serializeNulls().create();
		out.print(gson.toJson(obj));
		out.flush();
		out.close();
	}
	
	protected void send(HttpServletResponse response, int code, Object obj) throws IOException {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", code);
		map.put("data", obj);
		PrintWriter out = response.getWriter();
		Gson gson = new GsonBuilder().serializeNulls().create();
		out.print(gson.toJson(map));
		out.flush();
		out.close();
	}
}
