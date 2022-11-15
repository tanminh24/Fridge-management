<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Chi tiết sản phẩm</title>
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
	<section class="background-radial-gradient overflow-hidden" style="height: 667px">
		<div class="container text-center text-lg-start my-5">
			<c:if test="${sessionScope.role == null}">
				<div class="row">
					<div class="col-4" style="text-align: left">
						<a class="btn btn-secondary" href="login" role="button">Đăng
							nhập</a>
					</div>
					<div class="col-4" style="text-align: center">
					</div>
					<div class="col-4" style="text-align: right">
						<a class="btn btn-primary" href="tu-lanh" role="button">Trang
							chủ</a>
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
						<h3 style="color: gray;">${sessionScope.name}</h3>
					</div>
					<div class="col-4" style="text-align: right">
						<a class="btn btn-primary" href="tu-lanh" role="button">Trang
							chủ</a>
					</div>
				</div>
				<hr>
			</c:if>
			<div class="card bg-glass">
				<c:if test="${empty tuLanh}">
					<h1>Không có dữ liệu sản phẩm</h1>
				</c:if>
				<c:if test="${not empty tuLanh}">
					<h1 style="margin-left: 30%">Chi tiết thông tin sản phẩm</h1>
					<table class="table table-bordered table-hover text-center"
						style="vertical-align: middle;">
						<thead class="table-secondary">
							<tr style="vertical-align: middle;">
								<th>Thuộc tính</th>
								<th>Giá trị</th>
								<th>Thuộc tính</th>
								<th>Giá trị</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<th scope="row">Mã</th>
								<td>${tuLanh.id}</td>
								<th scope="row">Hãng</th>
								<td>${tuLanh.hang}</td>
							</tr>
							<tr>
								<th scope="row">Tên</th>
								<td>${tuLanh.ten}</td>
								<th scope="row">Phương pháp làm đông</th>
								<td><c:if test="${tuLanh.phuongPhapLamDong == true}">Trực tiếp</c:if>
									<c:if test="${tuLanh.phuongPhapLamDong == false}">Gián tiếp</c:if>
								</td>
							</tr>
							<tr>
								<th scope="row">Dung tích</th>
								<td>${tuLanh.dungTich}L</td>
								<th scope="row">Số buồng</th>
								<td>${tuLanh.soBuong}</td>
							</tr>
							<tr>
								<th scope="row">Mô tả</th>
								<td>${tuLanh.moTa}</td>
								<th scope="row">Giá thành</th>
								<td><fmt:setLocale value="vi_VN" /> <fmt:formatNumber
										value="${tuLanh.gia}" type="currency" /></td>
							</tr>
							<tr>
								<th scope="row">Số lượng</th>
								<td>${tuLanh.soLuong}</td>
								<th scope="row">Trạng thái</th>
								<td><c:if test="${tuLanh.trangThai == false}">Ngưng bán</c:if>
									<c:if test="${tuLanh.trangThai == true}">Đang bán</c:if></td>
							</tr>
							<tr>
								<th scope="row">Người tạo</th>
								<td>${tuLanh.nguoiTao}</td>
								<th scope="row">Thời điểm tạo</th>
								<td><fmt:formatDate value="${tuLanh.thoiDiemTao}"
										pattern="HH:mm:ss dd/MM/yyyy" /></td>
							</tr>
							<tr>
								<th scope="row">Người thay đổi cuối</th>
								<td>${tuLanh.nguoiThayDoiCuoi}</td>
								<th scope="row">Thời điểm thay đổi cuối</th>
								<td><fmt:formatDate value="${tuLanh.thoiDiemThayDoiCuoi}"
										pattern="HH:mm:ss dd/MM/yyyy" /></td>
							</tr>
						</tbody>
					</table>
				</c:if>
			</div>
		</div>
	</section>
</body>
</html>