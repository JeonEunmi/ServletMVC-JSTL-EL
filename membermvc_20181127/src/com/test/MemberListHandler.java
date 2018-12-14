package com.test;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.member.Member;
import com.member.MemberDAO;

public class MemberListHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("MemberListHandler ȣ��");

		request.setCharacterEncoding("UTF-8");
		String key = request.getParameter("key");
		String value = request.getParameter("value");

		if (key == null || key.equals("all")) {
			key = "all";
			value = "";
		}

		MemberDAO dao = new MemberDAO();

		List<Member> list = dao.memberList(key, value);
		int totalcount = dao.totalcount();

		String result = request.getParameter("result");
		String txt = "";
		if (result != null) {
			if (result.equals("success!")) {
				txt = "<div class=\"alert alert-success alert-dismissible fade in\" style=\"display: inline-block; padding-top: 5px; padding-bottom: 5px; margin: 0px;\"><a href=\"#\" class=\"close\" data-dismiss=\"alert\" aria-label=\"close\">&times;</a><strong>Success!</strong> ��û�� �۾��� ó���Ǿ����ϴ�.</div>";
			} else {
				txt = "<div class=\"alert alert-danger alert-dismissible fade in\" style=\"display: inline-block; padding-top: 5px; padding-bottom: 5px; margin: 0px;\"><a href=\"#\" class=\"close\" data-dismiss=\"alert\" aria-label=\"close\">&times;</a><strong>Fail!</strong> ��û�� �۾��� ó������ ���߽��ϴ�.</div>";
			}
		}

		request.setAttribute("key", key);
		request.setAttribute("value", value);
		request.setAttribute("list", list);
		request.setAttribute("totalcount", totalcount);
		request.setAttribute("txt", txt);

		// ����) SELECT �׼� ó�� �Ŀ���
		// ������ �׼��� ���� ���� ��ȯ
		return "memberlist.jsp";
	}

}
