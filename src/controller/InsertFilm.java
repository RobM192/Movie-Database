package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.*;

/**
 * Servlet implementation class InsertFilm
 */
@WebServlet("/InsertFilm")
public class InsertFilm extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertFilm() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		// tested, working
		FilmDAO fdao = new FilmDAO(); 
		int insertFilm = 0;
		int id; int year;
		
		id = (Integer.parseInt(request.getParameter("id"))); 
		String title = request.getParameter("title"); 
		year = (Integer.parseInt(request.getParameter("year")));
		String director = request.getParameter("director"); 
		String stars = request.getParameter("stars"); 
		String review = request.getParameter("review"); 
		
		Film f = new Film(id, title, year, director, stars, review);
		
		try {
			insertFilm = fdao.insertFilm(f);
			System.out.println("Return 1 if successful: " + insertFilm);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
