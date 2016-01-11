package com.bbs.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCUtil {
//	private static String url="jdbc:oracle:thin:@127.0.0.1:1521:orcl";
//	private static String Driver="oracle.jdbc.driver.OracleDriver";
//	private static String user="scott";
//	private static String pwd="a123";
	
	//mysql
	//数据库驱动类型
	 private static final String Driver="com.mysql.jdbc.Driver";
	 //数据库地址
	 private static final String url="jdbc:mysql://localhost:3306/bbs?useUnicode=true&characterEncoding=utf8";
	 //用户
	 private static final String user="root";
	 //密码
	 private static final String pwd="root";
	
	 /**
	  * 连接数据库
	  * @return
	  */
	public static Connection getConnection(){
		Connection conn=null;
		try {
			//虚拟机装载类
			Class.forName(Driver);
			//连接数据库
			conn=DriverManager.getConnection(url, user, pwd);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch(SQLException e){
			e.printStackTrace();
		}
		return conn;
	}
	
	/**
	 * 关闭数据库连接
	 * @param conn
	 * @param stmt
	 * @param psmt
	 * @param rs
	 */
	public static void close(Connection conn,Statement stmt,PreparedStatement psmt,ResultSet rs){
		try{
			if(rs!=null){
				rs.close();
			}
			if(psmt!=null){
				psmt.close();
			}
			if(stmt!=null){
				stmt.close();
			}
			if(conn!=null){
				conn.close();
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		
	}
}
