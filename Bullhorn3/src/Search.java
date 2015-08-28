

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import UtilPackage.DBUtil;
import model.Mytwitter;

/**
 * Servlet implementation class Search
 */
@WebServlet("/Search")
public class Search extends HttpServlet implements Servlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Search() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String message="";
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		try {

			String search = request.getParameter("search");
			String q="select t from Mytwitter t where t.comments LIKE \"%"+search+"%\"";

			TypedQuery<Mytwitter>bq =em.createQuery(q,Mytwitter.class);

			List<Mytwitter> list=bq.getResultList();
			message+="<table class=\"table table-hover\"><tr><td><b>User Name </td><td><b>Comments</td></tr>";
			for(Mytwitter temp:list){
				message+="<td>"+temp.getComments()+"</td><td>"+temp.getName()+"</td></tr>";
			}
			message+="</table>";
			request.setAttribute("message", message);

		 getServletContext().getRequestDispatcher("/output.jsp").forward(request, response);
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
