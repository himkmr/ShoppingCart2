

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.ProductPaymentHistory;
import model.ProductUser;

/**
 * Servlet implementation class History
 */
@WebServlet("/History")
public class History extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public History() {
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
		String message="<table class=\"table table-hover\" style=\"width:60%\"><tr><td><b>Payment Id</b></td><td>------------</td><td><b>Payment Amount</b></td></tr>";
		String username =(String)request.getSession().getAttribute("username");
		EntityManager em = UtilPackage.DBUtil.getEmFactory().createEntityManager();
		try {

			String q="select t from ProductPaymentHistory t where t.username ='"+username+"'";
//			/System.out.println(q);
			TypedQuery<ProductPaymentHistory>bq =em.createQuery(q,ProductPaymentHistory.class);

			List<ProductPaymentHistory> list2=bq.getResultList();

			for(ProductPaymentHistory temp:list2){
				message+="<tr><td><a href=\"getOrderDetails?orderid="+temp.getOrderId()+"\">"+temp.getOrderId()+"</a></td>"+
			"<td>------------</td><td>"+temp.getPaymentAmount()+"</td></tr>";
			}
			message+="</table>";
		}
		catch(Exception e)
		{
			e.printStackTrace();
			}
		

			request.setAttribute("message", message);

	request.getServletContext().getRequestDispatcher("/output.jsp")
			.forward(request, response);
	}
}
