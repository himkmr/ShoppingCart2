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
import model.ProductComment;

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
		message += getProduct(request, response);
		message += getComments(request, response);

		request.setAttribute("message", message);

		request.getServletContext().getRequestDispatcher("/output.jsp")
				.forward(request, response);

	}

	
	
	
	private String getProduct(HttpServletRequest request, HttpServletResponse response) {
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
				message += "<img src=\""
						+ temp.getId()
						+ ".jpg\" style=\"position:relative; left:10%;\">";
				message += "<table class=\"table table-hover\" style=\"width:60%\">";
				message += "<tr><td style=\"text-align:center;\"><b>Item ID </td></tr>"
						+ "<tr><td style=\"text-align:center;\">"
						+ temp.getId()
						+ "</td></tr>"
						+ "<tr><td style=\"text-align:center;\"><b>Item Name </td></tr>"
						+ "<tr><tr><td style=\"text-align:center;\">"
						+ temp.getName()
						+ "</td></tr>"
						+ "<tr><td style=\"text-align:center;\"><b>Description </td></tr>"
						+ "<tr><td style=\"text-align:center;\">"
						+ temp.getDescription()
						+ "</td></tr>"
						+ "<tr><td style=\"text-align:center;\"><b>Price</td></tr>"
						+ "<tr><td style=\"text-align:center;\">"
						+ temp.getPrice()
						+ "</td></tr>"
						+ "<tr><td style=\"text-align:center;\"><b>Quantity Available</td></tr>"
						+ "<tr><td style=\"text-align:center;\">"
						+ temp.getQuantityAvailable() + "</td></tr>";
				request.getSession().setAttribute("pid", temp.getId());
			}

			message += "</table>";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return message;
	}

	private String getComments(HttpServletRequest request,
			HttpServletResponse response) {
		String message = "";
		try {
			message = message
					+ "<form style=\"width:30%\" action =\"AddComments\"><b>Rate this Product</b><br><label class=\"radio-inline\">"
					+ "<input type=\"radio\" name=\"inlineRadioOptions\" id=\"inlineRadio1\" value=\"1\"> 1"
					+ "</label><label class=\"radio-inline\">"
					+ "<input type=\"radio\" name=\"inlineRadioOptions\" id=\"inlineRadio2\" value=\"2\"> 2"
					+ "</label><label class=\"radio-inline\">"
					+ "<input type=\"radio\" name=\"inlineRadioOptions\" id=\"inlineRadio3\" value=\"3\"> 3"
					+ "</label><label class=\"radio-inline\">"
					+ "<input type=\"radio\" name=\"inlineRadioOptions\" id=\"inlineRadio3\" value=\"3\"> 4"
					+ "</label><label class=\"radio-inline\">"
					+ "<input type=\"radio\" name=\"inlineRadioOptions\" id=\"inlineRadio3\" value=\"3\"> 5</label>"
					+ "<textarea class=\"form-control\" name =\"comments\" rows=\"3\"></textarea>"
					+ "<input type=\"submit\" value =\"Add Ratings\"></form>";

			EntityManager em2 = UtilPackage.DBUtil.getEmFactory()
					.createEntityManager();

			String q2 = "select t from ProductComment t where t.productId="
					+ request.getSession().getAttribute("pid");
			// System.out.println(q);
			TypedQuery<ProductComment> bq2 = em2.createQuery(q2,
					ProductComment.class);

			List<ProductComment> list2 = bq2.getResultList();
			message += "<br><br><h3>Comments</h3><br><table class=\"table table-hover\" style=\"width:100%\"><tr bgcolor=\"#C0C0C0\"><td><b>User </td><td><b>Rating </td><td><b>Comments</td></tr>";
			for (ProductComment temp2 : list2) {
				message += "<tr><td>" + temp2.getUserName() + "</td>";
				message += "<td>" + temp2.getRatings() + "</td>";
				message += "<td>" + temp2.getComments() + "</td></tr>";
			}
			message += "</table>";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return message;
	}
}
