package com.test;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.member.*;

public class MemberInsertHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		System.out.println("MemberInsertHandler 호출");

		//클라이언트가 보내는 자료 수신 -> request.getParameter() 메소드 사용
		request.setCharacterEncoding("utf-8");
	    String name_ = request.getParameter("name_");
	    String phone = request.getParameter("phone");
	    
		//데이터베이스 액션 처리 -> MemberDAO 객체의 memberAdd() 메소드 호출
		String txt = "";
		if (name_ != null) {
			MemberDAO dao = new MemberDAO();
			int result = dao.memberAdd(new Member(null, name_, phone));
			if (result == 1) {
		         txt = "success";
		    } else {
		         txt = "fail";
		    }
		}
		
		
		//주의) INSERT, UPDATE, DELETE 등 DML 액션 처리 후에는
		//리다이렉트 액션을 위한 정보 반환
		return "memberlist.it?result="+txt;
	}

}
