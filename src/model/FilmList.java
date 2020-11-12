package model;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlAccessType;

//root element is filmlist
@XmlRootElement (name = "filmlist")
@XmlAccessorType (XmlAccessType.FIELD)

/**
 * Class for handling list of films, defining root and child elements for handling xml
 * @author Rob Mackay
 * @version 1.0
 */	
public class FilmList {
	//new empty constructor
	public FilmList() {
		
	}
	
	//child element for each individual film is 'film'
	@XmlElement(name="film")
	
	// new list to hold the xml films list
	public List<Film> films;
	
	// construct the array
	public FilmList(List<Film> films) {
	
		// the new list given to films list
		this.films=films;
	}
}
