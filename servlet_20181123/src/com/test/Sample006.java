package com.test;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.member.Member;
import com.member.MemberDAO;

public class Sample006 extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// 응답 콘텐츠타입 설정
		resp.setContentType("text/html; charset=UTF-8");

		// 액션 코드 작성
		StringBuilder sb = new StringBuilder();
		MemberDAO dao = new MemberDAO();
		int totalcount = dao.totalcount();
		List<Member> list = dao.memberList();
		int count = list.size();
		for (Member m : list) {
			sb.append("<tr>");
			sb.append(String.format("<td>%s</td>", m.getMid_()));
			sb.append(String.format("<td>%s</td>", m.getName_()));
			sb.append(String.format("<td>%s</td>", m.getPhone()));
			sb.append("</tr>");
		}
		
		PrintWriter out = resp.getWriter();
		

	      out.write("<!DOCTYPE html>\r\n");
	      out.write("<html>\r\n");
	      out.write("<head>\r\n");
	      out.write("<title>쌍용교육센터</title>\r\n");
	      out.write("<meta charset=\"UTF-8\">\r\n");
	      out.write("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\r\n");
	      out.write("\r\n");
	      out.write("<!-- Latest compiled and minified CSS -->\r\n");
	      out.write("<link rel=\"stylesheet\"\r\n");
	      out.write("\thref=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css\">\r\n");
	      out.write("\r\n");
	      out.write("<!-- jQuery library -->\r\n");
	      out.write("<script\r\n");
	      out.write("\tsrc=\"https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js\"></script>\r\n");
	      out.write("\r\n");
	      out.write("<!-- Latest compiled JavaScript -->\r\n");
	      out.write("<script\r\n");
	      out.write("\tsrc=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js\"></script>\r\n");
	      out.write("\r\n");
	      out.write("<script>\r\n");
	      out.write("\t$(document).ready(function() {\r\n");
	      out.write("\r\n");
	      out.write("\t});\r\n");
	      out.write("</script>\r\n");
	      out.write("\r\n");
	      out.write("</head>\r\n");
	      out.write("<body>\r\n");
	      out.write("\r\n");
	      out.write("\t<div class=\"container\">\r\n");
	      out.write("\r\n");
	      out.write("\t\t<div class=\"panel page-header\" style=\"text-align: center;\">\r\n");
	      out.write("\t\t\t<h1 style=\"font-size: xx-large;\">\r\n");
	      out.write("\t\t\t\t<a href=\"sample006\"> <img src=\"resources/img/sist_logo.png\"\r\n");
	      out.write("\t\t\t\t\talt=\"sist_logo.png\"></a> 회원관리 <small>v5.0 BootStrap+MySQL</small>\r\n");
	      out.write("\t\t\t</h1>\r\n");
	      out.write("\t\t</div>\r\n");
	      out.write("\r\n");
	      out.write("\r\n");
	      out.write("\t\t<div class=\"panel panel-default\" id=\"input\">\r\n");
	      out.write("\t\t\t<div class=\"panel-heading\">회원 추가</div>\r\n");
	      out.write("\t\t\t<div class=\"panel-body\">\r\n");
	      out.write("\r\n");
	      out.write("\t\t\t\t<form action=\"sample006_insert\" method=\"post\">\r\n");
	      out.write("\t\t\t\t\t<div class=\"form-group\">\r\n");
	      out.write("\t\t\t\t\t\t<input type=\"text\" class=\"form-control\" id=\"name_\" name=\"name_\"\r\n");
	      out.write("\t\t\t\t\t\t\tplaceholder=\"Name(max 20)\" required>\r\n");
	      out.write("\t\t\t\t\t</div>\r\n");
	      out.write("\t\t\t\t\t<div class=\"form-group\">\r\n");
	      out.write("\t\t\t\t\t\t<input type=\"text\" class=\"form-control\" id=\"phone\" name=\"phone\"\r\n");
	      out.write("\t\t\t\t\t\t\tplaceholder=\"Phone(max 20)\" required>\r\n");
	      out.write("\t\t\t\t\t</div>\r\n");
	      out.write("\r\n");
	      out.write("\t\t\t\t\t<button type=\"submit\" class=\"btn btn-default\" id=\"btnAdd\">회원정보\r\n");
	      out.write("\t\t\t\t\t\t추가</button>\r\n");
	      out.write("\r\n");
	      out.write("\t\t\t\t</form>\r\n");
	      out.write("\r\n");
	      out.write("\t\t\t</div>\r\n");
	      out.write("\t\t</div>\r\n");
	      out.write("\r\n");
	      out.write("\t\t<div class=\"panel panel-default\" id=\"output\">\r\n");
	      out.write("\t\t\t<div class=\"panel-heading\">회원 목록</div>\r\n");
	      out.write("\t\t\t<div class=\"panel-body\">\r\n");
	      out.write("\r\n");
	      out.write("\t\t\t\t<table id=\"members\" class=\"table\">\r\n");
	      out.write("\t\t\t\t\t<tr>\r\n");
	      out.write("\t\t\t\t\t\t<th>Mid</th>\r\n");
	      out.write("\t\t\t\t\t\t<th>Name</th>\r\n");
	      out.write("\t\t\t\t\t\t<th>Phone</th>\r\n");
	      out.write("\t\t\t\t\t</tr>\r\n");
	      out.write("\t\t\t\t\t<!-- \r\n");
	      out.write("\t\t\t\t\t<tr>\r\n");
	      out.write("\t\t\t\t\t\t<td>M01</td>\r\n");
	      out.write("\t\t\t\t\t\t<td>Peter</td>\r\n");
	      out.write("\t\t\t\t\t\t<td>010-1234-1234</td>\r\n");
	      out.write("\t\t\t\t\t</tr>\r\n");
	      out.write("\t\t\t\t\t<tr>\r\n");
	      out.write("\t\t\t\t\t\t<td>M02</td>\r\n");
	      out.write("\t\t\t\t\t\t<td>Lois</td>\r\n");
	      out.write("\t\t\t\t\t\t<td>010-4321-4321</td>\r\n");
	      out.write("\t\t\t\t\t</tr>\r\n");
	      out.write("\t\t\t\t\t<tr>\r\n");
	      out.write("\t\t\t\t\t\t<td>M03</td>\r\n");
	      out.write("\t\t\t\t\t\t<td>Joe</td>\r\n");
	      out.write("\t\t\t\t\t\t<td>010-6543-6543</td>\r\n");
	      out.write("\t\t\t\t\t</tr>\r\n");
	      out.write("\t\t\t\t\t<tr>\r\n");
	      out.write("\t\t\t\t\t\t<td>M04</td>\r\n");
	      out.write("\t\t\t\t\t\t<td>Cleveland</td>\r\n");
	      out.write("\t\t\t\t\t\t<td>010-0987-0987</td>\r\n");
	      out.write("\t\t\t\t\t</tr>\r\n");
	      out.write("\t\t\t\t\t -->\r\n");
	      out.write("\t\t\t\t\t");
	      out.print(sb.toString());
	      out.write("</table>\r\n");
	      out.write("\r\n");
	      out.write("\t\t\t\t<button class=\"btn btn-default\">\r\n");
	      out.write("\t\t\t\t\tTotalCount <span class=\"badge\" id=\"totalcount\">");
	      out.print(totalcount);
	      out.write("</span>\r\n");
	      out.write("\t\t\t\t</button>\r\n");
	      out.write("\r\n");
	      out.write("\t\t\t</div>\r\n");
	      out.write("\t\t</div>\r\n");
	      out.write("\r\n");
	      out.write("\t</div>\r\n");
	      out.write("\r\n");
	      out.write("</body>\r\n");
	      out.write("</html>");
		
		
	}

	
	
}
