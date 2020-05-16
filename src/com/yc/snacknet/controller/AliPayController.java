package com.yc.snacknet.controller;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 沙箱支付
 * 源辰信息
 * @author navy
 * @date 2020年5月16日
 */
@WebServlet("/alipay")
public class AliPayController extends BasicController{
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获得初始化的AlipayClient
		AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id, AlipayConfig.merchant_private_key, "json", AlipayConfig.charset, AlipayConfig.alipay_public_key, AlipayConfig.sign_type);

		//设置请求参数
		AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
		alipayRequest.setReturnUrl(AlipayConfig.return_url);
		alipayRequest.setNotifyUrl(AlipayConfig.notify_url);

		//商户订单号，商户网站订单系统中唯一订单号，必填
		//String out_trade_no = String.valueOf(order.getOno());
		String out_trade_no = String.valueOf(System.currentTimeMillis());
		//session.setAttribute("ono", out_trade_no);
		//付款金额，必填
		//String total_amount = String.valueOf(order.getPrice());
		double total_amount = 126.8;
		//订单名称，必填
		String subject = "源辰零食网";

		alipayRequest.setBizContent("{\"out_trade_no\":\"" + out_trade_no +"\"," 
				+ "\"total_amount\":\""+ total_amount +"\"," 
				+ "\"subject\":\""+ subject +"\"," 
				+ "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
		String result = "";
		try {
			result = alipayClient.pageExecute(alipayRequest).getBody();
		} catch (AlipayApiException e) {
			e.printStackTrace();
		}
		this.send(response, result);
	}
}