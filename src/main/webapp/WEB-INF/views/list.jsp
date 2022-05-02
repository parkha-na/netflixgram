<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
    <meta name="description" content="NetflixGram 사이트" />
    <meta name="author" content="Park Ha-na" />
    <title>NetflixGram</title>
    <link rel="icon" type="image/x-icon" href="assets/favicon.ico" />
    <!-- Font Awesome icons (free version)-->
    <script src="https://use.fontawesome.com/releases/v6.1.0/js/all.js" crossorigin="anonymous"></script>
    <!-- Google fonts-->
    <link href="https://fonts.googleapis.com/css?family=Lora:400,700,400italic,700italic" rel="stylesheet" type="text/css" />
    <link href="https://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800" rel="stylesheet" type="text/css" />
    <!-- Core theme CSS (includes Bootstrap)-->
    <link href="css/styles.css" rel="stylesheet" />
</head>
<body>
<!-- Navigation-->
<nav class="navbar navbar-expand-lg navbar-light" id="mainNav">
    <div class="container px-4 px-lg-5">
        <a class="navbar-brand" href="/">NetflixGram</a>
        <form>
            <select name="ch1">
                <option value="nickname">nickname</option>
                <option value="contents">contents</option>
            </select>
            <input name="ch2" type="text"><input type="submit" value="검색">
        </form>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
            Menu
            <i class="fas fa-bars"></i>
        </button>
        <div class="collapse navbar-collapse" id="navbarResponsive">
            <ul class="navbar-nav ms-auto py-4 py-lg-0">
                <li class="nav-item"><a class="nav-link px-lg-3 py-3 py-lg-4" href="/post">글 작성하기</a></li>
                <li class="nav-item"><a href="/logout"><img src="assets/btnG_로그아웃.png" width="120" height="40"></a></li>
            </ul>
        </div>
    </div>
</nav>
<!-- Page Header-->
<header class="masthead">
</header>
<!-- Main Content-->
<div class="container px-4 px-lg-5">
    <div class="row gx-4 gx-lg-5 justify-content-center">
        <div class="col-md-12 col-lg-8 col-xl-7">
            <c:forEach var="net" items="${li}">
            <div class="row">
                <div class="col-md-6" style="float: left;">
                    <img src="/imageDownload?fileName=${net.img}" style="max-width: 300px;" onerror="this.src='assets/noimage.svg'" />
                </div>
                <div class="col-md-6" style="float: right;">
                	<p align="right">
                	<c:if test="${net.nickname eq loginUser.nickname}">
                    	<a href="deleteNet?id=${net.id}">x</a>
                    </c:if>
                    <c:if test="${net.nickname ne loginUser.nickname}">
                    	
                    </c:if>
                    </p>
                    <p>${net.contents}</p>
                    <p class="post-meta">
                        Posted by <a href="#!">${net.nickname}</a> on <fmt:formatDate pattern="yyyy.MM.dd" value="${net.uploaddate}"/>
                    </p>
                    <p><a href="/updateRecommend?id=${net.id}"><i class="fa fa-heart" aria-hidden="true"></i></a> ${net.recommend}</p>
                    <a href="/reply?id=${net.id}">댓글 ${net.replyCnt}개 모두 보기</a>
                    <!-- Divider-->
                </div>
                <hr class="my-4" />
            </div>
            </c:forEach>
            <!-- Pager-->
            <c:if test="${isNextPage}">
            <div class="d-flex justify-content-end mb-4"><a class="btn btn-primary text-uppercase" href="/list?page=${page + 1}">더보기 →</a></div>
            </c:if>
        </div>
    </div>
</div>
<!-- Footer-->
<footer class="border-top">
    <div class="container px-4 px-lg-5">
        <div class="row gx-4 gx-lg-5 justify-content-center">
            <div class="col-md-10 col-lg-8 col-xl-7">
                <div class="small text-center text-muted fst-italic">Copyright &copy; Park Ha-na 2022</div>
            </div>
        </div>
    </div>
</footer>
<!-- Bootstrap core JS-->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
<!-- Core theme JS-->
<script src="js/scripts.js"></script>
</body>
</html>