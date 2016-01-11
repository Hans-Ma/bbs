package com.bbs.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.bbs.model.Section;
import com.bbs.model.User;
import com.bbs.util.JDBCUtil;

public class SectionDao {
	public Connection conn = null;
	public PreparedStatement psmt = null;
	public Statement stmt = null;
	public ResultSet rs = null;

	/**
	 * 查询所有模块
	 * 
	 * @return
	 */
	public List<Section> select() {
		List<Section> sectionList = new ArrayList<Section>();
		// String sql = "select * from section";
		String sql = "select s.s_id sid,s.s_name sname,s.s_number snumber,s.s_info sinfo,s.s_u_id suid,u.u_name uname from section s,users u where s.s_u_id=u.u_id";
		conn = JDBCUtil.getConnection();
		try {
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			while (rs.next()) {
				int sid = rs.getInt("sid");
				String sname = rs.getString("sname");
				int snumber = rs.getInt("snumber");
				String sinfo = rs.getString("sinfo");
				int uid = rs.getInt("suid");
				String uname = rs.getString("uname");

				Section sections = new Section();
				sections.setSid(sid);
				sections.setSname(sname);
				sections.setSnumber(snumber);
				sections.setSinfo(sinfo);
				User user = new User();
				user.setUid(uid);
				user.setUname(uname);
				sections.setUser(user);

				sectionList.add(sections);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(conn, stmt, psmt, rs);
		}
		return sectionList;
	}

	/**
	 * 添加模块
	 * 
	 * @param section
	 * @return
	 */
	public int save(Section section) {
		// TODO Auto-generated method stub
		String sql = "insert into section(s_name,s_number,s_info,s_u_id) values(1,DEFAULT,1,1)";
		conn = JDBCUtil.getConnection();
		int count = 0;
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, section.getSname());
			psmt.setString(2, section.getSinfo());
			psmt.setInt(3, section.getUser().getUid());
			count = psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(conn, stmt, psmt, rs);
		}
		return count;
	}

	public List<Section> selectPage(int countMax, int countMin) {
		List<Section> sectionList = new ArrayList<Section>();
		// String sql = "select * from section";
		//String sql = "select * from(select s.*,rownum rn from(select s.s_id sid,s.s_name sname,s.s_number snumber,s.s_info sinfo,s.s_u_id suid,u.u_name uname from section s,users u where s.s_u_id=u.u_id order by s.s_id) s where rownum<="
			//	+ countMax + ") where rn>" + countMin;
		String sql = "select s.s_id sid,s.s_name sname,s.s_number snumber,s.s_info sinfo,s.s_u_id suid,u.u_name uname "
				+ "from section s,users u "
				+ "where s.s_u_id=u.u_id order by s.s_id ";
				if(countMin+countMax>0){
				sql += " limit "+ countMin + "," + countMax+" ";
				}
		conn = JDBCUtil.getConnection();
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				int sid = rs.getInt("sid");
				String sname = rs.getString("sname");
				int snumber = rs.getInt("snumber");
				String sinfo = rs.getString("sinfo");
				int uid = rs.getInt("suid");
				String uname = rs.getString("uname");

				Section sections = new Section();
				sections.setSid(sid);
				sections.setSname(sname);
				sections.setSnumber(snumber);
				sections.setSinfo(sinfo);
				User user = new User();
				user.setUid(uid);
				user.setUname(uname);
				sections.setUser(user);

				sectionList.add(sections);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(conn, stmt, psmt, rs);
		}
		return sectionList;
	}

	
	public Section selectByUid(Section section) {
		// TODO Auto-generated method stu
		String sql = "select * from section where s_id=?";
		conn = JDBCUtil.getConnection();
		Section sections = null;
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, section.getSid());
			rs = psmt.executeQuery();
			while (rs.next()) {
				int sid = rs.getInt("s_id");
				String sname = rs.getString("s_name");
				int snumber = rs.getInt("s_number");
				String sinfo = rs.getString("s_info");

				sections = new Section();
				sections.setSid(sid);
				sections.setSname(sname);
				sections.setSnumber(snumber);
				sections.setSinfo(sinfo);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(conn, stmt, psmt, rs);
		}
		return sections;
	}

	public List<Section> selectPage1(int countMax, int countMin, int uid) {
		List<Section> sectionList = new ArrayList<Section>();
		// String sql = "select * from section";
//		String sql = "select * from(select ss.*,rownum rn from(select s.s_id sid,s.s_name sname,s.s_number snumber,s.s_info sinfo from section s,users u where s.s_u_id=u.u_id and u.u_id="
//				+ uid
//				+ " order by s.s_id) ss where rownum<="
//				+ countMax
//				+ ") where rn>" + countMin;
		
		String sql = "select s.s_id sid,s.s_name sname,s.s_number snumber,s.s_info sinfo  from section s,users u "
				+ "where s.s_u_id=u.u_id and u.u_id = "+ uid+" order by s.s_id  limit "+ countMin + "," + countMax+" ";
		
		conn = JDBCUtil.getConnection();
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				int sid = rs.getInt("sid");
				String sname = rs.getString("sname");
				int snumber = rs.getInt("snumber");
				String sinfo = rs.getString("sinfo");

				Section sections = new Section();
				sections.setSid(sid);
				sections.setSname(sname);
				sections.setSnumber(snumber);
				sections.setSinfo(sinfo);

				sectionList.add(sections);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(conn, stmt, psmt, rs);
		}
		return sectionList;
	}

}
