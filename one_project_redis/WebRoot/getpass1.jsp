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
<script type="text/javascript" src="js/jquery-3.3.1.min.js"></script>

<script> 
	var emailcode = '<%=(Integer)request.getAttribute("numcode")%>';
  var type ='<%=request.getParameter("type")%>';

  if(type=='true'){

   alert("验证码发送成功，请查看！");

  }else{
  
   alert("验证码发送失败");
  }

</script>

<script type="text/javascript">
function checkNull(){
var flag = true;
    		var emailcode = '<%=(Integer)request.getAttribute("numcode")%>';
    		var password = document.getElementById("password").value;
    		var passwords = document.getElementById("passwords").value;
    		var numcode = document.getElementById("numcode").value;
    if(password == "" || password ==null)
        {
            alert("密码不能为空！");
            document.getElementById("password").focus();
            document.getElementById("password").select();
            flag = false;
            return false;
        }
        
        	 else if (password != passwords){
				alert("两次输入密码不一致！");
				flag = false;
				return false;
			}
			
			  
			else if (password == "" || password ==null){
				alert("请输入密码！");
				flag = false;
				return false;
			}
			
			else if (passwords == "" || passwords ==null){
				alert("请再次输入密码！");
				flag = false;
				return false;
			}
			
			else if (numcode != emailcode){
				alert("验证码错误！");
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

	<%
    int numcode = (Integer)request.getAttribute("numcode");
    String useremail = (String)request.getAttribute("useremail");
     %>

	<div id="ajax-hook"></div>
	<div class="wrap">
		<div class="wpn">
			<div class="form-data find_password">
				<h4>找回密码</h4>
				<p class="right_now">
					已有账号，<a href="./index.jsp">马上登录</a>
				</p>
				<form action="MailServletCode" method="post"
					onsubmit="return checkNull()">
					<p class="p-input pos 5">
						<label for="pc_tel">邮箱</label> <input type="text" id="email"
							name="email" readonly unselectable="on" value="<%=useremail%>" />
					</p>
					<p class="p-input pos 5">
						<label for="veri-code">更改密码</label> <input type="password"
							id="password" name="password" />



					</p>
					<p class="p-input pos 5">
						<label for="veri">确认密码</label> <input type="password"
							id="passwords" name="passwords" />



					</p>
					<p class="p-input pos 5">
						<label for="veri">邮箱验证码</label> <input type="number" id="numcode"
							name="numcode" />



					</p>
					<button class="lang-btn next">确认修改</button>
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
