package servlet;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.TuLanh;
import service.TuLanhService;

@WebServlet("/sua-tu-lanh")
public class EditTuLanhServlet extends HttpServlet {

	TuLanhService tuLanhService = new TuLanhService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if (req.getParameter("id") == null) {
			req.getRequestDispatcher("/WEB-INF/view/ListTuLanhPage.jsp").forward(req, resp);
			return;
		}

		int id = Integer.parseInt(req.getParameter("id"));
		TuLanh tuLanh = tuLanhService.getById(id);

		req.setAttribute("tuLanh", tuLanh);
		req.getRequestDispatcher("/WEB-INF/view/EditTuLanhPage.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=UTF-8");

		int id = Integer.parseInt(req.getParameter("id"));
		TuLanh tuLanh = tuLanhService.getById(id);
		HttpSession session = req.getSession();

		try {
			String hang = req.getParameter("hang");
			String ten = req.getParameter("ten");
			Boolean ppLamDong = Boolean.parseBoolean(req.getParameter("phuongPhapLamDong"));
			int dungTich = Integer.parseInt(req.getParameter("dungTich"));
			int soBuong = Integer.parseInt(req.getParameter("soBuong"));
			String moTa = req.getParameter("moTa");
			BigDecimal gia = BigDecimal.valueOf(Double.parseDouble(req.getParameter("gia")));
			int soLuong = Integer.parseInt(req.getParameter("soLuong"));
			Boolean trangThai = Boolean.parseBoolean(req.getParameter("trangThai"));

			if (dungTich < 0 || soBuong < 0 || Double.parseDouble(req.getParameter("gia")) < 0 || soLuong < 0) {
				resp.getWriter().println("<script type=\"text/javascript\">");
				resp.getWriter().println("alert('Vui lòng nhập các giá trị số > 0!');");
				resp.getWriter().println("location.replace('/PH18449_ASM/sua-tu-lanh');");
				resp.getWriter().println("</script>");
				return;
			}

			if (!hang.equals("Samsung")) {
				if (!hang.equals("Toshiba")) {
					if (!hang.equals("Panasonic")) {
						resp.getWriter().println("<script type=\"text/javascript\">");
						resp.getWriter().println("alert('Vui lòng nhập đúng thông tin hãng sản phẩm!');");
						resp.getWriter().println("location.replace('/PH18449_ASM/sua-tu-lanh');");
						resp.getWriter().println("</script>");
						return;
					}
				}
			}

			tuLanh.setHang(hang);
			tuLanh.setTen(ten);
			tuLanh.setPhuongPhapLamDong(ppLamDong);
			tuLanh.setDungTich(dungTich);
			tuLanh.setSoBuong(soBuong);
			tuLanh.setMoTa(moTa);
			tuLanh.setGia(gia);
			tuLanh.setSoLuong(soLuong);
			tuLanh.setNguoiThayDoiCuoi(session.getAttribute("username").toString());
			tuLanh.setThoiDiemThayDoiCuoi(new Date());
			tuLanh.setTrangThai(trangThai);

			if (tuLanhService.update(tuLanh)) {
				resp.getWriter().println("<script type=\"text/javascript\">");
				resp.getWriter().println("alert('Cập nhật thành công!');");
				resp.getWriter().println("location.replace('/PH18449_ASM/tu-lanh');");
				resp.getWriter().println("</script>");
			} else {
				resp.getWriter().println("<script type=\"text/javascript\">");
				resp.getWriter().println("alert('Cập nhật thất bại!');");
				resp.getWriter().println("location.replace('/PH18449_ASM/sua-tu-lanh');");
				resp.getWriter().println("</script>");
			}

		} catch (

		Exception e) {
			resp.getWriter().println("<script type=\"text/javascript\">");
			resp.getWriter().println("alert('Vui lòng nhập đúng và đủ định dạng các giá trị!');");
			resp.getWriter().println("location.replace('/PH18449_ASM/sua-tu-lanh');");
			resp.getWriter().println("</script>");
			return;
		}

	}

}
