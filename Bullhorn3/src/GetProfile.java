

import java.io.IOException;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Mytwitter;
import model.TwitterUser2;
import UtilPackage.DBUtil;

/**
 * Servlet implementation class GetProfile
 */
@WebServlet("/GetProfile")
public class GetProfile extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetProfile() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String message="";
		String name =request.getParameter("name");
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		try {


			String q="select t from TwitterUser2 t where t.name="+name;

			TypedQuery<TwitterUser2>bq =em.createQuery(q,TwitterUser2.class);

			List<TwitterUser2> list=bq.getResultList();
			message+="<table class=\"table table-hover\">";

			for(TwitterUser2 temp:list){
				String name1 = temp.getName();
				String motto = temp.getMotto();
				Date dt =(Date) temp.getJoinDate();
				SimpleDateFormat df =new SimpleDateFormat("MM/dd/yyyy");
				String dString = df.format(dt);
			message+="<tr><td><b>NAME</td><tr><td>"+name1+"</td></tr><tr><td><b>My Motto </td></tr><tr><td>"+motto+"</td></tr><tr><td>"+dString+"</td></tr>";
			}
			message+="</table>";
			
			//get POSTS

			String q2="select t from Mytwitter t where t.name="+name;

			TypedQuery<Mytwitter>bq2 =em.createQuery(q2,Mytwitter.class);

			List<Mytwitter> list2=bq2.getResultList();
			message+="<table class=\"table table-hover\">";
			message+="<tr><td><b>MY Comments</td></tr>";
			for(Mytwitter temp:list2){
				String comments = temp.getComments();

			message+="<tr><td>"+comments+"</td></tr>";
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
