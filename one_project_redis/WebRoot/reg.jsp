<%@page import="sun.security.util.Password"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
   
    <title>用户注册</title>
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
    		var admin = document.getElementById("tel").value;
    		var password1 = document.getElementById("passport1").value;
    		var password2 = document.getElementById("passport2").value;
    		var checkcode = document.getElementById("checkcode").value;
    if(admin == "" || admin ==null)
        {
            alert("用户名不能为空！");
            document.getElementById("tel").focus();
            document.getElementById("tel").select();
            flag = false;
            return false;
        }
       else if(admin == "admin")
        {
            alert("用户名不能admin！");
            document.getElementById("tel").focus();
            document.getElementById("tel").select();
            flag = false;
            return false;
        }
    else if (password1 != password2){
				alert("两次输入密码不一致！");
				flag = false;
				return false;
			}  
			else if (password1 == "" || password1 ==null){
				alert("请输入密码！");
				flag = false;
				return false;
			}			
			else if (password2 == "" || password2 ==null){
				alert("请再次输入密码！");
				flag = false;
				return false;
			}			
			else if (admin.length<11 || admin.length > 11){
				alert("请输入11位手机号！");
				flag = false;
				return false;
			}
			else if (checkcode == "" || checkcode ==null){
				alert("请输入验证码！");
				flag = false;
				return false;
			}			
			if(flag == true){
				//form.submit();
				return true;
			}
		  
}

	function reload(){
		document.getElementById("image").src="<%=request.getContextPath()%>/imageServlet?date="+new Date().getTime();
		$("#checkcode").val("");   // 将验证码清空
	} 
	 
$("#checkcode").ready(function(){
  $("#checkcode").blur(function(){
    var text=$.trim($("#checkcode").val());
		 $.post("${pageContext.request.contextPath}/verificationServlet",{checkcode:text},function(data){  //ajax-->post刷新
			 data=parseInt($.trim(data));
			 if(data>0){
				 
			 }else{
			 $("#checkcode").val("");
				 alert("验证码错误！");
				  // 将验证码清空
				 reload();  //验证失败后需要更换验证码
			 }
		 });
  });
});
</script>
    
</head>
<body>
<form id="reg" method="post" action="AddUserServlet"  onsubmit="return checkNull()">
    <div id="ajax-hook"></div>
    <div class="wrap">
        <div class="wpn">
            <div class="form-data pos">
                <a href=""><img src="./img/logo.png" class="head-logo"></a>
                <!--<p class="tel-warn hide"><i class="icon-warn"></i></p>-->
                
                     <p class="p-input pos">
                        <label for="tel">手机号</label>
                        <input type="number" id="tel" autocomplete="off" name="username">
                        <span class="tel-warn tel-err hide"><em></em><i class="icon-warn"></i></span>
                    </p>
                    <p class="p-input pos " id="pwd">
                        <label for="passport">输入密码</label>
                        <input type="password" style="display: none"/>
                        <input type="password" id="passport1" name="password">
                        <span class="tel-warn pwd-err hide"><em></em><i class="icon-warn" style="margin-left: 5px"></i></span>
                    </p>
                    <p class="p-input pos" id="confirmpwd">
                        <label for="passport2">确认密码</label>
                        <input type="password" style="position:absolute;top:-998px"/>
                        <input type="password" id="passport2">
                        <span class="tel-warn confirmpwd-err hide"><em></em><i class="icon-warn" style="margin-left: 5px"></i></span>
                    </p>
                    <p class="p-input pos" id="confirmpwd">
                        <label for="passport2">验证码</label>
                        <input type="text" style="position:absolute;top:-998px"/>
                        <input type="text" name="checkcode"  id="checkcode">
                        <span class="tel-warn confirmpwd-err hide"><em></em><i class="icon-warn" style="margin-left: 5px"></i></span>
                    </p>
                    
                      <p class="p-input pos" id="confirmpwd"> 
                     
                     <img  src="<%=request.getContextPath() %>/imageServlet" alt="验证码" id="image" />
                        <a href="javascript:reload();"><label>换一张</label></a><br>
                        
                      </p> 
                  
                <div class="reg_checkboxline pos">
                    <span class="z"><i class="icon-ok-sign boxcol" nullmsg="请同意!"></i></span>
                    <input type="hidden" name="agree" value="1">
                    <div class="Validform_checktip"></div>
                    <p>我已阅读并接受 <a href="#" target="_brack">《共享云协议说明》</a></p>
                </div>
                <!-- <button type="submit" onclick="return javascript:verificationcode()" class="lang-btn">注册</button> -->
                <!--  <input  type="button" value="提交"  onclick="javascript:verificationcode()"> -->
                <!--   <input type="submit" class="lang-btn" value="注册" /> -->
                 <button type="submit" class="lang-btn">注册</button> 
                <div class="bottom-info">已有账号，<a href="index.jsp">马上登录</a></div>
                <div class="third-party">
                    <a href="/login.do" class="log-qq icon-qq-round"></a>
                    <a href="#" class="log-qq icon-weixin"></a>
                    <a href="#" class="log-qq icon-sina1"></a>
                </div>
                <p class="right">Powered by © 2018</p>
            </div>
                   
        </div>

    </div>
   
    <script src="./js/jquery.js"></script>
    <script src="./js/agree.js"></script>
    <script src="./js/reg.js"></script>
	<div style="text-align:center;">

</div>

</form>
</body>
</html>
