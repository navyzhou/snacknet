package com.yc.snacknet.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 * 邮件发送的工具类
 * @author navy
 */
public class SendMailUtil{
	/**
	 * qq邮箱发送
	 * @param from_email：发送者邮箱
	 * @param pwd：发送者邮箱的授权码
	 * @param str：接受者邮箱，如果有多个，请有逗号隔开
	 * @param code：要发送的验证码
	 * @param name：用户姓名
	 * @return
	 */
	public boolean sendQQmail(String from_email, String pwd, String recevices, String code, String name){
		try {
			// 获取系统当前属性
			Properties props = new Properties();
			props.put("mail.transport.protocol", "smtp"); //设置发送邮件使用的协议 
			props.put("mail.smtp.host","smtp.qq.com");
			props.put("mail.smtp.auth","true");

			// 通过系统属性创建一个会话类
			Session session = Session.getInstance(props);

			//创建一个message对象，用来创建一封邮箱
			Message message = new MimeMessage(session);

			//设置邮件发送者邮箱地址
			message.setFrom(new InternetAddress(from_email));

			String[] addrs = recevices.split(",");
			InternetAddress[] to = new InternetAddress[addrs.length];
			for(int i = 0, len = addrs.length; i < len; i++){
				to[i] = new InternetAddress(addrs[i]);
			}


			//设置邮件发送的类型
			message.setRecipients(MimeMessage.RecipientType.TO, to);

			//邮件标题
			message.setSubject("源辰-零食网");
			message.setSentDate(new Date()); //设置时间
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
			String str = "<!DOCTYPE html><html><head><meta charset='UTF-8'></head><body><p style='font-size: 20px;font-weight:bold;'>尊敬的："+name+"，您好！</p>"
					+ "<p style='text-indent:2em; font-size: 20px;'>欢迎注册源辰零食网，您本次的注册码是 "
					+ "<span style='font-size:30px;font-weight:bold;color:red'>" + code + "</span>，3分钟之内有效，请尽快使用！</p>"
					+ "<p style='text-align:right; padding-right: 20px;'"
					+ "<a href='http://www.hyycinfo.com' style='font-size: 18px'>衡阳市源辰信息科技有限公司技术部</a></p>"
					+ "<span style='font-size: 18px; float:right; margin-right: 60px;'>" + sdf.format(new Date()) + "</span></body></html>";

			Multipart mul=new MimeMultipart();  //新建一个MimeMultipart对象来存放多个BodyPart对象
			BodyPart mdp=new MimeBodyPart();  //新建一个存放信件内容的BodyPart对象
			mdp.setContent(str, "text/html;charset=utf-8");
			mul.addBodyPart(mdp);  //将含有信件内容的BodyPart加入到MimeMultipart对象中
			message.setContent(mul); //把mul作为消息内容
			message.saveChanges();

			//创建一个传输对象
			Transport transport=session.getTransport("smtp");

			//建立与服务器的链接  465端口是 SSL传输
			transport.connect("smtp.qq.com", 587, from_email, pwd);

			//发送邮件
			transport.sendMessage(message,message.getAllRecipients());

			//关闭邮件传输
			transport.close();
		} catch (Exception e) {
			LogUtil.log.error(e);
			return false;
		}
		return true;
	}
}
