package com.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class CharacterEncodingFilter implements Filter {

	//최초 실행시 한 번만 실행되는 메소드
	@Override
	public void init(FilterConfig arg0) throws ServletException {
		//필터 객체의 초기화 액션 
	}
	
	//필터 객체 소멸시 한 번만 실행되는 메소드
	@Override
	public void destroy() {
		//마무리 액션
	}

	//사용자 요청시마다 매번 실행되는 메소드
	@Override
	public void doFilter(ServletRequest request
				, ServletResponse response
				, FilterChain chain)
			throws IOException, ServletException {
		//1.request에 대한 필터링 설정

		//한글 인코딩 설정 액션
		request.setCharacterEncoding("UTF-8");
		
		//2.필터 체인 설정
		chain.doFilter(request, response);
		
		//3.response에 대한 필터링 설정
		
	}

}
