package com.xm;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.corba.se.pept.transport.Connection;

public class GongNen extends HttpServlet {

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

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
		out.println("  <BODY>");
		out.print("    This is ");
		out.print(this.getClass());
		out.println(", using the GET method");
		out.println("  </BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();
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
		 request.setCharacterEncoding("UTF-8");
		 
	         Connection con=null;
	        try{
	       	Class.forName("com.mysql.jdbc.Driver");
	       	con=(Connection) DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/studb","root","admin");
	       	 Statement sta=(Statement)((java.sql.Connection) con).createStatement();
	       	 
	       	String username=request.getParameter("username");
	        String pwd=request.getParameter("pwd");
	        
	             ResultSet rs=(ResultSet) sta.executeQuery("SELECT * FROM shuji where xm='"+username+"' and mima='"+pwd+"'");
	       	if(rs.next()){
	       		 String xm=rs.getString("xm");   		 
	       		 String miam=rs.getString("mima");
	       		 if(xm.equals(username)&& miam.equals(pwd)){
	       			 System.out.println("登陆成功");
	       			 request.getRequestDispatcher("/yes").forward(request,response);
	       		 }
	       	}else{
	       			 response.sendRedirect("//GongNen/zhuce.html");
	       		 }
	       	 
	        }catch(ClassNotFoundException e){
	       	 System.out.println("不符合");
	        }catch(SQLException sqle){
	       	 System.out.println("连接异常");
	        }
	        
	        
	        

}

		
		
		


}
