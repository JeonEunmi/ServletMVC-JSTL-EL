package com.test;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.guestbook.GuestbookDAO;


public class Guestbook_insert extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// �׼� ���� �������� HTML �ڵ尡 ���� ���� Ư¡�̴�.
		String path = request.getContextPath();

		// Ŭ���̾�Ʈ�� ������ �ڷ� ����
		request.setCharacterEncoding("UTF-8");
		
		String name_ = request.getParameter("name_");
		String pw = request.getParameter("pw");
		String content = request.getParameter("content");
		String clientIp = request.getRemoteAddr();
		// ������ ���̽� �׼� ó��
		int result = 0;
		String txt = "";
		if (name_ != null) {
			GuestbookDAO dao = new GuestbookDAO();
			try {
				result = dao.gusetbookAdd(name_, pw, content, clientIp);
				if (result == 1) {
					txt = "success";
				} else {
					throw new Exception("�����ͺ��̽� ���� �߻�!");
				}
			} catch (Exception e) {
				e.printStackTrace();
				txt = "fail";
			}
		}

		//���(���� �Ǵ� ����) �޽��� ��ȯ -> ���� ���ڿ� ���
			//����)����� ��� ������ ǥ�⸦ �����Ѵ�.
			response.sendRedirect("guestbook_design?result=" + txt);
			
	}

	
	

	
	
}
