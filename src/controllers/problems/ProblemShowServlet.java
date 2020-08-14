package controllers.problems;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Area;
import models.Person;
import models.Problem;
import models.Solve2;
import utils.DBUtil;

/**
 * Servlet implementation class ProblemShowServlet
 */
@WebServlet("/problems/show")
public class ProblemShowServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProblemShowServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        Problem p;


        EntityManager em = DBUtil.createEntityManager();

        Person pp = ((Person)request.getSession().getAttribute("login_person"));

        try {
            //問題一覧画面から遷移した場合
            p = em.find(Problem.class, Integer.parseInt(request.getParameter("problem")));
        } catch (NumberFormatException e) {
            ///solve登録画面などから遷移した場合
            p = em.find(Problem.class, ((Problem) request.getSession().getAttribute("problem")).getId());
        }



        try {
            System.out.println(((Area) request.getSession().getAttribute("areaa")).getName());

        } catch(NullPointerException e) {
            //トップページから問題詳細画面へ遷移した場合

            request.getSession().setAttribute("areaa",p.getArea());
        }
        List<Solve2> solves = em.createNamedQuery("solveproblem",Solve2.class).setParameter("problem", p).setParameter("person", pp).getResultList();//その問題を、ログインした人が解いた情報


        List<Integer> tt = new ArrayList<Integer>();//目標時間
        List<Integer> ts = new ArrayList<Integer>();//回答時間
        List<String> day = new ArrayList<String>();//解いた日付



        Iterator<Solve2> it = solves.iterator();
        while (it.hasNext()) {
            Solve2 s = it.next();

            tt.add((s.getTargettime().getHour()*1440+s.getTargettime().getMinute()*60+s.getTargettime().getSecond()));
            ts.add(s.getSolvetime().getHour()*1440+s.getSolvetime().getMinute()*60+s.getSolvetime().getSecond());
            day.add(s.getDay());

        }

        em.close();

        request.getSession().setAttribute("problem", p);
        request.setAttribute("_token", request.getSession().getId());
        request.setAttribute("solves", solves);
        request.setAttribute("tt", tt);
        request.setAttribute("ts", ts);
        request.setAttribute("day", day);

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/problems/show.jsp");
        rd.forward(request, response);
    }

}
