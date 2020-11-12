package model;
import java.util.ArrayList;

//import baseAndTesterClasses.Film;

import java.sql.*;

/**
 * Class containing all the DAO methods for use with the film class
 * @author Rob Mackay
 * @version 1.0
 */
public class FilmDAO {
	
	Film oneFilm = null;
	Connection conn = null;
    Statement stmt = null;
    
    // amazon database login info
    //String username = "";
    //String password = "";
    //String amazonURL = "";
    
	public FilmDAO() {}
	
	/**
	 * Method to open connection with the film database
	 */
	private void openConnection(){
		// loading jdbc driver for mysql
		try{
		    Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch(Exception e) { System.out.println(e); }

		// connecting to database
		try{
			// connection string to mudfoot database
		    conn = DriverManager.getConnection ("");
		    
		    // connection to local database
		    // conn = DriverManager.getConnection ("");
			
			// connnection to amazon database
			//conn = DriverManager.getConnection(amazonURL, username, password);
	        
		    
		    stmt = conn.createStatement();
		} catch(SQLException se) { System.out.println(se); }	   
    }
	
	/**
	 * Method to close connection with the film database
	 */
	private void closeConnection(){
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Method to get get the next film object given a result set of film objects
	 * @return Film object
	 * @param result set
	 */
	private Film getNextFilm(ResultSet rs){
    	Film thisFilm=null;
		try {
			thisFilm = new Film(
					rs.getInt("id"),
					rs.getString("title"),
					rs.getInt("year"),
					rs.getString("director"),
					rs.getString("stars"),
					rs.getString("review"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return thisFilm;		
	}
	
	/**
	 * Method to create arraylist of all films in database
	 * @return Array of Film objects
	 */
	public ArrayList<Film> getAllFilms(){
	   
		ArrayList<Film> allFilms = new ArrayList<Film>();
		openConnection();
		
	    // Create select statement and execute it
		try{
		    String selectSQL = "SELECT * FROM films";
		    ResultSet rs1 = stmt.executeQuery(selectSQL);
	    // Retrieve the results
		    while(rs1.next()){
		    	oneFilm = getNextFilm(rs1);
		    	allFilms.add(oneFilm);
		   }

		    stmt.close();
		    closeConnection();
		} catch(SQLException se) { System.out.println(se); }

	   return allFilms;
   }

	/**
	 * Method to Insert single film into database using prepared statement
	 * @return int of 1 if successful
	 * @param film object
	 * @throws SQLException if error occurs when connecting  database 
	 */
   public int insertFilm(Film f) throws SQLException{
	   PreparedStatement pstmt = null;
	   int rowsAdded=0;
	   String statement = "INSERT INTO films (id, title, year, director, stars, review) VALUES(?,?,?,?,?,?);"; 
       //print the statement into the console
       System.out.println(statement);

       try {
    	   openConnection();
    	   pstmt = conn.prepareStatement(statement); 
       	//set the parameters to be updated
    	   pstmt.setInt(1, f.id);
    	   pstmt.setString(2, f.title);
           pstmt.setInt(3, f.year);
           pstmt.setString(4, f.director);
           pstmt.setString(5, f.stars);
           pstmt.setString(6, f.review);
           rowsAdded = pstmt.executeUpdate();
           pstmt.close();
       } catch (SQLException e) {
           System.out.println(e.getMessage());
           
       } 
       return rowsAdded;
       
   } 
   
   /**
	 * Method to Retrieve films with keywords specified from database
	 * @return arraylist of films
	 * @param string title of film
	 * @throws SQLException if error occurs when connecting  database 
	 */
   public ArrayList<Film> getFilm (String title) throws SQLException {
	   ArrayList<Film> filmArray = new ArrayList<Film>();
	   String query = "SELECT * from films WHERE title LIKE '%" + title + "%'";
	   
	   try {
			openConnection();
			ResultSet results = stmt.executeQuery(query);
			while (results.next()) { oneFilm = getNextFilm(results); filmArray.add(oneFilm); }
			stmt.close();
			closeConnection();
		} catch (SQLException e) {
			System.out.println(e);
		}
		System.out.println("Retrive query completed");
		System.out.println(filmArray);
		return filmArray;
	}
   
   /**
	 * Method to Update film in database using prepared statement
	 * @param film object
	 * @throws SQLException if error occurs when connecting  database 
	 */
   public void updateFilm(Film f) throws SQLException{
	   PreparedStatement pstmt = null;
	   String statement = "UPDATE films SET title = ?, year = ?, director = ?, stars = ?, review = ? WHERE id = ?";
       //print the statement into the console
       System.out.println(statement);

       try {
    	   openConnection();
    	   pstmt = conn.prepareStatement(statement); 
       	//set the parameters to be updated
    	   pstmt.setString(1, f.title);
           pstmt.setInt(2, f.year);
           pstmt.setString(3, f.director);
           pstmt.setString(4, f.stars);
           pstmt.setString(5, f.review);
           pstmt.setInt(6, f.id);
           
           pstmt.executeUpdate();
           pstmt.close();
       } catch (SQLException e) {
           System.out.println(e.getMessage());
       } 
       System.out.println("Film with id " + f.id + " updated in db");
       
   } 
   
   /**
	 * Method to Delete film in database
	 * @param id of film
	 * @throws SQLException if error occurs when connecting  database 
	 */
   public void deleteFilm(int id) throws SQLException{
	      
	   String query = "DELETE FROM films WHERE id="+id;
	   
	   try {
		   	openConnection();
		   	stmt = conn.createStatement();
			System.out.println("SQL Statement: " + query);
			stmt.executeUpdate(query);
			stmt.close();
		    closeConnection();
			
		} catch (SQLException e) {
			System.out.println(e);
		}
		System.out.println("Film with id " + id + " deleted from db");
		
   }
   
}
