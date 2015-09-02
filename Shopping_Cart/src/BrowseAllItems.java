

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
 * Servlet implementation class BrowseAllItems
 */
@WebServlet("/BrowseAllItems")
public class BrowseAllItems extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BrowseAllItems() {
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
		
		String message="";
		EntityManager em = UtilPackage.DBUtil.getEmFactory().createEntityManager();
		try {

			String q="select t from Product t";
			//System.out.println(q);
			TypedQuery<Product>bq =em.createQuery(q,Product.class);

			List<Product> list=bq.getResultList();
			message+="<table class=\"table table-hover\" style=\"width:60%\"><tr bgcolor=\"#C0C0C0\"><td><b>Item Name </td><td><b>Description </td><td><b>Price</td><td><b>Quantity</td></tr>";
			for(Product temp:list){
				message+="<tr ><td><a href=\"GetDescription?name="+temp.getName() +"\">"+temp.getName()+"</a></td>";
				message+="<td>"+temp.getDescription()+"</td>"
						+"<td>"+temp.getPrice()+"</td><td>"
								+ "<form action=\"AddToCart\"><input type=\"hidden\" name=\"name\" value=\""+temp.getName()+"\">"+
								"<form action=\"AddToCart\"><input type=\"hidden\" name=\"item_id\" value=\""+temp.getId()+"\">"+
								"<input type=\"hidden\" name=\"price\" value=\""+temp.getPrice()+"\">"
								+"<input  name=\"quantity\">"
								+ "<input type=\"submit\" value=\"AddToCart\"></form></td></tr>";
			}
			message+="</table>";
			request.setAttribute("message", message);

		 request.getServletContext().getRequestDispatcher("/output.jsp").forward(request, response);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	}

