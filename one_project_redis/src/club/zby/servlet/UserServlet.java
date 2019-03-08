package club.zby.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import club.zby.entity.User;
import club.zby.service.UserService;
import club.zby.service.impl.UserServiceImpl;

public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public UserServlet() {
    }

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		 request.setCharacterEncoding("utf-8");
			
		 response.setContentType("text/html;charset=utf-8");
		
		UserService userService = new UserServiceImpl();
		List<User> users = userService.queryUser();
		
		request.setAttribute("usersdb", users); //map:key - value  put		
		request.getRequestDispatcher("adminshow.jsp").forward(request,response);

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);

}
}
