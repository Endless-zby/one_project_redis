import java.io.File;
import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DelServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//设置编码格式
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		//String username = new String(request.getParameter("username").getBytes("iso8859-1"),"utf-8");
        //得到需要删除的文件
		String wenjianname = request.getParameter("username");//前台用户名
		String filename = request.getParameter("filename");
		filename = new String(filename.getBytes("ISO8859-1"),"UTF-8");
		System.out.println(filename);
		String path = this.getServletContext().getRealPath(wenjianname);
		File file = new File(path+File.separator+filename);
		boolean delete = file.delete();
		if (delete) {
			request.getRequestDispatcher("ShowServlet").forward(request, response);
		}else {
			response.sendRedirect("errorr.jsp");
		}
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);


	}

}
