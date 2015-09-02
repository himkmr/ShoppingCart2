

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import UtilPackage.DBUtil;
import model.Product;
import model.ProductCart;

/**
 * Servlet implementation class SignOut
 */
@WebServlet("/SignOut")
public class SignOut extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SignOut() {
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
		//System.out.println("in signout out post");
		ArrayList<CartItem> ct = (ArrayList<CartItem>) request.getSession().getAttribute("cart");
		if(ct==null)
			System.out.println("session cart is null");
		String username =(String) request.getSession().getAttribute("username");
		DBUtil.delete_cart(username);
		
		
		for(CartItem item:ct)
		{
			//System.out.println("in loop");
			ProductCart p =new ProductCart();
			p.setProductId(new BigDecimal(item.get_Item_id()));
			p.setProductQuantity(new BigDecimal(item.getQuantity()));
			p.setUsername((String) request.getSession().getAttribute("username"));
			p.setProductName(item.getItem_name());
			p.setPrice(new BigDecimal(item.getItem_price()));
			
			DBUtil.insert_in_Cart(p);
			
		}
		request.getSession().setAttribute("cart", null);
		request.getSession().setAttribute("username", null);
		request.getSession().removeAttribute("cart");
		request.getSession().removeAttribute("username");
		HttpSession session = request.getSession(false);
		if (session != null) {
		    session.invalidate();
		}
		response.setContentType("text/html");
		request.getServletContext().getRequestDispatcher("/index.html").forward(request, response);
	}

}
