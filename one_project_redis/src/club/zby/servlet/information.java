package club.zby.servlet;

import java.io.IOException;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import club.zby.entity.Information;
import club.zby.service.UserService;
import club.zby.service.impl.UserServiceImpl;

public class information extends HttpServlet {



	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		 request.setCharacterEncoding("utf-8");
			
		 response.setContentType("text/html;charset=utf-8");
		
		UserService userService = new UserServiceImpl();
		String name  = request.getParameter("name");
		System.out.println("------"+ name);
		
		Information information = new Information(name,null,null,null,null,null,null);		
		
		List<Information> usersd = userService.queryInformation(information);
		
		
		request.setAttribute("usersd", usersd); //map:key - value  put		
		request.getRequestDispatcher("Informations.jsp").forward(request,response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {


	}
}



