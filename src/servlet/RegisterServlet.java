package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import model.Account;
import model.AccountRole;
import repository.AccountRepository;
import service.AccountService;
import service.AuthenticationService;
import utility.HibernateUtil;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

	AccountService accountService = new AccountService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/WEB-INF/view/RegisterPage.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=UTF-8");

		HttpSession session = req.getSession();

		String username = req.getParameter("username");
		String name = req.getParameter("name");
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		String rePassword = req.getParameter("rePassword");

		// Validate
		if (username == null || name == null || email == null || password == null || rePassword == null) {
			req.setAttribute("message", "Không để trống thông tin");
			req.getRequestDispatcher("/WEB-INF/view/RegisterPage.jsp").forward(req, resp);
			return;
		}

		if (username.trim().isEmpty() || name.trim().isEmpty() || email.trim().isEmpty() || password.trim().isEmpty()
				|| rePassword.trim().isEmpty()) {
			req.setAttribute("message", "Không để trống thông tin");
			req.getRequestDispatcher("/WEB-INF/view/RegisterPage.jsp").forward(req, resp);
			return;
		}

		// validate email
		if (!email.matches("^\\w+@\\w+(\\.\\w+){1,2}+")) {
			req.setAttribute("message", "Email không đúng định dạng!");
			req.getRequestDispatcher("/WEB-INF/view/RegisterPage.jsp").forward(req, resp);
			return;
		}

		// validate password
		if (!password.matches("^(?=.*\\d)[a-z\\d]{8,16}+")) {
			req.setAttribute("message", "Mật khẩu từ 8- 16 ký tự, chứa ký tự số và không chứa ký tự viết hoa!");
			req.getRequestDispatcher("/WEB-INF/view/RegisterPage.jsp").forward(req, resp);
			return;
		}

		if (!password.equalsIgnoreCase(rePassword)) {
			req.setAttribute("message", "Mật khẩu xác nhận không khớp!");
			req.getRequestDispatcher("/WEB-INF/view/RegisterPage.jsp").forward(req, resp);
			return;
		}

		Account checkAcc = accountService.getByUserName(username);
		if (checkAcc != null) {
			req.setAttribute("message", "Tên tài khoản đã tồn tại!");
			req.getRequestDispatcher("/WEB-INF/view/RegisterPage.jsp").forward(req, resp);
			return;
		}

		Account acc = new Account();
		acc.setUsername(username);
		acc.setName(name);
		acc.setEmail(email);
		acc.setPassword(rePassword);
		acc.setRole(AccountRole.USER);
		acc.setVerify(false);

		if (accountService.create(acc)) {

			String verifyCodeR = accountService.sendEmailRegister(email);

			if (verifyCodeR != null) {
				session.setAttribute("isSentCodeR", true);
				session.setAttribute("verifyCodeR", verifyCodeR);
				session.setAttribute("usernameR", username);

				resp.getWriter().println("<script type=\"text/javascript\">");
				resp.getWriter().println("alert('Đăng ký thành công!');");
				resp.getWriter().println("</script>");
				req.getRequestDispatcher("/WEB-INF/view/RegisterVerifyPage.jsp").forward(req, resp);
			} else {
				resp.getWriter().println("<script type=\"text/javascript\">");
				resp.getWriter().println("alert('Lỗi!');");
				resp.getWriter().println("location.replace('/PH18449_ASM/register');");
				resp.getWriter().println("</script>");
			}

		}
	}

}
