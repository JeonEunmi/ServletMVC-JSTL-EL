package com.test;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Sample004_receive extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 응답 콘텐츠타입 설정
		response.setContentType("text/html; charset=UTF-8");

		// 액션 코드 작성
		request.setCharacterEncoding("UTF-8");
		String name_ = request.getParameter("name_");
		String phone = request.getParameter("phone");

		// 출력 결과 작성
		PrintWriter out = response.getWriter();
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
		out.write("\r\n");
		out.write("\t$(document).ready(function() {\r\n");
		out.write("\t\t\r\n");
		out.write("\t});\r\n");
		out.write("\r\n");
		out.write("</script>\r\n");
		out.write("\r\n");
		out.write("</head>\r\n");
		out.write("<body>\r\n");
		out.write("\r\n");
		out.write("\t<div class=\"container\">\r\n");
		out.write("\t\r\n");

		// 출력 결과 메시지 작성
		out.write("\t\t<h1>폼 전송 테스트</h1>\r\n");
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
		out.write("\t\t\t\t\t<tr>\r\n");
		out.write("\t\t\t\t\t\t<td>M01</td>\r\n");
		out.write("\t\t\t\t\t\t<td>");
		out.print(name_);
		out.write("</td>\r\n");
		out.write("\t\t\t\t\t\t<td>");
		out.print(phone);
		out.write("</td>\r\n");
		out.write("\t\t\t\t\t</tr>\r\n");
		out.write("\t\t\t\t</table>\r\n");
		out.write("\r\n");
		out.write("\t\t\t\t<form class=\"form-inline\" method=\"post\">\r\n");
		out.write("\t\t\t\t\t<div class=\"form-group\">\r\n");
		out.write("\t\t\t\t\t\t<!-- 전체 자료 갯수 -->\r\n");
		out.write("\t\t\t\t\t\t<button type=\"button\" class=\"btn btn-default\">\r\n");
		out.write("\t\t\t\t\t\t\tTotalCount <span class=\"badge\" id=\"totalcount\">4</span>\r\n");
		out.write("\t\t\t\t\t\t</button>\r\n");
		out.write("\t\t\t\t\t\t<!-- 검색 결과 자료 갯수 -->\r\n");
		out.write("\t\t\t\t\t\t<button type=\"button\" class=\"btn btn-default\">\r\n");
		out.write("\t\t\t\t\t\t\tCount <span class=\"badge\" id=\"count\">0</span>\r\n");
		out.write("\t\t\t\t\t\t</button>\r\n");
		out.write("\t\t\t\t\t\t<!-- 검색 기준 선택 항목 -->\r\n");
		out.write("\t\t\t\t\t\t<select class=\"form-control\" id=\"key\" name=\"key\">\r\n");
		out.write("\t\t\t\t\t\t\t<option value=\"mid\">Mid</option>\r\n");
		out.write("\t\t\t\t\t\t\t<option value=\"name\">Name</option>\r\n");
		out.write("\t\t\t\t\t\t\t<option value=\"phone\">Phone</option>\r\n");
		out.write("\t\t\t\t\t\t\t<option value=\"regDate\">RegDate</option>\r\n");
		out.write("\t\t\t\t\t\t\t<option value=\"dept\">Dept</option>\r\n");
		out.write("\t\t\t\t\t\t</select>\r\n");
		out.write("\t\t\t\t\t</div>\r\n");
		out.write("\t\t\t\t\t<div class=\"input-group\">\r\n");
		out.write("\t\t\t\t\t\t<!-- 검색 단어 입력 폼 -->\r\n");
		out.write("\t\t\t\t\t\t<input type=\"text\" class=\"form-control\" id=\"value\" name=\"value\"\r\n");
		out.write("\t\t\t\t\t\t\tplaceholder=\"Search\">\r\n");
		out.write("\t\t\t\t\t\t<div class=\"input-group-btn\">\r\n");
		out.write("\t\t\t\t\t\t\t<!-- 검색 진행 버튼 -->\r\n");
		out.write("\t\t\t\t\t\t\t<button type=\"button\" class=\"btn btn-default\" id=\"btnSearch\">\r\n");
		out.write("\t\t\t\t\t\t\t\t<i class=\"glyphicon glyphicon-search\"></i>\r\n");
		out.write("\t\t\t\t\t\t\t</button>\r\n");
		out.write("\t\t\t\t\t\t</div>\r\n");
		out.write("\t\t\t\t\t</div>\r\n");
		out.write("\r\n");
		out.write("\t\t\t\t</form>\r\n");
		out.write("\r\n");
		out.write("\t\t\t</div>\r\n");
		out.write("\t\t</div>\r\n");
		out.write("\r\n");
		out.write("\r\n");
		out.write("\r\n");
		out.write("\r\n");
		out.write("\t</div>\r\n");
		out.write("\r\n");
		out.write("</body>\r\n");
		out.write("</html>");
	}

}