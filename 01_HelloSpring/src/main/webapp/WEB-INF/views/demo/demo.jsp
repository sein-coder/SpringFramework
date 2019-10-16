<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="path" value="${pageContext.request.contextPath}"/>

<jsp:include page="/WEB-INF/views/common/header.jsp">
	<jsp:param name="pageTitle" value="DemoTest"/>
</jsp:include>

<style>
	div#demo-container{
		width:40%;
		padding:15px;
		margin:0 auto;
		border:1px solid lightgray;
		border-radius : 10px;
	}
</style>

<section id="content">

	<div id="demo-container">
		<h3>파라미터 테스트</h3>
		<form id="devFrm">
			<div class="form-group row">
				<label for="devName" class="col-sm-2 col-form-label">이름</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" id="devName" name="devName"/>
				</div>
			</div>
			<div class="form-group row">
				<label for="devAge" class="col-sm-2 col-form-label">나이</label>
				<div class="col-sm-10">
					<input type="number" class="form-control" id="devAge" name="devAge"/>
				</div>
			</div>
			<div class="form-group row">
				<label for="devEmail" class="col-sm-2 col-form-label">이메일</label>
				<div class="col-sm-10">
					<input type="email" class="form-control" id="devEmail" name="devEmail"/>
				</div>
			</div>
			<div class="form-group row">
				<label class="col-sm-2 col-form-label">성별</label>
				<div class="col-sm-10">
					<div class="form-check form-check-inline">
						<input class="form-check-input" type="radio" name="devGender"
						id="devGender0" value="M"/>
						<label class="form-check-label" for="devGender0">남</label>
						<input class="form-check-input" type="radio" name="devGender"
						id="devGender1" value="F"/>
						<label class="form-check-label" for="devGender1">여</label>
					</div>
				</div>
			</div>
			<div class="form-group row">
				<label class="col-sm-2 col-form-label">개발언어</label>
				<div class="col-sm-10">
					<div class="form-check form-check-inline">
						<input class="form-check-input" type="checkbox" name="devLang"
						id="devLang0" value="Java"/>
						<label class="form-check-label" for="devLang0">Java</label>
					</div>	
					<div class="form-check form-check-inline">
						<input class="form-check-input" type="checkbox" name="devLang"
						id="devLang1" value="C"/>
						<label class="form-check-label" for="devLang1">C</label>
					</div>	
					<div class="form-check form-check-inline">
						<input class="form-check-input" type="checkbox" name="devLang"
						id="devLang2" value="Javascript"/>
						<label class="form-check-label" for="devLang2">Javascript</label>
					</div>
				</div>
			</div>
			
			<div class="list-group">
				<button type="button" onclick="demo1('${path}/demo/demo1.do');" class="list-group-item list-group-item-action">
					HttpServletRequest로 파라미터 받기
				</button>
				
				<button type="button" onclick="demo1('${path}/demo/demo2.do');" class="list-group-item list-group-item-action">
					@RequestParam이용하여 파라미터 받기
				</button>
				
				<button type="button" onclick="demo1('${path}/demo/demo3.do');" class="list-group-item list-group-item-action">
					@RequestParam이용하여 Map객체 이용해서 받기
				</button>
				
				<button type="button" onclick="demo1('${path}/demo/demo4.do');" class="list-group-item list-group-item-action">
					Command객체 이용해서 받기(Vo)
				</button>
				
				<button type="button" onclick="demo1('${path}/demo/insertDemo.do');" class="list-group-item list-group-item-action">
					dev입력하기(insert)
				</button>
				
			</div>
			
		</form>
	</div>
	
</section>

<script>
	function demo1(url){
		$("#devFrm").attr("action",url);
		$("#devFrm").submit();
	}
</script>

<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
