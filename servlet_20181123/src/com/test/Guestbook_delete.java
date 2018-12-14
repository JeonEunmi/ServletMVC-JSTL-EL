package com.test;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.guestbook.GuestbookDAO;


public class Guestbook_delete extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// �׼� ���� �������� HTML �ڵ尡 ���� ���� Ư¡�̴�.
		String path = request.getContextPath();

		// Ŭ���̾�Ʈ�� ������ �ڷ� ����
		request.setCharacterEncoding("UTF-8");
		String gid = request.getParameter("gid");
		String pw = request.getParameter("pw");

		System.out.println(gid);
		// ������ ���̽� �׼� ó��
		int result = 0;
		String txt = "";
		if (gid != null) {
			GuestbookDAO dao = new GuestbookDAO();
			try {
				result = dao.guestbookDel(gid, pw);
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
