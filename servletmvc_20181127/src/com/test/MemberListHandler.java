package com.test;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.member.Member;
import com.member.MemberDAO;

//CommandHandler 인터페이스를 구현한 클래스 선언
public class MemberListHandler implements CommandHandler {

	//process() 메소드에 대한 오버라이딩
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
