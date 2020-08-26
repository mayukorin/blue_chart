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
        <table>
            <caption>最近学習した問題</caption>
            <tr>
                <th>問題</th>
                <th>ページ数</th>
                <th>一番最後に解いた日</th>
            </tr>
            <c:forEach var="entry" items="${latestp}">
                <c:choose>
                <c:when test="${entry.value.rate <=  1}">
                <tr class="blue">
                    <td><p><a href="<c:url value="/problems/show?problem=${entry.key.id}"/>"><c:out value="${entry.key.name}"/></a></td>
                    <td><c:out value="${entry.key.page}"/></td>
                    <td><c:out value="${entry.value.day}"/></td>
                </tr>
                </c:when>
                <c:otherwise>
                 <tr class="red">
                    <td><p><a href="<c:url value="/problems/show?problem=${entry.key.id}"/>"><c:out value="${entry.key.name}"/></a></td>
                    <td><c:out value="${entry.key.page}"/></td>
                    <td><c:out value="${entry.value.day}"/></td>
                </tr>
                </c:otherwise>
                </c:choose>
            </c:forEach>

        </table>
        <br/>
        <table>
            <caption>1ヶ月以上学習していない問題</caption>
            <tr>
                <th>問題</th>
                <th>ページ数</th>
                <th>一番最後に解いた日</th>
            </tr>
            <c:forEach var="entry" items="${notp}">
                <c:choose>
                <c:when test="${entry.value.rate <=  1}">
                <tr class="blue">
                    <td><p><a href="<c:url value="/problems/show?problem=${entry.key.id}"/>"><c:out value="${entry.key.name}"/></a></td>
                    <td><c:out value="${entry.key.page}"/></td>
                    <td><c:out value="${entry.value.day}"/></td>
                </tr>
                </c:when>
                <c:otherwise>
                <tr class="red">
                    <td><p><a href="<c:url value="/problems/show?problem=${entry.key.id}"/>"><c:out value="${entry.key.name}"/></a></td>
                    <td><c:out value="${entry.key.page}"/></td>
                    <td><c:out value="${entry.value.day}"/></td>
                </tr>
                </c:otherwise>
                </c:choose>
            </c:forEach>

        </table>

        <p><a href="<c:url value='/areas/index'/>">分野一覧を見る</a></p>
        <p><a href="<c:url value='/persons/edit'/>">会員情報を編集する</a></p>

    </c:param>
</c:import>