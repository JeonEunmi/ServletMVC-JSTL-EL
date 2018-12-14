package com.test;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Sample004_send extends HttpServlet {

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
		out.write("\t\t\t\t<form action=\"sample004_receive\" method=\"post\">\r\n");
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

}