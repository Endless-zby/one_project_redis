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
		// �ʼ������⡢���ġ��ռ��� {������ͼƬ}
		Address address = new InternetAddress(send, "zby��", "UTF-8");
		message.setFrom(address);
		message.setSubject("�����һ�", "UTF-8");

		message.setContent("��ã������֤����:" + numcode + ",���ĵģ���",
				"text/html;charset=utf-8");

		// �ռ������ͣ���ͨ�ռ��ˣ�TO��������(CC)������(BCC)��
		message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(
				receive, "�ռ���A", "UTF-8"));
		/*
		 * message.setRecipient(MimeMessage.RecipientType.TO,new
		 * InternetAddress(cReceive,"������B","UTF-8"));
		 * message.setRecipient(MimeMessage.RecipientType.TO,new
		 * InternetAddress(mReceive,"������C","UTF-8"));
		 */
		message.setSentDate(new Date());// ���÷���ʱ��

		message.saveChanges();// �����ʼ�

		return message;
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String useremail = request.getParameter("email"); // ��ȡǰ̨������
		String useremaill = "'" + useremail + "'";
		System.out.println(useremaill);

		try {

			// ���ز�ע����������

			Class.forName("com.mysql.jdbc.Driver");
			ResultSet rs = null;
			// �������ݿ�
			Connection conn = (Connection) DriverManager
					.getConnection(
							"jdbc:mysql://127.0.0.1:3306/zby?useUnicode=true&characterEncoding=utf-8",
							"root", "zby123456");
			// sql���
			String sql = "select * from zby1 where email=" + useremaill;
			System.out.println(sql);
			// Ԥ�ö���
			PreparedStatement pstmt = (PreparedStatement) conn
					.prepareStatement(sql);
			// ����ռλ��ֵ
			// pstmt.setString(1, useremaill);
			// ִ��sql��䣬����Ӱ������
			rs = pstmt.executeQuery(sql);
			if (rs.next()) {

				Properties props = new Properties();
				props.setProperty("mail.transport.protocol", "smtp");// ʹ�õ��ʼ�Э��
				props.setProperty("mail.smtp.host", "smtp.qq.com");// Э���ַ
				props.setProperty("mail.smtp.port", "465");// Э��˿�
				props.setProperty("mail.smtp.auth", "true");// ��Ҫ��Ȩ
				// QQ��SSL��ȫ��֤
				props.setProperty("mail.smtp.scoketFactory.class","javax.net.ssl.SSLSocketFactory");
				props.setProperty("mail.smtp.socketFactory.port", "465");
				props.setProperty("mail.smtp.ssl.enable", "true");
				Session session = Session.getInstance(props);
				session.setDebug(true);//������־
				// �����ʼ�

				try {
					int numcode = (int) (Math.random() * 8999) + 1000;// ���������֤��
																		// ����MailServletCode�еȴ�jsp������֤
					MimeMessage message = createMimeMessage(session,
							"381016296@qq.com", useremail, numcode);
					Transport transport = session.getTransport();
					transport.connect("381016296@qq.com", "xpvhpxhcarfmbijc");// �������ӣ�������������Ȩ�뷽ʽ��д
					transport.sendMessage(message, message.getAllRecipients());

					// String emailcode = String.valueOf(numcode);
					// System.out.println("intתString��" + emailcode);
					request.setAttribute("numcode", numcode);
					request.setAttribute("useremail", useremail);
					request.getRequestDispatcher("getpass1.jsp?type=true")
							.forward(request, response);
					transport.close();

				} catch (NoSuchProviderException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}// �������Ӷ���
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
