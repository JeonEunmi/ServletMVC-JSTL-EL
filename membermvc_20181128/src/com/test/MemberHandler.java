package com.test;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.member.Member;
import com.member.MemberDAO;

public class MemberHandler {

   public String list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

      MemberDAO dao = new MemberDAO();
      String key = request.getParameter("key");
      String value = request.getParameter("value");
      String result = request.getParameter("result");
      System.out.println(key);
      System.out.println(value);

      if (key == null) {
         key = "all";
         value = "";
      }
      List<Member> list = dao.memberList(key, value);

      int totalcount = dao.totalcount();

      String txt = "";
      if (result != null) {
         if (result.equals("success")) {
            // 성공 메시지
            txt = "<div class=\"alert alert-success alert-dismissible fade in\""
                  + "style=\"padding-top: 5px; padding-bottom: 5px; " + "margin-top: 5px; margin-bottom: 5px;\">"
                  + "<a href=\"#\" class=\"close\" data-dismiss=\"alert\"" + "aria-label=\"close\">&times;</a>"
                  + "<strong>Success!</strong> 요청한 작업이 처리되었습니다." + "</div>";
         } else {
            // 실패 메시지
            txt = "<div class=\"alert alert-danger alert-dismissible fade in\""
                  + "style=\"padding-top: 5px; padding-bottom: 5px; " + "margin-top: 5px; margin-bottom: 5px;\">"
                  + "<a href=\"#\" class=\"close\" data-dismiss=\"alert\"" + "aria-label=\"close\">&times;</a>"
                  + "<strong>Fail!</strong> 요청한 작업이 처리되지 못했습니다." + "</div>";
         }
      }

      request.setAttribute("list", list);
      request.setAttribute("totalcount", totalcount);
      request.setAttribute("txt", txt);

      return "/WEB-INF/views/memberlist.jsp?result=" + txt;

   }

   public String insert(HttpServletRequest request, HttpServletResponse response)
         throws ServletException, IOException {
      
      request.setCharacterEncoding("utf-8");
      String name_ = request.getParameter("name_");
      String phone = request.getParameter("phone");

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
      return "/member/list?result=" + txt;
   }

   public String delete(HttpServletRequest request, HttpServletResponse response)
         throws ServletException, IOException {
      
      System.out.println("delete");
   
      request.setCharacterEncoding("utf-8");
      String mid_ = request.getParameter("mid_");
      System.out.println(mid_);
      
      String txt = "";
      if (mid_ != null) {
         MemberDAO dao = new MemberDAO();
         int result = dao.memberRemove(new Member(mid_, null, null));
         if (result == 1) {
            txt = "success";
         } else {
            txt = "fail";
         }
      }

      return "/member/list?result=" + txt;
   }

   

   public String update(HttpServletRequest request, HttpServletResponse response)
         throws ServletException, IOException {

      request.setCharacterEncoding("utf-8");

       String name_ = request.getParameter("name_");
       String phone = request.getParameter("phone");
       String mid_ = request.getParameter("mid_");
       
      
      String txt = "";
      if (name_ != null) {
         MemberDAO dao = new MemberDAO();
         int result = dao.memberModify(new Member(mid_, name_, phone));
         if (result == 1) {
               txt = "success";
          } else {
               txt = "fail";
          }
      }
       
      
      
      return "/member/list?result=" + txt;
   }

}