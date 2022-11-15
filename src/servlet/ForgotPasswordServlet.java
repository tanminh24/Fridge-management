package servlet;

import java.io.IOException;
import java.util.Properties;
import java.util.Random;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
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
 
@WebServlet("/forgot-password")
public class ForgotPasswordServlet extends HttpServlet {

	AccountService accountService = new AccountService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession ss = req.getSession();
		if (ss.getAttribute("role") != null) {
			req.getRequestDispatcher("/WEB-INF/view/ErorrLoginPage.jsp").forward(req, resp);
		}else {
			req.getRequestDispatcher("/WEB-INF/view/ForgotPasswordPage.jsp").forward(req, resp);
		}
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		HttpSession session = req.getSession();
		
		final String fromEmail = "minhlhtph18449@fpt.edu.vn";
		final String password = "240101minh";
		
		final String username = req.getParameter("username");
		final String email= req.getParameter("email");
	
		Account acc = accountService.getByUserName(username);
		
		if (acc == null) {
			req.setAttribute("message", "Bạn đã nhập sai thông tin tài khoản!");
			req.getRequestDispatcher("/WEB-INF/view/ForgotPasswordPage.jsp").forward(req, resp);
			return;
		}
		
		if(!acc.getEmail().equals(email)) {
			req.setAttribute("message", "Bạn đã nhập sai thông tin tài khoản!");
			req.getRequestDispatcher("/WEB-INF/view/ForgotPasswordPage.jsp").forward(req, resp);
			return;
		}
		
		String verifyCode = accountService.sendEmailForgotPassword(email);
		
		if(verifyCode != null) {
			session.setAttribute("isSent", true);
			session.setAttribute("verifyCode", verifyCode);
			session.setAttribute("username", username);
			session.setAttribute("timeStart", System.currentTimeMillis());
			resp.sendRedirect("/PH18449_ASM/forgot-password-verify");
		}else {
			req.setAttribute("message", "Lỗi không gửi được email!");
			req.getRequestDispatcher("/WEB-INF/view/ForgotPasswordPage.jsp").forward(req, resp);
		}
		
	}
}
