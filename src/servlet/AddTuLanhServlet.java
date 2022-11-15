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

@WebServlet("/them-tu-lanh")
public class AddTuLanhServlet extends HttpServlet {

	TuLanhService tuLanhService = new TuLanhService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/WEB-INF/view/AddTuLanhPage.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=UTF-8");
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
			
			if (dungTich < 0 || soBuong < 0 || Double.parseDouble(req.getParameter("gia")) < 0 || soLuong < 0) {
				resp.getWriter().println("<script type=\"text/javascript\">");
				resp.getWriter().println("alert('Vui lòng nhập các giá trị số > 0!');");
				resp.getWriter().println("location.replace('/PH18449_ASM/them-tu-lanh');");
				resp.getWriter().println("</script>");
				return;
			}

			TuLanh tl = new TuLanh();
			tl.setHang(hang);
			tl.setTen(ten);
			tl.setPhuongPhapLamDong(ppLamDong);
			tl.setDungTich(dungTich);
			tl.setSoBuong(soBuong);
			tl.setMoTa(moTa);
			tl.setGia(gia);
			tl.setSoLuong(soLuong);
			tl.setNguoiTao(session.getAttribute("username").toString());
			tl.setNguoiThayDoiCuoi(session.getAttribute("username").toString());
			tl.setThoiDiemTao(new Date());
			tl.setThoiDiemThayDoiCuoi(new Date());
			tl.setTrangThai(trangThai);

			if (tuLanhService.create(tl)) {
				resp.getWriter().println("<script type=\"text/javascript\">");
				resp.getWriter().println("alert('Tạo thành công!');");
				resp.getWriter().println("location.replace('/PH18449_ASM/tu-lanh');");
				resp.getWriter().println("</script>");
			} else {
				resp.getWriter().println("<script type=\"text/javascript\">");
				resp.getWriter().println("alert('Tạo thất bại!');");
				resp.getWriter().println("location.replace('/PH18449_ASM/them-tu-lanh');");
				resp.getWriter().println("</script>");
			}

		} catch (Exception e) {
			resp.getWriter().println("<script type=\"text/javascript\">");
			resp.getWriter().println("alert('Vui lòng nhập đúng và đủ định dạng các giá trị!');");
			resp.getWriter().println("location.replace('/PH18449_ASM/them-tu-lanh');");
			resp.getWriter().println("</script>");
			return;
		}

	}

}
