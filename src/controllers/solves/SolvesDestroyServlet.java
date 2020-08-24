package controllers.solves;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Solve2;
import utils.DBUtil;

/**
 * Servlet implementation class SolvesDestroyServlet
 */
@WebServlet("/solves/destroy")
public class SolvesDestroyServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public SolvesDestroyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        EntityManager em = DBUtil.createEntityManager();

        Solve2 s2 = em.find(Solve2.class,Integer.parseInt(request.getParameter("id")));

        em.getTransaction().begin();
        em.remove(s2);
        em.getTransaction().commit();
        em.close();


        response.sendRedirect(request.getContextPath()+"/problems/index");
    }

}
