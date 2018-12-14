package com.test;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Sample001 extends HttpServlet {

	
	// 자동 호출 메소드 -> GET 방식 요청 -> 브라우저 주소창의 주소에 의한 요청
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {	
		
		//서버의 콘솔창에 메시지 출력
		System.out.println("GET 방식 요청!");		
		
		//결과 출력을 위한 out 객체 준비
		PrintWriter out = resp.getWriter();
		
		//메시지 반환
		out.println("<h1>Hello, Servlet world!</h1>");
	}

	// 자동 호출 메소드 -> POST 방식 요청 -> method="POST" 폼 요청
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("POST 방식 요청!");		
		PrintWriter out = resp.getWriter();
		out.println("<h1>Hello, Servlet world!</h1>");
	}
	
	

	
}
