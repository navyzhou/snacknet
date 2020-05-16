package com.yc.snacknet.dao;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.yc.snacknet.util.LogUtil;

public class ReadPro extends Properties {
	private static final long serialVersionUID = 1L;
	private static ReadPro instance = new ReadPro();  //这是对外提供的一个实例

	/**
	 * 外部调用这个方法来获取唯一的一个实例
	 */

	public static ReadPro getInstance(){
		return instance;
	}

	//单例模式最核心的是构造方法私有化
	private ReadPro(){
		//从db.properties文件中读取所有的配置信息
		InputStream is = null;
		//通过类的反射实例找到classpath路径下的资源文件,文件是db.properties,并建立一个流
		try {
			is = ReadPro.class.getClassLoader().getResourceAsStream("db.properties");
			this.load(is); //将流里面的字节码加载到MyPro对象中
		} catch (IOException e) {
			LogUtil.log.error(e.toString());
			throw new RuntimeException(e);
		} finally {
			if ( is != null) {
				try {
					is.close();
				} catch (IOException e1) {
					LogUtil.log.error(e1.toString());
				}
			}
		}
	}
}
