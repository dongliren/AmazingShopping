package com.oracle.shop.web.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oracle.shop.model.serviceImpl.factory.ServiceImplFactory;

public class ShopCartServlet extends HttpServlet {
 
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request,response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		//session中用户名为空就重定向登录界面
		if(null==session.getAttribute("name_suc")){
			response.sendRedirect("login.jsp");
		}
		//根据0和名字查找订单明细
		else{
			String username=(String) session.getAttribute("name_suc");
			List orderlist = ServiceImplFactory.getDao("UserOrderlist").findOrderlist(0,username);
			//把返回集合封装到request作用域中
			request.setAttribute("orderlist",orderlist);
			//把页面分发到购物车页面
			request.getRequestDispatcher("gouwuche.jsp").forward(request, response);
		}
		
	}

}
