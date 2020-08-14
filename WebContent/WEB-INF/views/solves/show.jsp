<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="../layout/app.jsp">
    <c:param name ="content">
    <c:choose>
        <c:when test="${solve != null}">
        <h2>解き方 詳細ページ</h2>

        <table>
            <tbody>
                <tr>
                    <th>問題</th>
                    <td><c:out value="${sessionScope.problem.name}"/></td>
                </tr>
                <tr>
                    <th>日付</th>
                    <td><c:out value="${solve.day}"/></td>
                </tr>
                <tr>
                    <th>目標時間</th>
                    <td><c:out value="${solve.targettime}"/></td>
                </tr>
                <tr>
                    <th>実際の時間</th>
                    <td><c:out value="${solve.solvetime}"/></td>
                </tr>
                <tr>
                    <th>コメント</th>
                    <td>
                        <pre><c:out value="${solve.content}"/></pre>
                    </td>
                </tr>

            </tbody>
        </table>
        </c:when>
        <c:otherwise>
            <h2>お探しのデータは見つかりませんでした。</h2>
        </c:otherwise>
    </c:choose>
    <p><a href="<c:url value="/problems/index"/>">問題一覧へ戻る</a></p>
    <p><a href="<c:url value="/problems/show"/>">問題詳細へ戻る</a></p>
    <p><a href="<c:url value="/solves/edit?solve=${solve.id}"/>">解答情報を編集する</a></p>
</c:param>
</c:import>