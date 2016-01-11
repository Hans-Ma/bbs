package com.bbs.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.bbs.model.Reply;
import com.bbs.model.Section;
import com.bbs.model.Topic;
import com.bbs.model.User;
import com.bbs.util.JDBCUtil;

public class ReplyDao {
	public Connection conn = null;
	public PreparedStatement psmt = null;
	public Statement stmt = null;
	public ResultSet rs = null;

	/**
	 * 添加回帖
	 * 
	 * @param reply
	 * @return
	 */

	public int save(Reply reply) {
		// TODO Auto-generated method stub
		String sql = "insert into reply(r_u_id,r_t_id,r_contents) values(?,?,?)";
		conn = JDBCUtil.getConnection();
		int count = 0;
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, reply.getUser().getUid());
			psmt.setInt(2, reply.getTopic().getTid());
			psmt.setString(3, reply.getRcontents());
			count = psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(conn, stmt, psmt, rs);
		}
		return count;
	}

	public int count() {
		// TODO Auto-generated method stub
		String sql = "select count(*) count from reply";
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

	public List<Reply> selectPage(int countMax, int countMin) {
		// TODO Auto-generated method stub
		List<Reply> replyList = new ArrayList<Reply>();
//		String sql = "select * from (select rut.*,rownum rn from (select r.r_id rid,r.r_t_id rtid,t.t_title ttitle,u.u_name uname,r.r_time rtime,r.r_contents rcontents from reply r,topic t,users u where r.r_u_id=u.u_id and r.r_t_id=t.t_id order by r_id) rut where rownum<="
//				+ countMax + ") where rn>" + countMin;
		String sql = "select r.r_id rid,r.r_t_id rtid,t.t_title ttitle,u.u_name uname,r.r_time rtime,r.r_contents rcontents from reply r,topic t,users u where r.r_u_id=u.u_id and r.r_t_id=t.t_id order by r_id limit "+countMin+","+ countMax+"";
		conn = JDBCUtil.getConnection();
		try {
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			while (rs.next()) {
				int rid = rs.getInt("rid");
				int tid = rs.getInt("rtid");
				String ttitle = rs.getString("ttitle");
				String uname = rs.getString("uname");
				Timestamp rtime = rs.getTimestamp("rtime");
				String rcontents = rs.getString("rcontents");

				Reply reply = new Reply();
				reply.setRid(rid);

				Topic topic = new Topic();
				topic.setTid(tid);
				topic.setTtitle(ttitle);
				reply.setTopic(topic);

				User user = new User();
				user.setUname(uname);
				reply.setUser(user);

				reply.setRtime(rtime);
				reply.setRcontents(rcontents);

				replyList.add(reply);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(conn, stmt, psmt, rs);
		}
		return replyList;
	}



	public List<Reply> selectPage1(int countMax, int countMin, int tid) {
		// TODO Auto-generated method stub
		List<Reply> replyList = new ArrayList<Reply>();
//		String sql = "select * from (select rut.*,rownum rn from (select r.r_id rid,u.u_name uname,r.r_time rtime,r.r_contents rcontents from reply r,users u where r.r_u_id=u.u_id and r.r_t_id="
//				+ tid
//				+ "order by r_id) rut where rownum<="
//				+ countMax
//				+ ") where rn>" + countMin;
		
		String sql = "select r.r_id rid,u.u_name uname,r.r_time rtime,r.r_contents rcontents from reply r,users u where r.r_u_id=u.u_id and r.r_t_id="
				+ tid + " order by r_id  ";
						// " limit "+countMin+","+ countMax+"";
		conn = JDBCUtil.getConnection();
		try {
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			while (rs.next()) {
				int rid = rs.getInt("rid");
				String uname = rs.getString("uname");
				Timestamp rtime = rs.getTimestamp("rtime");
				String rcontents = rs.getString("rcontents");

				Reply reply = new Reply();
				reply.setRid(rid);

				User user = new User();
				user.setUname(uname);
				reply.setUser(user);

				reply.setRtime(rtime);
				reply.setRcontents(rcontents);

				replyList.add(reply);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(conn, stmt, psmt, rs);
		}
		return replyList;
	}
}
