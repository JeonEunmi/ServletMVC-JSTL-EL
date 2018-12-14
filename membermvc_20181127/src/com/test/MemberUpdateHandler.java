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

		System.out.println("MemberUpdateHandler 호출");

		// 클라이언트가 보내는 자료 수신
		request.setCharacterEncoding("UTF-8");
		String name_ = request.getParameter("name_");
		String phone = request.getParameter("phone");
		String mid_ = request.getParameter("mid_");

		int result = 0;
		String txt = "";

		// 데이터베이스 액션 처리
		if (name_ != null) {
			MemberDAO dao = new MemberDAO();
			result = dao.memberModify(new Member(mid_, name_, phone));

			// 결과(성공 또는 실패) 메시지 반환
			if (result == 1) {
				txt = "success!";
			} else {
				txt = "fail";
			}
		}

		// 주의) INSERT, UPDATE, DELETE 등 DML 액션 처리 후에는
		// 리다이렉트 액션을 위한 정보 반환
		return "memberlist.it?result=" + txt;
	}

}
