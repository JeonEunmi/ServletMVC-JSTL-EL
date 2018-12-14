package com.filter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LoginFilter implements Filter {

	//���� ����� �� ���� ����Ǵ� �޼ҵ�
	
	
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

		HttpServletRequest httpRequest = (HttpServletRequest)request;
		HttpSession session = httpRequest.getSession();

		Boolean login = false;
		if (session != null) {
			if (session.getAttribute("login") != null) {
				login = true;
			}
		}
		
		//2.���� ü�� ����
		if (login) {
			chain.doFilter(request, response);
		} else {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.write("<!DOCTYPE html>\r\n");
			out.write("<html>\r\n");
			out.write("<head>\r\n");
			out.write("<title>�ֿ뱳������</title>\r\n");
			out.write("<meta charset=\"UTF-8\">\r\n");
			out.write("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\r\n");
			out.write("<link rel=\"stylesheet\"\r\n");
			out.write("\thref=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css\">\r\n");
			out.write("<script\r\n");
			out.write("\tsrc=\"https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js\"></script>\r\n");
			out.write("<script\r\n");
			out.write("\tsrc=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js\"></script>\r\n");
			out.write("</head>\r\n");
			out.write("<body>\r\n");
			out.write("\t<div class=\"container\" style=\"text-align:center;padding-top:100px;\">\r\n");
			out.write("\t<img src=\"");
			out.write(httpRequest.getContextPath());
			out.write("/resources/img/access_denied.png\">\r\n");
			out.write("\t</div>\r\n");
			out.write("</body>\r\n");
			out.write("</html>");
		}
		
		
	
	}
	@Override
	public void init(FilterConfig arg0) throws ServletException {
		//���� ��ü�� �ʱ�ȭ �׼� 
	}

}
