

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import UtilPackage.DBUtil;
import model.TwitterUser2;

/**
 * Servlet implementation class validate
 */
@WebServlet("/Validate")
public class Validate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Validate() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		 EntityTransaction trans = em.getTransaction();
		String username =request.getParameter("username");
		String password =request.getParameter("password");
		

		
		if(password!=null && username!=null)
		{
			String q="SELECT c FROM TwitterUser2 c WHERE c.name = '"+username+"'";
			TypedQuery<TwitterUser2>bq =em.createQuery(q,TwitterUser2.class);
			List<TwitterUser2> list=bq.getResultList();
			String pass=null;
			for(TwitterUser2 temp:list)
			pass=temp.getPassword();
		
			if(pass==null)
			{
				getServletContext().getRequestDispatcher("/index.html").forward(request, response);
			}
			else if(pass.equals(password)){
				request.getSession().setAttribute("username", username);
				getServletContext().getRequestDispatcher("/addcomment.html").forward(request, response);
			}
		}
		else
		{
			getServletContext().getRequestDispatcher("/index.html").forward(request, response);
		}
		 em.close();
	}
		 

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
