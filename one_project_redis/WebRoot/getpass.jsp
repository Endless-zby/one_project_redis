<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML>
<html lang="zh-CN">



<head>
<meta charset="UTF-8">
<title>找回密码</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<meta name="Keywords" content="zbyyun">
<meta name="Description" content="当个云盘吧">
<link rel="stylesheet" href="./css/base.css">
<link rel="stylesheet" href="./css/iconfont.css">
<link rel="stylesheet" href="./css/reg.css">
<script type="text/javascript" src="js/jQuery1.9.1.js"></script>
    <script language="javascript">
    function checkNull(){
    var flag = true;
    		var email = document.getElementById("email").value;
    if (email == "" || email ==null){
				alert("请输入注册邮箱！");
				flag = false;
				return false;
			}			
			if(flag == true){
				//form.submit();
				return true;
			}
    
    
    
    }

    </script>
    
</head>
<body>
	<div id="ajax-hook"></div>
	<div class="wrap">
		<div class="wpn">
			<div class="form-data find_password">
				<h4>找回密码</h4>
				<form method="post" action="MailServlet" onsubmit="return checkNull()">
					<p class="right_now">
						已有账号，<a href="./index.jsp">马上登录</a>
					</p>
					<p class="p-input pos 5">
						<label for="pc_tel">邮箱</label> <input type="email" id="email"
							name="email" /> <span class="tel-warn pc_tel-err hide"><em>最多五个字</em><i
							class="icon-warn"></i>
						</span>
					</p>

					<button class="lang-btn next">发送验证码</button>
				</form>
				<p class="right">Powered by © 2018</p>
			</div>
		</div>
	</div>
	<script src="./js/jquery.js"></script>
	<script src="./js/agree.js"></script>
	<script src="./js/reset.js"></script>
	<div style="text-align:center;"></div>
</body>
</html>
