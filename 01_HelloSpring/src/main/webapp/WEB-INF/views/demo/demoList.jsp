<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="path" value="${pageContext.request.contextPath}"/>

<jsp:include page="/WEB-INF/views/common/header.jsp">
	<jsp:param name="pageTitle" value="Dev리스트"/>
</jsp:include>

<section id="content">
	<table class="table">
		<tr>
			<th scope="col">번호</th>
			<th scope="col">이름</th>
			<th scope="col">나이</th>
			<th scope="col">이메일</th>
			<th scope="col">성별</th>
			<th scope="col">개발가능언어</th>
		</tr>
		<c:forEach items="${list}" var="l" varStatus="s">
			<tr>
				<td><c:out value="${ s.count }"/></td>
				<td><c:out value="${ l.devName }"/></td>
				<td><c:out value="${ l.devAge }"/></td>
				<td><c:out value="${ l.devEmail }"/></td>
				<td><c:out value="${ l.devGender }"/></td>
				<td>
					<c:forEach items="${ l.devLang }" var="lang" varStatus="v">
						${ v.index!=null&&v.index!=0?",":"" }${ lang }
					</c:forEach>
				</td>
				<td>
					<button type="button" class="btn btn-outline-light" onclick="location.href='${path}/demo/demoUpdate.do?devNo=${l.devNo}'">수정</button>
				</td>
				<td>
					<button type="button" class="btn btn-outline-light" onclick="location.href='${path}/demo/demoDelete.do?devNo=${l.devNo}'">삭제</button>
				</td>
			</tr>
		</c:forEach>
	</table>
	<jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>
</section>

<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
