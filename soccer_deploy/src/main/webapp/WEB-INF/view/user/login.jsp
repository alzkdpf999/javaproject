<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form"   uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insertere</title>

<link
	href="https://fonts.googleapis.com/css2?family=Montserrat:wght@400;700&display=swap"
	rel="stylesheet">
<link rel="stylesheet" href="/fonts/icomoon/style.css">
<link rel="stylesheet" href="/css/bootstrap/bootstrap.css">
<link rel="stylesheet" href="/css/jquery-ui.css">
<link rel="stylesheet" href="/css/owl.carousel.min.css">
<link rel="stylesheet" href="/css/owl.theme.default.min.css">
<link rel="stylesheet" href="/css/owl.theme.default.min.css">

<link rel="stylesheet" href="/css/jquery.fancybox.min.css">

<link rel="stylesheet" href="/css/bootstrap-datepicker.css">

<link rel="stylesheet" href="/fonts/flaticon/font/flaticon.css">

<link rel="stylesheet" href="/css/aos.css">

<link rel="stylesheet" href="/css/style.css">

</head>

<style>
#zero {
	position: relative;
	width: 1700px;
	height: 750px;
	padding: 4%;
	padding-left: 16%;
	padding-top : 8%;
}

#deploy {
	position: relative;
	display: flex;
	width: 100%;
	height: 100%;
}

#deploy_one {
	width: 10%;
	height: 100%;
	margin: 2%;
	padding: 1%;
}

#deploy_two {
	width: 80%;
	height: 100%;
	border-radius: 30px;
	border-style: solid;
	border-color: rgb(224, 224, 224);
	padding-left: 5%;
}

#deploy_three {
	width: 10%;
	height: 100%;
	margin: 2%;
	padding: 1%;
}

#image1 {
	width: 450px;
	height: 180px;
	margin-left: 5%;
	margin-right: 5%;
	margin-top: 3.3%;
	filter: invert() drop-shadow(5px 5px 5px #000);
	float: right;
	/* box-shadow: 5px 5px 5px #000; */
}

#deploy_two_In {
	positon: relative;
	display: flex;
	flex-directory: column;
	width: 100%;
	height: 100%;
}

#deploy_two_In_one {
	width: 60%;
	height: 100%;
}

#deploy_two_In_two {
	width: 40%;
	height: 100%;
	padding: 6.7%;
}

#to_center_footer {
	text-align: center;
}
.field-error {
  color: #ff0000;
}
</style>

<body>

<jsp:include page="/WEB-INF/view/include/header.jsp"></jsp:include>

	<!-- 여기까지가 기본구성.  -->
	<!-- 필요하면 지워도 되는데 책임은 못짐. -->

	<div id="zero">

		<div id="deploy">
		<div id="deploy_one"></div>
			<div id="deploy_two">

				<div class="site-section">
					<div class="container">
						<div id="deploy_two_In">
							<div id="deploy_two_In_one">
								<h3 class="mb-3">Login</h3>
								<br> <br>
								<div class="row">
									<div class="col-lg-7">
									<!-- loginForm Start -->
										<form:form modelAttribute="loginForm" class="form-signin" action="/user/login" method="post">
											<div class="form-group">
												<input type="text" class="form-control" placeholder="EMAIL" name="email" value="${loginForm.email}">
											</div>
											<div class="form-group">
												<input type="password" class="form-control"	placeholder="PASSWORD" name="passwd">
											</div>
												<form:errors path = "*" element = "div" cssClass="field-error"/>
      											<input type="hidden" name="redirect" value="${header.referer}">
											<div class="form-group">
												<button class="btn btn-primary py-3 px-3" type="submit">Login</button>
												<!-- <input type="submit" class="btn btn-primary py-3 px-3" value="Login" style="margin-right: 10px;"> -->
												<a href="/user/regist"><input type="button" class="btn btn-primary py-3 px-3" value="Sign Up"></a>
											</div>
											
										</form:form>
									<!-- loginForm End -->
									</div>
								</div>
							</div>

							<div id="deploy_two_In_two">
								<img src="/images/title2.png" id="image1">
							</div>
						</div>
					</div>
				</div>
			</div>
			<div id="deploy_three"></div>
		</div>

	</div>


	<footer class="footer-section">
		<div class="container">
			<div id="to_center_footer">
				<div class="row">
					<div class="col-lg-3">
						<div class="widget mb-3">
							<h3>News</h3>
							<ul class="list-unstyled links">
								<li><a href="#">All</a></li>
								<li><a href="#">Club News</a></li>
								<li><a href="#">Media Center</a></li>
								<li><a href="#">Video</a></li>
								<li><a href="#">RSS</a></li>
							</ul>
						</div>
					</div>
					<div class="col-lg-3">
						<div class="widget mb-3">
							<h3>Tickets</h3>
							<ul class="list-unstyled links">
								<li><a href="#">Online Ticket</a></li>
								<li><a href="#">Payment and Prices</a></li>
								<li><a href="#">Contact &amp; Booking</a></li>
								<li><a href="#">Tickets</a></li>
								<li><a href="#">Coupon</a></li>
							</ul>
						</div>
					</div>
					<div class="col-lg-3">
						<div class="widget mb-3">
							<h3>Matches</h3>
							<ul class="list-unstyled links">
								<li><a href="#">Standings</a></li>
								<li><a href="#">World Cup</a></li>
								<li><a href="#">La Lega</a></li>
								<li><a href="#">Hyper Cup</a></li>
								<li><a href="#">World League</a></li>
							</ul>
						</div>
					</div>

					<div class="col-lg-3">
						<div class="widget mb-3">
							<h3>Social</h3>
							<ul class="list-unstyled links">
								<li><a href="#">Twitter</a></li>
								<li><a href="#">Facebook</a></li>
								<li><a href="#">Instagram</a></li>
								<li><a href="#">Youtube</a></li>
							</ul>
						</div>
					</div>

				</div>

				<div class="row text-center">
					<div class="col-md-12">
						<div class=" pt-5">
							<p>
								<!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
								Copyright &copy;
								<script>
									document.write(new Date().getFullYear());
								</script>
								All rights reserved | This template is made with <i
									class="icon-heart" aria-hidden="true"></i> by <a
									href="https://colorlib.com" target="_blank">Colorlib</a>
								<!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
							</p>
						</div>
					</div>

				</div>
			</div>
		</div>
	</footer>




	<script src="js/jquery-3.3.1.min.js"></script>
	<script src="js/jquery-migrate-3.0.1.min.js"></script>
	<script src="js/jquery-ui.js"></script>
	<script src="js/popper.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/owl.carousel.min.js"></script>
	<script src="js/jquery.stellar.min.js"></script>
	<script src="js/jquery.countdown.min.js"></script>
	<script src="js/bootstrap-datepicker.min.js"></script>
	<script src="js/jquery.easing.1.3.js"></script>
	<script src="js/aos.js"></script>
	<script src="js/jquery.fancybox.min.js"></script>
	<script src="js/jquery.sticky.js"></script>
	<script src="js/jquery.mb.YTPlayer.min.js"></script>
	<script src="js/main.js"></script>


</body>
</html>