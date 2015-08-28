

import java.io.IOException;



import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



/**
 * Servlet implementation class AddMyComments
 */
@WebServlet("/AddMyComments")
public class AddMyComments extends HttpServlet {
	private static final long serialVersionUID = 1L;
     String message="";  
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddMyComments() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		message="";
		EntityManager em = UtilPackage.DBUtil.getEmFactory().createEntityManager();
		try {
			String comments =request.getParameter("comments");
			String username =(String) request.getSession().getAttribute("username");
			
	
			model.Mytwitter user = new model.Mytwitter();
			
			user.setComments(comments);
			user.setName(username);
			UtilPackage.DBUtil.insert(user);
			

		 getServletContext().getRequestDispatcher("/ViewComments").forward(request, response);

		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
