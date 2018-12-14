<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%-- jstl-1.2.jar 파일 필요 --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%-- 숫자 서식 지정을 위한 국제화 태그 라이브러리 설정 --%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>쌍용교육센터</title>

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

<style>
div#input:hover, div#output:hover {
	box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0
		rgba(0, 0, 0, 0.19);
}
</style>

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<!-- 로그인 후 경과시간 표시용 타이머 설정을 위한 스크립트 추가 -->
<script src="${pageContext.request.contextPath}/resources/script/timer.jquery.min.js"></script>

<script>
	$(document).ready(function() {
		
		//툴팁 설정 추가
		$('[data-toggle="tooltip"]').tooltip();
		
		//사진보기 버튼에 대한 click 이벤트 등록
		$(".btnPicture").on("click", function() {
			//이름 확인
			var name = $(this).parent().text();
			name = name.slice(0,name.indexOf("/"));
			//전화번호 확인
			var phone = $(this).parents("tr").find("td:eq(3)").text();
			//파일이름 확인
			var filename = $(this).val();
			//이름,전화번호 정보 출력
			$("#pictureModal h4").text("["+name+"/"+phone+"] 사진");
			//사진 정보 출력
			$("#pictureModal img").attr("src", "${pageContext.request.contextPath}/resources/pictures/"+filename);
		});
		
		//검색 진행시 상태값 유지 설정
        $("#key option[value='${key}']").attr("selected", "selected");
        $("#value").val('${value}');
        
        
        //사진등록 버튼에 대한 click 이벤트 등록
        $(".btnPictureForm").on("click", function() {
        	//번호 확인
        	var empId = $(this).parents("tr").find("td:eq(0)").text();
        	//이름 확인
        	var name = $(this).parents("tr").find("td:eq(1)").text();
        	name = name.slice(0,name.indexOf("/"));
        	
        	//번호, 이름 정보 출력
        	$("#pictureFormModal #empId").val(empId);
        	$("#pictureFormModal #content").val(name);
        	
        	//사진 존재 유무->hidden 태그
        	$("#pictureFormModal #pictureCount").val($(this).val());
        });
		
        //삭제 버튼에 대한 click 이벤트 등록
        $(".btnDelete").on("click", function() {
        	//번호 확인
        	var empId = $(this).parents("tr").find("td:eq(0)").text();
        	//이름 확인
        	var name = $(this).parents("tr").find("td:eq(1)").text();
        	name = name.slice(0,name.indexOf("/"));
        	
        	//번호, 이름 정보 출력
        	$("#employeeDeleteModal #empId").val(empId);
        	$("#employeeDeleteModal #content").val(name);
        });
        
        //수정 버튼에 대한 click 이벤트 등록
        $(".btnUpdateForm").on("click", function() {
 			//직원번호를 서버에 전송하는 과정 추가
 			//->submit 액션 or Ajax or GET방식전송
 			//->요청주소는 /employee/updateForm 가 된다.
 			var empId = $(this).val();
 			window.location.assign("${pageContext.request.contextPath}/employee/updateForm?empId="+empId);
 			
        });


		//로그인 후 경과시간 표시용 타이머 설정 - 초 단위로만 표시 가능
		$("#timer").timer({action:'start',seconds:${loginTime}});
		 
	});
</script>
</head>
<body>
	<div class="container">
		<div class="panel page-header" style="text-align: center;">
			<h1 style="font-size: xx-large;">
				<!-- 주의) 상대경로 대신 절대경로 표기를 권장한다. -->
				<a href="${pageContext.request.contextPath}/employee/list"> <img
					src="${pageContext.request.contextPath}/resources/img/sist_logo.png"
					alt="sist_logo.png"></a> 직원관리 <small>by ServletMVC</small>
			</h1>

		</div>

		<nav class="navbar navbar-default">
			<div class="container-fluid">
				<div class="navbar-header"></div>
				<ul class="nav navbar-nav">
					<li class="active"><a
						href="${pageContext.request.contextPath}/employee/list">직원 관리</a></li>
					<li><a href="${pageContext.request.contextPath}/region/list">지역
							관리</a></li>
					<li><a
						href="${pageContext.request.contextPath}/department/list">부서
							관리</a></li>
					<li><a href="${pageContext.request.contextPath}/position/list">직위
							관리</a></li>
					<li><a href="${pageContext.request.contextPath}/admin/pwupdateForm">관리자
							관리</a></li>
							
					<li>
					
					<!-- 로그인 후 경과시간 표시용 타이머 출력 -->
					<a href="${pageContext.request.contextPath}/login/logout">
						[${sessionScope.login.id_}] 로그아웃
						<span id="timer" class="badge">00:00</span>	
					</a>
						
					</li>

				</ul>
			</div>
		</nav>
		
        <%-- 성공 실패 메시지 출력 위치 --%>
        <%-- 주의) 성공, 실패 메시지 수신을 위해서 EL 표현(${raram.식별자}) 사용 --%>
        <%-- 주의) 성공, 실패 메시지 결과에 따른 메시지 출력을 위해서 JSTL 표현(<c:if test="조건식"></c:if>) 사용 --%>
        <c:if test="${param.result=='success'}">
            <div class="alert alert-success alert-dismissible fade in"
             	style="padding-top: 5px; padding-bottom: 5px; margin-top: 5px; margin-bottom: 5px;">
             	<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
             	<strong>Success!</strong> 요청한 작업이 처리되었습니다.</div>
        </c:if>
        <c:if test="${param.result=='fail'}">
            <div class="alert alert-danger alert-dismissible fade in"
             	style="padding-top: 5px; padding-bottom: 5px; margin-top: 5px; margin-bottom: 5px;">
             	<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
             	<strong>Fail!</strong> 요청한 작업이 처리되지 못했습니다.</div>
        </c:if>		

		<div class="panel-group" id="output">
			<div class="panel panel-default">
				<div class="panel-heading">직원 출력</div>
				<div class="panel-body">

					<table class="table">
						<thead>
							<tr>
								<th>
								
								<%-- 
								주의) 정렬 진행시 검색 결과를 유지하기 위해서
								key, value 설정 필요
								--%>
								<a
									href="${pageContext.request.contextPath}/employee/list?order=empId&key=${key}&value=${value}" 
									data-toggle="tooltip" data-placement="top"
									title="사번 기준 정렬">사번</a>
									
								</th>
								<th><a
									href="${pageContext.request.contextPath}/employee/list?order=name_&key=${key}&value=${value}" 
									data-toggle="tooltip" data-placement="top"
									title="이름 기준 정렬">이름/사진</a></th>
								<th><a
									href="${pageContext.request.contextPath}/employee/list?order=ssn&key=${key}&value=${value}" 
									data-toggle="tooltip" data-placement="top"
									title="주민번호 기준 정렬">주민번호</a></th>
								<th><a
									href="${pageContext.request.contextPath}/employee/list?order=phone&key=${key}&value=${value}" 
									data-toggle="tooltip" data-placement="top"
									title="전화번호 기준 정렬">전화번호</a></th>
								<th><a
									href="${pageContext.request.contextPath}/employee/list?order=hiredate&key=${key}&value=${value}" 
									data-toggle="tooltip" data-placement="top"
									title="입사일 기준 정렬">입사일</a></th>
								<th><a
									href="${pageContext.request.contextPath}/employee/list?order=reg_name&key=${key}&value=${value}" 
									data-toggle="tooltip" data-placement="top"
									title="지역명 기준 정렬">지역명</a></th>
								<th><a
									href="${pageContext.request.contextPath}/employee/list?order=dept_name&key=${key}&value=${value}" 
									data-toggle="tooltip" data-placement="top"
									title="부서명 기준 정렬">부서명</a></th>
								<th><a
									href="${pageContext.request.contextPath}/employee/list?order=job_title&key=${key}&value=${value}" 
									data-toggle="tooltip" data-placement="top"
									title="직위명 기준 정렬">직위명</a></th>
								<th><a
									href="${pageContext.request.contextPath}/employee/list?order=basicpay&key=${key}&value=${value}" 
									data-toggle="tooltip" data-placement="top"
									title="기본급 기준 정렬">기본급</a></th>
								<th><a
									href="${pageContext.request.contextPath}/employee/list?order=extrapay&key=${key}&value=${value}" 
									data-toggle="tooltip" data-placement="top"
									title="수당 기준 정렬">수당</a></th>
								<th><a
									href="${pageContext.request.contextPath}/employee/list?order=pay&key=${key}&value=${value}" 
									data-toggle="tooltip" data-placement="top"
									title="급여 기준 정렬">급여</a></th>
								<th>사진등록</th>
								<th>삭제/수정</th>
							</tr>
						</thead>
						<tbody>
							<!-- 
							<tr>
								<td>EMP001</td>
								<td>홍길동/
									<button type="button" class="btn btn-default btn-xs"
										data-toggle="modal" data-target="#pictureModal">사진</button>
								</td>
								<td>901010-1122345</td>
								<td>010-123-1234</td>
								<td>1998-10-11</td>
								<td>서울</td>
								<td>개발부</td>
								<td>사원</td>
								<td>2,000,000</td>
								<td>1,000,000</td>
								<td>3,000,000</td>
								<td><button type="button" class="btn btn-default btn-xs">사진등록</button></td>
								<td><button type="button" class="btn btn-default btn-xs">삭제</button>/
									<button type="button"
										class="btn btn-default btn-xs  btnUpdateForm">수정</button></td>
							</tr>
							 -->
							<c:forEach var="e" items="${list}">
							<tr>
								<td>${e.empId}</td>
								<td>${e.name_}/
									<button type="button" class="btn btn-default btn-xs btnPicture"
										data-toggle="modal" data-target="#pictureModal"  
										value="${e.filename}" 
										${e.pictureCount==0?"disabled='disabled'":""}>사진</button>
										
								</td>
								<td>${e.ssn}</td>
								<td>${e.phone}</td>
								<td>${e.hiredate}</td>
								<td>${e.reg_name}</td>
								<td>${e.dept_name}</td>
								<td>${e.job_title}</td>
								<td><fmt:formatNumber value="${e.basicpay}" groupingUsed="true"></fmt:formatNumber></td>
								<td><fmt:formatNumber value="${e.extrapay}" groupingUsed="true"></fmt:formatNumber></td>
								<td><fmt:formatNumber value="${e.pay}" groupingUsed="true"></fmt:formatNumber></td>								
								<td>
								<button type="button" class="btn btn-default btn-xs btnPictureForm"
								data-toggle="modal" data-target="#pictureFormModal" value="${e.pictureCount}">사진등록</button>
								</td>
								<td>
									<button type="button" 
									class="btn btn-default btn-xs btnDelete"
									data-toggle="modal" data-target="#employeeDeleteModal"
									>삭제</button>/
									<button type="button"
										class="btn btn-default btn-xs  btnUpdateForm" value="${e.empId}">수정</button></td>
							</tr>
							</c:forEach> 
							 
							 
						</tbody>
					</table>

					<%-- 
					주의) 
					검색 진행시 기존 설정을 초기화하기 위해서 
					action="" 속성을 명시적 지정 필요 
					--%>
					<form class="form-inline"
						action="${pageContext.request.contextPath}/employee/list" 
						role="form" method="post">
						
						<a href="${pageContext.request.contextPath}/employee/insertForm"
							class="btn btn-default">직원등록</a>
						<button type="button" class="btn btn-default">
							TotalCount <span class="badge">${totalcount}</span>
						</button>
						<button type="button" class="btn btn-default">
							Count <span class="badge">${count}</span>
						</button>
						<select class="form-control" id="key" name="key">
							<option value="all">전체</option>
							<option value="empId">사번</option>
							<option value="name_">이름</option>
							<option value="ssn">주민번호</option>
							<option value="phone">전화번호</option>
							<option value="hiredate">입사일</option>
							<option value="reg_name">지역명</option>
							<option value="dept_name">부서명</option>
							<option value="job_title">직위명</option>
						</select> <label for="name"></label> <input type="text"
							class="form-control" id="value" name="value" required="required">
						<button type="submit" class="btn btn-default">
							<span class="glyphicon glyphicon-search"></span> Search
						</button>
					</form>

				</div>
			</div>
		</div>

		<div id="pictureModal" class="modal fade" role="dialog">
			<div class="modal-dialog modal-sm">

				<!-- Modal content-->
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">&times;</button>
						<h4 class="modal-title">[OOO] 사진</h4>
					</div>
					<div class="modal-body">
						<div style="text-align: center;">
							<img
								src="${pageContext.request.contextPath}/resources/pictures/avatar.png"
								width="100%">
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
					</div>
				</div>

			</div>
		</div>

		<!-- 사진 업로드용 모달 -->
		<div id="pictureFormModal" class="modal fade" role="dialog">
			<div class="modal-dialog modal-md">

				<!-- Modal content-->
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">&times;</button>
						<h4 class="modal-title">사진업로드</h4>
					</div>
					<div class="modal-body">
						<form
							action="${pageContext.request.contextPath}/employee/fileUpload"
							method="POST" 
							enctype="multipart/form-data">
							
							<%-- 사진 유무 확인 정보 --%>
							<input type="hidden" id="pictureCount"
							  name="pictureCount" value="">
							
							<div class="form-group">
								<input type="text" class="form-control" id="empId" name="empId" value="" readonly="readonly" placeholder="EMPID">
							</div>
							<div class="form-group">
								<input type="text" class="form-control" id="content" name="content" value="" readonly="readonly" placeholder="CONTENT">
							</div>
							<div class="form-group">
								<input type="file" name="filename" class="form-control">
								<span class="help-block">(.jpg or .png, max 5M)</span>
							</div>
							<input type="submit" value="SUBMIT" class="btn btn-default">
						</form>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
					</div>
				</div>

			</div>
		</div>

		<!-- 직원 정보 삭제용 모달 -->
		<div id="employeeDeleteModal" class="modal fade" role="dialog">
			<div class="modal-dialog modal-md">

				<!-- Modal content-->
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">&times;</button>
						<h4 class="modal-title">직원 정보 삭제</h4>
					</div>
					<div class="modal-body">
						현재 선택한 직원 정보를 삭제할까요?
						<form
							action="${pageContext.request.contextPath}/employee/delete"
							method="POST">
							<div class="form-group">
								<input type="text" class="form-control" id="empId" name="empId" value="" readonly="readonly" placeholder="EMPID">
							</div>
							<div class="form-group">
								<input type="text" class="form-control" id="content" name="content" value="" readonly="readonly" placeholder="CONTENT">
							</div>
							<input type="submit" value="SUBMIT" class="btn btn-default">
						</form>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
					</div>
				</div>

			</div>
		</div>

	</div>

</body>
</html>
