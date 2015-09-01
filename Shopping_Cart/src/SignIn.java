

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;




import model.ProductCart;
import model.ProductUser;

/**
 * Servlet implementation class SignIn
 */
@WebServlet("/SignIn")
public class SignIn extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SignIn() {
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
		if(request.getSession().getAttribute("cart")==null)
		{
			request.getSession().setAttribute("cart", Cart.getList());
			
		}
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String message="";
		EntityManager em = UtilPackage.DBUtil.getEmFactory().createEntityManager();
		try {

			String q="select t from ProductUser t where t.username='"+username+"'";

			TypedQuery<ProductUser>bq =em.createQuery(q,ProductUser.class);
			String pass =null;
			List<ProductUser> list=bq.getResultList();
		
			for(ProductUser temp:list){
				pass = temp.getPassword();
			}
			if(pass!=null && password!=null && pass.equals(password))
			{
				message+="Hello "+username;
				
				String q2="select t from ProductCart t where t.username='"+username+"'";
				TypedQuery<ProductCart>bq2 =em.createQuery(q2,ProductCart.class);
				List<ProductCart> list2=bq2.getResultList();
				ArrayList<CartItem> c = (ArrayList<CartItem>) request.getSession().getAttribute("cart");
				for(ProductCart temp:list2){
					CartItem citem =new CartItem();
					citem.set_Item_id(temp.getProductId().intValue());
					citem.setItem_name(temp.getProductName());
					citem.setItem_price(temp.getPrice().doubleValue());
					citem.setQuantity(temp.getProductQuantity().intValue());
					c.add(citem);
				}
				
				request.getSession().setAttribute("cart", c);

				request.setAttribute("message", message);
				request.getSession().setAttribute("username", username);
				request.getServletContext().getRequestDispatcher("/output.jsp").forward(request, response);
			}
			
		
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}

}
