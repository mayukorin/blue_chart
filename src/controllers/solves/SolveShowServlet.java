package controllers.solves;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Problem;
import models.Solve2;
import utils.DBUtil;

/**
 * Servlet implementation class SolveShowServlet
 */
@WebServlet("/solves/show")
public class SolveShowServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public SolveShowServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        EntityManager em = DBUtil.createEntityManager();

        Solve2 s2 =  em.find(Solve2.class,Integer.parseInt(request.getParameter("solve")));

        if (request.getSession().getAttribute("problem") == null) {
            //問題一覧からsolve詳細ページに来た場合
            Problem p = (Problem) em.find(Problem.class, s2.getProblem().getId());
            request.getSession().setAttribute("problem",p);
        }

        em.close();

        request.setAttribute("solve", s2);
        request.setAttribute("_token", request.getSession().getId());

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/solves/show.jsp");
        rd.forward(request, response);
    }

}
