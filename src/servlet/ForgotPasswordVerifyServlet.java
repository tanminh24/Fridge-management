package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Account;
import service.AccountService;

@WebServlet("/forgot-password-verify")
public class ForgotPasswordVerifyServlet extends HttpServlet {

	AccountService accountService = new AccountService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession ss = req.getSession();
		if (ss.getAttribute("isSent") != null) {
			req.getRequestDispatcher("/WEB-INF/view/ForgotPasswordVerifyPage.jsp").forward(req, resp);
		} else {
			req.getRequestDispatcher("/WEB-INF/view/ErorrSentMailPage.jsp").forward(req, resp);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=UTF-8");
		HttpSession ss = req.getSession();

		if (ss.getAttribute("isSent") == null) {
			req.getRequestDispatcher("/WEB-INF/view/ErorrSentMailPage.jsp").forward(req, resp);
			return;
		}

		String verifyCode = req.getParameter("verifyCode");
		String password = req.getParameter("password");
		String rePassword = req.getParameter("rePassword");

		// Validate
		if (verifyCode == null || rePassword == null || password == null) {
			req.setAttribute("message", "Không để trống thông tin");
			req.getRequestDispatcher("/WEB-INF/view/ForgotPasswordVerifyPage.jsp").forward(req, resp);
			return;
		}

		if (verifyCode.trim().isEmpty() || password.trim().isEmpty() || rePassword.trim().isEmpty()) {
			req.setAttribute("message", "Không để trống thông tin");
			req.getRequestDispatcher("/WEB-INF/view/ForgotPasswordVerifyPage.jsp").forward(req, resp);
			return;
		}

		if (!ss.getAttribute("verifyCode").equals(verifyCode)) {
			req.setAttribute("message", "Mã xác thực không đúng!");
			req.getRequestDispatcher("/WEB-INF/view/ForgotPasswordVerifyPage.jsp").forward(req, resp);
			return;
		}

		if (!password.matches("^(?=.*\\d)[a-z\\d]{8,16}+")) {
			req.setAttribute("message", "Mật khẩu từ 8- 16 ký tự, chứa ký tự số và không chứa ký tự viết hoa!");
			req.getRequestDispatcher("/WEB-INF/view/ForgotPasswordVerifyPage.jsp").forward(req, resp);
			return;
		}

		if (!password.equalsIgnoreCase(rePassword)) {
			req.setAttribute("message", "Mật khẩu xác nhận không khớp!");
			req.getRequestDispatcher("/WEB-INF/view/ForgotPasswordVerifyPage.jsp").forward(req, resp);
			return;
		}

		if ((System.currentTimeMillis() - ((Long) ss.getAttribute("timeStart"))) > 32000) {
			ss.removeAttribute("verifyCode");
			resp.getWriter().println("<script type=\"text/javascript\">");
			resp.getWriter().println("alert('Mã xác thực hết hạn!');");
			resp.getWriter().println("location.replace('/PH18449_ASM/forgot-password');");
			resp.getWriter().println("</script>");
			return;
		}

		Account acc = accountService.getByUserName(ss.getAttribute("username").toString());
		acc.setPassword(rePassword);

		if (accountService.update(acc)) {
			ss.removeAttribute("verifyCode");
			resp.getWriter().println("<script type=\"text/javascript\">");
			resp.getWriter().println("alert('Cập nhật mật khẩu mới thành công!');");
			resp.getWriter().println("location.replace('/PH18449_ASM/logout');");
			resp.getWriter().println("</script>");
		} else {
			resp.getWriter().println("<script type=\"text/javascript\">");
			resp.getWriter().println("alert('Tạo thất bại!');");
			resp.getWriter().println("location.replace('/PH18449_ASM/forgot-password-verify');");
			resp.getWriter().println("</script>");
		}

	}

}
