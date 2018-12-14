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

	//DAO ��ü�� ���� ���� ������ ���� �ʵ� ����
	private LoginDAO loginDAO = new LoginDAO();
	
	//login.properties ���Ͽ� ��ϵ� �޼ҵ� ����
	public String loginForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// ������ �׼��� ���� ���� ��ȯ
		// ����) ����η� ����
		return "/WEB-INF/views/login/loginForm.jsp";
	}

	//login.properties ���Ͽ� ��ϵ� �޼ҵ� ����
	public String login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//request.setCharacterEncoding("UTF-8");
		//->���ڵ� ���ͷ� ��ü

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
				
				//�α��� ������ �ð� ���
				session.setAttribute("startTime", new Date().getTime());
				
				
				url = String.format("%s/employee/list",request.getContextPath());
				
			}
		}
		
		//�����̷�Ʈ �׼��� ���� ���� ��ȯ
		// ����) �����η� ����
		return url;
	}


	//login.properties ���Ͽ� ��ϵ� �޼ҵ� ����
	public String logoutForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// ������ �׼��� ���� ���� ��ȯ
		// ����) ����η� ����
		return "/WEB-INF/views/login/logoutForm.jsp";
	}

	//login.properties ���Ͽ� ��ϵ� �޼ҵ� ����
	public String logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//���� ��ü �Ҹ�
		HttpSession session = request.getSession();
		session.invalidate();
		
		//�����̷�Ʈ �׼��� ���� ���� ��ȯ
		// ����) �����η� ����
		return String.format("%s/login/logoutForm",request.getContextPath());
	}
	
	//login.properties ���Ͽ� ��ϵ� �޼ҵ� ����
	public String loginFailForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// ������ �׼��� ���� ���� ��ȯ
		// ����) ����η� ����
		return "/WEB-INF/views/login/loginFailForm.jsp";
	}

	//login.properties ���Ͽ� ��ϵ� �޼ҵ� ����
	public String loginFail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//�����̷�Ʈ �׼��� ���� ���� ��ȯ
		// ����) �����η� ����
		return String.format("%s/login/loginFailForm",request.getContextPath());
	}
}
