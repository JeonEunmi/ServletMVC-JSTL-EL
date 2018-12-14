package com.test;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//MVC ���Ͽ����� ��Ʈ�ѷ� Ŭ����
//->����� ��û ó�� �� �帧����
//->����) ó�� �׼� �ڵ� �ۼ����� �ʴ´�
//->����) ��� ��� �ڵ� �ۼ����� �ʴ´�
public class ControllerServlet002 extends HttpServlet {

	//�ڵ� ȣ��
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//���� ó�� �޼ҵ� ȣ��
		this.processRequest(req, resp);
	}

	//�ڵ� ȣ��
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//���� ó�� �޼ҵ� ȣ��
		this.processRequest(req, resp);
	}

	//doGet(), doPost() �޼ҵ��� ���� ó�� �޼ҵ�
	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//2�ܰ�. ��û �м� -> Command ����
		//-> ���� ���ڿ��� ?cmd=hello ���� �ʿ�
		String type = request.getParameter("cmd");
		
		//3�ܰ�. ��(.java)�� �̿��� ó�� �׼� ����
		CommandHandler result = null;
		if (type == null) {
			
			result = new NullHandler();
			
		} else if (type.equals("hello")) {
			
			//Ư�� �׼� ó���� ���� Handler ��ü ��û
			//->�������̽� �ڷ��� ������ ����
			result = new HelloHandler();
			
		} else if (type.equals("sample")) {
			
			//Ư�� �׼� ó���� ���� Handler ��ü ��û
			//->�������̽� �ڷ��� ������ ����
			result = new SampleHandler();
			
		}
		
		//Handler ��ü�� ���� �޼ҵ� ȣ��
		String uri = result.process(request, response);
		
		//4�ܰ�. �˸��� ��(.jsp)�� ������ or �˸��� �������� �����̷�Ʈ
		//-> Ư�� .jsp ������ �ּ� ���� �ʿ�
		RequestDispatcher dispatcher = request.getRequestDispatcher(uri);
		dispatcher.forward(request, response);
		
	}	
	
}
