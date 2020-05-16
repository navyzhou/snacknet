package com.yc.snacknet.util;

public class StringUtil {
	/**
	 * 为空检查
	 * @param strs
	 * @return
	 */
	public static boolean checkNull(String ... strs) {
		if (strs == null || strs.length <= 0) {
			return true;
		} 

		for (String str : strs) {
			if (str == null || "".equals(str) || "null".equals(str)) {
				return true;
			}
		}
		return false;
	}
}
