

import java.io.IOException;
import java.util.Date;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SignUp
 */
@WebServlet("/SignUp")
public class SignUp extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SignUp() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		EntityManager em = UtilPackage.DBUtil.getEmFactory().createEntityManager();
		try {
			String motto =request.getParameter("motto");
			String username =(String) request.getParameter("username");
			String password =(String) request.getParameter("password");
	
			model.TwitterUser2 user = new model.TwitterUser2();
			
			user.setMotto(motto);
			user.setName(username);
			user.setPassword(password);
			user.setJoinDate(new Date());
			
			
			System.out.println(user.getMotto());
			System.out.println(user.getName());
			System.out.println(user.getPassword());
			System.out.println(user.getJoinDate());
			
			UtilPackage.DBUtil.insert2(user);
			

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
		// TODO Auto-generated method stub
	}

}
