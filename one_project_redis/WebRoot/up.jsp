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
    
    
    <title>上传文件</title>
    <meta http-equiv="content-type" content="text/html; charset=utf-8" />
    <meta name="Keywords" content="zbyyun">
    <meta name="Description" content="当个云盘吧">
    <link rel="stylesheet" href="./css/base.css">
    <link rel="stylesheet" href="./css/iconfont.css">
    <link rel="stylesheet" href="./css/reg.css">

<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script type="text/javascript">
			var isIE = /msie/i.test(navigator.userAgent) && !window.opera;
		function fileChange(target, id) {
			var fileSize = 0;
			var filetypes = [".jpg", ".png", ".rar", ".txt", ".zip", ".doc", ".ppt", ".xls", ".pdf", ".docx", ".xlsx",".DOCX",".PDF",".vsdx",".exe",".EXE",".apk"];
				var filepath = target.value;
				var filemaxsize = 1024 * 10; //10M 
				if(filepath) {
					var isnext = false;
					var fileend = filepath.substring(filepath.indexOf("."));
					if(filetypes && filetypes.length > 0) {
						for(var i = 0; i < filetypes.length; i++) {
							if(filetypes[i] == fileend) {
								isnext = true;
								break;
							}
						}
					}
					if(!isnext) {
					target.value = "";
						alert("ZBY说了，这个文件不让传!略略略!");				
						return false;
					}
				} else {
				target.value = "";
					return false;
					
				}
				if(isIE && !target.files) {
					var filePath = target.value;
					var fileSystem = new ActiveXObject("Scripting.FileSystemObject");
					if(!fileSystem.FileExists(filePath)) {
						alert("附件不存在，请重新输入！");
						return false;
					}
					var file = fileSystem.GetFile(filePath);
					System.out.print("file");
					fileSize = file.Size;
					System.out.print("fileSize");
				} else {
					fileSize = target.files[0].size;
				}
				var size = fileSize / 1024;
				if(size > filemaxsize) {
					alert("附件大小不能大于" + filemaxsize / 1024 + "M！");
					target.value = "";
					return false;
				}
				if(size <= 0) {
					alert("附件大小不能为0M！");
					target.value = "";
					return false;
				}
			}
		</script>

  </head>
  <body>
  <form	action="UploadServlet?username=<%=request.getParameter("username")%>"
			method="post" enctype="multipart/form-data">
			
<div id="ajax-hook"></div>
<div class="wrap">
    <div class="wpn">  
        <div class="form-data find_password">       
           <h4> 禁止上传不合法文件 <h4><br/><br/>
<center>
   
   
   <div class="class" style="position:absolute;">
   &nbsp;&nbsp;&nbsp;&nbsp;<input type="file" name="filename" onchange="fileChange(this);">
   </div>
				<br/><br/><br/>				
			<button class="lang-btn log-btn">上传文件</button>		
		<a href="index.jsp"><button class="lang-btn off log-btn">返回文件列表</button></a>
	</center>      
            <p class="right">Powered by © 2018</p>
   
        </div>
    </div>
</div>
<script src="./js/jquery.js"></script>
<script src="./js/agree.js"></script>
<script src="./js/reset.js"></script>
<div style="text-align:center;">
</div>
</form>
</body>
</html>
