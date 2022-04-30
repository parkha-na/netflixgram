<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.net.URLEncoder" %>
<%@ page import="java.security.SecureRandom" %>
<%@ page import="java.math.BigInteger" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Netflixgram</title>
<style type="text/css">
	body{
		background-color:#000000;
		margin:0;
	}

	a:link {
	  color: #000000;
	  background-color: transparent;
	  text-decoration: none;
	}
	
	a:visited {
	  color: #000000;
	  background-color: transparent;
	  text-decoration: none;
	}
	
	a:hover {
	  color: #000000;
	  background-color: transparent;
	  text-decoration: underline;
	}
	
	a:active {
	  color: #000000;
	  background-color: transparent;
	  text-decoration: underline;
	}
	.wrapper {
		display: flex;
	  justify-content: center;
	  align-items: center;
	  min-height: 100vh;
	}
	.item {
		text-align: center;
	  padding: 50px;
	  font-weight: 900;
	  border-style:outset;
	}
	p {
		font-size: 2em;
		text-align: center;
		color: #ff0000;
	}
	span {
		background-color: #00ff00;
	}
</style>
</head>
<body>
<%
    String clientId = "MCfxN6QafHKnz4o_H26m";   /* 애플리케이션 클라이언트 아이디값 */
    String redirectURI = URLEncoder.encode("http://localhost:8081/callback", "UTF-8");
    SecureRandom random = new SecureRandom();
    String state = new BigInteger(130, random).toString();
    String loginApiUrl = "https://nid.naver.com/oauth2.0/authorize?response_type=code";
    loginApiUrl += "&client_id=" + clientId;
    loginApiUrl += "&redirect_uri=" + redirectURI;
    loginApiUrl += "&state=" + state;
    session.setAttribute("state", state);
%>
<div class="wrapper">
	<div class="item">
		<p>NETFLIXGRAM</p>
        <!-- 아래와같이 아이디를 꼭 써준다. -->
        <a href="<%=loginApiUrl%>"><img height="50" src="http://static.nid.naver.com/oauth/small_g_in.PNG"/></a>
<%--        <br>--%>
<%--        <a href="javascript:void(0)" onclick="naverLogout(); return false;">--%>
<%--            <span>네이버 로그아웃</span>--%>
<%--        </a>--%>
        <br>
    </div>
</div>
</body>
</html>