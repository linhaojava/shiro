<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/jsp/tag.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${baseurl }style/login.css">
<script type="text/javascript" src="${baseurl }js/jquery-1.4.4.min.js"></script>
<script type="text/javascript">
	//登录提示方法
	function loginsubmit() {
		$("#loginform").submit();

	}
</script>
</head>
<body>
	<form action="${baseurl}login" id="loginform" method="post">
		<p>
			用户名:<input type="text" name="username">
		</p>
		<p>
			密&nbsp;&nbsp;码:<input type="password" name="password">
		</p>
		<p>
			验证码： <input id="randomcode" name="randomcode" size="8" /> <img
				id="randomcode_img" src="${baseurl}validatecode.jsp" alt=""
				width="56" height="20" align='absMiddle' title="点击刷新验证码" />
		</p>
		<p>
			<input type="button" onclick="loginsubmit()" value="登&nbsp;&nbsp;录" />
		</p>
	</form>
</body>
</html>