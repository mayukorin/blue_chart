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
        List<Problem> ps = em.createNamedQuery("person_problem",Problem.class).setParameter("person", pp).getResultList();//その人が解いている問題
        for (int i= 0;i<ps.size();i++) {
            Problem p = ps.get(i);
            System.out.println(ps.get(i).getName());
            List<Solve2> s2 = em.createNamedQuery("solveproblem_desc",Solve2.class).setParameter("person",pp).setParameter("problem",p).getResultList();

            Solve2 s = s2.get(0);//一番最近といたヤツ
            Date day = s.getDate();
            long day_diff = (today.getTime()-day.getTime())/(1000 * 60 * 60 * 24 );//今日との差

            if (day_diff <= 8) {//最近といたヤツ
                latestp.put(s.getProblem(),s);
            } else if (day_diff >= 30) {
                notp.put(s.getProblem(),s);
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
