<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.net.URLEncoder" %>
<%@ page import="java.security.SecureRandom" %>
<%@ page import="java.math.BigInteger" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%--<meta name="google-signin-client_id" content="876712575275-dhfn70sci1qj97a4assntjc3c6e2g7fn.apps.googleusercontent.com">--%>
<%--<script src="https://apis.google.com/js/platform.js" async defer></script>--%>
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
	#naver {
		background-color: #00ff00;
	}
	#googlesignout {
		color: #ffffff;
	}
</style>
</head>
<body>
<%
    String clientId = (String) request.getAttribute("clientId");
    String redirectURI = (String) request.getAttribute("redirectURI");
    redirectURI = URLEncoder.encode(redirectURI, "UTF-8");
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
<%--		<a href="javascript:;" class="btn_social" data-social="google">--%>
<%--			<img src="assets/google.png"/>--%>
<%--		</a><br/>--%>
<%--		<a href="#" onclick="signOut()" id="googlesignout">Sign out</a><br>--%>
        
	    <a href="<%=loginApiUrl%>">
	        <img src="assets/btnG_축약형.png" width="120" height="40">
	    </a>
     </div>
</div>
<script src="http://code.jquery.com/jquery-3.3.1.min.js"></script>
<script>

// function signOut() {
// 	var auth2 = gapi.auth2.getAuthInstance();
// 	auth2.signOut().then(function () {
// 		console.log('User signed out.');
// 	});
// 	auth2.disconnect();
// }
//
// $(document).ready(function(){
//     $('.btn_social').click(function () {
//         const socialType = $(this).data('social');
//         location.href = '/oauth2/authorization/' + socialType;
//     });
// });
</script>

</body>
</html>