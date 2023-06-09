package by.htp.ex.controller.impl;

import java.io.IOException;
import java.util.List;

import by.htp.ex.bean.News;
import by.htp.ex.controller.Command;
import by.htp.ex.service.INewsService;
import by.htp.ex.service.ServiceException;
import by.htp.ex.service.ServiceProvider;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class GoToNewsList implements Command {

	private static final String SESSION_WARNING = "session_warning";
	private static final String WARNING = "warning";
	private static final String NEWS = "news";
	private static final String PRESENTATION = "presentation";
	private static final String NEWS_LIST = "newsList";

	private final INewsService newsService = ServiceProvider.getInstance().getNewsService();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<News> newsList;
		try {
			if (request.getSession() == null) {
				request.getSession(true).setAttribute(SESSION_WARNING, WARNING);
				response.sendRedirect("controller?command=go_to_base_page");
			}
			newsList = newsService.list();
			request.getSession().setAttribute(NEWS, newsList);
			request.getSession().setAttribute(PRESENTATION, NEWS_LIST);
			request.getRequestDispatcher("WEB-INF/pages/layouts/baseLayout.jsp").forward(request, response);
		} catch (ServiceException e) {
			request.getRequestDispatcher("/WEB-INF/pages/tiles/error.jsp").forward(request, response);
		}
	}
}
