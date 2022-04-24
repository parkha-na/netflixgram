<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>

<section>
 
<br>
<div id=div1 align="center">
<font size=5>오늘의 추천 드라마</font>
</div><br>
<div id=div2>
<table border=1>

<c:forEach var="m" items="${li}">

<tr>
	<td>${m.id}</td>
	<td>${m.contents}</td>
	<td>${m.nickname}</td>
	<td>${m.uploaddate}</td>
	
</tr>	
</c:forEach>

</table>


</div>
<br>
</section>