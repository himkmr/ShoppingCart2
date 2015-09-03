

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.ProductUserCredit;
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
			//System.out.println("Checkout");
			ArrayList<CartItem> ct = (ArrayList<CartItem>) request.getSession().getAttribute("cart");
			double total =0;
			String message = "";
			int item_count = 0;
			message += "<table class=\"table table-hover\"  style=\"width:60%\"><tr><td><b>Item Name </td><td><b>Price </td><td><b>Quantity</td></tr>";
			double tax=0;
			double grand_total=0;
			double grand_total_after=0;
			double dcredit=0;
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
			String credit  =""+request.getSession().getAttribute("credit");
			dcredit = Double.parseDouble(credit);
			
			if(dcredit > grand_total)
			{
				dcredit = dcredit - grand_total;
				grand_total_after = 0;
				
			}
			else
			{
				grand_total_after = grand_total -dcredit;
				dcredit = 0;
			}
			}
			message += "</table> <br> <b>Oreder Total = "+total;
			message += " <br> <b>Tax = "+tax;
			message += " <br> <b>GrandTotal = "+grand_total;
			message += "<br> <b>Total after deducting credits = "+grand_total_after;
			message += "<br> <b>Total availbale Credit= "+dcredit;
			message+="<br><a href=\"AcceptPayment\"> Make Payment</a>";
			request.getSession().setAttribute("payment_amount", grand_total_after);
			String username =(String) request.getSession().getAttribute("username");
			
			
			ProductUserCredit puc =new ProductUserCredit();
			puc.setCredit(new BigDecimal(dcredit));
			puc.setUsername(username);
			DBUtil.update_ser_credit(puc);

	
			request.setAttribute("message", message);

			request.getServletContext().getRequestDispatcher("/output.jsp")
					.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

