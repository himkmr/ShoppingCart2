

import java.io.IOException;
import java.math.BigDecimal;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import UtilPackage.DBUtil;
import model.ProductComment;

/**
 * Servlet implementation class AddComments
 */
@WebServlet("/AddComments")
public class AddComments extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddComments() {
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
		String name =(String)request.getSession().getAttribute("name");
		long pid = (long)request.getSession().getAttribute("pid");
		String rating =(String)request.getParameter("inlineRadioOptions");
		String comments =(String)request.getParameter("comments");
		
		ProductComment pc = new ProductComment();
		pc.setComments(comments);
		pc.setProductId(""+pid);
		pc.setRatings(new BigDecimal(rating));
		pc.setUserName(name);
		DBUtil.insert_in_Comments(pc);


		request.getServletContext().getRequestDispatcher("/BrowseAllItems")
				.forward(request, response);
		
		
	}

}
