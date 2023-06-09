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

public class GoToViewNews implements Command {

	private static final String ID_NEWS = "id";
	private static final String SESSION_WARNING = "session_warning";
	private static final String WARNING = "warning";
	private static final String PREVIOUS_ID = "idPre";
	private static final String NEWS_VIEW = "newsView";
	private static final String PRESENTATION = "presentation";
	private static final String VIEW_NEWS = "viewNews";

	private final INewsService newsService = ServiceProvider.getInstance().getNewsService();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		News newsView;
		String id;
		id = request.getParameter(ID_NEWS);
		try {
			if (request.getSession() == null) {
				request.getSession(true).setAttribute(SESSION_WARNING, WARNING);
				response.sendRedirect("controller?command=go_to_base_page");
			}
			if (id == null) {
				id = request.getSession().getAttribute(PREVIOUS_ID).toString();
			} else {
				request.getSession().setAttribute(PREVIOUS_ID, id);
			}
			newsView = newsService.findById(Integer.valueOf(id));
			request.getSession().setAttribute(NEWS_VIEW, newsView);
			request.getSession().setAttribute(PRESENTATION, VIEW_NEWS);
			request.getRequestDispatcher("WEB-INF/pages/layouts/baseLayout.jsp").forward(request, response);
		} catch (ServiceException e) {
			request.getRequestDispatcher("/WEB-INF/pages/tiles/error.jsp").forward(request, response);
		}
	}
}
