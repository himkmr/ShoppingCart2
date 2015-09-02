
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AddToCart
 */
@WebServlet("/AddToCart")
public class AddToCart extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddToCart() {
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
		if (request.getSession().getAttribute("username") == null
				|| request.getSession().getAttribute("username") == "") {
			String message = "Not Signed In!";
			request.setAttribute("message", message);
			response.setContentType("text/html");
			request.getServletContext().getRequestDispatcher("/output.jsp")
					.forward(request, response);
		} else {
			CartItem item = CartItem.getItem(request.getParameter("name"),
					Double.parseDouble(request.getParameter("price")),
					Integer.parseInt(request.getParameter("quantity")),
					Integer.parseInt(request.getParameter("item_id")));
			ArrayList<CartItem> ct = (ArrayList<CartItem>) request.getSession().getAttribute("cart");
			ct.add(item);
			request.getSession().setAttribute("cart", ct);
			response.setContentType("text/html");
			request.getServletContext().getRequestDispatcher("/BrowseAllItems")
					.forward(request, response);
		}
	}

}
