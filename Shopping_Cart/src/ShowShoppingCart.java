
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ShowShoppingCart
 */
@WebServlet("/ShowShoppingCart")
public class ShowShoppingCart extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ShowShoppingCart() {
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

		
		try {
			ArrayList<CartItem> ct = (ArrayList<CartItem>) request.getSession().getAttribute("cart");
			double total =0;
			String message = "";
			message += "<table class=\"table table-hover\"  style=\"width:60%\"><tr><td><b>Item Name </td><td><b>Price </td><td><b>Quantity</td></tr>";
		if(ct!=null){
			for (CartItem temp : ct) {
				total=total+(temp.item_price*temp.quantity);
				message += "<tr><td>" + temp.item_name + "</td>" + "<td>"
						+ temp.item_price + "</td>" + "<td>" + temp.quantity
						+ "</td></tr>";

			}}
			message += "</table> <br> <b>Oreder Total = "+total;
			
			message+="<div class=\"btn-group btn-group-justified\" role=\"group\">"+
			 "<a href=\"Checkout\"><div class=\"btn-group\" role=\"group\">"+
			    "<button type=\"button\" class=\"btn btn-default\">Checkout</button>"+
			 "</div>"+
			  "<a href=\"BrowseAllItems\"><div class=\"btn-group\" role=\"group\"><button type=\"button\" class=\"btn btn-default\">Continue Shopping</button>"+
			  "</div></div>";
			request.setAttribute("message", message);
			request.getServletContext().getRequestDispatcher("/output.jsp").forward(
					request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
