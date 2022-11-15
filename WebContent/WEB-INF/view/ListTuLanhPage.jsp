<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="model.AccountRole"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Trang chủ</title>
<link rel="icon" type="image/x-icon" href="">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.8.1/font/bootstrap-icons.css">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
<link rel="stylesheet" href="./css/Css.css" />
</head>
<body>
	<section class="background-radial-gradient overflow-hidden">
		<div class="container text-center text-lg-start my-5">

			<c:if test="${sessionScope.role == null}">
				<div class="row">
					<div class="col-4" style="text-align: left">
						<a class="btn btn-secondary" href="login" role="button">Đăng
							nhập</a>
					</div>
				</div>
				<hr>
			</c:if>

			<c:if test="${sessionScope.role != null}">
				<div class="row">
					<div class="col-4" style="text-align: left">
						<a class="btn btn-secondary" href="logout" role="button">Đăng
							xuất</a>
					</div>
					<div class="col-4" style="text-align: center">
						<h3 style="color: gray">${sessionScope.name}</h3>
					</div>
					<div class="col-4" style="text-align: right">
						<a class="btn btn-primary" href="them-tu-lanh" role="button">Thêm</a>
					</div>
				</div>
				<hr>
			</c:if>

			<div class="card bg-glass">
				<div class="card-body px-md-5">
					<div class="row gx-lg-5 align-items-center mb-5"
						style="text-align: center">
						<c:if test="${empty dsTuLanh}">
							<h1>Không có dữ liệu!</h1>
						</c:if>
						<c:if test="${not empty dsTuLanh}">
							<h1>Danh sách sản phẩm</h1>
							<hr>

							<form id="myForm" action="/PH18449_ASM/tu-lanh" method="GET"
								style="text-align: left" class="row">
								<div class="col-2 mb-3">
									<label class="form-label">Tên</label> <input type="text"
										name="name" class="form-control">
								</div>
								<div class="col-3 mb-3">
									<label class="form-label">Giá</label>
									<div class="row">
										<div class="col">
											<input type="number" name="price-min" class="form-control"
												placeholder="Từ">
										</div>
										<div class="col">
											<input type="number" name="price-max" class="form-control"
												placeholder="Đến">
										</div>
									</div>
								</div>
								<div class="col-2 mb-3">
									<label class="form-label">Số lượng</label> <select
										class="form-select" name="quantity" id="inputQuantity">
										<option value="all" selected>Sắp xếp</option>
										<option value="asc">Tăng dần</option>
										<option value="desc">Giảm dần</option>
									</select>
								</div>
								<c:if test="${sessionScope.role == AccountRole.ADMIN}">
									<div class="col-2 mb-3">
										<label class="form-label">Trạng thái</label> <select
											class="form-select" name="deleted">
											<option value="all" selected>Tất cả</option>
											<option value="true">Đang bán</option>
											<option value="false">Ngưng bán</option>
										</select>
									</div>
								</c:if>
								<div class="col-2 mb-3">
									<button type="submit" class="btn btn-primary" form="myForm"
										style="margin-top: 30px">
										<i class="fa-solid fa-check"></i> Lọc
									</button>
								</div>
							</form>
							<div class="table-responsive">
								<table class="table table-bordered table-hover text-center"
									style="vertical-align: middle;">
									<thead class="table-secondary">
										<tr style="vertical-align: middle;">
											<th>Mã</th>
											<th>Hãng</th>
											<th>Tên</th>
											<th>Pp làm đông</th>
											<th>Dung tích</th>
											<th>Số buồng</th>
											<th>Giá thành</th>
											<th>Số lượng</th>
											<th>Người thay đổi cuối</th>
											<th>Thời điểm thay đổi cuối</th>
											<th>Trạng thái</th>
											<c:if test="${sessionScope.role != null}">
												<th>Hành động</th>
											</c:if>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${dsTuLanh}" var="tuLanh">
											<tr>
												<td>${tuLanh.id}</td>
												<td>${tuLanh.hang}</td>
												<td>${tuLanh.ten}</td>
												<td><c:if test="${tuLanh.phuongPhapLamDong == true}">Trực tiếp</c:if>
													<c:if test="${tuLanh.phuongPhapLamDong == false}">Gián tiếp</c:if>
												</td>
												<td>${tuLanh.dungTich}L</td>
												<td>${tuLanh.soBuong}</td>
												<td><fmt:setLocale value="vi_VN" /> <fmt:formatNumber
														value="${tuLanh.gia}" type="currency" /></td>
												<td>${tuLanh.soLuong}</td>
												<td>${tuLanh.nguoiThayDoiCuoi}</td>
												<td><fmt:formatDate
														value="${tuLanh.thoiDiemThayDoiCuoi}"
														pattern="HH:mm:ss dd/MM/yyyy" /></td>
												<td><c:if test="${tuLanh.trangThai == true}">Đang bán</c:if>
													<c:if test="${tuLanh.trangThai == false}">Ngưng bán</c:if></td>
												<c:if test="${sessionScope.role != null}">
													<td><a
														style="margin-bottom: 5px; font-size: 14px; width: 75px"
														class="btn btn-outline-secondary"
														href="tu-lanh?id=${tuLanh.id}" role="button">Chi tiết</a>
														<a
														style="margin-bottom: 5px; font-size: 14px; width: 75px"
														class="btn btn-outline-warning"
														href="sua-tu-lanh?id=${tuLanh.id}" role="button">Sửa</a> <c:if
															test="${role == AccountRole.ADMIN}">
															<a style="font-size: 14px; width: 75px"
																class="btn btn-outline-danger"
																href="xoa-tu-lanh?id=${tuLanh.id}" role="button"
																onclick="return confirm('Xác nhận xóa!')">Xóa</a>
														</c:if></td>
												</c:if>
											</tr>
										</c:forEach>
									</tbody>
								</table>
								<form action="/PH18449_ASM/tu-lanh" method="GET">
									<div class="row text-center">
										<nav aria-label="Page navigation example">
											<ul class="pagination justify-content-center">
												<li class="page-item"><a
													href="tu-lanh?start-position=0&end-position=${endPosition}" type="submit"
													class="page-link bi bi-chevron-double-left"></a></li>
												<li class="page-item"><a 
													href="tu-lanh?start-position=${startPosition - 6}&end-position=${endPosition}" type="submit"
													class="page-link bi bi-chevron-left"></a></li>
												<li class="page-item"><a 
													href="tu-lanh?start-position=${startPosition + 6}&end-position=${endPosition}" type="submit"
													class="page-link bi bi-chevron-right"></a></li>
												<li class="page-item"><a 
													href="tu-lanh?start-position=${finalPage}&end-position=${endPosition}" type="submit"
													class="page-link bi bi-chevron-double-right"></a></li>
											</ul>
										</nav>
									</div>
								</form>
							</div>
						</c:if>
					</div>
				</div>
			</div>

		</div>
	</section>
</body>
</html>