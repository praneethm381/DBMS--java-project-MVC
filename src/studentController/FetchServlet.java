package studentController;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import studentModel.StudentBean;

/**
 * Servlet implementation class FetchServlet
 */
@WebServlet("/FetchServlet")
public class FetchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public FetchServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		try {

			HttpSession session = request.getSession(true);
			response.setContentType("text/html");

			StudentBean user = new StudentBean();
			user.setDept_name(request.getParameter("dept-name"));
			user.setTotal_credits(request.getParameter("total-credits"));

			ArrayList<StudentBean> al = new ArrayList<StudentBean>();
			al = StudentDAO.fetch(user);
			if (!al.isEmpty()) {
				session.setAttribute("al", al);
				response.sendRedirect("studentDetail.jsp");
			} else {
				response.sendRedirect("invalid.jsp");
			}

		} catch (Exception theException) {
			theException.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
