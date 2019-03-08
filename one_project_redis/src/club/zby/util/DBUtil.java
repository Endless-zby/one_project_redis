package club.zby.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;



public class DBUtil {
	public static ResultSet showAdmin(String sql , Object[] params)throws SQLException {
        PreparedStatement pstmt = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            //"jdbc:mysql://cdb-6kot9g5e.cd.tencentcdb.com:10007/zby?characterEncoding=utf8&useSSL=true", "root", "zby123456"
            Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/zby?useUnicode=true&characterEncoding=utf-8", "root", "zby123456");
            pstmt = connection.prepareStatement(sql) ;
           
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return pstmt.executeQuery() ;//rs

    }
	
    public static ResultSet executeQuery(String sql , Object[] params)throws SQLException {
        PreparedStatement pstmt = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/zby?useUnicode=true&characterEncoding=utf-8", "root", "zby123456");
            pstmt = connection.prepareStatement(sql) ;
            for(int i=0;i<params.length;i++){
                pstmt.setObject(i+1, params[i]);
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return pstmt.executeQuery() ;//rs

    }

    public static void executeUpdate(String sql,Object[] params) throws SQLException{
        PreparedStatement pstmt = null ;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/zby?useUnicode=true&characterEncoding=utf-8", "root", "zby123456");
//			"insert into student values(?,?)"
            pstmt = connection.prepareStatement(sql) ;

            for(int i=0;i<params.length;i++){
                pstmt.setObject(i+1, params[i]);
            }

            pstmt.executeUpdate() ;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
    
   
    
}

