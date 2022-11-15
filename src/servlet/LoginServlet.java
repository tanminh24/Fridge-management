package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Account;
import model.AccountRole;
import service.AccountService;
import service.AuthenticationService;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

	private AuthenticationService authenticationService = new AuthenticationService();
	AccountService accountService = new AccountService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession ss = req.getSession();
		if (ss.getAttribute("role") != null) {
			req.getRequestDispatcher("/WEB-INF/view/ErorrLoginPage.jsp").forward(req, resp);
		}else {
			req.getRequestDispatcher("/WEB-INF/view/LoginPage.jsp").forward(req, resp);
		}
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=UTF-8");
		
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		Account account = authenticationService.authenticate(username, password);
		HttpSession session = req.getSession();

		if (account == null) {
			session.setAttribute("erorr", "Tài khoản hoặc mật khẩu không chính xác!");
			resp.sendRedirect("/PH18449_ASM/login");
		} else {

			// xác thực tài khoản mới được vào
			if (account.getVerify() == false) {
				String verifyCodeR = accountService.sendEmailRegister(account.getEmail());
				session.setAttribute("isSentCodeR", true);
				session.setAttribute("verifyCodeR", verifyCodeR);
				session.setAttribute("usernameR", username);
				session.setAttribute("emailR", account.getEmail());
				
				req.getRequestDispatcher("/WEB-INF/view/RegisterVerifyPage.jsp").forward(req, resp);
			} else {

				session.setAttribute("username", account.getUsername());
				session.setAttribute("name", account.getName());
				session.setAttribute("role", account.getRole());
				session.removeAttribute("erorr");

				resp.sendRedirect("/PH18449_ASM/tu-lanh");
			}
		}
	}
}
