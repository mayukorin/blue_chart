<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:import url="../layout/app.jsp">
    <c:param name="content">
        <c:if test="${flush != null}">
            <div id="flush_success">
                <c:out value="${flush}"></c:out>
            </div>
        </c:if>
        <h2>青チャート管理システムへようこそ</h2>
        <p><a href="<c:url value='/persons/edit'/>">会員情報を編集する</a></p>
        <p><a href="<c:url value='/areas/index'/>">分野一覧を見る</a></p>
    </c:param>
</c:import>