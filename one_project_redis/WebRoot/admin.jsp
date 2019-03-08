<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-CN">
<head>

<meta charset="UTF-8">
<title>欢迎使用zby云</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<meta name="Keywords" content="zbyyun">
<meta name="Description" content="当个云盘吧">
<link rel="stylesheet" href="./css/base.css">
<link rel="stylesheet" href="./css/iconfont.css">
<link rel="stylesheet" href="./css/reg.css">

<script type="text/javascript">
    	function check(){
    		var flag = true;
    		var admin = document.getElementById("adminname").value;
    		var password = document.getElementById("pwd").value;
			if (admin == "" || admin ==null){
				//alert("请输入账号！");
				document.getElementById("adminname").focus();
            document.getElementById("adminname").select();
				flag = false;
				return false;
			}
			else if (password ==""){
				//alert("请输入密码！");
				document.getElementById("pwd").focus();
            document.getElementById("pwd").select();
				flag = false;
				return false;
			}
			if(flag == true){
				//form.submit();
				return true;
			}
		}
    </script>

<script> 



  var error ='<%=request.getParameter("flag")%>';

  if(error=='error'){

   alert("管理员账号或密码错误!");

  }else if(error=='add'){
  
   alert("该账号已被注册");
  }

</script>


</head>
<body>

	<div id="ajax-hook"></div>
	<div class="wrap">
		<div class="wpn">
			<div class="form-data pos">
				<a href=""><img src="./img/logo.png" class="head-logo">
				</a>

				<div class="change-login">
					<p class="account_number on">管理员登录</p>
				</div>
				<form id="index" method="post" action="AdminServlet"
					onclick="return check();">
					<div class="form1">
						<p class="p-input pos">
							<label for="num">手机号/用户名/邮箱</label> <input type="text"
								id="adminname" name="name" />
						</p>
						<p class="p-input pos">
							<label for="pass">请输入密码</label> <input type="password"
								style="display:none" /> <input type="password" id="pwd"
								name="password" />

						</p>
					</div>
					<br />
					<button class="lang-btn off log-btn">登录</button>
				</form>

				<br />

				<p class="right">Powered by © 2018</p>

				<div style="width:180px;margin:0 auto; padding:20px 0;">
					<a target="_blank"
						href="http://www.beian.gov.cn/portal/registerSystemInfo?recordcode=61030302000269"
						style="display:inline-block;text-decoration:none;height:20px;line-height:20px;"><img
						src="" style="float:left;" />
					<p
							style="float:left;height:20px;line-height:20px;margin: 0px 0px 0px 5px; color:#939393;">陕公网安备
							61030302000269号</p>
					</a>
				</div>
				<div style="width:180px;margin:0 auto; padding: 0;">
					<a
						style="display:inline-block;text-decoration:none;height:20px;line-height:20px;width: 100%;"
						href="http://www.beian.gov.cn/portal/registerSystemInfo?recordcode=11010102002019&amp;token=4d610631-b485-48db-a80b-ae9b277c88c4"
						target="_blank"> <img style="float:left;" src="./img/11.png">
						<p
							style="float:left;height:20px;line-height:20px;margin: 0px 0px 0px 15px; color:#666666;">
							陕ICP备18015113号-1</p>
					</a> <br />邮箱：<a href="mailto:381016296@qq.com">381016296@qq.com</a>
				</div>
				</form>
			</div>
		</div>
	</div>
	<script src="./js/jquery.js"></script>
	<script src="./js/agree.js"></script>
	<script src="./js/login.js"></script>
	<div style="text-align:center;"></div>

</body>
</html>
