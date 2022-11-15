package servlet;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import javax.management.Query;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.AccountRole;
import model.TuLanh;
import service.TuLanhService;

@WebServlet("/tu-lanh")
public class TuLanhServlet extends HttpServlet {

	TuLanhService tuLanhService = new TuLanhService();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=UTF-8");
		HttpSession session = req.getSession();
		
		
		session.setAttribute("startPosition", 0);
		session.setAttribute("endPosition", 6);
		System.out.println("Set: "+ session.getAttribute("startPosition"));
		System.out.println("Set: "+ session.getAttribute("endPosition"));
		
		
//		long numberOfPages = new BigDecimal(tuLanhService.countTL()).divide(new BigDecimal(6), RoundingMode.UP).longValue();
		System.out.println("Số lượng tất cả sản phẩm: "+tuLanhService.countTL(null));
		System.out.println("Số lượng sản phẩm đang bán: "+tuLanhService.countTL(true));
		
//		session.setAttribute("page", 1);
//		session.setAttribute("numOfPages", numberOfPages);
//		System.out.println("Set: "+ session.getAttribute("page"));
//		System.out.println("Set: "+ session.getAttribute("numOfPages"));
		
		String id = req.getParameter("id");
		if (id == null) {
			
			String name = req.getParameter("name");
			String priceMin = req.getParameter("price-min");
			String priceMax = req.getParameter("price-max");
			String quantity = req.getParameter("quantity");
			String deleted = req.getParameter("deleted");
			
			String startPosition = req.getParameter("start-position");
			String endPosition = req.getParameter("end-position");
			
			BigDecimal numPriceMin = null;
			BigDecimal numPriceMax = null;
			Boolean isDeleted = null;
			Integer start = 0;
			Integer end = 6;
			
			System.out.println("Lấy từ jsp: " + startPosition);
			System.out.println("Lấy từ jsp: " + endPosition);
			if(startPosition != null && endPosition != null) {
				if(Float.parseFloat(startPosition)==-6) {
					start = 0;
				}else {
					start = Integer.parseInt(startPosition);
				}
				end = Integer.parseInt(endPosition);
				session.setAttribute("startPosition", start);
			}
			
			
			if (name == null) {
				name = "";
			}
			if (priceMin != null && priceMax != null) {
				try {
					numPriceMin = BigDecimal.valueOf(Double.parseDouble(priceMin));
					numPriceMax = BigDecimal.valueOf(Double.parseDouble(priceMax));
				} catch (Exception e) {
				}
			}
			if (quantity != null) {
				if (!quantity.equalsIgnoreCase("DESC") && !quantity.equalsIgnoreCase("ASC")) {
					quantity = null;
				}
			}
			if (deleted != null) {
				if (!deleted.equals("true") && !deleted.equals("false")) {
					isDeleted = null;
				} else if (deleted.equals("true")) {
					isDeleted = true;
				} else if (deleted.equals("false")) {
					isDeleted = false;
				}
			}
			
			List<TuLanh> dsTuLanh = null;
			System.out.println("Gán: "+start);
			System.out.println("Gán: "+end);
			if (session.getAttribute("role") == AccountRole.ADMIN) {
				int index = tuLanhService.countTL(null) % 6;
				if(index == 0) {
					session.setAttribute("finalPage", (tuLanhService.countTL(null)-6));
				}else {
					session.setAttribute("finalPage", (tuLanhService.countTL(null)-index));
				}
				if(start >= tuLanhService.countTL(null)) {
					start = (Integer) session.getAttribute("finalPage");
					session.setAttribute("startPosition", start);
				}
				dsTuLanh = tuLanhService.getByFilter(name, quantity, numPriceMin, numPriceMax, isDeleted, start, end);
			} else {
				int index = tuLanhService.countTL(true) % 6;
				if(index == 0) {
					session.setAttribute("finalPage", (tuLanhService.countTL(true)-6));
				}else {
					session.setAttribute("finalPage", (tuLanhService.countTL(true)-index));
				}
				if(start >= tuLanhService.countTL(true)) {
					start = (Integer) session.getAttribute("finalPage");
					session.setAttribute("startPosition", start);
				}
				dsTuLanh = tuLanhService.getByFilter(name, quantity, numPriceMin, numPriceMax, true, start, end);
			}
			session.setAttribute("dsTuLanh", dsTuLanh);
			req.getRequestDispatcher("/WEB-INF/view/ListTuLanhPage.jsp").forward(req, resp);
		} else {
			Integer id1 = Integer.parseInt(id);
			TuLanh tuLanh = tuLanhService.getById(id1);
			req.setAttribute("tuLanh", tuLanh);
			req.getRequestDispatcher("/WEB-INF/view/ChiTietTuLanhPage.jsp").forward(req, resp);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	}

}
