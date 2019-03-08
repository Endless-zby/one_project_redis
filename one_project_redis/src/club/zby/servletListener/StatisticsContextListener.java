package club.zby.servletListener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class StatisticsContextListener implements ServletContextListener {
	private java.util.Timer timer = null;

	/**
	 * ���������WebӦ�÷������ý��������ʱ�򱻵��á�
	 * 
	 * @see javax.servlet.ServletContextListener#contextInitialized(javax.servlet.ServletContextEvent)
	 */
	public void contextInitialized(ServletContextEvent event) {
		timer = new java.util.Timer(true);
		event.getServletContext().log("��ʱ��������");
		timer.schedule(new StatisticsTask(event.getServletContext()), 0, 60 * 60 * 1000);// ÿ��1Сʱ��redis����һ��
		event.getServletContext().log("�Ѿ����������ȱ�");
	}

	/**
	 * ���������WebӦ�÷����Ƴ���û�������ٽ��������ʱ�򱻵��á�
	 * 
	 * @see javax.servlet.ServletContextListener#contextDestroyed(javax.servlet.ServletContextEvent)
	 */
	public void contextDestroyed(ServletContextEvent event) {
		timer.cancel();
		event.getServletContext().log("��ʱ������");
	}
}
