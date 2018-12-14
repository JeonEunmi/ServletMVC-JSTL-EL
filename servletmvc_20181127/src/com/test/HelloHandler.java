package com.test;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//CommandHandler �������̽��� ������ Ŭ���� ����
public class HelloHandler implements CommandHandler {

	//process() �޼ҵ忡 ���� �������̵�
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String result = "Hello, World!";
		//ó�� ����� ��� �����ϱ� ���� ��ü ����
		//->������ �׼��� ��� request ��ü�� �� ���� ����
		//->�����̷�Ʈ �׼��� ��� request ��ü�� �� ���� �Ұ�
		request.setAttribute("result", result);
		
		// ��(View) ������ ���� ��ȯ
		return "hello.jsp";
	}

}