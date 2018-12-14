<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%-- jstl-1.2.jar 파일 필요 --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
<script
	src="${pageContext.request.contextPath}/resources/script/timer.jquery.min.js"></script>


<script>
	$(document).ready(function() {

		//로그인 후 경과시간 표시용 타이머 설정
			action : 'start', 
			seconds : ${loginTime}
		});
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
					<li><a href="${pageContext.request.contextPath}/employee/list">직원
							관리</a></li>
					<li><a href="${pageContext.request.contextPath}/region/list">지역
							관리</a></li>
					<li><a
						href="${pageContext.request.contextPath}/department/list">부서
							관리</a></li>
					<li><a href="${pageContext.request.contextPath}/position/list">직위
							관리</a></li>
					<li class="active"><a
						href="${pageContext.request.contextPath}/admin/pw">관리자 관리</a></li>

					<li>
						<!-- 로그인 후 경과시간 표시용 타이머 출력 --> <a
						href="${pageContext.request.contextPath}/login/logout">
							[${sessionScope.login.id_}] 로그아웃 <span id="timer" class="badge">00:00</span>
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
				<strong>Success!</strong> 요청한 작업이 처리되었습니다.
			</div>
		</c:if>
		<c:if test="${param.result=='fail'}">
			<div class="alert alert-danger alert-dismissible fade in"
				style="padding-top: 5px; padding-bottom: 5px; margin-top: 5px; margin-bottom: 5px;">
				<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
				<strong>Fail!</strong> 요청한 작업이 처리되지 못했습니다.
			</div>
		</c:if>

		<div class="panel panel-default" id="input">
			<div class="panel-heading">패스워드 변경</div>
			<div class="panel-body" id="demo">
				<form role="form"
					action="${pageContext.request.contextPath}/admin/pwupdate"
					method="post">
					<input type="hidden" name="id_" value="${sessionScope.login.id_}">
					<div class="form-group">
						<input type="password" class="form-control" id="oldpw"
							name="oldpw" placeholder="기존패스워드 (30자 이내)" maxlength="30"
							required="required">
					</div>
					<div class="form-group">
						<input type="password" class="form-control" id="newpw"
							name="newpw" placeholder="신규패스워드 (30자 이내)" maxlength="30"
							required="required">
					</div>
					<button type="submit" class="btn btn-default">Submit</button>

				</form>

			</div>
		</div>

	</div>

</body>
</html>
