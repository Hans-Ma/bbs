package com.bbs.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.bbs.model.Section;
import com.bbs.model.Topic;
import com.bbs.model.User;
import com.bbs.util.JDBCUtil;

public class TopicDao {
	public Connection conn=null;
	public PreparedStatement psmt=null;
	public Statement stmt=null;
	public ResultSet rs=null;
	
	public List<Topic> select(){
		List<Topic> topicList = new ArrayList<Topic>();
		String sql="select " +
				"t.t_id tid, " +
				"s.s_name sname, " +
				"t.t_title ttitle, " +
				"u.u_name uname, " +
				"t.t_contents tcontents, " +
				"t.t_time ttime, " +
				"t.t_number tnumber " +
				"from topic t,users u,section s " +
				"where t.t_s_id=s.s_id and t.t_u_id=u.u_id";
		conn=JDBCUtil.getConnection();
		try{
			psmt=conn.prepareStatement(sql);
			rs=psmt.executeQuery();
			while(rs.next()){
				int tid = rs.getInt("tid");
				String sname = rs.getString("sname");
				String ttitle = rs.getString("ttitle");
				String uname = rs.getString("uname");
				String tcontents = rs.getString("tcontents");
				Timestamp ttime = rs.getTimestamp("ttime");
				int tnumber = rs.getInt("tnumber");
				
				Topic topic = new Topic();
				topic.setTid(tid);
				Section section = new Section();
				section.setSname(sname);
				topic.setTtitle(ttitle);
				User user = new User();
				user.setUname(uname);
				topic.setUser(user);
				topic.setSection(section);
				topic.setTcontents(tcontents);
				topic.setTnumber(tnumber);
				topic.setTtime(ttime);
				topicList.add(topic);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			JDBCUtil.close(conn, stmt, psmt, rs);
		}
		return topicList;
	}

	public int save(Topic topic) {
		// TODO Auto-generated method stub
		String sql="insert into topic values(default,?,?,?,default,default,?)";
		conn = JDBCUtil.getConnection();
		int count = 0;
		try{
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, topic.getSection().getSid());
			psmt.setInt(2, topic.getUser().getUid());
			psmt.setString(3, topic.getTcontents());
			psmt.setString(4, topic.getTtitle());
			count = psmt.executeUpdate();
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			JDBCUtil.close(conn, stmt, psmt, rs);
		}
		return count;
	}

	public Topic selectByTid(Topic topic) {
		// TODO Auto-generated method stub
		String sql ="select t.t_id tid, s.s_name sname, t.t_title ttitle,t.t_s_id sid,t.t_time ttime, u.u_name uname, t.t_contents tcontents, t.t_number tnumber from topic t,users u,section s where t.t_s_id=s.s_id and t.t_u_id=u.u_id and t.t_id=?";
		conn = JDBCUtil.getConnection();
		Topic topics = null;
		try{
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, topic.getTid());
			rs = psmt.executeQuery();
			while(rs.next()){
				int tid = rs.getInt("tid");
				String ttitle = rs.getString("ttitle");
				String sname = rs.getString("sname");
				String uname = rs.getString("uname");
				String tcontents = rs.getString("tcontents");
				int tnumber = rs.getInt("tnumber");
				int sid = rs.getInt("sid");
				Timestamp ttime = rs.getTimestamp("ttime");
				
				topics = new Topic();
				topics.setTid(tid);
				topics.setTtitle(ttitle);
				topics.setTtime(ttime);
				
				Section section = new Section();
				section.setSname(sname);
				section.setSid(sid);
				topics.setSection(section);
				
				User user = new User();
				user.setUname(uname);
				topics.setUser(user);
				topics.setTcontents(tcontents);
				topics.setTnumber(tnumber);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			JDBCUtil.close(conn, stmt, psmt, rs);
		}
		return topics;
	}

	public List<Topic> selectPage(int countMax, int countMin) {
		// TODO Auto-generated method stub
		List<Topic> topicList = new ArrayList<Topic>();
	//	String sql="select * from (select tus.*,rownum rn from (select t.t_id tid,s.s_id sid, s.s_name sname, t.t_title ttitle, u.u_name uname, t.t_contents tcontents, t.t_time ttime, t.t_number tnumber from topic t,users u,section s  where t.t_s_id=s.s_id and t.t_u_id=u.u_id order by t.t_id) tus where rownum<="+countMax+") where rn>"+countMin;
		String sql = "select t.t_id tid,s.s_id sid, s.s_name sname, t.t_title ttitle, u.u_name uname, t.t_contents tcontents, t.t_time ttime, t.t_number tnumber from topic t,users u,section s  where t.t_s_id=s.s_id and t.t_u_id=u.u_id order by t.t_id ";
			if(countMax+countMin > 0){
				sql +=" limit "+countMin+","+countMax+"";
			}
		conn=JDBCUtil.getConnection();
		try{
			psmt=conn.prepareStatement(sql);
			rs=psmt.executeQuery();
			while(rs.next()){
				int tid = rs.getInt("tid");
				int sid = rs.getInt("sid");
				String sname = rs.getString("sname");
				String ttitle = rs.getString("ttitle");
				String uname = rs.getString("uname");
				String tcontents = rs.getString("tcontents");
				Timestamp ttime = rs.getTimestamp("ttime");
				int tnumber = rs.getInt("tnumber");
				
				Topic topic = new Topic();
				topic.setTid(tid);
				
				Section section = new Section();
				section.setSname(sname);
				section.setSid(sid);
				
				topic.setTtitle(ttitle);
				
				User user = new User();
				user.setUname(uname);
				
				topic.setUser(user);
				topic.setSection(section);
				
				topic.setTcontents(tcontents);
				topic.setTnumber(tnumber);
				topic.setTtime(ttime);
				topicList.add(topic);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			JDBCUtil.close(conn, stmt, psmt, rs);
		}
		return topicList;
	}

	public List<Topic> selectPage1(int countMax, int countMin,int tsid) {
		// TODO Auto-generated method stub
		List<Topic> topicList = new ArrayList<Topic>();
	//	String sql="select * from (select tus.*,rownum rn from (select t.t_id tid,s.s_id sid, s.s_name sname, t.t_title ttitle, u.u_name uname, t.t_contents tcontents, t.t_time ttime, t.t_number tnumber from topic t,users u,section s  where t.t_s_id=s.s_id and t.t_u_id=u.u_id and t_s_id=? order by t.t_id) tus where rownum<="+countMax+") where rn>"+countMin;
		String sql = "select t.t_id tid,s.s_id sid, s.s_name sname, t.t_title ttitle, u.u_name uname, t.t_contents tcontents, t.t_time ttime, t.t_number tnumber from topic t,users u,section s "
				+ " where t.t_s_id=s.s_id and t.t_u_id=u.u_id and t_s_id=? order by t.t_id ";
		
		if(countMax+countMin > 0){
			sql += "limit "+countMin+","+ countMax+"";
		}
		conn=JDBCUtil.getConnection();
		try{
			psmt=conn.prepareStatement(sql);
			psmt.setInt(1, tsid);
			rs=psmt.executeQuery();
			while(rs.next()){
				int tid = rs.getInt("tid");
				int sid = rs.getInt("sid");
				String sname = rs.getString("sname");
				String ttitle = rs.getString("ttitle");
				String uname = rs.getString("uname");
				String tcontents = rs.getString("tcontents");
				Timestamp ttime = rs.getTimestamp("ttime");
				int tnumber = rs.getInt("tnumber");
				
				Topic topic = new Topic();
				topic.setTid(tid);
				
				Section section = new Section();
				section.setSname(sname);
				section.setSid(sid);
				
				topic.setTtitle(ttitle);
				
				User user = new User();
				user.setUname(uname);
				
				topic.setUser(user);
				topic.setSection(section);
				
				topic.setTcontents(tcontents);
				topic.setTnumber(tnumber);
				topic.setTtime(ttime);
				topicList.add(topic);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			JDBCUtil.close(conn, stmt, psmt, rs);
		}
		return topicList;
	}

}
