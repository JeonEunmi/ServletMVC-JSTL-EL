package com.test;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class NullHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response)	throws ServletException, IOException {
		
		//주의) 액션이 없는 상태로 작성한다.
		
		return "hello.jsp";
	}


}
