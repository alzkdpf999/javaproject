<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>match</title>

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
/* --------------------- */
</style>

<body>

	<jsp:include page="/WEB-INF/view/include/header.jsp"></jsp:include>



	<div class="wrap bg-visual"
		style="background-image: url('/images/match.jpg'); background-position: center center; background-size: cover">
		<div class="container">
			<div class="row align-items-center">
				<div class="col-lg-5 mx-auto text-center">
					<h1 class="text-white">Matches</h1>
				</div>
			</div>
		</div>
	</div>

	<div class="wrapper">
		<h2>경기결과</h2>
		<div class="contentWrap">
			<div class="pop">
				<h4>저장할 경기기록이 없습니다.</h4>
				<div class="btn_pop">
					<button type="button" class="check">확인</button>
				</div>
			</div>
			<div class="top">
				<div class="center-date">
					<h3>${empty param.Month ? month : param.Month }월</h3>
					<c:choose>
						<c:when test="${empty param.Month }">
							<a type="button" id="prev" class="btn-outline-info btn"
								href="./result?Year=${param.Year}&Month=${month - 1 < 1 ? '01' : (month - 1 < 10 ? '0'+= month - 1 : month -1 )}"
								onclick="${month eq 1 ? 'return false' : ''}">이전</a>
							<a type="button" id="next" class="btn-outline-info btn"
								href="./result?Year=${param.Year}&Month=${month + 1 > 12 ? '12' : (month + 1< 10 ? '0'+= month + 1 : month + 1)}"
								onclick="${month eq 12 ? 'return false' : ''}">다음</a>
						</c:when>
						<c:otherwise>
							<a type="button" id="prev" class="btn-outline-info btn"
								href="./result?Year=${param.Year}&Month=${param.Month - 1 < 1 ? '01' : (param.Month - 1 < 10 ? '0'+= param.Month - 1 : param.Month -1 )}"
								onclick="${param.Month eq 1 ? 'return false' : ''}">이전</a>
							<a type="button" id="next" class="btn-outline-info btn"
								href="./result?Year=${param.Year}&Month=${param.Month + 1 > 12 ? '12' : (param.Month + 1< 10 ? '0'+= param.Month + 1 : param.Month + 1)}"
								onclick="${param.Month eq 12 ? 'return false' : ''}">다음</a>
						</c:otherwise>
					</c:choose>

				</div>
				<div class="right">
					
					<form id="matchForm" method="get" action="./result">
						<select name="Year" id="Year">
							<c:forEach var="year" items="${year}">
								<option value="${year}" ${param.Year eq year ? "selected" : ""}>20${year}</option>
							</c:forEach>
						</select> <select name="Month" id="Month">
							<c:forEach var="i" begin="1" end="12">
								<c:choose>
									<c:when test="${i < 10 }">
										<option value="0${i}"
											${empty param.Month ? (month eq i ? "selected" : "") : (param.Month eq i ? "selected" : "")}>${i}월</option>
									</c:when>

									<c:when test="${i eq 10 }">
										<option value="${i}"
											${empty param.Month ? (month eq i ? "selected" : "") : (param.Month eq i ? "selected" : "")}>${i}월</option>
									</c:when>
									<c:otherwise>
										<option value="${i}"
											${empty param.Month ? (month eq i ? "selected" : "") : (param.Month eq i ? "selected" : "")}>${i}월</option>
									</c:otherwise>
								</c:choose>

							</c:forEach>
						</select> <input id="search" type="submit" value="검색">
						<a href="/lineup/result" class="btn btn-primary" id="matchReg"
							data-line="${!empty lineup ? true : false }">등록</a>
					</form>
				</div>
			</div>

			<div class="bottom">


				<c:choose>
					<c:when test="${empty result }">
						<ul>
							<li>
								<div class="date">날짜</div>
								<ul class="board">
									<li>기록없음</li>

								</ul>
							</li>
							<li>
								<div class="place">장소</div>
								<ul class="board">


									<li>기록없음</li>
								</ul>
							</li>
							<li>
								<div class="result">경기결과</div>
								<ul class="board">

									<li>기록없음</li>
								</ul>

							</li>


							<li>
								<div class="log">결과보기</div>
								<ul class="board">
									<li>기록없음</li>
								</ul>
							</li>
						</ul>
					</c:when>
					<c:otherwise>
						<ul>
							<li>
								<div class="date">날짜</div>
								<ul class="board">

									<c:forEach var="item" items="${result}">
										<fmt:formatDate var="set" value="${item.matchDate}"
											pattern="yy/MM/dd HH:mm" />
										<li>${set}</li>
									</c:forEach>
								</ul>
							</li>

							<li>
								<div class="place">장소</div>
								<ul class="board">

									<c:forEach var="item" items="${result}">
										<li class="place">${item.matchPlace}</li>
									</c:forEach>
								</ul>
							</li>
							<li>
								<div class="result">경기결과</div>
								<ul class="board">
									<c:forEach var="item" items="${result}">

										<li class="imgclass"><img src="/images/logo_1.png"
											alt="Image" class="img-fluid customCh"> <span
											class="imgspan">MyTeam <span class="score">${item.win}
													: ${item.count - item.win}</span> ${item.opteam}
										</span> <img src="/images/${item.matchImgName}" alt="Image"
											class="img-fluid customCh"></li>
									</c:forEach>
								</ul>

							</li>


							<li>
								<div class="log">결과보기</div>
								<ul class="board">
									<c:forEach var="item" items="${result}">
										<li><a href="./result/${item.matchId }">결과보기</a></li>
									</c:forEach>
								</ul>
							</li>
						</ul>
					</c:otherwise>

				</c:choose>
			</div>

			<!-- #wrapper  -->

			
			<div id="popup-01" class="layer-popup" >
				<section class="body">
					<div class="wrapper">
						<table border="1">
							<tr>
								<th scope="col">TEAM1</th>
								<th scope="col">SCORE</th>
								<th scope="col">TEAM2</th>
								<th scope="col">WIN</th>
								<th scope="col">TIME</th>
							</tr>
							<tr>
								<td>TEAM1</td>
								<td>0:0</td>
								<td>TEAM2</td>
								<td>TEAM1</td>
								<td>80</td>
							</tr>
						</table>
					</div>
					<!-- quater -->
					<div class="wrapper">
						<table border="1">
							<tr>
								<th scope="col">QUATER</th>
								<th scope="col">TEAM</th>
								<th scope="col">SCORE</th>
								<th scope="col">TEAM</th>
								<th scope="col">WIN</th>
								<th scope="col">TIME</th>
								<th scope="col">GOAL</th>
								<th scope="col">ASSIST</th>
							</tr>
							<tr>
								<td>QUATER1</td>
								<td>TEAM1</td>
								<td>0:0</td>
								<td>TEAM2</td>
								<td>TEAM1</td>
								<td>80</td>
								<td>3</td>
								<td>1</td>
							</tr>
						</table>
					</div>
				</section>
			</div>

			<!---기본 메뉴--->



			<jsp:include page="/WEB-INF/view/include/footer.jsp"></jsp:include>


			<script src="/js/viewDetail.js"></script>
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
			<script src="/js/matchReg.js"></script>
</body>
</html>