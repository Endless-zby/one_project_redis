package club.zby.emailcode;

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;

import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class MailServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private MimeMessage createMimeMessage(Session session, String send,
			String receive, int numcode) throws Exception {
		// TODO Auto-generated method stub
		MimeMessage message = new MimeMessage(session);
		// 邮件：标题、正文、收件人 {附件、图片}
		Address address = new InternetAddress(send, "zby云", "UTF-8");
		message.setFrom(address);
		message.setSubject("密码找回", "UTF-8");

		message.setContent("你好，你的验证码是:" + numcode + ",悄悄的！！",
				"text/html;charset=utf-8");

		// 收件人类型：普通收件人（TO），抄送(CC)，密送(BCC)；
		message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(
				receive, "收件人A", "UTF-8"));
		/*
		 * message.setRecipient(MimeMessage.RecipientType.TO,new
		 * InternetAddress(cReceive,"抄送人B","UTF-8"));
		 * message.setRecipient(MimeMessage.RecipientType.TO,new
		 * InternetAddress(mReceive,"密送人C","UTF-8"));
		 */
		message.setSentDate(new Date());// 设置发送时间

		message.saveChanges();// 保存邮件

		return message;
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String useremail = request.getParameter("email"); // 获取前台的邮箱
		String useremaill = "'" + useremail + "'";
		System.out.println(useremaill);

		try {

			// 加载并注册驱动程序

			Class.forName("com.mysql.jdbc.Driver");
			ResultSet rs = null;
			// 连接数据库
			Connection conn = (Connection) DriverManager
					.getConnection(
							"jdbc:mysql://127.0.0.1:3306/zby?useUnicode=true&characterEncoding=utf-8",
							"root", "zby123456");
			// sql语句
			String sql = "select * from zby1 where email=" + useremaill;
			System.out.println(sql);
			// 预置对象
			PreparedStatement pstmt = (PreparedStatement) conn
					.prepareStatement(sql);
			// 设置占位符值
			// pstmt.setString(1, useremaill);
			// 执行sql语句，返回影响行数
			rs = pstmt.executeQuery(sql);
			if (rs.next()) {

				Properties props = new Properties();
				props.setProperty("mail.transport.protocol", "smtp");// 使用的邮件协议
				props.setProperty("mail.smtp.host", "smtp.qq.com");// 协议地址
				props.setProperty("mail.smtp.port", "465");// 协议端口
				props.setProperty("mail.smtp.auth", "true");// 需要授权
				// QQ：SSL安全认证
				props.setProperty("mail.smtp.scoketFactory.class","javax.net.ssl.SSLSocketFactory");
				props.setProperty("mail.smtp.socketFactory.port", "465");
				props.setProperty("mail.smtp.ssl.enable", "true");
				Session session = Session.getInstance(props);
				session.setDebug(true);//开启日志
				// 创建邮件

				try {
					int numcode = (int) (Math.random() * 8999) + 1000;// 生成随机验证码
																		// 传到MailServletCode中等待jsp返回验证
					MimeMessage message = createMimeMessage(session,
							"381016296@qq.com", useremail, numcode);
					Transport transport = session.getTransport();
					transport.connect("381016296@qq.com", "xpvhpxhcarfmbijc");// 建立连接，其中密码以授权码方式填写
					transport.sendMessage(message, message.getAllRecipients());

					// String emailcode = String.valueOf(numcode);
					// System.out.println("int转String后：" + emailcode);
					request.setAttribute("numcode", numcode);
					request.setAttribute("useremail", useremail);
					request.getRequestDispatcher("getpass1.jsp?type=true")
							.forward(request, response);
					transport.close();

				} catch (NoSuchProviderException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}// 建立连接对象
				catch (MessagingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

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

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);

	}
}
