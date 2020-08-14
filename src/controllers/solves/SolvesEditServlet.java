package controllers.solves;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Solve2;
import utils.DBUtil;

/**
 * Servlet implementation class SolvesEditServlet
 */
@WebServlet("/solves/edit")
public class SolvesEditServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public SolvesEditServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        response.getWriter().append("Served at: ").append(request.getContextPath());
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        EntityManager em = DBUtil.createEntityManager();

        Solve2 s2 = em.find(Solve2.class, Integer.parseInt(request.getParameter("solve")));



        if (s2 != null) {
            request.setAttribute("solve", s2);
            request.setAttribute("_token", request.getSession().getId());
            request.getSession().setAttribute("s2_id", s2.getId());

        }


        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/solves/edit.jsp");
        rd.forward(request, response);



    }

}
