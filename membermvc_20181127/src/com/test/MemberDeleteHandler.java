package com.test;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.member.*;

public class MemberDeleteHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("MemberDeleteHandler ȣ��");

		// Ŭ���̾�Ʈ�� ������ �ڷ� ����
		request.setCharacterEncoding("UTF-8");
		String mid_ = request.getParameter("mid_");
		System.out.println("mid:"+mid_);

		int result = 0;
		String txt = "";

		// �����ͺ��̽� �׼� ó��
		if (mid_ != null) {
			MemberDAO dao = new MemberDAO();
			result = dao.memberRemove(new Member(mid_, null, null));

			// ���(���� �Ǵ� ����) �޽��� ��ȯ
			if (result == 1) {
				txt = "success!";
			} else {
				txt = "fail";
			}
		}

		// ����) INSERT, UPDATE, DELETE �� DML �׼� ó�� �Ŀ���
		// �����̷�Ʈ �׼��� ���� ���� ��ȯ
		return "memberlist.it?result="+txt;
	}

}
