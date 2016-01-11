package com.bbs.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bbs.dao.SectionDao;
import com.bbs.model.Section;

/**
 * Servlet implementation class SectionServlet
 */
public class SectionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SectionServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String method = request.getParameter("method");
		if (method != null && method.equals("select")) {
			String page = request.getParameter("goPage");
			int id = Integer.parseInt(request.getParameter("id"));

			SectionDao sectionDao = new SectionDao();
			

			List<Section> sectionList = sectionDao.selectPage(0,
					0);

			request.setAttribute("sectionList", sectionList);
			if (id == 1) {
				request.getRequestDispatcher("section/section.jsp").forward(
						request, response);
			} else {
				request.getRequestDispatcher("section/sectionSelect.jsp")
						.forward(request, response);
			}

		}
	}
}
