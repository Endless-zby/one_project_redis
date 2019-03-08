package club.zby.servletListener;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimerTask;

import javax.servlet.ServletContext;

import club.zby.redis.JsonUtils;
import club.zby.redis.mysqltoredis;



import redis.clients.jedis.Jedis;

public class StatisticsTask extends TimerTask {
	private static boolean isRunning = false;
	private ServletContext context = null;
	private static final String DRIVER = "com.mysql.jdbc.Driver";
	private static final String URL = "jdbc:mysql://localhost:3306/zby";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "zby123456";

	public StatisticsTask(ServletContext context) {
		this.context = context;
	}

	@Override
	public void run() {
		if (!isRunning) {			
				isRunning = true;
				context.log("��ʼִ��ָ������");
				// TODO ����Զ������ϸ����
				try {
					executeTask();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// ָ������ִ�н���
				isRunning = false;
				context.log("ָ������ִ�н���");			
		} 
	}

	/** * ִ������ */
	public void executeTask() throws Exception {
		Connection connection = getConnection();
		club.zby.redis.mysqltoredis.mysqlToRedis();  //����redis�е�mysqltoRedis���ж�ʱ����
        PreparedStatement number = connection.prepareStatement("select * from count");
        ResultSet counts = number.executeQuery();
        while(counts.next()){
        	int count = counts.getInt("number")+1;
        	PreparedStatement updata = connection
					.prepareStatement("update count set number = " + count);
        	updata.executeUpdate();	        	
        }
        counts.close();       
	}
	public static Connection getConnection(){
        Connection connection = null;
        try {
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }catch (SQLException e) {
            e.printStackTrace();
        }catch (Exception e) {
            e.printStackTrace();
        }
        return connection ;
    }
}
