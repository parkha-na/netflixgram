<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<!DOCTYPE html>
<html lang="ko">
    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
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
<%--        <!-- Navigation-->--%>
<%--        <nav class="navbar navbar-expand-lg navbar-light" id="mainNav">--%>
<%--            <div class="container px-4 px-lg-5">--%>
<%--                <a class="navbar-brand" href="index.html">Start Bootstrap</a>--%>
<%--                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">--%>
<%--                    Menu--%>
<%--                    <i class="fas fa-bars"></i>--%>
<%--                </button>--%>
<%--                <div class="collapse navbar-collapse" id="navbarResponsive">--%>
<%--                    <ul class="navbar-nav ms-auto py-4 py-lg-0">--%>
<%--                        <li class="nav-item"><a class="nav-link px-lg-3 py-3 py-lg-4" href="index.html">Home</a></li>--%>
<%--                        <li class="nav-item"><a class="nav-link px-lg-3 py-3 py-lg-4" href="about.jsp">About</a></li>--%>
<%--                        <li class="nav-item"><a class="nav-link px-lg-3 py-3 py-lg-4" href="post.jsp">Sample Post</a></li>--%>
<%--                        <li class="nav-item"><a class="nav-link px-lg-3 py-3 py-lg-4" href="post.jsp">Contact</a></li>--%>
<%--                    </ul>--%>
<%--                </div>--%>
<%--            </div>--%>
<%--        </nav>--%>
<%--        <!-- Page Header-->--%>
<%--        <header class="masthead" style="background-image: url('assets/img/contact-bg.jpg')">--%>
<%--            <div class="container position-relative px-4 px-lg-5">--%>
<%--                <div class="row gx-4 gx-lg-5 justify-content-center">--%>
<%--                    <div class="col-md-10 col-lg-8 col-xl-7">--%>
<%--                        <div class="page-heading">--%>
<%--                            <h1>Contact Me</h1>--%>
<%--                            <span class="subheading">Have questions? I have answers.</span>--%>
<%--                        </div>--%>
<%--                    </div>--%>
<%--                </div>--%>
<%--            </div>--%>
<%--        </header>--%>
        <!-- Main Content-->
        <main class="mb-4">
            <div class="container px-4 px-lg-5">
                <div class="row gx-4 gx-lg-5 justify-content-center">
                    <div class="col-md-10 col-lg-8 col-xl-7">
                        <p>포스트 작성</p>
                        <div class="my-5">
                            <c:choose>
                                <c:when test="${netVo ne null}">
                            <form id="contactForm" action="${path}/post/update" method="post" enctype="multipart/form-data">
                                <input type="hidden" name="user_id" value="${loginUser.id}" />
                                <input type="hidden" name="nickname" value="${loginUser.nickname}" />
                                <div class="form-floating">
                                    <textarea class="form-control" id="contents" name="contents" placeholder="내용을 입력해 주세요" style="height: 12rem" data-sb-validations="required">${netVo.contents}</textarea>
                                    <label for="contents">내용</label>
                                </div>
                                <div class="input-group">
                                    <div class="input-group-prepend">
                                        <span class="input-group-text" id="inputGroupFileAddon01">Upload</span>
                                    </div>
                                    <div class="custom-file">
                                        <input type="file" class="custom-file-input" id="inputGroupFile01" name="imgFile" aria-describedby="inputGroupFileAddon01">
                                        <label class="custom-file-label" for="inputGroupFile01">Choose file</label>
                                    </div>
                                </div>
                                <!-- Submit Button-->
                                <button class="btn btn-primary text-uppercase" id="submitButton" type="submit">수정</button>
                            </form>
                                </c:when>
                                <c:otherwise>
                            <form id="contactForm" action="${path}/post/write" method="post" enctype="multipart/form-data">
                                <input type="hidden" name="user_id" value="${loginUser.id}" />
                                <input type="hidden" name="nickname" value="${loginUser.nickname}" />
                                <div class="form-floating">
                                    <textarea class="form-control" id="contents" name="contents" placeholder="내용을 입력해 주세요" style="height: 12rem" data-sb-validations="required"></textarea>
                                    <label for="contents">내용</label>
                                </div>
                                <div class="input-group">
                                    <div class="input-group-prepend">
                                        <span class="input-group-text" id="inputGroupFileAddon01">Upload</span>
                                    </div>
                                    <div class="custom-file">
                                        <input type="file" class="custom-file-input" id="inputGroupFile01" name="imgFile" aria-describedby="inputGroupFileAddon01">
                                        <label class="custom-file-label" for="inputGroupFile01">Choose file</label>
                                    </div>
                                </div>
                                <!-- Submit Button-->
                                <button class="btn btn-primary text-uppercase" id="submitButton" type="submit">작성</button>
                                <button class="btn btn-primary text-uppercase" id="resetButton" type="reset">다시 작성</button>
                            </form>
                                </c:otherwise>
                            </c:choose>
                        </div>
                    </div>
                </div>
            </div>
        </main>
        <!-- Footer-->
        <footer class="border-top">
            <div class="container px-4 px-lg-5">
                <div class="row gx-4 gx-lg-5 justify-content-center">
                    <div class="col-md-10 col-lg-8 col-xl-7">
<%--                        <ul class="list-inline text-center">--%>
<%--                            <li class="list-inline-item">--%>
<%--                                <a href="#!">--%>
<%--                                    <span class="fa-stack fa-lg">--%>
<%--                                        <i class="fas fa-circle fa-stack-2x"></i>--%>
<%--                                        <i class="fab fa-twitter fa-stack-1x fa-inverse"></i>--%>
<%--                                    </span>--%>
<%--                                </a>--%>
<%--                            </li>--%>
<%--                            <li class="list-inline-item">--%>
<%--                                <a href="#!">--%>
<%--                                    <span class="fa-stack fa-lg">--%>
<%--                                        <i class="fas fa-circle fa-stack-2x"></i>--%>
<%--                                        <i class="fab fa-facebook-f fa-stack-1x fa-inverse"></i>--%>
<%--                                    </span>--%>
<%--                                </a>--%>
<%--                            </li>--%>
<%--                            <li class="list-inline-item">--%>
<%--                                <a href="#!">--%>
<%--                                    <span class="fa-stack fa-lg">--%>
<%--                                        <i class="fas fa-circle fa-stack-2x"></i>--%>
<%--                                        <i class="fab fa-github fa-stack-1x fa-inverse"></i>--%>
<%--                                    </span>--%>
<%--                                </a>--%>
<%--                            </li>--%>
<%--                        </ul>--%>
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
