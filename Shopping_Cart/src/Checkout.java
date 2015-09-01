

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import UtilPackage.DBUtil;

/**
 * Servlet implementation class Checkout
 */
@WebServlet("/Checkout")
public class Checkout extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Checkout() {
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
		try {
			ArrayList<CartItem> ct = (ArrayList<CartItem>) request.getSession().getAttribute("cart");
			double total =0;
			String message = "";
			int item_count = 0;
			message += "<table class=\"table table-hover\"  style=\"width:60%\"><tr><td><b>Item Name </td><td><b>Price </td><td><b>Quantity</td></tr>";
			double tax=0;
			double grand_total=0;
			if(ct!=null){
			for (CartItem temp : ct) {
				item_count++;
				total=total+(temp.item_price*temp.quantity);
				message += "<tr><td>" + temp.item_name + "</td>" + "<td>"
						+ temp.item_price + "</td>" + "<td>" + temp.quantity
						+ "</td></tr>";

			}
			tax = total*6/100;
			grand_total =total+tax;
			}
			message += "</table> <br> <b>Oreder Total = "+total;
			message += "</table> <br> <b>Tax = "+tax;
			message += "</table> <br> <b>GrandTotal = "+grand_total;
			
			String username =(String) request.getSession().getAttribute("username");
			DBUtil.delete_cart(username);
			
			request.setAttribute("message", message);

			request.getServletContext().getRequestDispatcher("/output.jsp")
					.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
