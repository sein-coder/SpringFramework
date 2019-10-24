<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="path" value="${pageContext.request.contextPath}"/>

<jsp:include page="/WEB-INF/views/common/header.jsp">
	<jsp:param name="pageTitle" value=""/>
</jsp:include>

<section id="content">
<div id="enroll-container">
		<form name="memberEnrollFrm" action="${pageContext.request.contextPath}/member/memberEnrollEnd.do" method="post" onsubmit="return validate();" >
			<input type="text" class="form-control" placeholder="아이디 (4글자이상)" name="userId" id="userId_" required readonly="readonly" value="${member.userId }">
			<input type="text" value="${member.userName }" class="form-control" placeholder="이름" name="userName" id="userName" required readonly="readonly">
			<input type="number" value="${member.age }" class="form-control" placeholder="나이" name="age" id="age" readonly="readonly">
			<input type="email" value="${member.email }" class="form-control" placeholder="이메일" name="email" id="email" required readonly="readonly">
			<input type="tel" value="${ member.phone }" class="form-control" placeholder="전화번호 (예:01012345678)" name="phone" id="phone" maxlength="11" required readonly="readonly">
			<input type="text" value="${member.address }" class="form-control" placeholder="주소" name="address" id="address" readonly="readonly">
		</form>
	</div>
</section>

<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
