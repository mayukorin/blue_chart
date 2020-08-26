package controllers.toppage;

import java.io.IOException;
import java.text.SimpleDateFormat;
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
        String str = "";
        Date today = new Date();
        Map<Problem,Solve2> notp = new HashMap<Problem,Solve2>();//問題と、その問題を最後に解いた日

        EntityManager em = DBUtil.createEntityManager();

        Person pp = ((Person)request.getSession().getAttribute("login_person"));
        List<Problem> problems = em.createNamedQuery("getAllProblems",Problem.class).getResultList();

        for (int i=0;i<problems.size();i++) {
            Problem p = problems.get(i);
            List<Solve2> s2 = em.createNamedQuery("solveproblem",Solve2.class).setParameter("problem", p).setParameter("person", pp).getResultList();
            if (s2.size() > 0) {
                Date day = s2.get(s2.size()-1).getDate();//problemを解いた最も新しい日

                long day_diff = (today.getTime()-day.getTime())/(1000 * 60 * 60 * 24 );


                if (day_diff<=8) {
                    //一週間以内にやった問題
                    str = new SimpleDateFormat("yyyy-MM-dd").format(day);
                    latestp.put(p,s2.get(s2.size()-1));


                } else if (day_diff >=30) {
                    //一ヶ月くらいやっていない問題
                    str = new SimpleDateFormat("yyyy-MM-dd").format(day);
                    notp.put(p,s2.get(s2.size()-1));

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
