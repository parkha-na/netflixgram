<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>

<section>
 
<br>
<div id=div1 align="center">
<font size=5>게시글 입력</font>
</div><br>
<div id=div2>
<form name="f1" action="${path}/net_formOK.do">
 <table>
 
 <tr><td>내용</td>
    <td class=td2>
    <input type=text name="contents"/></td></tr>
  <tr><td>닉네임</td>
    <td class=td2>
    <input type=text name="nickname" size=10 ></td></tr>

 <tr><td colspan=2 align="center">
  <input type=submit value="등록" >
  <input type=reset value="다시작성" >
  </td>
  </tr>
 </table>
</form>
</div>
<br>
</section>