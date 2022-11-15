<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Đăng nhập</title>
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
    <div class="container px-4 py-5 px-md-5 text-center text-lg-start my-5">
        <div class="row gx-lg-5 align-items-center mb-5">

            <div class="col-lg-6 mb-5 mb-lg-0" style="z-index: 10;">
                <h1 class="my-5 display-5 fw-bold ls-tight" style="color: hsl(218, 81%, 95%)">
                    Đăng nhập <br />
                </h1>
                <h2 class="mb-4 opacity-70" style="color: hsl(218, 81%, 75%);">
                    Công ty TNHH Một Mình Phong
                    <span style="color: hsl(218, 81%, 85%); font-size: 25px">Quản lý sản phẩm tủ lạnh</span>
                </h2>
            </div>

            <div class="col-lg-6 mb-5 mb-lg-0 position-relative">
                <div id="radius-shape-1" class="position-absolute rounded-circle shadow-5-strong"></div>
                <div id="radius-shape-2" class="position-absolute shadow-5-strong"></div>

                <div class="card bg-glass">
                    <div class="card-body px-4 py-5 px-md-5">
                        <form action="/PH18449_ASM/login" method="POST">
                            <div class="form-outline mb-4">
                                <label class="form-label">Tài khoản</label>
                                <input name="username" type="text" class="form-control" />
                            </div>
                            <div class="form-outline mb-4">
                                <label class="form-label">Mật khẩu</label>
                                <input name="password" type="password" class="form-control" />
                            </div>
                            <c:if test="${sessionScope.erorr != null}">
                                <div style="margin-top: 15px" class="alert alert-danger d-flex align-items-center" role="alert">
                                    <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" fill="currentColor" class="bi bi-exclamation-triangle-fill flex-shrink-0 me-2" viewBox="0 0 16 16" role="img" aria-label="Warning:">
                    	            	<path d="M8.982 1.566a1.13 1.13 0 0 0-1.96 0L.165 13.233c-.457.778.091 1.767.98 1.767h13.713c.889 0 1.438-.99.98-1.767L8.982 1.566zM8 5c.535 0 .954.462.9.995l-.35 3.507a.552.552 0 0 1-1.1 0L7.1 5.995A.905.905 0 0 1 8 5zm.002 6a1 1 0 1 1 0 2 1 1 0 0 1 0-2z" />
                    				</svg>
                                    <div>${sessionScope.erorr}</div>
                                </div>
                            </c:if>
                            <button type="submit" class="btn btn-primary btn-block mb-4" style="width: 100%;">
                                Đăng nhập
                            </button>
                            <div class="row form-outline mb-4">
                                <label class="form-label col-8">Chưa có tài khoản? 
                                    <a class="link-primary" href="register">Đăng ký</a>
                                </label>
                                <a style="margin-right: 0px;" class="link-secondary col-4" href="forgot-password">Quên mật khẩu?</a>
                            </div>
                        </form>
                    </div>
                </div>
            </div>

        </div>
    </div>
</section>
</body>
</html>