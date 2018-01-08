package com.xpay.pay.controller;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.xpay.pay.model.Order;
import com.xpay.pay.model.Store;
import com.xpay.pay.model.StoreGoods;
import com.xpay.pay.service.PaymentService;
import com.xpay.pay.service.StoreGoodsService;
import com.xpay.pay.service.StoreService;

public class GoodsQrCodeServlet extends HttpServlet {
	private static final long serialVersionUID = 5131564714596607060L;
	protected final Logger logger = LogManager.getLogger("AccessLog");

	@Autowired
	protected StoreGoodsService goodsService;
	
	@Autowired
	protected PaymentService paymentService;
	
	@Autowired
	protected StoreService storeService;

	@Override
	public void init(ServletConfig config) throws ServletException {
		SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, config.getServletContext());
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String uri = request.getRequestURI();
		String goodsCode = uri.substring(uri.lastIndexOf("/") + 1);
		StoreGoods goods = goodsService.findByCode(goodsCode);
		
		if (goods == null) {
			response.sendError(400, "无效商品");
			return;
		}
		String path = request.getPathInfo();
		String parameters = StringUtils.isBlank(request.getQueryString()) ? "" : "?" + request.getQueryString();
		logger.info("GoodsQrCode: " + path + parameters);
		String uid = request.getParameter("uid");
		Store store = storeService.findById(goods.getStoreId());
		Order order = paymentService.createGoodsOrder(store, goods, uid);
		if(order == null || StringUtils.isBlank(order.getCodeUrl())) {
			response.sendError(400, "无效商品");
		    return;
		}
		response.sendRedirect(order.getCodeUrl());
	}
}
