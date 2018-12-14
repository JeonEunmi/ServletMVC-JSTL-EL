package com.test;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.member.*;

public class MemberInsertHandler implements CommandHandler {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		System.out.println("MemberInsertHandler ȣ��");

		//Ŭ���̾�Ʈ�� ������ �ڷ� ���� -> request.getParameter() �޼ҵ� ���
		request.setCharacterEncoding("utf-8");
	    String name_ = request.getParameter("name_");
	    String phone = request.getParameter("phone");
	    
		//�����ͺ��̽� �׼� ó�� -> MemberDAO ��ü�� memberAdd() �޼ҵ� ȣ��
		String txt = "";
		if (name_ != null) {
			MemberDAO dao = new MemberDAO();
			int result = dao.memberAdd(new Member(null, name_, phone));
			if (result == 1) {
		         txt = "success";
		    } else {
		         txt = "fail";
		    }
		}
		
		
		//����) INSERT, UPDATE, DELETE �� DML �׼� ó�� �Ŀ���
		//�����̷�Ʈ �׼��� ���� ���� ��ȯ
		return "memberlist.it?result="+txt;
	}

}
