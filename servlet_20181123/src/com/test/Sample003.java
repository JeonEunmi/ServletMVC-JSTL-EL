package com.test;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Sample003 extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// 출력 결과에 한글 포함된 경우 인코딩 지정
		resp.setContentType("text/html; charset=UTF-8");

		/*
		 * 구구단 출력 임의의 수(1 ~ 9)를 가지고 구구단 연산 결과 출력. 난수 발생 필요.
		 * 
		 * 출력예) 5 * 1 = 5 ...
		 * 
		 * 5 * 9 = 45
		 */

		int num = (int) (Math.random() * 9 + 1);

		StringBuilder sb = new StringBuilder();
		sb.append(String.format("<h3>%d단</h3>", num));
		sb.append("<div>");
		for (int a = 1; a <= 9; ++a) {
			sb.append(String.format("%d * %d = %d <br>", num, a, (num * a)));
		}
		sb.append("</div>");

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
		out.write(String.format("\t %s \r\n", sb.toString()));
		out.write("\t\r\n");
		out.write("\t</div>\r\n");
		out.write("\r\n");
		out.write("</body>\r\n");
		out.write("</html>");
	}

}
