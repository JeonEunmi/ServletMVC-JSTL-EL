package com.test;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.member.Member;
import com.member.MemberDAO;

//CommandHandler �������̽��� ������ Ŭ���� ����
public class MemberListHandler implements CommandHandler {

	//process() �޼ҵ忡 ���� �������̵�
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		MemberDAO dao = new MemberDAO();
		List<Member> list = dao.memberList("all", "");
		
		int totalcount = dao.totalcount();
		int count = list.size();
		
		request.setAttribute("list", list);
		request.setAttribute("totalcount", totalcount);
		request.setAttribute("count", count);

		return "memberlist.jsp";
	}

}
