<%@ page import="java.util.*" %>
<%@ page import="model.*" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%

List<Film> filmList = (List<Film>) request.getAttribute("films");
Film f = null;

for (int i = 0; i < filmList.size(); i++) {
	
	f = filmList.get(i);
	out.write(f.getId() + "#" + f.getTitle() + "#" + f.getYear() + "#" + f.getDirector() + "#" + f.getStars() + "#" + f.getReview() + "----");
	
	//output in formatted way to browser
	//out.write("ID: " + f.getId() + " Title: " + f.getTitle() + " Year: " + f.getYear() + " Director: " + f.getDirector() + " Stars: " + f.getStars() + " Review: " + f.getReview() + "\n\n");
}
	
%> 