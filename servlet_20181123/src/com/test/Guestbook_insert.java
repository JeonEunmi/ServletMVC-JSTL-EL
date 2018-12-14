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
		
		// 액션 전용 페이지는 HTML 코드가 없는 것이 특징이다.
		String path = request.getContextPath();

		// 클라이언트가 보내는 자료 수신
		request.setCharacterEncoding("UTF-8");
		
		String name_ = request.getParameter("name_");
		String pw = request.getParameter("pw");
		String content = request.getParameter("content");
		String clientIp = request.getRemoteAddr();
		// 데이터 베이스 액션 처리
		int result = 0;
		String txt = "";
		if (name_ != null) {
			GuestbookDAO dao = new GuestbookDAO();
			try {
				result = dao.gusetbookAdd(name_, pw, content, clientIp);
				if (result == 1) {
					txt = "success";
				} else {
					throw new Exception("데이터베이스 오류 발생!");
				}
			} catch (Exception e) {
				e.printStackTrace();
				txt = "fail";
			}
		}

		//결과(성공 또는 실패) 메시지 반환 -> 쿼리 문자열 사용
			//주의)상대경로 대신 절대경로 표기를 권장한다.
			response.sendRedirect("guestbook_design?result=" + txt);
			
	}

	
	

	
	
}
