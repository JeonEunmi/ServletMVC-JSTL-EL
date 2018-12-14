<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri = "http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<title>쌍용교육센터</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
   href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

<!-- jQuery library -->
<script
   src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

<!-- Latest compiled JavaScript -->
<script
   src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<script>
   $(document).ready(function() {
   
      
   });
</script>

</head>
<body>

   <div class="container">

      <div class="panel page-header" style="text-align: center;">
         <h1 style="font-size: xx-large;">
            <a href="memberlist.jsp"> <img src="resources/img/sist_logo.png"
               alt="sist_logo.png"></a> 회원관리 <small>v5.1 BootStrap+MySQL</small>
         </h1>
      </div>
      <%-- 성공, 실패 메시지 출력 위치 --%>
      
      <div class="panel panel-default" id="input">
         <div class="panel-heading">회원 추가</div>
         <div class="panel-body">

            <!-- 폼 전송시 action="", method="" 속성 필수 -->
            <!-- DML 액션시 폼 페이지, 액션 페이지 분리 필요(새로고침 문제점 해결) -->
            <form action="sample041_insert.jsp" method="post">
            
               <!-- 수정 액션 진행시 필요 -->
               <!-- hidden 태그는 프로그램 진행상 필요로 하는 값을 임시 저장할 때 사용 -->
               <!-- 주의) form 엘리먼트 안쪽에 위치해야 한다 -->   
               <input type="hidden" id="mid_" name="mid_" value="값">
               
               <div class="form-group">
                  <!-- 폼 전송시 name="" 속성 필수 -->
                  <!-- 동일 자료, 동일 식별자 사용 -->
                  <!-- required 속성 추가시 내용을 채워야 submit 가능 -->
                  <input type="text" class="form-control" 
                     id="name_" name="name_"
                     placeholder="Name(max 20)" required  value="">
               </div>
               <div class="form-group">
                  <input type="text" class="form-control" id="phone" name="phone"
                     placeholder="Phone(max 15)" required value="">
               </div>

               <!-- 폼 전송시 type="submit" 속성 필수 -->
               <button type="submit" class="btn btn-default">회원정보
                  추가</button>

            </form>

         </div>
      </div>

      <div class="panel panel-default" id="output">
         <div class="panel-heading">회원 목록</div>
         <div class="panel-body">

            <table id="members" class="table">
               <tr>
                  <th>Mid</th>
                  <th>Name</th>
                  <th>Phone</th>
                  <th>Delete/Update</th>
               </tr>
               <!-- 
               <tr>
                  <td>M01</td>
                  <td>Peter</td>
                  <td>010-1234-1234</td>
                  <td>
                  <div class="btn-group">
                     <button type="button" class="btn btn-default btn-xs btnDel">Delete</button>
                     <button type="button" class="btn btn-default btn-xs btnUp">Update</button>
                  </div>
                  </td>
               </tr>
               <tr>
                  <td>M02</td>
                  <td>Lois</td>
                  <td>010-4321-4321</td>
                  <td>
                  <div class="btn-group">
                     <button type="button" class="btn btn-default btn-xs btnDel">Delete</button>
                     <button type="button" class="btn btn-default btn-xs btnUp">Update</button>
                  </div>
                  </td>
               </tr>
               <tr>
                  <td>M03</td>
                  <td>Joe</td>
                  <td>010-6543-6543</td>
                  <td>
                  <div class="btn-group">
                     <button type="button" class="btn btn-default btn-xs btnDel">Delete</button>
                     <button type="button" class="btn btn-default btn-xs btnUp">Update</button>
                  </div>
                  </td>
               </tr>
               -->
               <c:forEach var="m" items="${list}">
               <tr>
                  <td>${m.mid_}</td>
                  <td>${m.name_}</td>
                  <td>${m.phone}</td>
                  <td>
                  <div class="btn-group">
                     <button type="button" class="btn btn-default btn-xs btnDel">Delete</button>
                     <button type="button" class="btn btn-default btn-xs btnUp">Update</button>
                  </div>
                  </td>
               </tr>
               </c:forEach>
               
            </table>

            <!-- action="" 속성 생략시 자기자신에게 폼 전송 -->
            <form class="form-inline" method="POST">
               <div class="form-group">
                  <!-- 전체 자료 갯수 -->
                  <button type="button" class="btn btn-default">
                     TotalCount <span class="badge" id="totalcount">${totalCount}</span>
                  </button>
                  <!-- 검색 결과 자료 갯수 -->
                  <button type="button" class="btn btn-default">
                     Count <span class="badge" id="count"> </span>
                  </button>
                  <!-- 검색 기준 선택 항목 -->
                  <select class="form-control" 
                     id="key" name="key">
                     <option value="all">All</option>
                     <option value="mid_">Mid</option>
                     <option value="name_">Name</option>
                     <option value="phone">Phone</option>
                  </select>
               </div>
               <div class="input-group">
                  <!-- 검색 단어 입력 폼 -->
                  <input type="text" class="form-control" 
                     id="value" name="value"
                     placeholder="Search">
                  <div class="input-group-btn">
                     <!-- 검색 진행 버튼 -->
                     <button type="submit" class="btn btn-default" id="btnSearch">
                        <i class="glyphicon glyphicon-search"></i>
                     </button>
                  </div>
               </div>

            </form>

         </div>
      </div>

   </div>

</body>
</html>