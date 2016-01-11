package com.bbs.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bbs.dao.ReplyDao;
import com.bbs.dao.TopicDao;
import com.bbs.dao.UserDao;
import com.bbs.model.Reply;
import com.bbs.model.Topic;
import com.bbs.model.User;

/**
 * Servlet implementation class ReplyServlet
 */
public class ReplyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReplyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String method = request.getParameter("method");
		if(method!=null&&method.equals("select")){
			int id = Integer.parseInt(request.getParameter("id"));
			int tid = Integer.parseInt(request.getParameter("tid"));
			ReplyDao replyDao = new ReplyDao();
			
			List<Reply> replyList = null;
			if(id==1){
				replyList = replyDao.selectPage1(0, 0,tid);
			}else{
				replyList = replyDao.selectPage(0, 0);
			}
			
			request.setAttribute("replyList", replyList);
			if(id==1){
				Topic topic = new Topic();
				topic.setTid(tid);
				TopicDao topicDao = new TopicDao();
				topic = topicDao.selectByTid(topic);
				request.setAttribute("topic", topic);
				request.getRequestDispatcher("reply/reply.jsp").forward(
						request, response);
			}else{
				request.getRequestDispatcher("reply/replySelect.jsp").forward(
						request, response);
			}
		}else if(method!=null&&method.equals("save")){
			int tid = Integer.parseInt(request.getParameter("tid"));
			String rcontents = request.getParameter("rcontents");
			UserDao userDao = new UserDao();
			User user = (User)request.getSession().getAttribute("user");
			int uid = userDao.selectByUname(user.getUname());
			Reply reply = new Reply();
			
			Topic topic = new Topic();
			topic.setTid(tid);
			reply.setTopic(topic);
			
			user = new User();
			user.setUid(uid);
			reply.setUser(user);
			
			reply.setRcontents(rcontents);
			
			ReplyDao replyDao = new ReplyDao();
			int count =replyDao.save(reply);
			
			if(count>0){
				
				Topic topics = new Topic();
				topics.setTid(tid);
				TopicDao topicDao = new TopicDao();
				topics = topicDao.selectByTid(topics);
				request.setAttribute("topic", topics);
				
				List<Reply> replyList = replyDao.selectPage1(0, 0,tid);

				
				request.setAttribute("replyList", replyList);
				request.getRequestDispatcher("reply/reply.jsp").forward(request, response);
			}else{
				request.getRequestDispatcher("fail.jsp").forward(request, response);
			}
		}
	}

}
