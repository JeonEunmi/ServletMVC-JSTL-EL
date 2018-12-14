package com.test;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.member.*;

public class MemberUpdateHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("MemberUpdateHandler ȣ��");

		// Ŭ���̾�Ʈ�� ������ �ڷ� ����
		request.setCharacterEncoding("UTF-8");
		String name_ = request.getParameter("name_");
		String phone = request.getParameter("phone");
		String mid_ = request.getParameter("mid_");

		int result = 0;
		String txt = "";

		// �����ͺ��̽� �׼� ó��
		if (name_ != null) {
			MemberDAO dao = new MemberDAO();
			result = dao.memberModify(new Member(mid_, name_, phone));

			// ���(���� �Ǵ� ����) �޽��� ��ȯ
			if (result == 1) {
				txt = "success!";
			} else {
				txt = "fail";
			}
		}

		// ����) INSERT, UPDATE, DELETE �� DML �׼� ó�� �Ŀ���
		// �����̷�Ʈ �׼��� ���� ���� ��ȯ
		return "memberlist.it?result=" + txt;
	}

}
