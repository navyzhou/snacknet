
package com.yc.snacknet.listener;

import java.io.File;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.yc.snacknet.util.FileUploadUtil;
import com.yc.snacknet.util.StringUtil;

@WebListener
public class ApplicationListener implements ServletContextListener{
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
	
	}
	
	@Override
	public void contextInitialized(ServletContextEvent arg) {
		String path = arg.getServletContext().getInitParameter("uploadPath");
		if (StringUtil.checkNull(path)) {
			return;
		}
		
		String basePath = arg.getServletContext().getRealPath("/");
		File fl = new File(basePath, "../" + path);
		if (!fl.exists()) {
			fl.mkdirs();
		}
		FileUploadUtil.uploadPath = "../" + path;
	}
}
