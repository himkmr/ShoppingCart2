
import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Product;

/**
 * Servlet implementation class GetDescription
 */
@WebServlet("/GetDescription")
public class GetDescription extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GetDescription() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String message = "";
		try {
			EntityManager em = UtilPackage.DBUtil.getEmFactory()
					.createEntityManager();
			String search = request.getParameter("name");
			String q = "select t from Product t where t.name='" + search + "'";
			// System.out.println(q);
			TypedQuery<Product> bq = em.createQuery(q, Product.class);
		
			List<Product> list = bq.getResultList();
			
			for (Product temp : list) {
				message+="<img src=\""+temp.getId()+".jpg\" style=\"position:absolute; left:150; height:200; width:250;\">";
				message += "<table class=\"table table-hover\" style=\"width:60%\">";
				message += "<tr><td style=\"text-align:center;\"><b>Item ID </td></tr>"
						+ "<tr><td style=\"text-align:center;\">"+ temp.getId()+"</td></tr>"+
						"<tr><td style=\"text-align:center;\"><b>Item Name </td></tr>"
						+ "<tr><tr><td style=\"text-align:center;\">"+ temp.getName() +"</td></tr>"+
						"<tr><td style=\"text-align:center;\"><b>Description </td></tr>"
						+ "<tr><td style=\"text-align:center;\">"+ temp.getDescription() +"</td></tr>"+
						"<tr><td style=\"text-align:center;\"><b>Price</td></tr>"
						+ "<tr><td style=\"text-align:center;\">"+ temp.getPrice() +"</td></tr>"+
						"<tr><td style=\"text-align:center;\"><b>Quantity Available</td></tr>"
						+ "<tr><td style=\"text-align:center;\">"+ temp.getQuantityAvailable() +"</td></tr>";
			}
			
			message+="</table>";
			request.setAttribute("message", message);

		 request.getServletContext().getRequestDispatcher("/output.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
