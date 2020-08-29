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


<label for="day"> 日付（カレンダーが表示されない場合は、2020-08-01のように入力してください）</label><br/>
<input type="date" name="day" value="${solve.day}"/>
<br/><br/>

<p>目標時間</p>
<div class="minute">
<label for="target_minute">分</label><br/>
<input type="number"  min="0" name="target_minute" value="${solve.target_minute }"/>
</div>

<div class="second">
<label for="target_second">秒</label><br/>
<input type="number" min="0" max="60" name="target_second" value="${solve.target_second }"/>
</div>
<br/><br/><br/>
<p>解答時間</p>
<div class="minute">
<label for="minute">分</label><br/>
<input type="number"  min="0" name="minute" value="${solve.solve_minute }"/>
</div>

<div class="second">
<label for="second">秒</label><br/>
<input type="number" min="0" max="59" name="second" value="${solve.solve_second }"/>
</div>
<br/><br/><br/>


<label for="content">コメント</label><br/>
<textarea name="content" rows="10" cols="50">${solve.content}</textarea>
<br /><br />

<input type="hidden" name="_token" value="${_token}"/>
<button type="submit">登録</button>