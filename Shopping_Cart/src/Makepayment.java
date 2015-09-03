

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import UtilPackage.DBUtil;
import model.Product;
import model.ProductOrderHistory;
import model.ProductPaymentHistory;
import model.ProductUser;

/**
 * Servlet implementation class Makepayment
 */
@WebServlet("/Makepayment")
public class Makepayment extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Makepayment() {
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
		ArrayList<CartItem> list =(ArrayList<CartItem>) request.getSession().getAttribute("cart");
		String username = (String)request.getSession().getAttribute("username");
		Random r = new Random();
		String billing_address = request.getParameter("street");
		String city = request.getParameter("city");
		String state = request.getParameter("state");
		String card_number = request.getParameter("cardnumber");
		String s_city="";
		String s_state="";
		String s_address="";
		double payment_amount = (double)request.getSession().getAttribute("payment_amount");
	
		int order_number = 9999 + r.nextInt();
		int order_number_2 = 1 +  r.nextInt();
		int order_num = order_number + order_number_2;
	
		EntityManager em = UtilPackage.DBUtil.getEmFactory().createEntityManager();
		try {

			String q="select t from ProductUser t where t.username ='"+username+"'";
			//System.out.println(q);
			TypedQuery<ProductUser>bq =em.createQuery(q,ProductUser.class);

			List<ProductUser> list2=bq.getResultList();

			for(ProductUser temp:list2){
				s_address = temp.getStreetaddress();
				s_city = temp.getCity();
				s_state =temp.getState();	
			}
			
		
			//insert into order history
			for (CartItem temp : list) {
				ProductOrderHistory po =new ProductOrderHistory();
				po.setProductName(temp.item_name);
				po.setProductPrice(new BigDecimal(temp.getItem_price()));
				po.setProductQuantity(temp.getQuantity()+"");
				po.setOrederId(new BigDecimal(order_num));
				po.setUserName(username);
				DBUtil.insert_in_Cart(po);
			}
			//insert in payment
			
			ProductPaymentHistory ph = new ProductPaymentHistory();
			ph.setBillingAddress(billing_address);
			ph.setBillingState(state);
			ph.setCardUsed(new BigDecimal(card_number));
			ph.setOrderId(new BigDecimal(order_num));
			ph.setPaymentAmount(new BigDecimal(payment_amount));
			ph.setShipiingCity(s_city);
			ph.setShippingAddress(s_address);
			ph.setShippingState(s_state);
			ph.setUsername(username);
			DBUtil.insert_in_Cart(ph);
			
			
			DBUtil.delete_cart(username);
			
			
			request.getSession().setAttribute("cart", null);
			
			String message ="Payment Made!";
			request.getSession().setAttribute("message", message);
		 request.getServletContext().getRequestDispatcher("/output.jsp").forward(request, response);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
	
	}

}
