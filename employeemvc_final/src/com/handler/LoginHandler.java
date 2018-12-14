package com.handler;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.domain.Login;
import com.persistance.LoginDAO;

public class LoginHandler {

	//DAO 객체에 대한 공유 설정을 위한 필드 선언
	private LoginDAO loginDAO = new LoginDAO();
	
	//login.properties 파일에 등록된 메소드 선언
	public String loginForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 포워딩 액션을 위한 정보 반환
		// 주의) 상대경로로 지정
		return "/WEB-INF/views/login/loginForm.jsp";
	}

	//login.properties 파일에 등록된 메소드 선언
	public String login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//request.setCharacterEncoding("UTF-8");
		//->인코딩 필터로 대체

		String id_ = request.getParameter("id_");
		String pw_ = request.getParameter("pw_");
		
		String url = String.format("%s/login/loginFail",request.getContextPath());
		if (id_ == null) {
		} else {
			Login result = this.loginDAO.login(new Login(id_, pw_));
			if (result == null) {
			} else {
				HttpSession session = request.getSession();
				session.setAttribute("login", result);
				
				//로그인 성공한 시간 기록
				session.setAttribute("startTime", new Date().getTime());
				
				
				url = String.format("%s/employee/list",request.getContextPath());
				
			}
		}
		
		//리다이렉트 액션을 위한 정보 반환
		// 주의) 절대경로로 지정
		return url;
	}


	//login.properties 파일에 등록된 메소드 선언
	public String logoutForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 포워딩 액션을 위한 정보 반환
		// 주의) 상대경로로 지정
		return "/WEB-INF/views/login/logoutForm.jsp";
	}

	//login.properties 파일에 등록된 메소드 선언
	public String logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//세션 객체 소멸
		HttpSession session = request.getSession();
		session.invalidate();
		
		//리다이렉트 액션을 위한 정보 반환
		// 주의) 절대경로로 지정
		return String.format("%s/login/logoutForm",request.getContextPath());
	}
	
	//login.properties 파일에 등록된 메소드 선언
	public String loginFailForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 포워딩 액션을 위한 정보 반환
		// 주의) 상대경로로 지정
		return "/WEB-INF/views/login/loginFailForm.jsp";
	}

	//login.properties 파일에 등록된 메소드 선언
	public String loginFail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//리다이렉트 액션을 위한 정보 반환
		// 주의) 절대경로로 지정
		return String.format("%s/login/loginFailForm",request.getContextPath());
	}
}
