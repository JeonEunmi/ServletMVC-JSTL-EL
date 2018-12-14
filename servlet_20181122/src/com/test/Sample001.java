package com.test;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Sample001 extends HttpServlet {

	
	// �ڵ� ȣ�� �޼ҵ� -> GET ��� ��û -> ������ �ּ�â�� �ּҿ� ���� ��û
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {	
		
		//������ �ܼ�â�� �޽��� ���
		System.out.println("GET ��� ��û!");		
		
		//��� ����� ���� out ��ü �غ�
		PrintWriter out = resp.getWriter();
		
		//�޽��� ��ȯ
		out.println("<h1>Hello, Servlet world!</h1>");
	}

	// �ڵ� ȣ�� �޼ҵ� -> POST ��� ��û -> method="POST" �� ��û
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("POST ��� ��û!");		
		PrintWriter out = resp.getWriter();
		out.println("<h1>Hello, Servlet world!</h1>");
	}
	
	

	
}
