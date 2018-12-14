package com.test;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//MVC 패턴에서의 컨트롤러 클래스
//->사용자 요청 처리 및 흐름제어
//->주의) 처리 액션 코드 작성하지 않는다
//->주의) 결과 출력 코드 작성하지 않는다
public class ControllerServlet002 extends HttpServlet {

	//자동 호출
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//통합 처리 메소드 호출
		this.processRequest(req, resp);
	}

	//자동 호출
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//통합 처리 메소드 호출
		this.processRequest(req, resp);
	}

	//doGet(), doPost() 메소드의 통합 처리 메소드
	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//2단계. 요청 분석 -> Command 패턴
		//-> 쿼리 문자열에 ?cmd=hello 지정 필요
		String type = request.getParameter("cmd");
		
		//3단계. 모델(.java)을 이용한 처리 액션 구현
		CommandHandler result = null;
		if (type == null) {
			
			result = new NullHandler();
			
		} else if (type.equals("hello")) {
			
			//특정 액션 처리를 위한 Handler 객체 요청
			//->인터페이스 자료형 변수에 저장
			result = new HelloHandler();
			
		} else if (type.equals("sample")) {
			
			//특정 액션 처리를 위한 Handler 객체 요청
			//->인터페이스 자료형 변수에 저장
			result = new SampleHandler();
			
		}
		
		//Handler 객체의 공통 메소드 호출
		String uri = result.process(request, response);
		
		//4단계. 알맞은 뷰(.jsp)로 포워딩 or 알맞은 서블릿으로 리다이렉트
		//-> 특정 .jsp 페이지 주소 지정 필요
		RequestDispatcher dispatcher = request.getRequestDispatcher(uri);
		dispatcher.forward(request, response);
		
	}	
	
}
