<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<c:set var="path" value="${pageContext.request.contextPath}" scope="session"/>

<section>
<br>
 <div align="center">
&emsp;<a href="${path}/net/net_form.jsp">  게시판등록 </a> 
&emsp;<a href="${path}/net_list.do">게시판목록 </a> 
</div>
</section>
