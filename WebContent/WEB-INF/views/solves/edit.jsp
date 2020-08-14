<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="../layout/app.jsp">
    <c:param name ="content">
    <h2>解答情報 編集ページ</h2>

    <form method="POST" action="<c:url value="/solves/update"/>">
        <c:import url="_form.jsp"/>
    </form>

    <p><a href="<c:url value="/solves/show?solve=${solve.id}"/>">解答情報詳細に戻る</a></p>
    </c:param>
</c:import>