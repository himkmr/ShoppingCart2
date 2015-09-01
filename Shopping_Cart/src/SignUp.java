

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import UtilPackage.DBUtil;
import model.Product;
import model.ProductUser;

/**
 * Servlet implementation class SignUp
 */
@WebServlet("/SignUp")
public class SignUp extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SignUp() {
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

			String message="Successfully signed up, Happy Shopping!";
			String name = request.getParameter("name");
			String address = request.getParameter("address");
			String city = request.getParameter("city");
			String state = request.getParameter("state");
			String zip = request.getParameter("zip");
			String password = request.getParameter("password");
			String username = request.getParameter("username");
			String email = request.getParameter("email");
			
			
			System.out.println(name);
			System.out.println(address);
			System.out.println(city);
			System.out.println(state);
			System.out.println(zip);
			System.out.println(password);
			System.out.println(username);
			System.out.println(email);
			ProductUser puser =new ProductUser();
			puser.setCity(city);
			puser.setEmail(email);
			puser.setFullname(name);
			puser.setPassword(password);
			puser.setState(state);
			puser.setStreetaddress(address);
			puser.setUsername(username);
			puser.setZip(zip);
			
			DBUtil.insert(puser);
			request.setAttribute("message", message);
			request.getServletContext().getRequestDispatcher("/output.jsp").forward(request, response);
	}

}
