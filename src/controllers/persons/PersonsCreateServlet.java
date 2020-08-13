package controllers.persons;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Person;
import models.validators.PersonValidator;
import utils.DBUtil;
import utils.EncryptUtil;

/**
 * Servlet implementation class PersonsCreateServlet
 */
@WebServlet("/persons/create")
public class PersonsCreateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public PersonsCreateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        String _token=(String)request.getParameter("_token");

        if (_token != null && _token.equals(request.getSession().getId())) {

            EntityManager em = DBUtil.createEntityManager();

            Person p = new Person();

            p.setCode(request.getParameter("code"));
            p.setName(request.getParameter("name"));

            p.setPassword(
                    EncryptUtil.getPasswordEncrypt(request.getParameter("password"),(String)this.getServletContext().getAttribute("salt")));

            Timestamp currentTime = new Timestamp(System.currentTimeMillis());
            p.setCreated_at(currentTime);
            p.setUpdated_at(currentTime);

            List<String> errors = PersonValidator.validate(p, true, true);


            if (errors.size() > 0) {
                em.close();


                request.setAttribute("_token", request.getSession().getId());

                request.setAttribute("person", p);
                request.setAttribute("errors", errors);

                RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/persons/new.jsp");
                rd.forward(request, response);
            } else {

                em.getTransaction().begin();
                em.persist(p);
                em.getTransaction().commit();
                em.close();

                request.getSession().setAttribute("flush","登録が完了しました。");

                response.sendRedirect(request.getContextPath()+"/login");
            }
        }
    }

}
