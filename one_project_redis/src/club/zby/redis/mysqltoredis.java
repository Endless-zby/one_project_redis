package club.zby.redis;

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

import redis.clients.jedis.Jedis;

public class mysqltoredis {
	private static final String DRIVER = "com.mysql.jdbc.Driver";
	private static final String URL = "jdbc:mysql://localhost:3306/zby";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "zby123456";

	private static Jedis jedis = new Jedis("127.0.0.1", 6379);
	// TODO Auto-generated method stub
	
	
			//将mysql中student表的全部数据，放入redis    （采取逐个放入）
		    public  static void mysqlToRedis()  throws Exception{
		        Connection connection = getConnection();
		        //获取一个pstmt对象
		        PreparedStatement pstmt = connection.prepareStatement("select * from zby1 ");
		        //mysql -> redis :mysql ->查->redis
		        ResultSet rs =pstmt.executeQuery();//将查询的结果保存在了rs中
		       
		        while(rs.next()){//判断是否还有元素
		        	List< Map<String,Object>> list = new ArrayList<Map<String, Object>>();
		        	String name = rs.getString("name");
					String password = rs.getString("password");
					String email = rs.getString("email");
					String idname = rs.getString("idname");
					String data = rs.getString("data");
					int age = rs.getInt("age");
					String school = rs.getString("school");
		            Map<String,Object> map = new HashMap<String, Object>();
		            map.put("name", name);
					map.put("password", password);
					map.put("email", email);
					map.put("idname", idname);
					map.put("data", data);
					map.put("age", age);
					map.put("school", school);
		            list.add(map);
		            String jsonStr =  JsonUtils.objectToJsonStr(list) ;
			        //System.out.println(jsonStr);
			        jedis.hset("user",String.valueOf(name),jsonStr);
			        System.out.println(list);
		        }
		       
		        /*
		        目标：将数据存入redis，redis又要求数据格式必须是key -value
		                student - 数据的json格式
		         */
		        //将list处转成“数据的json格式”

		        closeAll(rs,pstmt,connection);
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

		    public static void closeAll(ResultSet rs ,Statement stmt,Connection connection){
		        try {
		            if(rs!=null)rs.close();
		            if(stmt!=null)stmt.close();
		            if(stmt!=null)connection.close();
		        } catch (SQLException e) {
		            e.printStackTrace();
		        } catch (Exception e) {
		            e.printStackTrace();
		        }

		    }
}
