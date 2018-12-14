<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%-- JSTL 설정을 위해서 jstl-1.2.jar 필요  --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>	
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
		//검색 진행 후 상태값 유지를 위한 설정 필요
		//<select> 태그에 대한 상태값 설정
		$("#key option[value='${key}']").attr("selected", "selected");
		//<input type="text"> 태그에 대한 상태값 설정
		$("#value").val("${valule}");
		
		//삭제 진행을 위한 버튼 click 이벤트 추가
		//->on() 메소드
		//->버튼 클릭시 confirm() 호출
		//->번호, 이름, 전화번호 포함 메시지 출력
		$(".btnDel").on("click", function() {
			//삭제 버튼의 조상 태그(td)의 형제들 탐색
			var siblings = $(this).parents("td").siblings("td");
			//특정 번째 형제 태그의 텍스트 탐색
			var mid_ = $(siblings).eq(0).text();
			var name_ = $(siblings).eq(1).text();
			var phone = $(siblings).eq(2).text();
			//confirm() 호출 및 메시지 출력
			var txt = "";
			txt += mid_ + "/" + name_ + "/" + phone + "\r\n";
			txt += "현재 자료를 삭제할까요?";
			if (confirm(txt)) {
				//삭제 액션 페이지 요청 및 번호 전달
				//->location 객체의 assign() 메소드 사용
				//->요청 주소 및 쿼리 문자열 사용
				window.location.assign("memberdelete.it?mid_="+$(this).val());
			}
		});
		
		//수정 진행을 위한 버튼 click 이벤트 추가
		//->on() 메소드
		$(".btnUp").on("click", function() {
			//입력폼을 수정폼으로 변경
			//기존 입력폼에 자료 저장
			//hidden 태그에 고유번호 저장
			//action="" 속성 변경

			//하위에 존재하는 유일한 엘리먼트라면 엘리먼트명 탐색
			//하위에 존재하는 여러개의 엘리먼트라면 식별자 탐색
			$("#input .panel-heading").text("회원 수정");
			
			$("#input button").text("회원정보 수정");
			//$("#input").find("button").text("회원정보 수정");
			
			//수정 버튼의 조상 태그(td)의 형제들 탐색
			var siblings = $(this).parents("td").siblings("td");
			
			//특정 번째 형제 태그의 텍스트 탐색
			var mid_ = $(this).val();
			var name_ = $(siblings).eq(1).text();
			var phone = $(siblings).eq(2).text();
			
			//수정 폼의 특정 항목에 값 변경
			$("#input #mid_").val(mid_);
			$("#input #name_").val(name_);
			$("#input #phone").val(phone);
			
			//수정 폼의 action 속성의 값 변경
			$("#input form").attr("action", "memberupdate.it");
			
		});	
		
	});
</script>

</head>
<body>

	<div class="container">

		<div class="panel page-header" style="text-align: center;">
			<h1 style="font-size: xx-large;">
				<!-- 주의)상대경로 대신 절대경로 표기를 권장한다. -->
				<a href="${pageContext.request.contextPath}/memberlist.it"> <img src="${pageContext.request.contextPath}/resources/img/sist_logo.png"
					alt="sist_logo.png"></a> 회원관리 <small>v6.1 by ServletMVC</small>
			</h1>
		</div>

		<div class="panel panel-default" id="input">
			<div class="panel-heading">회원 추가</div>
			<div class="panel-body">
				
				<!-- 주의)상대경로 대신 절대경로 표기를 권장한다. -->
				<!-- 주의) action="" 속성 주소 지정시 서블릿 주소로만 지정 가능 -->
				<!-- 폼 전송시 action="", method="" 속성 필수 -->
				<!-- DML 액션시 폼 페이지, 액션 페이지 분리 필요(새로고침 문제점 해결) -->
				<form action="${pageContext.request.contextPath}/memberinsert.it" method="post">
				
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
					<tr>
						<td>M04</td>
						<td>Cleveland</td>
						<td>010-0987-0987</td>
						<td>
						<div class="btn-group">
							<button type="button" class="btn btn-default btn-xs btnDel">Delete</button>
							<button type="button" class="btn btn-default btn-xs btnUp">Update</button>
						</div>
						</td>
					</tr>
					 -->
					 
					<%-- JSTL를 이용한 반복문 처리 --%>
					<c:forEach var="m" items="${list}"> 
					<tr>
						
						<td>
						
						<%-- <%=m.getMid_()%> --%>
						${m.mid_}
						System.out.println(${m.mid_});
						</td>
						<td>${m.name_}</td>
						<td>${m.phone}</td>
						<td>
						<div class="btn-group">
							<button type="button" class="btn btn-default btn-xs btnDel" value="${m.mid_}">Delete</button>
							<button type="button" class="btn btn-default btn-xs btnUp" value="${m.mid_}">Update</button>
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
							TotalCount <span class="badge" id="totalcount">${totalcount}</span>
						</button>
						<!-- 검색 결과 자료 갯수 -->
						<button type="button" class="btn btn-default">
							Count <span class="badge" id="count"></span>
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