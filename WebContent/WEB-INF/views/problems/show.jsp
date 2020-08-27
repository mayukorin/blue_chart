<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.Iterator"%>
<%@ page import="java.time.LocalTime"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:import url="../layout/app.jsp">
    <c:param name="content">
        <c:choose>
            <c:when test="${problem != null}">
                <h2>${problem.name}詳細ページ</h2>

                <table>
                    <tbody>
                        <tr>
                            <th>問題</th>
                            <td><c:out value="${problem.name}" /></td>
                        </tr>
                        <tr>
                            <th>テーマ</th>
                            <td><c:out value="${problem.title}" /></td>
                        </tr>
                        <tr>
                            <th>ページ</th>
                            <td><c:out value="${problem.page}" /></td>
                        </tr>
                        <tr>
                            <th>難易度</th>
                            <td><c:out value="${problem.star}" /></td>
                        </tr>

                    </tbody>
                </table>
        &nbsp;&nbsp;&nbsp;
        <table>
                    <tbody>
                        <tr>
                            <th>日付</th>
                            <th>目標時間（分：秒）</th>
                            <th>解答時間（分：秒）</th>
                        </tr>
                        <c:forEach var="solve" items="${solves}">
                            <c:choose>
                                <c:when test="${solve.rate <= 1}">
                                    <tr class="blue">
                                        <td><a
                                            href="<c:url value="/solves/show?solve=${solve.id}"/>"><c:out
                                                    value="${solve.day}" /></a></td>
                                        <td>${solve.target_minute}:${solve.target_second}</td>
                                        <td>${solve.solve_minute}:${solve.solve_second}</td>
                                    </tr>
                                </c:when>
                                <c:otherwise>
                                    <tr class="red">
                                        <td><a
                                            href="<c:url value="/solves/show?solve=${solve.id}"/>"><c:out
                                                    value="${solve.day}" /></a></td>
                                        <td>${solve.target_minute}:${solve.target_second}</td>
                                        <td>${solve.solve_minute}:${solve.solve_second}</td>
                                    </tr>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>

                    </tbody>
                </table>
                <p>
                    <a href="<c:url value="/solves/new"/>">新しい解答情報を入れる</a>
                </p>
            </c:when>
            <c:otherwise>
                <h2>お探しのデータは見つかりませんでした。</h2>
            </c:otherwise>
        </c:choose>
        <p>
            <a href="<c:url value="/problems/index"/>">問題一覧に戻る</a>

        </p>

        <script>var array =[];</script>
        <% List<String> day =(List<String>)request.getAttribute("day");%>
        <%Iterator<String> it = day.iterator();%>
        <% while (it.hasNext()) { %>
        <%      String a =it.next();%>
        <script> var aa='<%=a%>';</script>


        <script>array.push(aa);</script>

        <%} %>

        <script>var srray =[];</script>
        <% List<Integer> sl =(List<Integer>)request.getAttribute("tt");%>
        <%Iterator<Integer> st = sl.iterator();%>
        <% while (st.hasNext()) { %>
        <%      Integer is =st.next();%>
        <script> var ss='<%=is%>';</script>
        <script> var sw=parseInt(ss);</script>
        <script>srray.push(sw);</script>

        <%} %>

        <script>var grray =[];</script>
        <% List<Integer> ss =(List<Integer>)request.getAttribute("ts");%>
        <%Iterator<Integer> tt = ss.iterator();%>
        <% while (tt.hasNext()) { %>
        <%      Integer is =tt.next();%>
        <script> var sp='<%=is%>';</script>
        <script> var sw=parseInt(sp);</script>
        <script>grray.push(sw);</script>

        <%} %>
        <% Integer max = (Integer)request.getAttribute("max_time"); %>
        <script> var max_time = '<%=max%>';</script>

        <h2>目標時間・解答時間の比較</h2>
        <canvas id="myBarChart"></canvas>
        <script
            src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.7.2/Chart.bundle.js"></script>

        <script>
          var ctx = document.getElementById("myBarChart");
          var myBarChart = new Chart(ctx, {
            type: 'line',
            data: {
              labels: array,
              datasets: [
                {
                  label: '解答時間',
                  data: grray,
                  borderColor: "rgba(255,0,0,1)",
                  backgroundColor: "rgba(0,0,0,0)",

                },
                {
                    label: '目標時間',
                    data: srray,
                    borderColor: "rgba(0,0,255,1)",
                    backgroundColor: "rgba(0,0,0,0)",

                  }
              ],
            },
            options: {
                title: {
                  display: true,
                  text: '解答した日付'

                },
                scales: {
                    yAxes: [{
                        ticks: {
                          suggestedMin: 0,
                          suggestedMax: max_time*1.1,
                          callback:function(value) {
                              return Math.floor(value/1440)+":"+Math.floor((value%1440)/60)+":"+(value%1440)%60;
                          }
                        }
                      }]
                },

              }
            });

          </script>
    </c:param>
</c:import>