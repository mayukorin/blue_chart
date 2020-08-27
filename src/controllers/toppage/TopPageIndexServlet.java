package controllers.toppage;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Person;
import models.Problem;
import models.Solve2;
import utils.DBUtil;

/**
 * Servlet implementation class TopPageIndexServlet
 */
@WebServlet("/index.html")
public class TopPageIndexServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public TopPageIndexServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        Map<Problem,Solve2> latestp = new HashMap<Problem,Solve2>();//問題と、その問題を最後に解いた日
        Date today = new Date();
        Map<Problem,Solve2> notp = new HashMap<Problem,Solve2>();//問題と、その問題を最後に解いた日

        EntityManager em = DBUtil.createEntityManager();

        Person pp = ((Person)request.getSession().getAttribute("login_person"));


        List<Solve2> s2s = em.createNamedQuery("solves",Solve2.class).setParameter("person", pp).getResultList();
        for (int i = 0;i<s2s.size();i++) {
            Solve2 s2 = s2s.get(i);

            Date day = s2.getDate();
            long day_diff = (today.getTime()-day.getTime())/(1000 * 60 * 60 * 24 );

            if (day_diff<=8) {
                //一週間以内にやった問題

                if (latestp.get(s2.getProblem()) == null) {
                    latestp.put(s2.getProblem(),s2);
                } else {
                    //すでに、その問題が登録されている時
                    if ((latestp.get(s2.getProblem()).getDate()).before(s2.getDate())) {
                        //登録されてた日付よりs2.getDateの方が後の場合
                        latestp.replace(s2.getProblem(),s2);

                    }
                }


            } else if (day_diff >=30) {
                //一ヶ月くらいやっていない問題

                if (notp.get(s2.getProblem()) == null) {
                    notp.put(s2.getProblem(),s2);
                } else {
                    //すでに、その問題が登録されている時
                    if ((notp.get(s2.getProblem()).getDate()).after(s2.getDate())) {
                        //登録されてた日付より、s2.getDateの方が前の場合
                        latestp.replace(s2.getProblem(),s2);

                    }
                }

            }

        }



        if (request.getSession().getAttribute("flush") != null) {
            request.setAttribute("flush", request.getSession().getAttribute("flush"));
            request.getSession().removeAttribute("flush");
        }
        request.setAttribute("latestp", latestp);
        request.setAttribute("notp", notp);
        RequestDispatcher rd =request.getRequestDispatcher("/WEB-INF/views/topPage/index.jsp");
        rd.forward(request,response);
    }

}
