package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.AccountRole;

@WebFilter(urlPatterns = {"/sua-tu-lanh", "/them-tu-lanh"})
public class EditFilter implements Filter {

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		HttpSession session = req.getSession();

		if (session.getAttribute("role") != null) {
			chain.doFilter(request, response);
		} else {
			resp.sendRedirect("/PH18449_ASM/tu-lanh");
		}

	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}
	
}
