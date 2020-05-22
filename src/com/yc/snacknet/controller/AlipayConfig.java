package com.yc.snacknet.controller;

import java.io.FileWriter;
import java.io.IOException;

/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *修改日期：2017-04-05
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
 */
public class AlipayConfig {
	// 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
	public static String app_id = "2016102200741262";

	// 商户私钥，您的PKCS8格式RSA2私钥
	public static String merchant_private_key = "MIIEvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQCtPYxijPLRRAoHfzH+MzqCpTSB2MmxJrb/w8d1WfZgzzLJal7xS6g0dLK/PCBsSvdRyPfpQoLxoj3WbTkJ1URhQ+clG68AIIBs0JH9VR9JA2R1RmVgqjXHLRKQmGGmZIneIK8WJMvI5ZMZtM00173ZDCHjorZlGuMNHvmfoH1+7z9MlB44ASDEtlOhmOu4JHUA/C7Aap/Kd6cgS9rLz4puzdIMSI0hBADaOI38XgpjwzemuytfrWq7C6nqgca+Gg2XHhLyrnZ4WKmRgvOXUuVSht8JCEz7HsyMebro5x7wwEm88TyIaaIBKLxXEbYbrpqhs8WTKVEY0q265aC9DVkBAgMBAAECggEBAJK60rCV2QPNsQDwhpNbKG+HJZXk28+jjtPKKqIGdFtCoy1dXtTY07q6LLs0XfHa/ObUZMW4g9fNJrr6/5iIfcY0EQ6pB+v/m0aO0VfURQ8+RFPgL/VTtQDh+BtoNS0Ncogk/qauu7uIVrMrI7NiF3ZChGGdawbvYue2GI9PC3v6F4GSZkoZZx6CzbemJpxgT0c8BwXqi25U1UPo/M8Z0me8vw3IMzGggd19Ix7vUQqObf8AOeX9GrdcYAVcG7nFD6Yu5eQmTzIhSaEyg56NtofJtIzlgd08UP3Kzp/JKNhLu3/Z4pIqc/JHGWA3BIVa+GHjDeBHn0+vIeAm6cFPgFECgYEA8VGr/rXSKauX5pSDNC4M3fgRn+IJNLJ/gPYmZOvaKA3INZ3RlTeWa2hG3x/733+4/MI1CnqNYQKhEdckQC1samqTUU2bWC7aK2VUfDprz7ejjMzbIMasjqQEhq3B7XyxoNV0la/zKEaXpO/M3YiwSeST9eT4E+xtTsHAIAn9oBcCgYEAt8ec8u+1nCta12jmbMJFSWibrvC/T7aLmqi0y+V/yoctBWf+NGTKMauUXw4uZzTX/oGm5/R1uYaTG7YJkJLZ+rZV1FGBWM0sFnKYl7cko8G86U97V3iCMFuN9YPZWfU+loz1DJ8tfH4ZbBT4tWR9J4W/IKHmMfqTX2HjsM9tpqcCgYEAwRYOPrGQxVLNKevux1OJbBnPUFR19TpVlwRdWCb22VGdvnjWd0SREMPQ4TsgN4Zkp6lk80t30AVgh3vTP56YvtUjpswn3fkvC8X6QaQlG4DeVgyb0K/m5gMKtBBXuQQqxHvG2vZ74uAI+in9i8yQ8a75TKi2boqgM4IHjhswWmUCgYBE+9CWABSTglKe+OlFeca4P2Pwzr2BFmxgqWL0BjlptEPZ+6cX051x943CAHi6lMNe7RvRhZmLAlDF3hbJBdZ8nWSqRc3fn2QlKO7ORBikv1xB12vc8Ycsz7EBr3QuqDfOGysAid9xE4RHZbfVntq7syCDB0J5EC2xZ/UnsTTfkQKBgQC/pq8H1LMdCEG2N0rux+HXVSPc0AFZTu5Tr8wbNKwvwCAtLMbXwM8ZuvcmbPdghSbHDhZ/6fgDSeoCkPQ2c2VDxRLfEjVqPjf8vy9Z4uFEOe88LK0UejHZWBvyVTTPFjkOJ1/+a0MDmNx27rkH880hIMbRRQqT4NbViDXpxSBL2A==";

	// 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
	public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEArT2MYozy0UQKB38x/jM6gqU0gdjJsSa2/8PHdVn2YM8yyWpe8UuoNHSyvzwgbEr3Ucj36UKC8aI91m05CdVEYUPnJRuvACCAbNCR/VUfSQNkdUZlYKo1xy0SkJhhpmSJ3iCvFiTLyOWTGbTNNNe92Qwh46K2ZRrjDR75n6B9fu8/TJQeOAEgxLZToZjruCR1APwuwGqfynenIEvay8+Kbs3SDEiNIQQA2jiN/F4KY8M3prsrX61quwup6oHGvhoNlx4S8q52eFipkYLzl1LlUobfCQhM+x7MjHm66Oce8MBJvPE8iGmiASi8VxG2G66aobPFkylRGNKtuuWgvQ1ZAQIDAQAB";

	// 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	// public static String notify_url = "http://127.0.0.1:8080/snacknet/notify_url.jsp";
	public static String notify_url = "http://www.maiduit.com/snacknet/notify_url.jsp";

	// 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	//public static String return_url = "http://127.0.0.1:8080/snacknet/return_url.jsp";
	public static String return_url = "http://www.maiduit.com/snacknet/return_url.jsp";

	// 签名方式
	public static String sign_type = "RSA2";

	// 字符编码格式
	public static String charset = "utf-8";

	// 支付宝网关
	public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";

	// 支付宝网关
	//public static String log_path = "C:\\";
	public static String log_path = "/";

	/** 
	 * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
	 * @param sWord 要写入日志里的文本内容
	 */
	public static void logResult(String sWord) {
		FileWriter writer = null;
		try {
			writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis()+".txt");
			writer.write(sWord);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (writer != null) {
				try {
					writer.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}

