

import java.io.IOException;
import java.math.BigDecimal;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import UtilPackage.DBUtil;
import model.ProductUserCredit;

/**
 * Servlet implementation class StoreCredit
 */
@WebServlet("/StoreCredit")
public class StoreCredit extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StoreCredit() {
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
		String username =request.getParameter("username");
		String amount =request.getParameter("amount");
		ProductUserCredit puc = new ProductUserCredit();
		puc.setCredit(new BigDecimal(amount));
		puc.setUsername(username);
		
		DBUtil.insert_in_user(puc);
		String message = "Credit Stored!";

		request.setAttribute("message", message);

request.getServletContext().getRequestDispatcher("/output.jsp")
		.forward(request, response);
	}

}
