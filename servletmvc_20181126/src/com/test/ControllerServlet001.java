package com.test;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// MVC 패턴에서의 컨트롤러 클래스
// -> 사용자 요청 처리 및 흐름제어
// -> 주의) 액션 코드 작성하지 않는다.
// -> 주의) 결과 출력 코드 작성하지 않는다.
public class ControllerServlet001 extends HttpServlet {
	
	// 자동 호출
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 통합 처리 메소드 호출
		this.processRequest(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 통합 처리 메소드 호출
		this.processRequest(req, resp);
	}
	
	// doGet(), doPost() 메소드의 통합 처리 메소드
	protected void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// 2단계. 요청 분석
		// 쿼리 문자열에 ?type=hello 지정 필요
		String type = req.getParameter("type");
		
		// 3단계. 모델(.Java)을 이용한 처리 액션 구현
		Object result = null;
		
		if(type == null) {
			
		} else if(type.equals("hello")) {
			result = "Hello, world!";
			// 처리 결과를 뷰로 전달하기 위한 객체 설정
			// -> 포워딩 액션인 경우 request 객체의 값 공유 가능
			// -> 리다이렉트 액션인 경우 request 객체의 값 공유 불가
			req.setAttribute("result", result);
		} else if(type.equals("sample")) {
			result = "안녕하세요!";
			req.setAttribute("result", result);
		}
		// 4단계. 알맞은 뷰(.jsp)로 포워딩 or 알맞은 서블리스으로 리다이렉트
		// -> 특정 .jsp 페이지 주소 지정 필요
		RequestDispatcher dispatcher = req.getRequestDispatcher("hello.jsp");
		dispatcher.forward(req, resp);		
	}

}
