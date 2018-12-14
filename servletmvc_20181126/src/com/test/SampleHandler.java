package com.test;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//CommandHandler 인터페이스를 구현한 클래스 선언
public class SampleHandler implements CommandHandler {

	//process() 메소드에 대한 오버라이딩
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String result = "안녕하세요!";
		//처리 결과를 뷰로 전달하기 위한 객체 설정
		//->포워딩 액션인 경우 request 객체의 값 공유 가능
		//->리다이렉트 액션인 경우 request 객체의 값 공유 불가
		request.setAttribute("result", result);
		
		return "hello.jsp";
	}

}
