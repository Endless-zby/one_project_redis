package club.zby.servlet;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import club.zby.entity.Information;
import club.zby.redis.mysqltoredis;
import club.zby.service.UserService;
import club.zby.service.impl.UserServiceImpl;

public class updateServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;




	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		 request.setCharacterEncoding("utf-8");
			
		 response.setContentType("text/html;charset=utf-8");
		
		UserService userService = new UserServiceImpl() ;
			String name = request.getParameter("name");
			String password = request.getParameter("password");
			//String idname = new String(request.getParameter("idname"));
			String idname = request.getParameter("idname");
			String email = request.getParameter("email");
			String data = request.getParameter("data");
			String age = request.getParameter("age");
			String school = request.getParameter("school");
			System.out.println(name+";"+password+";"+idname+";"+email+";"+data+";"+age+";"+school);
			System.out.println(name);
			
			
			Information information = new Information();
			
			information.setName(name);
			information.setPassword(password);
			information.setIdname(idname);
			information.setEmail(email);
			information.setData(data);
			information.setAge(age);
			information.setSchool(school);
			
			userService.updateuser(name,information);
			
			request.getRequestDispatcher("UserServlet").forward(request, response);
			try {
				club.zby.redis.mysqltoredis.mysqlToRedis();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	}


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
				doGet(request, response);

	}



}
