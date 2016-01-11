package com.bbs.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bbs.dao.SectionDao;
import com.bbs.dao.UserDao;
import com.bbs.model.Section;
import com.bbs.model.User;

/**
 * Servlet implementation class UserServlet
 */
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String method = request.getParameter("method");
		if (method != null && method.equals("login")) {
			String uname = request.getParameter("username");
			String upassword = request.getParameter("password");

			User user = new User();
			user.setUname(uname);
			user.setUpassword(upassword);
			UserDao userDao = new UserDao();
			User users = new User();
			users = userDao.login(user);
			if (users != null) {
				int ustatus = users.getUstatus();
				// && (ustatus == 1 || ustatus == 2)) {
				// HttpSession session = request.getSession();
				// session.setAttribute("user", users);
				// request.getRequestDispatcher("back/main.jsp").forward(
				// request, response);
				// } else if (users != null && ustatus == 3) {
				HttpSession session = request.getSession();
				session.setAttribute("ustatus", ustatus);
				session.setAttribute("user", users);
				request.getRequestDispatcher("index.jsp").forward(
						request, response);
			}
			// else {
			// request.getRequestDispatcher("back/fail.jsp");
			// }
		} else if (method != null && method.equals("register")) {
			String uname = request.getParameter("name");
			String upassword = request.getParameter("pwd");
			User user = new User();
			user.setUname(uname);
			user.setUpassword(upassword);
			UserDao userDao = new UserDao();
			int count = userDao.save(user);
			if (count > 0) {
				SectionDao sectionDao = new SectionDao();
				List<Section> sectionList = sectionDao.select();
				request.setAttribute("sectionList", sectionList);
				HttpSession session = request.getSession();
				session.setAttribute("user", user);
				request.getRequestDispatcher("index.jsp").forward(
						request, response);
			} else {
				request.getRequestDispatcher("user/register.jsp").forward(
						request, response);
			}
		} else if (method != null && method.equals("logout")) {
			request.getSession().removeAttribute("user");
			request.getSession().removeAttribute("uid");
			request.getSession().removeAttribute("ustatus");

			request.getRequestDispatcher("login.jsp")
					.forward(request, response);
		} else if (method != null && method.equals("list")) {
			String searchVal = request.getParameter("searchVal");
			UserDao userDao = new UserDao();
			List<User> userList = userDao.select(searchVal);
			request.setAttribute("userList", userList);
			request.getRequestDispatcher("user/userList.jsp").forward(request,
					response);
		} else if (method != null && method.equals("toEdit")) {
			String uid = request.getParameter("uid");
			User user = null;
			if (uid != null && !"".equals(uid)) {
				int id = Integer.parseInt(uid);
				User u = new User();
				u.setUid(id);
				UserDao userDao = new UserDao();
				user = userDao.selectByUid(u);
			}
			request.setAttribute("user", user);
			request.getRequestDispatcher("user/editUser.jsp").forward(request,response);
		}else if (method != null && method.equals("edit")) {
			String uid = request.getParameter("uid");
			String uname = request.getParameter("name");
			String upassword = request.getParameter("pwd");
			User user = new User();
			user.setUid(Integer.parseInt(uid));
			user.setUname(uname);
			user.setUpassword(upassword);
			UserDao userDao = new UserDao();
			int count = userDao.update(user);
			if (count > 0) {
				List<User> userList = userDao.select(null);
				request.setAttribute("userList", userList);
				request.getRequestDispatcher("user/userList.jsp").forward(request,
						response);
			}
		}else if (method != null && method.equals("del")) {
			String uid = request.getParameter("uid");
			
			User user = new User();
			user.setUid(Integer.parseInt(uid));
			
			UserDao userDao = new UserDao();
			int count = userDao.delete(user);
			if (count > 0) {
				List<User> userList = userDao.select(null);
				request.setAttribute("userList", userList);
				request.getRequestDispatcher("user/userList.jsp").forward(request,
						response);
			}
		}
	}

}
