

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import UtilPackage.DBUtil;

/**
 * Servlet implementation class RemoveItem
 */
@WebServlet("/RemoveItem")
public class RemoveItem extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RemoveItem() {
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
		String id =request.getParameter("id");
		String username = (String)request.getSession().getAttribute("username");
		ArrayList<CartItem> ct = (ArrayList<CartItem>) request.getSession().getAttribute("cart");
		ArrayList<CartItem> ct2 =new ArrayList<CartItem>(ct);
		for (CartItem temp : ct2) {
		if(id.equals(""+temp.item_id))
		{
				ct.remove(temp);
		}
		}
		request.getSession().setAttribute("cart", ct);
		request.getServletContext().getRequestDispatcher("/ShowShoppingCart").forward(
				request, response);
	}

}
