<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
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

<div class="wrapper">
	<div class="item">
		<p>Netflixgram</p>
      <!-- 아래와같이 아이디를 꼭 써준다. -->
      <a id="naverIdLogin_loginButton" href="javascript:void(0)">
          <span>네이버 로그인</span>
      </a>
      <br>
      <a href="javascript:void(0)" onclick="naverLogout(); return false;">
          <span>네이버 로그아웃</span>
      </a>
     </div>
</div>
<!-- 네이버 스크립트 -->
<script src="https://static.nid.naver.com/js/naveridlogin_js_sdk_2.0.2.js" charset="utf-8"></script>

<script>

var naverLogin = new naver.LoginWithNaverId(
		{
			clientId: "MCfxN6QafHKnz4o_H26m", //내 애플리케이션 정보에 cliendId를 입력해줍니다.
			callbackUrl: "http://localhost:8081/naverLogin", // 내 애플리케이션 API설정의 Callback URL 을 입력해줍니다.
			isPopup: false,
			callbackHandle: true
		}
	);	

naverLogin.init();

window.addEventListener('load', function () {
	naverLogin.getLoginStatus(function (status) {
		if (status) {
			var email = naverLogin.user.getEmail(); // 필수로 설정할것을 받아와 아래처럼 조건문을 줍니다.
    		
			console.log(naverLogin.user); 
    		
            if( email == undefined || email == null) {
				alert("이메일은 필수정보입니다. 정보제공을 동의해주세요.");
				naverLogin.reprompt();
				return;
			}
		} else {
			console.log("callback 처리에 실패하였습니다.");
		}
	});
});


var testPopUp;
function openPopUp() {
    testPopUp= window.open("https://nid.naver.com/nidlogin.logout", "_blank", "toolbar=yes,scrollbars=yes,resizable=yes,width=1,height=1");
}
function closePopUp(){
    testPopUp.close();
}

function naverLogout() {
	openPopUp();
	setTimeout(function() {
		closePopUp();
		}, 1000);
	
	
}
</script>
 
</body>
</html>