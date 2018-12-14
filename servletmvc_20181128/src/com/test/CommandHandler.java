package com.test;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//Handler 클래스들의 공통 요소 적용을 위한 인터페이스 선언
public interface CommandHandler {
	
	public String process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;

}
