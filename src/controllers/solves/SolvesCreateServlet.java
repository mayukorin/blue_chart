package controllers.solves;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

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
import models.validators.SolveValidator;
import utils.DBUtil;

/**
 * Servlet implementation class SolvesCreateServlet
 */
@WebServlet("/solves/create")
public class SolvesCreateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public SolvesCreateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        LocalTime tt = null;
        LocalTime st = null;

        String _token = (String) request.getParameter("_token");
        if (_token != null && _token.equals(request.getSession().getId())) {
            EntityManager em = DBUtil.createEntityManager();

            Solve2 s2 = new Solve2();

            Person p = ((Person)request.getSession().getAttribute("login_person"));
            s2.setPerson(p);

            Problem pp = (Problem) request.getSession().getAttribute("problem");
            s2.setProblem(pp);

            if (!request.getParameter("targettime").equals("")) {
                tt = LocalTime.parse(request.getParameter("targettime"),DateTimeFormatter.ofPattern("HH:mm:ss"));
                s2.setTargettime(tt);
            }

            if (!request.getParameter("solvetime").equals("")) {
                st = LocalTime.parse(request.getParameter("solvetime"),DateTimeFormatter.ofPattern("HH:mm:ss"));
                s2.setSolvetime(st);
            }




            s2.setDay(request.getParameter("day"));

            s2.setContent(request.getParameter("content"));

            ///目標時間と解答時間のどちらか小さいか
            if (s2.getSolvetime() != null) {
                if (st.isBefore(tt)) {
                    //実際の時時間の方が、目標時間より速い場合
                    s2.setRate(0.5);
                } else {
                    s2.setRate(1.5);
                }
            }


            List<String> errors = SolveValidator.validate(s2);

            if (errors.size() >0) {
                em.close();

                request.setAttribute("_token", request.getSession().getId());
                request.setAttribute("solve", s2);
                request.setAttribute("errors", errors);

                RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/solves/new.jsp");
                rd.forward(request, response);
            } else {

                try {
                    SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy-MM-dd");
                    Date date =  sdFormat.parse(s2.getDay());
                    s2.setDate(date);
                } catch (java.text.ParseException e) {

                    e.printStackTrace();
                }
                em.getTransaction().begin();
                em.persist(s2);
                em.getTransaction().commit();
                em.close();

                request.getSession().setAttribute("flush", "登録が完了しました。");

                response.sendRedirect(request.getContextPath() + "/problems/show");
            }


        }
    }

}
