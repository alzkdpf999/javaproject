
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<head>
<title>Soccer &mdash; Website by Colorlib</title>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">


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

<link rel="stylesheet" href="/css/match.css">

</head>


<style>
#matchProfile{
	border-radius: 100%;
	width: 30px;
	height: 30px;
}
#matchProfile:hover{
	border: #F00 2px solid;
}

.table th, .table td {
    text-align: center;
}
</style>
<body>
<jsp:include page="/WEB-INF/view/include/header.jsp"></jsp:include>
	<div class="wrap bg-visual"
		style="background-image: url('/images/match.jpg'); background-position: center center; background-size: cover">
		<div class="container">
			<div class="row align-items-center">
				<div class="col-lg-5 mx-auto text-center">
					<h1 class="text-white">Players</h1>
				</div>
			</div>
		</div>
	</div>
					<div class="container" style="margin-top: 20px; margin-bottom: 20px;">
						<form class="row row-cols-lg-auto g-3 align-items-center">
							<div class="col-12">
								<p>검색된 회원 수 : ${userList.totalElements}</p>
							</div>
							
							<div class="col-12" style="float: right;">
								<input type="text" name="value">
								<button type="submit" class="btn btn-primary">검색</button>
							</div>
							
						</form>
						<table class="table custom-table">
							<thead>
								<tr>
									<th>이름</th>
								<th>나이</th>
								<th>포지션</th>
								<th>등번호</th>
								</tr>
							</thead>

							<tbody>

								<c:forEach items="${userList.content}" var="user" varStatus="status">
									<tr>
										<td class="text-white"><img src="/images/profile/${user.imgFileName}" id="matchProfile"><a href="/user/${user.id}"class="text-white">${user.name}</a></td>
										<td class="text-white">${user.age}</td>
									<td class="text-white">${user.position}</td>
									<td class="text-white">${user.backNum}</td>
									</tr>
								</c:forEach>

							</tbody>
						</table>
					
					<div class="navBar" style="margin: auto;">
					<nav aria-label="Page navigation example">
						<ul class="pagination justify-content-center">
							<li class="page-item"><a class="page-link" href="/user/list?page=0">처음으로</a></li>

							<c:forEach begin="${startBlockPage}" end="${endBlockPage}" var="i">
								<li class="page-item"><a class="page-link" href="/user/list/?page=${i-1}&value=${value}">${i}</a></li>
							</c:forEach>
							
							<li class="page-item"><a class="page-link" href="#">다음으로</a></li>
						</ul>
					</nav>
				</div>
			</div>
			
		

		<jsp:include page="/WEB-INF/view/include/footer.jsp"></jsp:include>


	<!-- .site-wrap -->

	<script src="/js/jquery-3.3.1.min.js"></script>
	<script src="/js/jquery-migrate-3.0.1.min.js"></script>
	<script src="/js/jquery-ui.js"></script>
	<script src="/js/popper.min.js"></script>
	<script src="/js/bootstrap.min.js"></script>
	<script src="/js/owl.carousel.min.js"></script>
	<script src="/js/jquery.stellar.min.js"></script>
	<script src="/js/jquery.countdown.min.js"></script>
	<script src="/js/bootstrap-datepicker.min.js"></script>
	<script src="/js/jquery.easing.1.3.js"></script>
	<script src="/js/aos.js"></script>
	<script src="/js/jquery.fancybox.min.js"></script>
	<script src="/js/jquery.sticky.js"></script>
	<script src="/js/jquery.mb.YTPlayer.min.js"></script>


	<script src="/js/main.js"></script>

</body>

</>
