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
tr,td{font-size:16px;<br/>color:#000000;<br/>background:#ffffff;<br/>}
</style>

  </head>
  
  <body>
<div id="ajax-hook"></div>
<div class="wrap">
    <div class="wpn">
        <div class="form-data find_password">
      


            <h4> 欢迎: <a
						href="information?name=<%=request.getAttribute("username")%>"><c:out value="${username}"></c:out><br></br></a> </h4>
           
                 
<br/>
      
       <center>
       
       

       
 <div style=" overflow-y:auto; width:470px; height:320px;">  
			<%-- <%
			 String username = (String) request.getParameter("username"); 
				 
				if (username.equals("admin")) {
			%> --%>
 <table border="0" width="420px" rules=rows font-size="16px"> 			
			<tr>
				<td
					style="text-align: center; font-weight: bold; background-color: #eee;">文件名</td>
				<td colspan="2"
					style="text-align: center; font-weight: bold; background-color: #eee;">操作</td>
			</tr>
			<c:forEach var="map" items="${map}">
				<tr>
					<td style="color:06B9D1;"><c:out value="${map.key}"></c:out>
					</td>
					<td style="text-align: center;"><a
						href="DownServlet?filename=${map.value}&username=<%=request.getAttribute("username")%>">下载</a>
					</td>
					<td style="text-align: center;"><a
						href="DelServlet?filename=${map.key}&username=<%=request.getAttribute("username")%>">删除</a>
					</td>
				</tr>
			</c:forEach>
			
			
			
</table>			
			<%-- <%
				} else {
			%> --%>
				
		<%-- <table border="0" width="420px" rules=rows font-size="16px">
			<tr>
				<td 
					style="text-align: center; font-weight: bold; background-color: #ffffff;" width="150px" height = "30px">文件名</td>
				<td colspan="2"
					style="text-align: center; font-weight: bold; background-color: #ffffff;" width="50px">操作</td>
			</tr>

			<c:forEach var="map" items="${map}">
				<tr>
					<td style="color:06B9D1;"><c:out value="${map.key}"></c:out>
					</td>
					<td style="text-align: center;" width="50px"><a
						href="DownServlet?filename=${map.value}&username=<%=request.getAttribute("username")%>">下载</a>
					</td>
					
				</tr>
			</c:forEach>
			<%
				}
			%>
			--%>
		</table> 
</div> 

		<a style="text-align: right;"  
			href="up.jsp?username=<%=request.getAttribute("username")%>"><button class="lang-btn log-btn">上传文件</button></a>
			
			 <a href="index.jsp"><button class="lang-btn off log-btn">退出登录</button></a>	
		</br>
		
	</center>
           
            
            
            
            <p class="right">Powered by © 2018</p>
            <!-- <div style="width:180px;margin:0 auto; padding:20px 0;">
		 		<a target="_blank" href="http://www.beian.gov.cn/portal/registerSystemInfo?recordcode=61030302000269" style="display:inline-block;text-decoration:none;height:20px;line-height:20px;"><img src="" style="float:left;"/><p style="float:left;height:20px;line-height:20px;margin: 0px 0px 0px 5px; color:#939393;">陕公网安备 61030302000269号</p></a> 		 	
		 	</div>
            <div style="width:180px;margin:0 auto; padding: 0;">
							 	<a style="display:inline-block;text-decoration:none;height:20px;line-height:20px;width: 100%;" href="http://www.beian.gov.cn/portal/registerSystemInfo?recordcode=11010102002019&amp;token=4d610631-b485-48db-a80b-ae9b277c88c4" target="_blank">
								 	<img style="float:left;" src="./img/11.png">
								 	<p style="float:left;height:20px;line-height:20px;margin: 0px 0px 0px 15px; color:#666666;">
								 	陕ICP备18015113号-1
								 	</p></a>
								 	 <br/>邮箱：<a href="mailto:381016296@qq.com">381016296@qq.com</a>
			 </div> -->    
        </div>
        
    </div>
</div>
<script src="./js/jquery.js"></script>
<script src="./js/agree.js"></script>
<script src="./js/reset.js"></script>
<div style="text-align:center;">
</div>
</body>
</html>
