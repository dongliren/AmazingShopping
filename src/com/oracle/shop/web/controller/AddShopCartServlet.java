package com.oracle.shop.web.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oracle.shop.model.entity.Goods;
import com.oracle.shop.model.entity.UserOrderlist;
import com.oracle.shop.model.serviceImpl.factory.ServiceImplFactory;

public class AddShopCartServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request,response);
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html,charset=utf-8");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		request.setCharacterEncoding("utf-8");
		//获取session(false),获取"name_scu"属性不能为空
		HttpSession session = request.getSession(false);
		if(null==session.getAttribute("name_suc")){
			out.println("<span>请登录</span>");
		}
		else{
			//获取传过来的id和数量
			int img_id=Integer.parseInt(request.getParameter("id"));
			int count=Integer.parseInt(request.getParameter("count"));
			String username=(String) session.getAttribute("name_suc");
			//根据id查询Goods，返回good对象
			Goods good = ServiceImplFactory.getDao("Goods").findGoods(img_id);
			//创建订单明细对象，并把good中对应的属性赋值给orderlist对象
			UserOrderlist orderlist=new UserOrderlist();
			orderlist.setPrice(good.getPrice());
			orderlist.setBrandName(good.getBrandName());
			orderlist.setAmount(count);
			orderlist.setOrderTime(new Date(System.currentTimeMillis()));
			orderlist.setTypeId(good.getTypeId());
			orderlist.setOrderType(0);
			
			orderlist.setDiscribe(good.getGoodsDiscribe());
			orderlist.setImgpath(good.getImgpath());
			orderlist.setTotalprice(good.getPrice()*count);
			orderlist.setGoods_id(good.getId());
			orderlist.setUserName(username);
			//在把对象存入数据库UserOrderlist
			ServiceImplFactory.getDao("UserOrderlist").save(orderlist);
			out.println("<span>已加入购物车</span>");
		}
		out.close();
	}

}
