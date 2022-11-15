<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Thêm sản phẩm</title>
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
				<h1 style="margin-top: 15px; margin-left: 30%;">Bổ sung sản
					phẩm tủ lạnh</h1>
				<form class="row g-3" action="/PH18449_ASM/them-tu-lanh"
					method="POST">
					<div class="col-md-4" style="margin-left: 60px;">
						<label class="form-label">Hãng</label> <select class="form-select"
							name="hang">
							<option value="Samsung" selected>Samsung</option>
							<option value="Toshiba">Toshiba</option>
							<option value="Panasonic">Panasonic</option>
						</select>
					</div>
					<div class="col-md-4">
						<label class="form-label">Tên</label> <input class="form-control"
							name="ten" required>
					</div>
					<div class="col-md-3">
						<label class="form-label">Phương pháp làm đông</label>
						<div class="form-check">
							<input type="radio" name="phuongPhapLamDong" value="true"
								id="ppLamDong1"><label style="margin-left: 4px;"
								for="ppLamDong1">Trực tiếp</label> <input type="radio"
								name="phuongPhapLamDong" value="false" id="ppLamDong2" checked><label
								style="margin-left: 4px;" for="ppLamDong2">Gián tiếp</label>
						</div>
					</div>
					<div class="col-md-4" style="margin-left: 60px;">
						<label class="form-label">Dung tích</label> <input
							class="form-control" name="dungTich" type="number" required>
					</div>
					<div class="col-md-4">
						<label class="form-label">Số buồng</label> <input
							class="form-control" name="soBuong" type="number" required>
					</div>
					<div class="col-md-3">
						<label class="form-label">Trạng thái</label>
						<div class="form-check">
							<input type="radio" name="trangThai" value="True" id="trangThai1"><label
								style="margin-left: 4px;" for="trangThai1">Đang bán</label> <input
								type="radio" name="trangThai" value="False" id="trangThai2"
								checked><label style="margin-left: 4px;"
								for="trangThai2">Ngưng bán</label>
						</div>
					</div>
					<div class="col-11" style="margin-left: 60px;">
						<label class="form-label">Mô tả</label>
						<textarea class="form-control" name="moTa"></textarea>
					</div>
					<div class="col-md-4" style="margin-left: 60px;">
						<label class="form-label">Giá thành</label> <input
							class="form-control" name="gia" type="number" required>
					</div>
					<div class="col-md-4">
						<label class="form-label">Số lượng</label> <input
							class="form-control" name="soLuong" type="number" required>
					</div>
					<div class="col-md-3">
						<label class="form-label">Người tạo</label> <input
							class="form-control" name="nguoiTao"
							value="${sessionScope.username}" disabled>
					</div>
					<div class="col-12" style="margin-left: 60px; margin-bottom: 25px;">
						<button type="submit" class="btn btn-primary">Thêm</button>
					</div>
				</form>
			</div>
		</div>
	</section>
</body>
</html>