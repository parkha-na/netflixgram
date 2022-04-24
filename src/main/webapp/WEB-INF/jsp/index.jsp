<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <title>넷플릭스그램</title>
    <meta charset="UTF-8">
    <meta name="description" content="테스트페이지">
    <meta name="keywords" content="테스트, 테스트1, 테스트2">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="canonical" href="https://getbootstrap.com/docs/5.1/examples/blog/">
    <!-- Bootstrap core CSS -->
    <link rel="stylesheet" href="css/bootstrap.min.css">

    <style>
        .bd-placeholder-img {
            font-size: 1.125rem;
            text-anchor: middle;
            -webkit-user-select: none;
            -moz-user-select: none;
            user-select: none;
        }

        @media (min-width: 768px) {
            .bd-placeholder-img-lg {
                font-size: 3.5rem;
            }
        }
    </style>

    <!-- Custom styles for this template -->
    <link href="https://fonts.googleapis.com/css?family=Playfair&#43;Display:700,900&amp;display=swap" rel="stylesheet">
    <!-- Custom styles for this template -->
    <link href="css/blog.css" rel="stylesheet">

</head>
<body>

<!--<div class="container">
    <div class="row">
        <div class="col-12">
            <h1>List Group</h1>
            <ul class="list-group">
                <c:forEach items="${list}" var="board">
                    <li class="list-group-item">${board.title} / ${board.title_eng}</li>
                </c:forEach>
            </ul>
        </div>
    </div></div>-->

<main class="container">

    <div class="row g-5">
        <div class="col-md-12">

            <article class="blog-post">
                <h2 class="blog-post-title">오늘의 추천 영화</h2>
                <p class="blog-post-meta">2022-04-22 <a href="#">박하나</a></p>

                게시글 내용

            </article>

            <nav class="blog-pagination" aria-label="Pagination">
                <a class="btn btn-outline-primary" href="#">이전</a>
                <a class="btn btn-outline-secondary disabled">다음</a>
            </nav>

        </div>
        <div class="col-md-10">

        </div>
        <div class="col-md-2">

            <nav class="blog-pagination" aria-label="Pagination">
                <a class="btn btn-outline-primary" href="#">글쓰기</a>
            </nav>

        </div>
    </div>
</main>

<footer class="blog-footer">
<%--    <p>Blog template built for <a href="https://getbootstrap.com/">Bootstrap</a> by <a href="https://twitter.com/mdo">@mdo</a>.</p>--%>
    <p>
        <a href="#">위로 올라가기</a>
    </p>
</footer>

<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>

</body>
</html>