package com.oracle.shop.web.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oracle.shop.model.daoImpl.factory.DaoImplFactory;
import com.oracle.shop.model.entity.Seqence;
import com.oracle.shop.model.entity.UserOrder;
import com.oracle.shop.model.entity.UserOrderlist;
import com.oracle.shop.model.serviceImpl.factory.ServiceImplFactory;

public class JieSuanServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request,response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/xml charset=utf-8");
		PrintWriter out = response.getWriter();
		//StringBuilder拼接<?xml version=\"1.0\" encoding=\"utf-8\" ?>
		StringBuilder date=new StringBuilder("<?xml version=\"1.0\" encoding=\"utf-8\" ?>");
		date.append("<date>");
		//获取用户名‘name_suc’   false
		HttpSession session = request.getSession(false);
		String username=(String) session.getAttribute("name_suc");
			//获取传过来的字符串‘orderlist’
			String s=request.getParameter("orderlist");
			//System.out.println(s);
			//按照逗号切割，返回字符串数组
			String[] split = s.split(",");
			double sum=0;
			//String gouwuche_id="";
			//循环拿总计和拿迭代的序列号
			for(int i=0;i<split.length;i++){
				//按照空格切割
				String[] split2 = split[i].split(" ");
				//强转double
				sum = Double.parseDouble(split2[1]);
				date.append("<info>"+sum+"</info>");
			}
			date.append("<date>");
			//拿订单表的序列 ‘Seqence’,返回Seqence对象
			Seqence seq = DaoImplFactory.getDao("Seqence").selectSeq();
			//从对象获取
			int order_id=seq.getNextSeq();
			UserOrder uorder=new UserOrder();
			uorder.setOrderId(order_id);
			uorder.setTotal(sum);
			uorder.setOrderUser(username);
			uorder.setOrderTiem(new Date(System.currentTimeMillis()));
			uorder.setOrderType(1);
			//System.out.println(uorder);
			//插入订单
			ServiceImplFactory.getDao("UserOrder").save(uorder);
			UserOrderlist order=null;
			//循坏取值，修改订单明细表的数据
			for(String str:split){
				order=new UserOrderlist();
				String[] split2 = str.split(" ");
				int id=Integer.parseInt(split2[4]);
				double price=Double.parseDouble(split2[3]);
				int count=Integer.parseInt(split2[2]);
				//System.out.println(price+"--"+count);
				order.setOrderNumber(order_id);
				order.setOrderLisetid(id);
				order.setAmount(count);
				order.setTotalprice(price*count);
				order.setOrderType(1);
				//System.out.println(order);
				int update = ServiceImplFactory.getDao("UserOrderlist").update(order);
				//System.out.println(update);
			}
			//System.out.println(date.toString());
			out.print(date.toString());
		
		out.close();
	}

}
