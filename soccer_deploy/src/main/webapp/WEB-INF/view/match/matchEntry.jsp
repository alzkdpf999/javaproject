<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<title>Soccer &mdash; Website by Colorlib</title>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link rel="stylesheet" href="/css/board.css">


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
#matchProfile{
	border-radius: 100%;
	width: 30px;
	height: 30px;
}
#matchProfile:hover{
	border: #F00 2px solid;
}
</style>

<body>

	<jsp:include page="/WEB-INF/view/include/header.jsp"></jsp:include>

	<div class="herol overlay" >
		<div class="container">

			<div class="row align-items-center line_up_row">

				<div class="col-lg-12 board-list" style="height: calc(100vh - 250px);">
				<div class="col-lg-12">
						<div class="widget-next-match">
							<div class="widget-title">
								<h3>Match Info</h3>
							</div>
							<div class="widget-body mb-3">
								<div class="widget-vs">
									<div class="d-flex align-items-center justify-content-around justify-content-between w-100">
										<div class="team-1 text-center">
											<img src="/images/logo_1.png" alt="Image">
											<h3 id="v">My Team</h3>
										</div>
										<div>
											<span class="vs"><span>VS</span></span>
										</div>
										<div class="team-2 text-center">
											<img src="/images/profile/${match.matchImgName}" alt="Image">
											<h3 id="v">${match.opteam}</h3>
										</div>
									</div>
								</div>
							</div>
   
							<div class="text-center widget-vs-contents mb-4">
								<h4 style="text-shadow: -1px 0 #000, 0 1px #000, 1px 0 #000, 0 -1px #000;">경기 시간 / 장소</h4>
								<p class="mb-5">
									<fmt:formatDate var="time" value="${match.matchDate}" pattern="yy/MM/dd HH:mm" />
									<span class="d-block" id="v">${time}</span> 
									<a href="" onClick="window.open('/findRoad/${match.id}','','width=700,height=500');return false"><span class="d-block" id="v">${match.matchPlace}</span></a>
								</p>
							</div>
						</div>
					</div>
					<br>
					<br>
					
					<div class="wrapper" style="text-align: center;">
						<h3>신청한 선수 목록</h3>
					</div>
					<table class="table custom-table board">

						<thead>
							<tr>
								<th>이름</th>
								<th>나이</th>
								<th>포지션</th>
								<th>등번호</th>
							</tr>
						</thead>
						<tbody class="text-center">
							<c:forEach items="${entry}" var="entry" varStatus="status">
								<tr>
									<td class="text-white"><img src="/images/profile/${entry.user.imgFileName}" id="matchProfile">  ${entry.user.name}</td>
									<td class="text-white">${entry.user.age}</td>
									<td class="text-white">${entry.user.position}</td>
									<td class="text-white">${entry.user.backNum}</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>

					<c:if test="${empty entry}">
						<div class="no-article">
							<span>신청한 선수가 없습니다.</span>
						</div>
					</c:if>

				</div>


			</div>
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
</html>