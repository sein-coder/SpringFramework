<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="path" value="${pageContext.request.contextPath}"/>

<jsp:include page="/WEB-INF/views/common/header.jsp">
	<jsp:param name="pageTitle" value=""/>
</jsp:include>

<section id="content">
	 <p>총 ${totalCount }건의 게시물이 있습니다.</p>
     <input type="button" value="글쓰기" id='btn-add' class='btn btn-outline-success' onclick='location.href="${path}/board/boardForm"'/>
     <table id="tbl-board" class="table table-striped table-hover">
         <tr>
             <th>번호</th>
             <th>제목</th>
             <th>작성자</th>
             <th>작성일</th>
             <th>첨부파일</th>
             <th>조회수</th>
         </tr>
         <c:if test="${not empty list }">
         	<c:forEach items="${list }" var="b">
         		<tr>
         			<td><c:out value="${ b.boardNo }"/></td>
         			<td><a href="${path }/board/boardView?boardNo=${b.boardNo}"><c:out value="${ b.boardTitle }"/></a></td>
         			<td><c:out value="${ b.boardWriter }"/></td>
         			<td><c:out value="${ b.boardDate }"/></td>
         			<td><c:out value="${ b.attachCount }"/></td>
         			<td><c:out value="${ b.boardReadCount }"/></td>
         		</tr>
         	</c:forEach>
         </c:if>
     </table>
     
     ${pageBar }
     
</section>

<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
