package com.test;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//CommandHandler �������̽��� ������ Ŭ���� ����
public class HelloHandler{

	//process() �޼ҵ忡 ���� �������̵�
	
	//command.properties ���Ͽ� ��ϵ� �޼ҵ� ����
	public String hello(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String result = "Hello, World!";
		//ó�� ����� ��� �����ϱ� ���� ��ü ����
		//->������ �׼��� ��� request ��ü�� �� ���� ����
		//->�����̷�Ʈ �׼��� ��� request ��ü�� �� ���� �Ұ�
		request.setAttribute("result", result);
		
		//��(View) ������ ���� ��ȯ
		return "/WEB-INF/views/hello.jsp";
	}
	
	//command.properties ���Ͽ� ��ϵ� �޼ҵ� ����
	public String test(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String result = "TEST!";
		request.setAttribute("result", result);
		
		return "/WEB-INF/views/hello.jsp";
	}

}
