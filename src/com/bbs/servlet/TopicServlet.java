package com.bbs.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bbs.dao.SectionDao;
import com.bbs.dao.TopicDao;
import com.bbs.dao.UserDao;
import com.bbs.model.Section;
import com.bbs.model.Topic;
import com.bbs.model.User;

/**
 * Servlet implementation class TopicServlet
 */
public class TopicServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TopicServlet() {
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
		if (method != null && method.equals("select")) {
			String page = request.getParameter("goPage");
			int id = Integer.parseInt(request.getParameter("id"));
			int sid = Integer.parseInt(request.getParameter("sid"));
			TopicDao topicDao = new TopicDao();
			
			List<Topic> topicList = null;
			if(id==1){
				topicList = topicDao.selectPage1(0, 0,sid);
			}else{
				topicList = topicDao.selectPage(0, 0);
			}
			
			request.setAttribute("topicList", topicList);
			if(id==1){
				Section section = new Section();
				section.setSid(sid);
				SectionDao sectionDao = new SectionDao();
				section = sectionDao.selectByUid(section);
				request.setAttribute("section", section);
				request.getRequestDispatcher("topic/topic.jsp").forward(
						request, response);
			}else{
				request.getRequestDispatcher("topic/topicSelect.jsp").forward(
						request, response);
			}
		} else if (method != null && method.equals("save")) {
			int id = Integer.parseInt(request.getParameter("id"));
			int sid = Integer.parseInt(request.getParameter("sid"));
			UserDao userDao = new UserDao();
			User users = (User)request.getSession().getAttribute("user");
			int uid = userDao.selectByUname(users.getUname());
			String tcontents = request.getParameter("tcontents");
			String ttitle = request.getParameter("ttitle");
			Topic topic = new Topic();
			
			Section section = new Section();
			section.setSid(sid);
			
			User user = new User();
			user.setUid(uid);
			
			topic.setSection(section);
			topic.setUser(user);
			topic.setTcontents(tcontents);
			topic.setTtitle(ttitle);

			TopicDao topicDao = new TopicDao();
			int count = topicDao.save(topic);
			List<Topic> topicList = null;
				if(id==1){
					Section sections = new Section();
					sections.setSid(sid);
					SectionDao sectionDao = new SectionDao();
					sections = sectionDao.selectByUid(sections);
					request.setAttribute("section", sections);
					topicList = topicDao.selectPage1(0, 0,sid);
				}else{
					topicList = topicDao.selectPage(0, 0);
				}
				request.setAttribute("topicList", topicList);
				if(id==1){
					request.getRequestDispatcher("topic/topic.jsp").forward(
							request, response);
				}else{
					request.getRequestDispatcher("topic/topicSelect.jsp").forward(
							request, response);
				}
			}
		
	}

}
