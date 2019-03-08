package club.zby.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import redis.clients.jedis.Jedis;

import club.zby.entity.AddUser;
import club.zby.entity.Admin;

import club.zby.redis.JsonUtils;
import club.zby.service.UserService;
import club.zby.service.impl.UserServiceImpl;

public class AddUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Jedis jedis = new Jedis("127.0.0.1", 6379);
	public AddUserServlet() {
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		 request.setCharacterEncoding("utf-8");
			
		 response.setContentType("text/html;charset=utf-8");
		
		UserService userService = new UserServiceImpl();
		
		String name = request.getParameter("username");
		String password = request.getParameter("password");

		AddUser adduser = new AddUser(name);
		Admin addusers = new Admin(name, password);

		List<AddUser> users = userService.queryAddUser(adduser);

		System.out.println();
		if (null == users || users.size() == 0) {

			boolean result = userService.adduserd(addusers);
			if (result) {
				try {
					List< Map<String,Object>> list = new ArrayList<Map<String, Object>>();
		        	Map<String,Object> map = new HashMap<String, Object>();
		        	Admin student = new Admin(name,password);
		            map.put("name", name);
					map.put("password", password);
		            list.add(map);		   
			        jedis.hset("user",String.valueOf(name),JsonUtils.objectToJsonStr(list));
					jedis.close();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					response.sendRedirect("break_off.jsp");
				}
				request.getRequestDispatcher("index.jsp?flag=addok").forward(request,
						response);
				
			} else {
				request.getRequestDispatcher("index.jsp?flag=adderror")
						.forward(request, response);
			}

		} else {
			request.getRequestDispatcher("index.jsp?flag=added").forward(request,
					response);
		}

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);

	}
}
