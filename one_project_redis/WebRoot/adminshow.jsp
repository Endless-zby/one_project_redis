<%@page import="club.zby.entity.User"%>
<%@page import="java.util.List"%>
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
<base href="<%=basePath%>">


<title>欢迎登录</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<meta name="Keywords" content="网站关键词">
<meta name="Description" content="网站介绍">
<link rel="stylesheet" href="./css/base.css">
<link rel="stylesheet" href="./css/iconfont.css">
<link rel="stylesheet" href="./css/reg.css">


<style>
tr,td {
	font-size: 16px; <
	br />color: #000000; <
	br />background: #ffffff;
	<
	br
	/>
}
</style>
</head>
<body>
	<div id="ajax-hook"></div>
	<div class="wrap">
		<div class="wpn">
			<div class="form-data find_password">
				<br />
				<center>
					<div style=" overflow-y:auto; width:470px; height:320px;">
						<table border="0" width="420px" rules=rows font-size="16px">
							<tr>
								<th>用户名</th>

								<th>密码</th>
								<th>邮箱</th>
								<th>更多</th>
							</tr>
							<%
			List<User> usersd = (List<User>)request.getAttribute("usersdb");
			for(User user:usersd){
		%>
							<tr>
								<td><font size="2" color="DodgerBlue"><%=(String)user.getName() %></font>
								</td>
								<td><font size="2" color="DodgerBlue"><%=(String)user.getPassword() %></font>
								</td>
								<td><font size="2" color="DodgerBlue"> <%  if((String)user.getEmail()==null){
							      %> （未填写） <%	}else{   %> <%=(String)user.getEmail() %> <%	} %> </font>
								</td>
								<td><a href="information?name=<%=(String)user.getName() %>">详情</a>
								</td>
							</tr>
							<%
			}
		%>
						</table>
					</div>
			</div>
		</div>
		<script src="./js/jquery.js"></script>
		<script src="./js/agree.js"></script>
		<script src="./js/reset.js"></script>
		<div style="text-align:center;"></div>
</body>
</html>