package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.TuLanh;
import service.TuLanhService;

@WebServlet("/xoa-tu-lanh")
public class DeleteTuLanhServlet extends HttpServlet {
	
	TuLanhService tuLanhService = new TuLanhService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if (req.getParameter("id") == null) {
			req.getRequestDispatcher("/WEB-INF/view/ListTuLanhPage.jsp").forward(req, resp);
			return;
		}

		int id = Integer.parseInt(req.getParameter("id"));
		TuLanh tuLanh = tuLanhService.getById(id);
		if(tuLanh.getTrangThai()==false) {
			resp.getWriter().println("<script type=\"text/javascript\">");
			resp.getWriter().println("alert('Sản phẩm đã bị xóa rồi!');");
			resp.getWriter().println("location.replace('/PH18449_ASM/tu-lanh');");
			resp.getWriter().println("</script>");
			return;
		}else {
			tuLanh.setTrangThai(false);
			if (tuLanhService.update(tuLanh)) {
				resp.getWriter().println("<script type=\"text/javascript\">");
				resp.getWriter().println("alert('Xóa thành công!');");
				resp.getWriter().println("location.replace('/PH18449_ASM/tu-lanh');");
				resp.getWriter().println("</script>");
			} else {
				resp.getWriter().println("<script type=\"text/javascript\">");
				resp.getWriter().println("alert('Xóa thất bại!');");
				resp.getWriter().println("location.replace('/PH18449_ASM/tu-lanh');");
				resp.getWriter().println("</script>");
			}
		}
		
	}
	
}
