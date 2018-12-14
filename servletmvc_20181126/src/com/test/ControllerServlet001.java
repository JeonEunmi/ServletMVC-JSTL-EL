package com.test;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// MVC ���Ͽ����� ��Ʈ�ѷ� Ŭ����
// -> ����� ��û ó�� �� �帧����
// -> ����) �׼� �ڵ� �ۼ����� �ʴ´�.
// -> ����) ��� ��� �ڵ� �ۼ����� �ʴ´�.
public class ControllerServlet001 extends HttpServlet {
	
	// �ڵ� ȣ��
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// ���� ó�� �޼ҵ� ȣ��
		this.processRequest(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// ���� ó�� �޼ҵ� ȣ��
		this.processRequest(req, resp);
	}
	
	// doGet(), doPost() �޼ҵ��� ���� ó�� �޼ҵ�
	protected void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// 2�ܰ�. ��û �м�
		// ���� ���ڿ��� ?type=hello ���� �ʿ�
		String type = req.getParameter("type");
		
		// 3�ܰ�. ��(.Java)�� �̿��� ó�� �׼� ����
		Object result = null;
		
		if(type == null) {
			
		} else if(type.equals("hello")) {
			result = "Hello, world!";
			// ó�� ����� ��� �����ϱ� ���� ��ü ����
			// -> ������ �׼��� ��� request ��ü�� �� ���� ����
			// -> �����̷�Ʈ �׼��� ��� request ��ü�� �� ���� �Ұ�
			req.setAttribute("result", result);
		} else if(type.equals("sample")) {
			result = "�ȳ��ϼ���!";
			req.setAttribute("result", result);
		}
		// 4�ܰ�. �˸��� ��(.jsp)�� ������ or �˸��� ���������� �����̷�Ʈ
		// -> Ư�� .jsp ������ �ּ� ���� �ʿ�
		RequestDispatcher dispatcher = req.getRequestDispatcher("hello.jsp");
		dispatcher.forward(req, resp);		
	}

}
