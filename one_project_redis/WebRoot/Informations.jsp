<%@page import="club.zby.entity.User"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
	+ request.getServerName() + ":" + request.getServerPort()
	+ path + "/";
%>
<%@page import="club.zby.entity.Information"%>
<%@page import="java.util.List"%>
<!DOCTYPE HTML>
<html lang="zh-CN">
<head>
<base href="<%=basePath%>">
<meta http-equiv="content-type" content="text/html; charset=utf-8" />

<title>欢迎登录</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<meta name="Keywords" content="网站关键词">
<meta name="Description" content="网站介绍">
<link rel="stylesheet" href="./css/base.css">
<link rel="stylesheet" href="./css/iconfont.css">
<link rel="stylesheet" href="./css/reg.css">

<script type="text/javascript" src="js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="js/jquery-3.3.1.min.js"></script>
<script type="text/javascript">
		function check()
		{
			//$("#id值")
			var name = $("#nameid").val() ;//获取name所在文本框的值
			var password = $("#passwordid").val();
			var email = $("#emailid").val();
			var idname = $("#idnameid").val();
			var age = $("#ageid").val();
			var data = $("#dataid").val();
			var school = $("#schoolid").val();
			
			//alert(name);
			if(email.length == 0){
				alert("邮箱未填写！");
				return false ;
			}else if(idname.length == 0){
				alert("姓名未填写");
				return false ;
			}else if(age.length == 0){
				alert("年龄未填写");
				return false ;
			}else if(data.length == 0){
				alert("出生日期未填写");
				return false ;
			}else if(school.length == 0){
				alert("就读院校未填写");
				return false ;
			}
			return true ;
		}
	</script>





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
						<form id="index" method="post" action="updateServlet"
							onsubmit="return check()">
							<table border="2" width="420px" rules=none font-size="20px">

								<tr>
									<th>详情</th>


								</tr>
								<%
									List<Information> users = (List<Information>) request
											.getAttribute("usersd");
									System.out.println(users.size() - 1);
									System.out.println("----------------");
									System.out.println((String) users.get(users.size() - 1).getEmail());
									System.out.println("----------------");
									/* for (int i = 1; i < users.size(); i++) { */
								%>
								<tr>
									<td>
										<div class="form1">
											<span style="font-size:20px">用户名（不可更改）：</span>
											<p class="p-input pos">

												<input type="text" readonly unselectable="on" id="nameid"
													name="name"
													value="<%=(String) users.get(users.size() - 1).getName()%>" />
											</p>
										</div> <%-- 用户名：<%=(String)users.get(i)  .getName() %> --%>
									</td>
								</tr>
								<tr>
									<td>

										<div class="form1">
											<span style="font-size:20px">密码：</span>
											<p class="p-input pos">
												<input type="text" id="passwordid" name="password"
													value="<%=(String) users.get(users.size() - 1).getPassword()%>" />
											</p>
										</div>
									</td>
								</tr>
								<tr>
									<td>
										<div class="form1">
											<span style="font-size:20px">邮箱：</span>
											<p class="p-input pos">

												<%
													if ((String) users.get(users.size() - 1).getEmail() != null) {
												%>
												<input type="email" id="emailid" name="email"
													value="<%=(String) users.get(users.size() - 1).getEmail()%>" />


												<%
													} else {
												%>
												<input type="email" id="emailid" name="email"
													placeholder="（未填写）" />
												<%
													}
												%>

											</p>
										</div>
									</td>
								</tr>
								<tr>
									<td>

										<div class="form1">
											<span style="font-size:20px">姓名：</span>
											<p class="p-input pos">

												<%
													if ((String) users.get(users.size() - 1).getIdname() != null) {
												%>
												<input type="text" id="idnameid" name="idname" maxlength="4"
													value="<%=(String) users.get(users.size() - 1).getIdname()%>" />
												<%
													} else {
												%>
												<input type="text" id="idnameid" name="idname" maxlength="4"
													placeholder="（未填写）" />
												<%
													}
												%>
											</p>
										</div>
									</td>
								</tr>
								<tr>
									<td>

										<div class="form1">
											<span style="font-size:20px">出生日期：</span>
											<p class="p-input pos">

												<%
													if ((String) users.get(users.size() - 1).getData() != null) {
												%>
												<input type="date" id="dataid" name="data"
													value="<%=(String) users.get(users.size() - 1).getData()%>" />
												<%-- <input type="text" id="dataid" name="data" onFocus="createTime()" 
													value="<%=(String) users.get(users.size()-1).getData()%>"/> --%>
												<%
													} else {
												%>
												<input type="date" id="dataid" name="data"
													placeholder="（未填写）" />
												<%-- 	<input type="text" id="dataid" name="data" onFocus="createTime()" 
													value="<%=(String) users.get(users.size()-1).getData()%>" /> --%>
												<%
													}
												%>

											</p>
										</div>
									</td>
								</tr>
								<tr>
									<td>

										<div class="form1">
											<span style="font-size:20px">年龄：</span>
											<p class="p-input pos">




												<%
													if ((String) users.get(users.size() - 1).getAge() != null) {
												%>
												<input type="number" id="ageid" maxlength="3" name="age"
													min="1" max="100"
													value="<%=(String) users.get(users.size() - 1).getAge()%>" />
												<%
													} else {
												%>
												<input type="number" id="ageid" name="age" min="1" max="100"
													placeholder="（未填写）" />
												<%
													}
												%>

											</p>
										</div>
									</td>
								</tr>
								<tr>
									<td>

										<div class="form1">
											<span style="font-size:20px">就读学校：</span>
											<p class="p-input pos">




												<%
													if ((String) users.get(users.size() - 1).getSchool() != null) {
												%>
												<input type="text" id="schoolid" name="school"
													value="<%=(String) users.get(users.size() - 1).getSchool()%>" />
												<%
													} else {
												%>
												<input type="text" id="schoolid" name="school"
													placeholder="（未填写）" />
												<%
													}
												%>





											</p>
										</div>
									</td>
								</tr>


							</table>
							<button class="lang-btn off log-btn">确认修改</button>
							<a href="javascript:;" onClick="javascript:history.back(-1);"><button
									class="lang-btn off log-btn">返回</button> </a>


						</form>

					</div>
			</div>
		</div>
		<script src="./js/jquery.js"></script>
		<script src="./js/agree.js"></script>
		<script src="./js/reset.js"></script>
		<div style="text-align:center;"></div>
</body>
</html>