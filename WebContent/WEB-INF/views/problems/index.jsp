<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="../layout/app.jsp">
    <c:param name ="content">
        <c:if test ="${flush != null }">
            <div id="flush_success">
                <c:out value ="${flush}"></c:out>
            </div>
        </c:if>
        <h2>問題一覧</h2>
        <table id="area_list">
            <tbody>
                <tr>
                    <th>問題番号</th>
                    <th>ページ</th>
                    <c:forEach var="i" begin="1" end="${max_count}"  step="1">
                        <th><c:out value="${i}" /></th>
                    </c:forEach>
                </tr>
                <c:forEach var="problem"  items="${problems}">
                    <tr>
                        <td><a href="<c:url value="/problems/show?problem=${problem.id}"/>"><c:out value="${problem.name}" /></a></td>
                        <td><c:out value="${problem.page}" /></td>
                        <c:forEach var="solve" items="${solves}" >
                           <c:choose>
                               <c:when test="${solve.problem == problem}">
                                <c:choose>
                                    <c:when test="${solve.rate < 1 }">
                                        <td class="blue"><a href="<c:url value="/solves/show?solve=${solve.id}"/>"><c:out value="${solve.day}" /></a></td>
                                    </c:when>
                                    <c:otherwise>
                                        <td class="red"><a href="<c:url value="/solves/show?solve=${solve.id}"/>"><c:out value="${solve.day}" /></a></td>
                                    </c:otherwise>
                                </c:choose>
                               </c:when>
                           </c:choose>
                       </c:forEach>

                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <p><a href="<c:url value="/areas/index"/>">分野一覧に戻る</a>

    </c:param>


</c:import>

