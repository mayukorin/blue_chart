package controllers.problems;

import java.io.IOException;
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
 * Servlet implementation class ProblemsIndexServlet
 */
@WebServlet("/problems/index")
public class ProblemIndexServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProblemIndexServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub

        if (request.getSession().getAttribute("problem") != null) {
            request.getSession().removeAttribute("problem");
        }

        long max_count = 0;
        Area area;


        EntityManager em = DBUtil.createEntityManager();

        Person p = ((Person)request.getSession().getAttribute("login_person"));

        try {
            area= em.find(Area.class,Integer.parseInt(request.getParameter("id")));
            request.getSession().setAttribute("areaa", area);
        } catch(NumberFormatException e) {
            area = (Area) request.getSession().getAttribute("areaa");


        }

        List<Problem> problems = em.createNamedQuery("getProblemsinArea",Problem.class).setParameter("area", area).getResultList();

        List<Solve2> solves = em.createNamedQuery("areasolve2",Solve2.class).setParameter("area", area).setParameter("person",p).getResultList();


        for (int i=0;i<problems.size();i++) {
            Problem pp = problems.get(i);
            long count = (long)em.createNamedQuery("problemcount",Long.class).setParameter("problem", pp).setParameter("person",p).getSingleResult();
            if (count > max_count) {
                max_count = count;
            }


        }

        em.close();

        request.setAttribute("problems", problems);
        request.setAttribute("solves", solves);
        request.setAttribute("max_count", max_count);

        if(request.getSession().getAttribute("flush") != null) {
            request.setAttribute("flush", request.getSession().getAttribute("flush"));
            request.getSession().removeAttribute("flush");
        }

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/problems/index.jsp");
        rd.forward(request, response);

    }

}
