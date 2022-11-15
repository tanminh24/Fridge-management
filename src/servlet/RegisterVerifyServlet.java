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

@WebServlet("/register-verify")
public class RegisterVerifyServlet extends HttpServlet{

	AccountService accountService = new AccountService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		
		if(session.getAttribute("usernameR")==null) {
			req.getRequestDispatcher("/WEB-INF/view/ErorrSentMailPage.jsp").forward(req, resp);
		}
		
		Account acc = accountService.getByUserName(session.getAttribute("usernameR").toString());
		acc.setVerify(true);
		
		if(accountService.update(acc)) {
			session.removeAttribute("isSentCodeR");
			session.removeAttribute("verifyCodeR");
			session.removeAttribute("usernameR");
			
			resp.setContentType("text/html;charset=UTF-8");
			resp.getWriter().println("<script type=\"text/javascript\">");
			resp.getWriter().println("alert('Tài khoản đã được xác thực');");
			resp.getWriter().println("location.replace('/PH18449_ASM/login');");
			resp.getWriter().println("</script>");
		}else {
			resp.getWriter().println("<script type=\"text/javascript\">");
			resp.getWriter().println("alert('Lỗi!');");
			resp.getWriter().println("location.replace('/PH18449_ASM/register');");
			resp.getWriter().println("</script>");
		}
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPost(req, resp);
	}

}
