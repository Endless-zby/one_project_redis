package club.zby.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import club.zby.entity.Admin;

import club.zby.service.UserService;
import club.zby.service.impl.UserServiceImpl;

public class AdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public AdminServlet() {
    }


	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		 request.setCharacterEncoding("utf-8");
			
		 response.setContentType("text/html;charset=utf-8");
		
		UserService userService = new UserServiceImpl();
		String name  = request.getParameter("name");
		String password = request.getParameter("password");
		
		
		
		Admin admin = new Admin(name,password);
		
		List<Admin> users = userService.queryAdmin(admin) ;
	System.out.println();
	if(null == users || users.size() ==0 ){		
		request.getRequestDispatcher("admin.jsp?flag=error").forward(request,response);
		}else{
			request.getRequestDispatcher("UserServlet").forward(request,response);
		}
		
		

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);

	
}
}
