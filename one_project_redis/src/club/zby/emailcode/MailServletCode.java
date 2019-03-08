package club.zby.emailcode;

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import club.zby.redis.mysqltoredis;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class MailServletCode extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		// System.out.println("�޸ĺ������Ϊ��"+password);
		response.setContentType("text/html;charset=utf-8");

		/*
		 * PrintWriter out = response.getWriter(); out.println(
		 * "<html><head><title>ConcurrentServlet</title></head><body><h1>");
		 * out.println("�û�" + email + "�޸ĺ������Ϊ��" + password);
		 */

		try {

			// ���ز�ע����������

			Class.forName("com.mysql.jdbc.Driver");

			// �������ݿ�
			Connection conn = (Connection) DriverManager
					.getConnection(
							"jdbc:mysql://127.0.0.1:3306/zby?useUnicode=true&characterEncoding=utf-8",
							"root", "zby123456");
			// sql���
			String sql = "update zby1 set password=? where email=?";
			// Ԥ�ö���
			PreparedStatement pstmt = (PreparedStatement) conn
					.prepareStatement(sql);
			// ����ռλ��ֵ
			pstmt.setString(1, password);
			pstmt.setString(2, email);
			// ִ��sql��䣬����Ӱ������
			int res = pstmt.executeUpdate();
			if (res > 0) {
				try {
					mysqltoredis.mysqlToRedis();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				request.getRequestDispatcher("index.jsp?flag=updateok")
						.forward(request, response);
			} else {
				request.getRequestDispatcher("index.jsp?flag=updateerror")
						.forward(request, response);
			}
			pstmt.close();
			conn.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		/*
		 * out.println("</h1></body></html>"); out.close();
		 */
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
