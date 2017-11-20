package com.oracle.shop.web.controller;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.PageContext;
import com.oracle.shop.model.daoImpl.entity.GoodsImpl;
import com.oracle.shop.model.daoImpl.entity.TypeImpl;
import com.oracle.shop.model.entity.Goods;
import com.oracleoaec.exception.DataAccessException;
public class DetailServlet extends HttpServlet {
	/**
	 * Constructor of the object.
	 */
	public DetailServlet() {
		super();
	}
	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}
	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			response.setCharacterEncoding("utf-8");
			response.setContentType("text/html;charset=utf-8");
			HttpSession session = request.getSession();
			GoodsImpl findGoods=new GoodsImpl();
			TypeImpl goodsTotal=new TypeImpl();
			//获取类型id
			int	id= Integer.parseInt(request.getParameter("id"));
			//查找到是某一页
			int	rownum= Integer.parseInt(request.getParameter("rownum"));
			ArrayList<Goods> goods=null;//定义商品的集合
			int totalGoods=0;//定义查找到的商品总数
			int page=0;//页数初始值
			try {
				//获取商品总数
				totalGoods=goodsTotal.getTotalGoods(id);
				//算出页数
				page = (int)Math.ceil(totalGoods/11.0);
				//判断页数是否小于o或者大于总页数
				if(rownum<=0){
					rownum=1;
				}
				else if(rownum>=page){
					rownum=page;
				}
				goods = (ArrayList<Goods>)findGoods.findByTypeRange(id,rownum);
				
			} catch (DataAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
			request.setAttribute("rownum", rownum);
			request.setAttribute("page", page);
			request.setAttribute("goods", goods);
			request.getRequestDispatcher("/Details.jsp").forward(request, response);										 
	}
	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
		out.println("  <BODY>");
		out.print("    This is ");
		out.print(this.getClass());
		out.println(", using the POST method");
		out.println("  </BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();
	}
	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}
}
