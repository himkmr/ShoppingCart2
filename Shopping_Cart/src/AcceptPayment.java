

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.ProductUser;

/**
 * Servlet implementation class AcceptPayment
 */
@WebServlet("/AcceptPayment")
public class AcceptPayment extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AcceptPayment() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = (String) request.getSession().getAttribute("username");
		String message="<br>";

		EntityManager em = UtilPackage.DBUtil.getEmFactory().createEntityManager();
		try {

			String q="select t from ProductUser t where t.username='"+username+"'";
			TypedQuery<ProductUser>bq =em.createQuery(q,ProductUser.class);
			String pass =null;
			List<ProductUser> list=bq.getResultList();
			String cname ="";
			String address ="";
			for(ProductUser temp:list){
				cname =temp.getFullname();
				System.out.println(cname);
				address = temp.getStreetaddress();
				
				message+="<form style=\"width:30%;\" class=\"form-horizontal\" action=\"PlaceOrder\" id=\"myform2\">"+
						"<div class=\"form-group\" >"+
						"<label class=\"control-label col-sm-10\" for=\"name\">Shipping Address</label></div>"+
				"<div class=\"form-group\" >"+
				    "<label class=\"control-label col-sm-2\" for=\"name\">Name</label>"+
				    "<div class=\"col-sm-10\">"+
				    "<input type=\"text\" name=\"cname\" class=\"form-control\" value ="+cname+">"+
				  "</div></div>"+
				  "<div class=\"form-group\">"+
				    "<label class=\"control-label col-sm-2\" for=\"text\">Street</label>"+
				    "<div class=\"col-sm-10\">"+
				    "<input type=\"text\" class=\"form-control\" name=\"street\" value='"+address+"'>"+
				  "</div></div>"+
				  "<div class=\"form-group\">"+
				    "<label class=\"control-label col-sm-2\" for=\"text\" >City</label>"+
				    "<div class=\"col-sm-10\">"+
				    "<input type=\"text\" class=\"form-control\" name=\"city\" value="+temp.getCity()+">"+
				  "</div></div>"+
				  "<div class=\"form-group\">"+
				    "<label class=\"control-label col-sm-2\" for=\"text\">State</label>"+
				    "<div class=\"col-sm-10\">"+
				    "<input type=\"text\" class=\"form-control\" name=\"state\" value="+temp.getState()+">"+
				  "</div></div>"+
				"</form>";
								
			}
			//message=message+ cname+"<br>"+address;
			request.setAttribute("message", message);

			request.getServletContext().getRequestDispatcher("/payment.jsp")
					.forward(request, response);
		}catch(Exception e)
		{
			e.printStackTrace();
		}

}
}
