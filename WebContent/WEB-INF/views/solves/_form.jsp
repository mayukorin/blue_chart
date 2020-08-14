<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${errors != null }">
    <div id="flush_error">
        入力内容にエラーがあります。<br/>
        <c:forEach var="error" items="${errors}">
            <c:out value="${error}" /><br/>
        </c:forEach>

    </div>

</c:if>
<label for="problem">問題</label><br/>
<c:out value="${sessionScope.problem.name}"/>
<br/><br/>


<label for="day"> 日付</label><br/>
<input type="date" name="day" value="${solve.day}"/>
<br/><br/>

<label for="targettime">目標時間</label><br/>
<input type="time" step="1" name="targettime" value="${solve.targettime}"/>
<br/><br/>

<label for="solvetime">解答時間</label><br/>
<input type="time" step="1" name="solvetime" value="${solve.solvetime}"/>
<br/><br/>

<label for="content">コメント</label><br/>
<textarea name="content" rows="10" cols="50">${solve.content}</textarea>
<br /><br />

<input type="hidden" name="_token" value="${_token}"/>
<button type="submit">投稿</button>