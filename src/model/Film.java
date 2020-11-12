package model;

/**
 * Represents a film record
 * @author Rob Mackay
 * @version 1.0
 */
public class Film {
	
	int id;
	String title;
	int year;
	String director;
	String stars;
	String review;
	
	/**
	 * This constructs a film with specified attributes
	 * @param id uniquely identifies each film
	 * @param title gives name of the film
	 * @param year gives year of film release
	 * @param director shows director/s of the film
	 * @param stars shows actor/s in the film
	 * @param review gives brief review 
	 */
	public Film(int id, String title, int year, String director, String stars,
			String review) {
		super();
		this.id = id;
		this.title = title;
		this.year = year;
		this.director = director;
		this.stars = stars;
		this.review = review;
	}
   
	/**
	 * This constructs empty film object
	 */
   public Film() {
	   
   }

   /**
	* This returns the id of the film
	* @return int id of film
	*/
   public int getId() {
	   return id;
   }
   
   /**
	 * This sets the id of the film
	 * @param int id
	 */
   public void setId(int id) {
	   this.id = id;
   }
   
   /**
	* This returns the title of the film
	* @return String title
	*/
   public String getTitle() {
	   return title;
   }
   
   /**
	 * This sets the title of the film
	 * @param string title
	 */
   public void setTitle(String title) {
		this.title = title;
   }
   
   /**
	* This returns the year of the film
	* @return int year 
	*/
	public int getYear() {
		return year;
	}
	
	/**
	 * This sets the year the film
	 * @param int year
	 */
	public void setYear(int year) {
		this.year = year;
	}
	
	/**
 	* This returns the director of the film
 	* @return String director of film
 	*/
	public String getDirector() {
		return director;
	}

	/**
	 * This sets the director of the film
	 * @param String director
	 */
	public void setDirector(String director) {
		this.director = director;
	}

	/**
 	* This returns the stars of the film
 	* @return String stars of film
 	*/
	public String getStars() {
		return stars;
	}

	/**
	 * This sets the stars of the film
	 * @param stars string
	 */
	public void setStars(String stars) {
		this.stars = stars;
	}

	/**
	 * This returns the review of the film
	 * @return String review of film
	 */
	public String getReview() {
		return review;
	}

	/**
	 * This sets the review of the film
	 * @param String review
	 */
	public void setReview(String review) {
	this.review = review;
	}

	/**
	 * This returns a string representation of the films attributes
	 * @return String of this films attributes
	 */
	@Override
	public String toString() {
		return "Film [id=" + id + ", title=" + title + ", year=" + year
				+ ", director=" + director + ", stars=" + stars + ", review="
				+ review + "]";
	}   
}
