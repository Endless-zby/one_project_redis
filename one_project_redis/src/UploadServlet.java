
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class UploadServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {


		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		DiskFileItemFactory factory= new DiskFileItemFactory();
		ServletFileUpload fileupload=new ServletFileUpload(factory);
		List<FileItem> lists=null;
		
			try {
				lists=fileupload.parseRequest(request);
				
				for(FileItem fileItem : lists){
					
					if(((FileItem) fileItem).isFormField()){
						
						System.out.println("表单域");
						
					}else{
						String wenjianname = request.getParameter("username");//前台用户名
						String name = new File(fileItem.getName()).getName();
						
						String path = this.getServletContext().getRealPath(wenjianname);
						
						File file = new File(path+File.separator+name);
						
						fileItem.write(file);
						
						System.out.println("上传成功");
						
					}
			
			}
			} catch (FileUploadException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			HttpSession session = request.getSession();
			session.setAttribute("username", new String(request.getParameter("username").getBytes("iso8859-1"),"utf-8"));
			request.getRequestDispatcher("ShowServlet").forward(request, response);
		
			
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request,response);
	}

}
