package com.test;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.member.Member;
import com.member.MemberDAO;

public class Sample006_insert extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// ���� ������Ÿ�� ����
		req.setCharacterEncoding("UTF-8");
		String name_ = req.getParameter("name_");
		String phone = req.getParameter("phone");

		// �׼� �ڵ� �ۼ�
		int result = 0;
		String txt = "";
		if (name_ != null) {
			MemberDAO dao = new MemberDAO();
			try {
				result = dao.memberAdd(new Member(null, name_, phone));
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
		
		// ���(���� �Ǵ� ����) �޽��� ��ȯ
		resp.sendRedirect("sample006?result=" + txt);

	}

}
