<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div align="center">
<form action="/insertReply" method="post">
<table>
	<c:forEach var="m" items="${b}" varStatus="s">
	<tr>
	<td rowspan=${s.index}><img src="/imageDownload?fileName=${m.img}" style="max-width: 500px;" onerror="this.src='assets/noimage.svg'" /></td>
	<td>${m.nickname} ${m.contents}</td>
	</tr>
	<c:forEach var="re" items="${r}">
	<tr><td>${re.replynickname} ${re.replycontents}</td></tr>
	</c:forEach>
	<tr><td><a href="/updateRecommend?id=${m.id}">♥</a> ${m.recommend}</td></tr>
	</c:forEach>
	<tr><td><input type=text name=replynickname size=5><input type=text name=replycontents><input type=submit value="댓글달기"></td>
</table>
</form>
</div>
</body>
</html>