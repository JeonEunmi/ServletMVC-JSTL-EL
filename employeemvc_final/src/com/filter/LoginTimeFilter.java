package com.filter;

import java.io.IOException;
import java.util.Date;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LoginTimeFilter implements Filter {

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
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        // 1.request�� ���� ���͸� ����
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpSession session = httpRequest.getSession();
 
        // �α��� �� ����ð� ���
        long endTime = (new Date().getTime() - (long) session.getAttribute("startTime")) / 1000;
        request.setAttribute("loginTime", endTime);
 
        // 2.���� ü�� ����
        chain.doFilter(request, response);
 
        // 3.response�� ���� ���͸� ����
        
    }

}