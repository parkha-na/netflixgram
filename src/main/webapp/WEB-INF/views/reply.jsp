<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>리플 달기</title>
</head>
<body>
<div align="center">
<form action="/insertReply" method="post">
<table>
	<tr>
		<td><img src="/imageDownload?fileName=${m.img}" style="max-width: 500px;" onerror="this.src='assets/noimage.svg'" /></td>
		<td>${m.nickname} ${m.contents}</td>
	</tr>
	<c:forEach var="re" items="${r}">
	<tr>
		<td>${re.replynickname} ${re.replycontents}</td>
	</tr>
	</c:forEach>
	<tr>
		<td><a href="/updateRecommend?id=${b.id}">♥</a> ${b.recommend}</td>
	</tr>
	<tr>
		<td>
			<input type="hidden" name="boardId" value="${b.id}">
			<input type="hidden" name="replynickname" value="${loginUser.nickname}" />
			<input type="text" name="replycontents">
			<input type="submit" value="댓글달기">
		</td>
</table>
</form>
</div>
</body>
</html>