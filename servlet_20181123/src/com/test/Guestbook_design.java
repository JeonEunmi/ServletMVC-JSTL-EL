package com.test;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.guestbook.Guestbook;
import com.guestbook.GuestbookDAO;

public class Guestbook_design extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// ���� ������Ÿ�� ����
		resp.setContentType("text/html; charset=UTF-8");

		// �׼� �ڵ� �ۼ�
		// ���� ��� ����� ���� ���ڿ� �غ�
		StringBuilder sb = new StringBuilder();

		// DAO Ŭ������ ���� ��ü ����
		GuestbookDAO dao = new GuestbookDAO();

		String path = req.getContextPath();
		
		// ��� �� �˻� �޼ҵ� ȣ�� -> �÷��� ��ȯ
		String pageCount = req.getParameter("pageCount");
		String pageNum = req.getParameter("pageNum");
		

		

		if (pageNum == null) {
			pageNum = "1"; // ������ ��ȣ �ʱⰪ
			pageCount = "10";
		}

		int pageCountTemp = Integer.parseInt(pageCount);
		int pageNumTemp = Integer.parseInt(pageNum);
		int pageStart = pageCountTemp * (pageNumTemp - 1);

		List<Guestbook> list = dao.pageList(pageStart, Integer.parseInt(pageCount));

		int guestbookCount = list.size();
		int totalcount = dao.notBlindCount();

		int startPage = 1;
		int endPage = totalcount / pageCountTemp;

		if (totalcount % pageCountTemp > 0) {
			endPage++;
		}

		// �÷��� �м�
		int count = list.size();
		for (Guestbook g : list) {
			String temp = "";
			String randomNum = String.format("%02d",((int)(Math.random()*10)+1));
			if (g.getBlind() == 0) {
				sb.append("<tr>");
				// ��� ����� ���� ���� ���̺� �±� ����
				sb.append(String.format("<td>%s</td>", g.getGid()));
				 sb.append(String.format("<td><img src=\"%s/resources/img/Fotolia%s.png\" title=\"%s\"></td>", path, randomNum, g.getName_()));
				sb.append(String.format("<td>%s</td>", g.getContent()));
				sb.append(String.format("<td>%s</td>", g.getRegDate()));
		        sb.append(String.format("<td><button type=\"button\" class=\"btn btn-default btn-xs btnDel\" value=\"%s\">Del</button></td>", g.getGid()));
				sb.append("</tr>");
			}
		}

		PrintWriter out = resp.getWriter();

		out.write("\r\n");
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
		out.write("<style>\r\n");
		out.write("div#carousel:hover, div#input:hover, div#output:hover {\r\n");
		out.write("\tbox-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0\r\n");
		out.write("\t\trgba(0, 0, 0, 0.19);\r\n");
		out.write("}\r\n");
		out.write("</style>\r\n");
		out.write("\r\n");
		out.write("<!-- Google Map API -->\r\n");
		out.write("<script src=\"https://maps.googleapis.com/maps/api/js\"></script>\r\n");
		out.write("\r\n");
		out.write("<!-- jQuery library -->\r\n");
		out.write("<script\r\n");
		out.write("\tsrc=\"https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js\"></script>\r\n");
		out.write("\r\n");
		out.write("<!-- Latest compiled JavaScript -->\r\n");
		out.write("<script\r\n");
		out.write("\tsrc=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js\"></script>\r\n");
		out.write("\r\n");
		out.write("\r\n");
		out.write("<script>\r\n");
		out.write("\t$(document).ready(function() {\r\n");
	       out.write("\t\t$(\".btnDel\").on(\"click\", function() {\r\n");
	       out.write("\t\t\t//������ ���� ��ȣ�� �����ϴ� �׼� �߰�\r\n");
	       out.write("\t\t\t//-><input type=\"hidden\"> �±��� value=\"\" �Ӽ�\r\n");
	       out.write("\t\t\t$(\"div#deleteFormModal input#gid\").val($(this).val());\r\n");
	       out.write("\t\t\t//���â ����\r\n");
	       out.write("\t\t\t$(\"div#deleteFormModal\").modal();\r\n");
	       out.write("\t\t});\r\n");
		/*out.write("\t\t\t\t\t\t//���� ������ ���� ��ư click  �̺�Ʈ �߰�\r\n");
		out.write("\t\t\t\t\t\t//->on() �޼ҵ�\r\n");
		out.write("\t\t\t\t\t\t$(\".btnDel\")\r\n");
		out.write("\t\t\t\t\t\t\t\t.on(\r\n");
		out.write("\t\t\t\t\t\t\t\t\t\t\"click\",\r\n");
		out.write("\t\t\t\t\t\t\t\t\t\tfunction() {\r\n");
		out.write("\t\t\t\t\t\t\t\t\t\t\tvar m = $(this).parents(\"td\").siblings()\r\n");
		out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t.eq(0).text();\r\n");
		out.write("\t\t\t\t\t\t\t\t\t\t\tvar n = $(this).parents(\"td\").siblings()\r\n");
		out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t.eq(1).text();\r\n");
		out.write("\t\t\t\t\t\t\t\t\t\t\tvar p = $(this).parents(\"td\").siblings()\r\n");
		out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t.eq(2).text();\r\n");
		out.write("\t\t\t\t\t\t\t\t\t\t\t// ��ȣ, �̸�, ��ȭ��ȣ ���� �޽��� ���\r\n");
		out.write("\t\t\t\t\t\t\t\t\t\t\tvar temp = m + \"/\" + n + \"/\" + p\r\n");
		out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t+ \"\\n\\n���� �ڷḦ �����Ͻðڽ��ϱ�?\";\r\n");
		out.write("\t\t\t\t\t\t\t\t\t\t\t//->��ư Ŭ���� confirm() ȣ��\r\n");
		out.write("\t\t\t\t\t\t\t\t\t\t\tif (confirm(temp)) {\r\n");
		out.write("\t\t\t\t\t\t\t\t\t\t\t\t// ���� �׼� ������ ��û �� ��ȣ ����\r\n");
		out.write("\t\t\t\t\t\t\t\t\t\t\t\twindow.location\r\n");
		out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\t.assign(\"guestbook_delete?gid=\"\r\n");
		out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t+ $(this).val());\r\n");
		out.write("\t\t\t\t\t\t\t\t\t\t\t}\r\n");
		out.write("\r\n");
		out.write("\t\t\t\t\t\t\t\t\t\t});\r\n");
		out.write("\r\n");
		out.write("\t\r\n");
		out.write("\t\t\r\n");*/
		out.write("\t});\r\n");
		out.write("</script>\r\n");
		out.write("\r\n");
		out.write("\r\n");
		out.write("<script>\r\n");
		out.write("\tfunction myMap() {\r\n");
		out.write("\r\n");
		out.write("\t\t//����, �浵 \r\n");
		out.write("\t\tvar handok = new google.maps.LatLng(37.499285, 127.033271);\r\n");
		out.write("\t\tvar center = new google.maps.LatLng(37.500544, 127.033215);\r\n");
		out.write("\r\n");
		out.write("\t\t//�� ����\r\n");
		out.write("\t\tvar mapProp = {\r\n");
		out.write("\t\t\tcenter : center,\r\n");
		out.write("\t\t\tzoom : 17,\r\n");
		out.write("\t\t};\r\n");
		out.write("\t\t//�� ��û\r\n");
		out.write("\t\tvar map = new google.maps.Map(document.getElementById(\"googleMap\"),\r\n");
		out.write("\t\t\t\tmapProp);\r\n");
		out.write("\r\n");
		out.write("\t\t//��Ŀ ǥ��\r\n");
		out.write("\t\tvar marker = new google.maps.Marker({\r\n");
		out.write("\t\t\tposition : handok\r\n");
		out.write("\t\t});\r\n");
		out.write("\t\tmarker.setMap(map);\r\n");
		out.write("\r\n");
		out.write("\t\t//InfoWindow\r\n");
		out.write("\t\tvar infowindow = new google.maps.InfoWindow(\r\n");
		out.write("\t\t\t\t{\r\n");
		out.write(
				"\t\t\t\t\tcontent : \"<div style=\\\"text-align:center;\\\"><strong>�ѵ���ǰ����</strong><br>����Ư���� ������ ����1�� 735<br><img src=\\\"resources/img/handok_small.png\\\"></div>\"\r\n");
		out.write("\t\t\t\t});\r\n");
		out.write("\t\tinfowindow.open(map, marker);\r\n");
		out.write("\r\n");
		out.write("\t}\r\n");
		out.write("</script>\r\n");
		out.write("\r\n");
		out.write("</head>\r\n");
		out.write("<body>\r\n");
		out.write("\r\n");
		out.write("\t<div class=\"container\" id=\"top\">\r\n");
		out.write("\r\n");
		out.write("\t\t<div class=\"panel page-header\" style=\"text-align: center;\">\r\n");
		out.write("\t\t\t<h1 style=\"font-size: xx-large;\">\r\n");
		out.write("\t\t\t\t<a href=\"\"><img src=\"resources/img/sist_logo.png\"\r\n");
		out.write("\t\t\t\t\talt=\"sist_logo.png\"></a> ���� <small>v1.0</small> <span\r\n");
		out.write("\t\t\t\t\tstyle=\"font-size: small; color: #777777;\"></span>\r\n");
		out.write("\t\t\t</h1>\r\n");
		out.write("\t\t</div>\r\n");
		out.write("\r\n");
		out.write("\t\t<div class=\"nanel panel-default\" id=\"title\" style=\"padding-bottom: 10px;\">\r\n");
		out.write("\t\t\t<div class=\"panel-heading\">\r\n");
		out.write("\t\t\t\t����Ư���� ������ ���� 1�� 735 �ѵ���ǰ���� 8�� �ֿ뱳������ / ����ö 2ȣ�� ���￪ 3���ⱸ<br>\r\n");
		out.write("\t\t\t\tJava&amp;Python ��� ����SW ������ �缺���� 2018.06.25 ~ 2019.01.17 <span\r\n");
		out.write("\t\t\t\t\tstyle=\"color: red;\" id=\"date\">(D-50)</span>\r\n");
		out.write("\t\t\t\t<button class=\"btn btn-default btn-xs map\">Map</button>\r\n");
		out.write(
				"\t\t\t\t<button class=\"btn btn-default btn-xs admin\" data-toggle=\"modal\" data-target=\"#adminFormModal\">Admin</button>\r\n");
		out.write("\t\t\t</div>\r\n");
		out.write("\t\t</div>\r\n");
		out.write("\r\n");
		out.write("\t\t<div class=\"panel panel-default\" id=\"carousel\">\r\n");
		out.write("\t\t\t<div class=\"panel-heading\">Picture List</div>\r\n");
		out.write("\t\t\t<div class=\"panel-body\">\r\n");
		out.write("\t\t\t\t<div id=\"myCarousel\" class=\"carousel slide\" data-ride=\"carousel\">\r\n");
		out.write("\t\t\t\t\t<!-- Indicators -->\r\n");
		out.write("\t\t\t\t\t<ol class=\"carousel-indicators\">\r\n");
		out.write("\t\t\t\t\t\t<li data-target=\"#myCarousel\" data-slide-to=\"0\" class=\"active\"></li>\r\n");
		out.write("\t\t\t\t\t</ol>\r\n");
		out.write("\r\n");
		out.write("\t\t\t\t\t<!-- Wrapper for slides -->\r\n");
		out.write("\t\t\t\t\t<div class=\"carousel-inner\">\r\n");
		out.write("\r\n");
		out.write("\t\t\t\t\t\t<div class=\"item active\">\r\n");
		out.write("\t\t\t\t\t\t\t<img src=\"resources/picture/chicago.jpg\" alt=\"Chicago\"\r\n");
		out.write("\t\t\t\t\t\t\t\tstyle=\"width: 100%;\">\r\n");
		out.write("\t\t\t\t\t\t\t<div class=\"carousel-caption\">\r\n");
		out.write("\t\t\t\t\t\t\t\t<h3>Chicago</h3>\r\n");
		out.write("\t\t\t\t\t\t\t</div>\r\n");
		out.write("\t\t\t\t\t\t</div>\r\n");
		out.write("\t\t\t\t\t</div>\r\n");
		out.write("\r\n");
		out.write("\t\t\t\t\t<!-- Left and right controls -->\r\n");
		out.write("\t\t\t\t\t<a class=\"left carousel-control\" href=\"#myCarousel\"\r\n");
		out.write("\t\t\t\t\t\tdata-slide=\"prev\"> <span\r\n");
		out.write("\t\t\t\t\t\tclass=\"glyphicon glyphicon-chevron-left\"></span> <span\r\n");
		out.write("\t\t\t\t\t\tclass=\"sr-only\">Previous</span>\r\n");
		out.write("\t\t\t\t\t</a> <a class=\"right carousel-control\" href=\"#myCarousel\"\r\n");
		out.write("\t\t\t\t\t\tdata-slide=\"next\"> <span\r\n");
		out.write("\t\t\t\t\t\tclass=\"glyphicon glyphicon-chevron-right\"></span> <span\r\n");
		out.write("\t\t\t\t\t\tclass=\"sr-only\">Next</span>\r\n");
		out.write("\t\t\t\t\t</a>\r\n");
		out.write("\t\t\t\t</div>\r\n");
		out.write("\t\t\t</div>\r\n");
		out.write("\t\t</div>\r\n");
		out.write("\r\n");
		out.write("\t\t<div class=\"panel panel-default\" id=\"input\">\r\n");
		out.write("\t\t\t<div class=\"panel-heading\">���� �۾���</div>\r\n");
		out.write("\t\t\t<div class=\"panel-body\">\r\n");
		out.write("\r\n");
		out.write("\t\t\t\t<form action=\"guestbook_insert\" method=\"post\">\r\n");
		out.write("\t\t\t\t\t<div class=\"form-group\">\r\n");
		out.write("\t\t\t\t\t\t<input type=\"text\" class=\"form-control\" id=\"name_\" name=\"name_\"\r\n");
		out.write("\t\t\t\t\t\t\tplaceholder=\"Name(max:50)\" required>\r\n");
		out.write("\t\t\t\t\t</div>\r\n");
		out.write("\t\t\t\t\t<div class=\"form-group\">\r\n");
		out.write("\t\t\t\t\t\t<input type=\"password\" class=\"form-control\" id=\"pw\" name=\"pw\"\r\n");
		out.write("\t\t\t\t\t\t\tplaceholder=\"PASSWORD(max:50)\" required>\r\n");
		out.write("\t\t\t\t\t</div>\r\n");
		out.write("\t\t\t\t\t<div class=\"form-group\">\r\n");
		out.write("\t\t\t\t\t\t<input type=\"text\" class=\"form-control\" id=\"content\"\r\n");
		out.write("\t\t\t\t\t\t\tname=\"content\" placeholder=\"CONTENT(max:500)\">\r\n");
		out.write("\t\t\t\t\t</div>\r\n");
		out.write("\r\n");
		out.write("\r\n");
		out.write("\t\t\t\t\t<button type=\"submit\" class=\"btn btn-default\">Submit</button>\r\n");
		out.write("\r\n");
		out.write("\t\t\t\t</form>\r\n");
		out.write("\r\n");
		out.write("\r\n");
		out.write("\t\t\t</div>\r\n");
		out.write("\t\t</div>\r\n");
		out.write("\r\n");
		out.write("\r\n");
		out.write("\t\t<div class=\"panel panel-default\" id=\"output\">\r\n");
		out.write("\t\t\t<div class=\"panel-heading\">���� �۸��</div>\r\n");
		out.write("\t\t\t<div class=\"panel-body\">\r\n");
		out.write("\r\n");
		out.write("\t\t\t\t<table class=\"table table-striped\">\r\n");
		out.write("\t\t\t\t\t<thead>\r\n");
		out.write("\t\t\t\t\t\t<tr>\r\n");
		out.write("\t\t\t\t\t\t\t<th>��ȣ</th>\r\n");
		out.write("\t\t\t\t\t\t\t<th>�۾���</th>\r\n");
		out.write("\t\t\t\t\t\t\t<th>�۳���</th>\r\n");
		out.write("\t\t\t\t\t\t\t<th>�ۼ���</th>\r\n");
		out.write("\t\t\t\t\t\t\t<th>����</th>\r\n");
		out.write("\t\t\t\t\t\t</tr>\r\n");
		out.write("\t\t\t\t\t</thead>\r\n");
		out.write("\t\t\t\t\t<tbody>\r\n");
		out.write(sb.toString());
		out.write("\t\t\t\t\t</tbody>\r\n");
		out.write("\t\t\t\t</table>\r\n");
		out.write("\r\n");
		out.write("\t\t\t\t<form class=\"form-inline\" method=\"post\">\r\n");
		out.write("\t\t\t\t\t<div class=\"form-group\">\r\n");
		out.write("\t\t\t\t\t\t<button type=\"button\" class=\"btn btn-default\">\r\n");
		out.write("\t\t\t\t\t\tTotalCount <span class=\"badge\" id=\"totalcount\">");
		out.print(totalcount);
		out.write("</span>\r\n");
		out.write("\t\t\t\t\t\t</button>\r\n");
		out.write("\t\t\t\t\t\t<button type=\"button\" class=\"btn btn-default\">\r\n");
		out.write("\t\t\t\t\t\t\tCount <span class=\"badge\">1</span>\r\n");
		out.write("\t\t\t\t\t\t</button>\r\n");
		out.write("\t\t\t\t\t\t<button type=\"button\" class=\"btn btn-default btnPrevious\">\r\n");
		out.write("\t\t\t\t\t\t\t<span class=\"glyphicon glyphicon-step-backward\"></span> Previous\r\n");
		out.write("\t\t\t\t\t\t</button>\r\n");
		out.write("\t\t\t\t\t\t<button type=\"button\" class=\"btn btn-default btnNext\">\r\n");
		out.write("\t\t\t\t\t\t\tNext <span class=\"glyphicon glyphicon-step-forward\"></span>\r\n");
		out.write("\t\t\t\t\t\t</button>\r\n");
		out.write("\r\n");
		out.write("\t\t\t\t\t\t<!-- �˻� ���� ���� �׸� �߰� -->\r\n");
		out.write("\t\t\t\t\t\t<select class=\"form-control\" id=\"key\" name=\"key\">\r\n");
		out.write("\t\t\t\t\t\t\t<option value=\"name_\">Name</option>\r\n");
		out.write("\t\t\t\t\t\t\t<option value=\"content\">Content</option>\r\n");
		out.write("\t\t\t\t\t\t\t<option value=\"regDate\">RegDate</option>\r\n");
		out.write("\t\t\t\t\t\t</select>\r\n");
		out.write("\t\t\t\t\t</div>\r\n");
		out.write("\t\t\t\t\t<div class=\"input-group\">\r\n");
		out.write("\t\t\t\t\t\t<input type=\"text\" class=\"form-control\" id=\"value\" name=\"value\"\r\n");
		out.write("\t\t\t\t\t\t\tplaceholder=\"Search\">\r\n");
		out.write("\r\n");
		out.write("\t\t\t\t\t\t<div class=\"input-group-btn\">\r\n");
		out.write("\t\t\t\t\t\t\t<button class=\"btn btn-default\" type=\"submit\">\r\n");
		out.write("\t\t\t\t\t\t\t\t<i class=\"glyphicon glyphicon-search\"></i>\r\n");
		out.write("\t\t\t\t\t\t\t</button>\r\n");
		out.write("\t\t\t\t\t\t</div>\r\n");
		out.write("\t\t\t\t\t</div>\r\n");
		out.write("\r\n");
		out.write("\t\t\t\t</form>\r\n");
		out.write("\r\n");
		out.write("\t\t\t</div>\r\n");
		out.write("\r\n");
		out.write("\r\n");
		out.write("\t\t</div>\r\n");
		out.write("\r\n");
		out.write("\t</div>\r\n");
		out.write("\t\r\n");
		out.write("\r\n");
		out.write("\t<!-- Modal -->\r\n");
		out.write("\t<div id=\"deleteFormModal\" class=\"modal fade\" role=\"dialog\">\r\n");
		out.write("\t\t<div class=\"modal-dialog\">\r\n");
		out.write("\r\n");
		out.write("\t\t\t<!-- Modal content-->\r\n");
		out.write("\t\t\t<div class=\"modal-content\">\r\n");
		out.write("\t\t\t\t<div class=\"modal-header\">\r\n");
		out.write("\t\t\t\t\t<button type=\"button\" class=\"close\" data-dismiss=\"modal\">&times;</button>\r\n");
		out.write("\t\t\t\t\t<h4 class=\"modal-title\">Delete</h4>\r\n");
		out.write("\t\t\t\t</div>\r\n");
		out.write("\t\t\t\t<div class=\"modal-body\">\r\n");
		out.write("\r\n");
		out.write("\t\t\t\t\t<p>������ �ۼ��� ������ Ȯ���ϴ� �����Դϴ�.</p>\r\n");
		out.write("\r\n");
		out.write("\t\t\t\t\t<form action=\"guestbook_delete\" method=\"post\">\r\n");
		out.write("\r\n");
		out.write("\t\t\t\t\t\t<!-- ����) ���� ������ ���ؼ� �۹�ȣ ������ �ʿ��մϴ�. -->\r\n");
		out.write("\t\t\t\t\t\t<input type=\"hidden\" id=\"gid\" name=\"gid\" value=\"G001\">\r\n");
		out.write("\r\n");
		out.write("\t\t\t\t\t\t<div class=\"form-group\">\r\n");
		out.write("\t\t\t\t\t\t\t<input type=\"password\" class=\"form-control\" id=\"pw\" name=\"pw\"\r\n");
		out.write("\t\t\t\t\t\t\t\tplaceholder=\"PASSWORD(max:20)\" required>\r\n");
		out.write("\t\t\t\t\t\t</div>\r\n");
		out.write("\r\n");
		out.write("\t\t\t\t\t\t<button type=\"submit\" class=\"btn btn-default\">Submit</button>\r\n");
		out.write("\r\n");
		out.write("\t\t\t\t\t</form>\r\n");
		out.write("\r\n");
		out.write("\t\t\t\t</div>\r\n");
		out.write("\t\t\t\t<div class=\"modal-footer\">\r\n");
		out.write(
				"\t\t\t\t\t<button type=\"button\" class=\"btn btn-default\" data-dismiss=\"modal\">Close</button>\r\n");
		out.write("\t\t\t\t</div>\r\n");
		out.write("\t\t\t</div>\r\n");
		out.write("\r\n");
		out.write("\t\t</div>\r\n");
		out.write("\t</div>\r\n");
		out.write("\r\n");
		out.write("\r\n");
		out.write("\t<!-- Modal -->\r\n");
		out.write("\t<div id=\"adminFormModal\" class=\"modal fade\" role=\"dialog\">\r\n");
		out.write("\t\t<div class=\"modal-dialog\">\r\n");
		out.write("\r\n");
		out.write("\t\t\t<!-- Modal content-->\r\n");
		out.write("\t\t\t<div class=\"modal-content\">\r\n");
		out.write("\t\t\t\t<div class=\"modal-header\">\r\n");
		out.write("\t\t\t\t\t<button type=\"button\" class=\"close\" data-dismiss=\"modal\">&times;</button>\r\n");
		out.write("\t\t\t\t\t<h4 class=\"modal-title\">Admin Login</h4>\r\n");
		out.write("\t\t\t\t</div>\r\n");
		out.write("\t\t\t\t<div class=\"modal-body\">\r\n");
		out.write("\r\n");
		out.write("\t\t\t\t\t<p>���������� Ȯ���ϴ� �����Դϴ�.</p>\r\n");
		out.write("\r\n");
		out.write("\t\t\t\t\t<form action=\"\" method=\"post\">\r\n");
		out.write("\r\n");
		out.write("\t\t\t\t\t\t<div class=\"form-group\">\r\n");
		out.write("\t\t\t\t\t\t\t<input type=\"text\" class=\"form-control\" id=\"id\" name=\"id\"\r\n");
		out.write("\t\t\t\t\t\t\t\tplaceholder=\"ID(max:20)\" required>\r\n");
		out.write("\t\t\t\t\t\t</div>\r\n");
		out.write("\r\n");
		out.write("\t\t\t\t\t\t<div class=\"form-group\">\r\n");
		out.write("\t\t\t\t\t\t\t<input type=\"password\" class=\"form-control\" id=\"pw\" name=\"pw\"\r\n");
		out.write("\t\t\t\t\t\t\t\tplaceholder=\"PASSWORD(max:20)\" required>\r\n");
		out.write("\t\t\t\t\t\t</div>\r\n");
		out.write("\r\n");
		out.write("\t\t\t\t\t\t<button type=\"submit\" class=\"btn btn-default\">Submit</button>\r\n");
		out.write("\r\n");
		out.write("\t\t\t\t\t</form>\r\n");
		out.write("\r\n");
		out.write("\t\t\t\t</div>\r\n");
		out.write("\t\t\t\t<div class=\"modal-footer\">\r\n");
		out.write(
				"\t\t\t\t\t<button type=\"button\" class=\"btn btn-default\" data-dismiss=\"modal\">Close</button>\r\n");
		out.write("\t\t\t\t</div>\r\n");
		out.write("\t\t\t</div>\r\n");
		out.write("\r\n");
		out.write("\t\t</div>\r\n");
		out.write("\t</div>\r\n");
		out.write("\r\n");
		out.write("\r\n");
		out.write("\t<!-- Modal -->\r\n");
		out.write("\t<div id=\"googleMapModal\" class=\"modal fade\" role=\"dialog\">\r\n");
		out.write("\t\t<div class=\"modal-dialog\">\r\n");
		out.write("\r\n");
		out.write("\t\t\t<!-- Modal content-->\r\n");
		out.write("\t\t\t<div class=\"modal-content\">\r\n");
		out.write("\t\t\t\t<div class=\"modal-header\">\r\n");
		out.write("\t\t\t\t\t<button type=\"button\" class=\"close\" data-dismiss=\"modal\">&times;</button>\r\n");
		out.write("\t\t\t\t\t<h4 class=\"modal-title\">Google Map</h4>\r\n");
		out.write("\t\t\t\t</div>\r\n");
		out.write("\t\t\t\t<div class=\"modal-body\">\r\n");
		out.write("\r\n");
		out.write("\t\t\t\t\t<!-- �� ��� -->\r\n");
		out.write("\t\t\t\t\t<div id=\"googleMap\" style=\"width: 100%; height: 500px;\"></div>\r\n");
		out.write("\r\n");
		out.write("\t\t\t\t</div>\r\n");
		out.write("\t\t\t\t<div class=\"modal-footer\">\r\n");
		out.write(
				"\t\t\t\t\t<button type=\"button\" class=\"btn btn-default\" data-dismiss=\"modal\">Close</button>\r\n");
		out.write("\t\t\t\t</div>\r\n");
		out.write("\t\t\t</div>\r\n");
		out.write("\r\n");
		out.write("\t\t</div>\r\n");
		out.write("\t</div>\r\n");
		out.write("\r\n");
		out.write("</body>\r\n");
		out.write("</html>");

	}

}
