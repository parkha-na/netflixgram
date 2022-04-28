<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8" />
    <title>File Upload Example</title>
</head>
<body>
<h1>File Upload Example</h1>
<form method="post" action="/fileUpload" enctype="multipart/form-data">
    <input type="file" name="file" />
    <input type="submit" value="submit" />
</form>
</body>
</html>