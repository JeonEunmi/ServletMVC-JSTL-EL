package com.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class CharacterEncodingFilter implements Filter {

	//���� ����� �� ���� ����Ǵ� �޼ҵ�
	@Override
	public void init(FilterConfig arg0) throws ServletException {
		//���� ��ü�� �ʱ�ȭ �׼� 
	}
	
	//���� ��ü �Ҹ�� �� ���� ����Ǵ� �޼ҵ�
	@Override
	public void destroy() {
		//������ �׼�
	}

	//����� ��û�ø��� �Ź� ����Ǵ� �޼ҵ�
	@Override
	public void doFilter(ServletRequest request
				, ServletResponse response
				, FilterChain chain)
			throws IOException, ServletException {
		//1.request�� ���� ���͸� ����

		//�ѱ� ���ڵ� ���� �׼�
		request.setCharacterEncoding("UTF-8");
		
		//2.���� ü�� ����
		chain.doFilter(request, response);
		
		//3.response�� ���� ���͸� ����
		
	}

}
