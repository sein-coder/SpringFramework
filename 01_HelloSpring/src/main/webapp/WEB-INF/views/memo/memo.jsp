<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="path" value="${pageContext.request.contextPath}"/>

<jsp:include page="/WEB-INF/views/common/header.jsp">
	<jsp:param name="pageTitle" value=""/>
</jsp:include>

<style>
	div#memo-container{width:60%; margin:0 auto;}
</style>

<section id="content">
    <div id="memo-container">
        <form action="${pageContext.request.contextPath}/memo/insertMemo.do" class="form-inline">
            <input type="text" class="form-control col-sm-6" name="memo" placeholder="메모" required/>&nbsp;
            <input type="password" class="form-control col-sm-2" name="password" maxlength="4" placeholder="비밀번호" required/>&nbsp;
            <button class="btn btn-outline-success" type="submit" >저장</button>
        </form>
    </div>
    <br />
    <!-- 메모목록 -->
       <table class="table">
           <tr>
               <th scope="col">번호</th>
               <th scope="col">메모</th>
               <th scope="col">날짜</th>
               <th scope="col">삭제</th>
           </tr>
           <c:if test="${ not empty list }">
           		<c:forEach items="${ list }" var="m" varStatus="i">
	           		<tr>
	           			<td><c:out value="${ m.memoNo }"/></td>
	           			<td><c:out value="${ m.memo }"/></td>
	           			<td><fmt:formatDate value="${ m.memoDate }" dateStyle="long"/></td>
	           			<td><c:out value="${ m.password }"/></td>
	           		</tr>
           		</c:forEach>
           </c:if>
        </table>
</section>

<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
