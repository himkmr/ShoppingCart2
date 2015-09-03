

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.ProductOrderHistory;
import model.ProductPaymentHistory;

/**
 * Servlet implementation class getOrderDetails
 */
@WebServlet("/getOrderDetails")
public class getOrderDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public getOrderDetails() {
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
		String message="<table class=\"table table-hover\" style=\"width:60%\"><tr><td><b>Product Name Id</b></td><td>Product Price</td><td><b>Quantity</b></td></tr>";
		String username =(String)request.getSession().getAttribute("username");
		String order_id = (String) request.getParameter("orderid");
		EntityManager em = UtilPackage.DBUtil.getEmFactory().createEntityManager();
		try {

			String q="select t from ProductOrderHistory t where t.orederId ="+order_id;
			//System.out.println(q);
			TypedQuery<ProductOrderHistory>bq =em.createQuery(q,ProductOrderHistory.class);

			List<ProductOrderHistory> list2=bq.getResultList();

			for(ProductOrderHistory temp:list2){
				message+="<tr><td>"+temp.getProductName()+"</td>"+
			"<td>"+temp.getProductPrice()+"</td><td>"+temp.getProductQuantity()+"</td></tr>";
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
