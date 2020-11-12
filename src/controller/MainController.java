package controller;

import java.sql.SQLException;
import java.util.ArrayList;

import com.google.gson.Gson;

import model.Film;
import model.FilmDAO;

public class MainController {
	
	// Controller for testing dao methods as java application and debugging using console

	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		FilmDAO fdao = new FilmDAO();
		
		ArrayList<Film> films = fdao.getAllFilms();
		
		// print all films to the console
		
		for (int i=0; i<films.size(); i++) {
			Film oneFilm = films.get(i);
			System.out.println(oneFilm.toString());
		}
		
		/**
		
		Gson gson = new Gson();
		String allFilmsJson = gson.toJson(films);
		System.out.println(allFilmsJson);
		
		// Test retrieve films including name alien
		ArrayList<Film> alienFilms = fdao.getFilm("alien");
		System.out.println(alienFilms);
		
		// Test insert new film
		Film filmTestInsert = new Film(12345, "Test film", 1990, "Test Director", "Test Stars", "Test review");
		System.out.println(filmTestInsert.toString());
		fdao.insertFilm(filmTestInsert);
		ArrayList<Film> testFilms = fdao.getFilm("test");
		System.out.println(testFilms);
		
		// Test update a film.
		Film updatedFilm = new Film(12345, "Updated film", 1991, "Updated Director", "Updated Stars", "Updated review");
		fdao.updateFilm(updatedFilm);
		
		// Test delete a film
		fdao.deleteFilm(12345);
		
		**/
		
		
	}

}
