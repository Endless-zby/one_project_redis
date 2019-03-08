package club.zby.servletListener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class StatisticsContextListener implements ServletContextListener {
	private java.util.Timer timer = null;

	/**
	 * 这个方法在Web应用服务做好接受请求的时候被调用。
	 * 
	 * @see javax.servlet.ServletContextListener#contextInitialized(javax.servlet.ServletContextEvent)
	 */
	public void contextInitialized(ServletContextEvent event) {
		timer = new java.util.Timer(true);
		event.getServletContext().log("定时器已启动");
		timer.schedule(new StatisticsTask(event.getServletContext()), 0, 60 * 60 * 1000);// 每隔1小时对redis更新一次
		event.getServletContext().log("已经添加任务调度表");
	}

	/**
	 * 这个方法在Web应用服务被移除，没有能力再接受请求的时候被调用。
	 * 
	 * @see javax.servlet.ServletContextListener#contextDestroyed(javax.servlet.ServletContextEvent)
	 */
	public void contextDestroyed(ServletContextEvent event) {
		timer.cancel();
		event.getServletContext().log("定时器销毁");
	}
}
