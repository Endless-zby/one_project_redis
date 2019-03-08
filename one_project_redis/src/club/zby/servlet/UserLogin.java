package club.zby.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import redis.clients.jedis.Jedis;

import club.zby.redis.JsonUtils;

public class UserLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static Jedis jedis = new Jedis("127.0.0.1", 6379);
	public UserLogin() {
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");

		response.setContentType("text/html;charset=utf-8");
		String name = request.getParameter("username");
		String password = request.getParameter("password");	


		// 查redis
		/*
		 * 例如：查找用户名为999的用户（先按照用户名查找field）
		 *  
		 */
		if(jedis.hexists("user", name)){
			String UserData = jedis.hget("user", name);
			//将Json字符串解析成一个List对象
	//		System.out.println(studentData);
			List<Map<String, Object>> list = null;
			try {
				list = JsonUtils.jsonStrToObject(UserData,List.class);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				jedis.close();
				response.sendRedirect("break_off.jsp");
			}
			
			for (Map<String, Object> map : list) {
				if (((String) map.get("password")).equals(password)) {
					jedis.close();
					PrintWriter out = response.getWriter();

					HttpSession session = request.getSession();
					session.setAttribute("name", name);
					request.getRequestDispatcher("ShowServlet").forward(request,
							response);
					
					out.close();
				}else {
					jedis.close();
					PrintWriter out = response.getWriter();
					response.sendRedirect("index.jsp?flag=loginerror");					
					out.close();
				}
				break;
			}
			
		}else {
			jedis.close();
			PrintWriter out = response.getWriter();
			response.sendRedirect("index.jsp?flag=regerror");					
			out.close();
			
		}
		jedis.close();			
	}



	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);

	}
}
