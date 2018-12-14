package com.test;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Sample005 extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// ���� ������Ÿ�� ����
		response.setContentType("text/html; charset=UTF-8");

		// �׼� �ڵ� �ۼ�


		// ��� ��� �ۼ�
		PrintWriter out = response.getWriter();
		out.write("<!DOCTYPE html>\r\n");
		out.write("<html>\r\n");
		out.write("<head>\r\n");
		out.write("<title>�ֿ뱳������</title>\r\n");
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

		// ��� ��� �޽��� �ۼ�
		out.write("\t\t<h1>�� ���� �׽�Ʈ</h1>\r\n");
		out.write("\r\n");
		out.write("\t\t<div class=\"panel panel-default\" id=\"input\">\r\n");
		out.write("\t\t\t<div class=\"panel-heading\">ȸ�� �߰�</div>\r\n");
		out.write("\t\t\t<div class=\"panel-body\">\r\n");
		out.write("\r\n");
		out.write("\t\t\t\t<!-- �� ���ۿ� �ʿ��� �� ������Ʈ ���� -->\r\n");
		out.write("\t\t\t\t<!-- ���� ���, ����ڰ� ���������� �Է��� �̸�, ��ȭ��ȣ�� ������ ���� -->\r\n");
		out.write("\t\t\t\t<!-- \r\n");
		out.write("\t\t\t\tform ������Ʈ���� \r\n");
		out.write("\t\t\t\t\r\n");
		out.write("\t\t\t\taction=\"\" �Ӽ��� \r\n");
		out.write("\t\t\t\t����� �׼� ����� ����� ���� ���α׷� �ּ� ����\r\n");
		out.write("\t\t\t\taction=\"\" �Ӽ� ������ �ڱ� �ڽſ��� ���۵ȴ�\r\n");
		out.write("\t\t\t\t\r\n");
		out.write("\t\t\t\tmethod=\"POST\" �Ӽ���\r\n");
		out.write("\t\t\t\t���� ����� �����Ѵ�. \r\n");
		out.write("\t\t\t\tGET �Ǵ� POST �߿� �ϳ��� �����Ѵ�.\r\n");
		out.write("\t\t\t\t�Ϲ������� �� ���ۿ��� POST ���.\r\n");
		out.write("\t\t\t\tmethod=\"\" �Ӽ� ������ GET ��� �����ȴ�.\r\n");
		out.write("\t\t\t\t--> \r\n");
		out.write("\t\t\t\t<form method=\"post\">\r\n");
		out.write("\t\t\t\t\t<div class=\"form-group\">\r\n");
		out.write("\t\t\t\t\t\t<label for=\"name_\">Name:</label> \r\n");
		out.write("\t\t\t\t\t\t<!-- \r\n");
		out.write("\t\t\t\t\t\tinput ������Ʈ���� JSP �������� �����\r\n");
		out.write("\t\t\t\t\t\tname=\"�ĺ���\" �Ӽ� ���� �ʼ�\r\n");
		out.write("\t\t\t\t\t\t���� �ڷ�, ���� �ĺ���\r\n");
		out.write("\t\t\t\t\t\t\r\n");
		out.write("\t\t\t\t\t\trequired �Ӽ��� �ʼ� �Է� �׸� ������ ���\r\n");
		out.write("\t\t\t\t\t\t����� �׼� ����� �ԷµǾ����� Ȯ���Ѵ�.\r\n");
		out.write("\t\t\t\t\t\t\r\n");
		out.write("\t\t\t\t\t\t����) name_ �ĺ��� ���� \"name\"�� �ƴ϶� \"name_\"�� ����� ��\r\n");
		out.write("\t\t\t\t\t\t-->\r\n");
		out.write("\t\t\t\t\t\t<input type=\"text\"\r\n");
		out.write("\t\t\t\t\t\t\tclass=\"form-control\" \r\n");
		out.write("\t\t\t\t\t\t\tid=\"name_\" name=\"name_\" placeholder=\"max 20\"\r\n");
		out.write("\t\t\t\t\t\t\trequired>\r\n");
		out.write("\t\t\t\t\t</div>\r\n");
		out.write("\t\t\t\t\t<div class=\"form-group\">\r\n");
		out.write("\t\t\t\t\t\t<label for=\"phone\">Phone:</label> <input type=\"text\"\r\n");
		out.write("\t\t\t\t\t\t\tclass=\"form-control\" \r\n");
		out.write("\t\t\t\t\t\t\tid=\"phone\" name=\"phone\" placeholder=\"max 20\"\r\n");
		out.write("\t\t\t\t\t\t\trequired>\r\n");
		out.write("\t\t\t\t\t</div>\r\n");
		out.write("\r\n");
		out.write("\t\t\t\t\t<!-- \r\n");
		out.write("\t\t\t\t\tJSP �������� ������ ���ؼ�\r\n");
		out.write("\t\t\t\t\tinput, button ������Ʈ����\r\n");
		out.write("\t\t\t\t\ttype=\"submit\" �Ӽ� ���� �ʼ�\r\n");
		out.write("\t\t\t\t\t��ư Ŭ���� ����� �׼��� ����ȴ�.\r\n");
		out.write("\t\t\t\t\t-->\r\n");
		out.write("\t\t\t\t\t<button type=\"submit\" class=\"btn btn-default\" id=\"btnAdd\">ȸ������\r\n");
		out.write("\t\t\t\t\t\t�߰�</button>\r\n");
		out.write("\r\n");
		out.write("\t\t\t\t</form>\r\n");
		out.write("\r\n");
		out.write("\t\t\t</div>\r\n");
		out.write("\t\t</div>\r\n");
		out.write("\r\n");
		out.write("\r\n");
		out.write("\r\n");
		out.write("\t</div>\r\n");
		out.write("\r\n");
		out.write("</body>\r\n");
		out.write("</html>");
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// ���� ������Ÿ�� ����
		response.setContentType("text/html; charset=UTF-8");

		// �׼� �ڵ� �ۼ�
		request.setCharacterEncoding("UTF-8");
		String name_ = request.getParameter("name_");
		String phone = request.getParameter("phone");

		// ��� ��� �ۼ�
		PrintWriter out = response.getWriter();
		out.write("<!DOCTYPE html>\r\n");
		out.write("<html>\r\n");
		out.write("<head>\r\n");
		out.write("<title>�ֿ뱳������</title>\r\n");
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

		// ��� ��� �޽��� �ۼ�
		out.write("\t\t<h1>�� ���� �׽�Ʈ</h1>\r\n");
		out.write("\t\t<div class=\"panel panel-default\" id=\"output\">\r\n");
		out.write("\t\t\t<div class=\"panel-heading\">ȸ�� ���</div>\r\n");
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
		out.write("\t\t\t\t\t\t<!-- ��ü �ڷ� ���� -->\r\n");
		out.write("\t\t\t\t\t\t<button type=\"button\" class=\"btn btn-default\">\r\n");
		out.write("\t\t\t\t\t\t\tTotalCount <span class=\"badge\" id=\"totalcount\">4</span>\r\n");
		out.write("\t\t\t\t\t\t</button>\r\n");
		out.write("\t\t\t\t\t\t<!-- �˻� ��� �ڷ� ���� -->\r\n");
		out.write("\t\t\t\t\t\t<button type=\"button\" class=\"btn btn-default\">\r\n");
		out.write("\t\t\t\t\t\t\tCount <span class=\"badge\" id=\"count\">0</span>\r\n");
		out.write("\t\t\t\t\t\t</button>\r\n");
		out.write("\t\t\t\t\t\t<!-- �˻� ���� ���� �׸� -->\r\n");
		out.write("\t\t\t\t\t\t<select class=\"form-control\" id=\"key\" name=\"key\">\r\n");
		out.write("\t\t\t\t\t\t\t<option value=\"mid\">Mid</option>\r\n");
		out.write("\t\t\t\t\t\t\t<option value=\"name\">Name</option>\r\n");
		out.write("\t\t\t\t\t\t\t<option value=\"phone\">Phone</option>\r\n");
		out.write("\t\t\t\t\t\t\t<option value=\"regDate\">RegDate</option>\r\n");
		out.write("\t\t\t\t\t\t\t<option value=\"dept\">Dept</option>\r\n");
		out.write("\t\t\t\t\t\t</select>\r\n");
		out.write("\t\t\t\t\t</div>\r\n");
		out.write("\t\t\t\t\t<div class=\"input-group\">\r\n");
		out.write("\t\t\t\t\t\t<!-- �˻� �ܾ� �Է� �� -->\r\n");
		out.write("\t\t\t\t\t\t<input type=\"text\" class=\"form-control\" id=\"value\" name=\"value\"\r\n");
		out.write("\t\t\t\t\t\t\tplaceholder=\"Search\">\r\n");
		out.write("\t\t\t\t\t\t<div class=\"input-group-btn\">\r\n");
		out.write("\t\t\t\t\t\t\t<!-- �˻� ���� ��ư -->\r\n");
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