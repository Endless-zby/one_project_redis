import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class DownServlet extends HttpServlet {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//设置编码格式
				request.setCharacterEncoding("utf-8");
				response.setContentType("text/html;charset=utf-8");
				//接受下载名
				/*String filename = request.getParameter("filename");*/
				String wenjianname = request.getParameter("username");//前台用户名
				String filename = new String(request.getParameter("filename").getBytes("ISO8859-1"),"UTF-8");
				//得到当前路径
				String path = this.getServletContext().getRealPath(wenjianname);
				//创建全路径
				File file = new File(path+File.separator+filename);
				//创建输入流
				FileInputStream inputStream = new FileInputStream(file);
				
				//创建输出流
				
				ServletOutputStream outputStream = response.getOutputStream();
				
				response.setHeader("Content-Disposition", "attachment;filename="
						+ URLEncoder.encode(filename, "UTF-8"));

				
				
				byte[] b = new byte[1024];
				
				while(inputStream.read(b)!=-1){
					outputStream.write(b);				
				}
				inputStream.close();
				outputStream.close();
				
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
doGet(request, response);
		
	}
}
	
