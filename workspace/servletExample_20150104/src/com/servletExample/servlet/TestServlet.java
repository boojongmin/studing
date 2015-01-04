package com.servletExample.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TestServlet extends HttpServlet {
	
//	@Override
//	public void doGet(HttpServletRequest request,
//						HttpServletResponse response){
//		
//		System.out.println("get 요청 받음!!");
//	}
//	
//	@Override
//	public void doPost(HttpServletRequest request,
//						HttpServletResponse response){
//		
//		System.out.println("post 요청 받음!!");
//	}
	
	
	@Override
	public void service(HttpServletRequest request,
						HttpServletResponse response) 
								throws IOException, ServletException{
		
		RequestDispatcher dispatcher 
			= request.getRequestDispatcher("/testService.jsp");
		dispatcher.forward(request, response);
		
	}
}
