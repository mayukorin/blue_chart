package controllers.persons;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Person;

/**
 * Servlet implementation class PersonsEditServlet
 */
@WebServlet("/persons/edit")
public class PersonsEditServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public PersonsEditServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        Person p = ((Person)request.getSession().getAttribute("login_person"));
        request.setAttribute("_token", request.getSession().getId());
        request.setAttribute("person",p);

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/persons/edit.jsp");
        rd.forward(request, response);


    }

}
