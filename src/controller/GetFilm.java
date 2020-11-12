package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Film;
import model.FilmDAO;

/**
 * Servlet implementation class GetFilm
 */
@WebServlet("/GetFilm")
public class GetFilm extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetFilm() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		FilmDAO fdao = new FilmDAO();
		ArrayList<Film> films = new ArrayList<Film>();
		
		request.setAttribute("films", films);
		String searchFilmName = request.getParameter("title");
				
		try {
			films = fdao.getFilm(searchFilmName);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (Film f: films) {
			System.out.println(f.toString());
		}
		
		request.setAttribute("films", films);
		String format = request.getParameter("format");
		//if no format given json is default
		if (format==null) {
			format = "json";
		}
		String outputPage = "";
		if ("xml".equals(format)) {
		      response.setContentType("text/xml");
		      outputPage = "/WEB-INF/results/films-xml.jsp";
		}
		else if ("json".equals(format)) {
			response.setContentType("application/json");
			outputPage = "/WEB-INF/results/films-json.jsp";
		}
		else if ("text".equals(format)) {
			response.setContentType("text/plain");
			outputPage = "/WEB-INF/results/films-string.jsp";
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(outputPage);
		dispatcher.include(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
