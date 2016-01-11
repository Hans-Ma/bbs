package com.bbs.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.bbs.model.User;
import com.bbs.util.JDBCUtil;



public class UserDao {

	public Connection conn = null;
	public PreparedStatement psmt = null;
	public Statement stmt = null;
	public ResultSet rs = null;

//	public static void main(String arg[]){
//		Connection conn = JDBCUtil.getConnection(); 
//		if(conn != null){
//			System.out.println("已连接");
//		}else {
//			System.out.println("连接失败");
//		}
//	}
	
	public User login(User user) {
		User users = null;
		//定义sql
		String sql = "select * from users where u_name=? and u_password=?";
		//获取数据库连接
		conn = JDBCUtil.getConnection();
		try {
			//编译sql
			psmt = conn.prepareStatement(sql);
			//设置参数
			psmt.setString(1, user.getUname());
			psmt.setString(2, user.getUpassword());
			//执行sql
			rs = psmt.executeQuery();
			//获取结果集
			while (rs.next()) {
				users = new User();
				int uid = rs.getInt("u_id");
				String uname = rs.getString("u_name");
				String upassword = rs.getString("u_password");
				int ustatus = rs.getInt("u_status");
				user.setUid(uid);
				users.setUname(uname);
				users.setUpassword(upassword);
				users.setUstatus(ustatus);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			//最后要关闭连接
			JDBCUtil.close(conn, stmt, psmt, rs);
		}
		return users;
	}

	public List<User> select(String searchVal) {
		List<User> userList = new ArrayList<User>();
		String sql = "select * from users where 1=1 ";
		if(searchVal !=null && !"".equals(searchVal)){
			sql += " and u_name like '%"+searchVal+"%' ";
		}
		sql += " order by u_id desc";
		User users = null;
		conn = JDBCUtil.getConnection();
		try {
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			while (rs.next()) {

				int uid = rs.getInt("u_id");
				String uname = rs.getString("u_name");
				String upassword = rs.getString("u_password");
				int ustatus = rs.getInt("u_status");

				users = new User();
				users.setUid(uid);
				users.setUname(uname);
				users.setUpassword(upassword);
				users.setUstatus(ustatus);
				userList.add(users);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCUtil.close(conn, stmt, psmt, rs);
		}
		return userList;
	}

	public int save(User user) {
		String sql = "insert into users values(default,?,?,?)";
		conn = JDBCUtil.getConnection();
		int count = 0;
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, user.getUname());
			psmt.setString(2, user.getUpassword());
			psmt.setInt(3, user.getUstatus());
			count = psmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCUtil.close(conn, stmt, psmt, rs);
		}
		return count;
	}
	
	public int delete(User user){
		String sql="delete from users where u_id=?";
		conn=JDBCUtil.getConnection();
		int count = 0;
		try{
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, user.getUid());
			count = psmt.executeUpdate();
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			JDBCUtil.close(conn, stmt, psmt, rs);
		}
		return count;
	}

	public User selectByUid(User user) {
		// TODO Auto-generated method stub
		String sql="select * from users where u_id=?";
		conn=JDBCUtil.getConnection();
		User users=null;
		try{
			psmt=conn.prepareStatement(sql);
			psmt.setInt(1, user.getUid());
			rs=psmt.executeQuery();
			while (rs.next()) {

				int uid = rs.getInt("u_id");
				String uname = rs.getString("u_name");
				String upassword = rs.getString("u_password");
				int ustatus = rs.getInt("u_status");

				users = new User();
				users.setUid(uid);
				users.setUname(uname);
				users.setUpassword(upassword);
				users.setUstatus(ustatus);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			JDBCUtil.close(conn, stmt, psmt, rs);
		}
		return users;
	}

	public int update(User user) {
		// TODO Auto-generated method stub
		String sql="update users set u_name=?, u_password=? where u_id=?";
		conn=JDBCUtil.getConnection();
		int count = 0;
		try{
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, user.getUname());
			psmt.setString(2, user.getUpassword());
			psmt.setInt(3, user.getUid());
			count = psmt.executeUpdate();
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			JDBCUtil.close(conn, stmt, psmt, rs);
		}
		return count;
	}

	public int selectByUname(String uname) {
		// TODO Auto-generated method stub
		int uid = 0;
		String sql="select * from users where u_name=?";
		conn=JDBCUtil.getConnection();
		try{
			psmt=conn.prepareStatement(sql);
			psmt.setString(1, uname);
			rs=psmt.executeQuery();
			while (rs.next()) {
				uid = rs.getInt("u_id");
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			JDBCUtil.close(conn, stmt, psmt, rs);
		}
		return uid;
	}

	public int count() {
		// TODO Auto-generated method stub
		String sql = "select count(*) count from users";
		conn = JDBCUtil.getConnection();
		int count = 0;
		try {
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			rs.next();
			count = rs.getInt("count");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(conn, stmt, psmt, rs);
		}
		return count;
	}

	public List<User> selectPage(int countMax, int countMin) {
		// TODO Auto-generated method stub
		List<User> userList = new ArrayList<User>();
		//String sql = "select * from(select u.*,rownum rn from(select u_id, u_name,u_password,u_status from users order by u_id) u where rownum<="+countMax+") where rn>"+countMin;
		String sql = "select u_id, u_name,u_password,u_status from users order by u_id limit "+countMin+","+countMax+"";
		User users = null;
		conn = JDBCUtil.getConnection();
		try {
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			while (rs.next()) {

				int uid = rs.getInt("u_id");
				String uname = rs.getString("u_name");
				String upassword = rs.getString("u_password");
				int ustatus = rs.getInt("u_status");

				users = new User();
				users.setUid(uid);
				users.setUname(uname);
				users.setUpassword(upassword);
				users.setUstatus(ustatus);
				userList.add(users);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCUtil.close(conn, stmt, psmt, rs);
		}
		return userList;
	}
}
