//Global variable for table headings declared used in 'Get' functions, starts forming html tables with column headings
var tableHeadings = "<table class='greyGridTable'><tr><th>ID</th><th>Title</th><th>Year</th><th>Director</th><th>Stars</th><th>Review</th></tr>";

// Map the buttons in the html page to the javascript functions
$(function() {
	
	$("#button-GetAllFilms-JSON").click(getAllFilmsJSON);
	$("#button-GetAllFilms-XML").click(getAllFilmsXML);
	$("#button-GetAllFilms-Text").click(getAllFilmsText);
	$("#button-GetFilm-JSON").click(getFilmJSON);
	$("#button-GetFilm-XML").click(getFilmXML);
	$("#button-GetFilm-Text").click(getFilmText);
	$("#button-InsertFilm").click(insertFilm);
	$("#button-UpdateFilm").click(updateFilm);
	$("#button-DeleteFilm").click(deleteFilm);
	
});

// Get all films in JSON format, request data from servlet using ajax, formats the data into table and displays in html
function getAllFilmsJSON() {
	var films;
	var table = tableHeadings;
	console.log("getAllFilmsJSON function started");
	
	$.ajax({
        type: "GET",
        url: "GetAllFilms",
        dataType: "json",
        data: "format=json",
                
        complete: function(data) {	
        	films=data.responseJSON;
        	        	
        	for(i=0;i<films.length;i++){
        		table+="<tr><td>"+films[i].id+"</td>"+
        		"<td>"+films[i].title+"</td>"+
        		"<td>"+films[i].year+"</td>"+
        		"<td>"+films[i].director+"</td>"+
        		"<td>"+films[i].stars+"</td>"+
        		"<td>"+films[i].review+"</td></tr>"        		
        	}
        	
        	table+="</table>"
        	
        	//empty where message may currently be displayed and create new message in html, jquery UI effect when message appears
        	$('#GetAllFilmsJSONMessage').empty();	
        	$('#GetAllFilmsJSONMessage').html(films.length + " records found!");$( "#GetAllFilmsJSONMessage" ).effect( "bounce", "slow" );
        	
        	//empty where table may currently be displayed and create new table in html	
        	$('#getAllFilms-json-display').empty();
        	$('#getAllFilms-json-display').html(table); 	        	
        	}
	});
}

//Get all films in XML format, request data from servlet using ajax, formats the data into table and displays in html
function getAllFilmsXML() {
	var films;
	var table = tableHeadings;
	console.log("getAllFilmsXML function started");
	
	$.ajax({
		type: 'GET',
		url: 'GetAllFilms',
		dataType: 'xml',
		data: 'format=xml',
		
		complete: function(data) {
			filmlist=data.responseXML;
			var nofilms = $(filmlist).find("film").length; 
			console.log("number of films: " + nofilms);
			
			$(filmlist).find('film').each(function() {
				table+= "<tr><td>"
					+ $(this).find('id').text() + "</td><td>"
					+ $(this).find('title').text() + "</td><td>"
					+ $(this).find('year').text() + "</td><td>"
					+ $(this).find('director').text() + "</td><td>"
					+ $(this).find('stars').text() + "</td><td>"
					+ $(this).find('review').text() + "</td></tr>"
			})
			
			table += "</table>";
			//empty where message may currently be displayed and create new table in html. jquery UI effect when message appears
			$('#GetAllFilmsXMLMessage').empty();	
        	$('#GetAllFilmsXMLMessage').html(nofilms + " records found!"); $( "#GetAllFilmsXMLMessage" ).effect( "bounce", "slow" );
        	//empty where table may currently be displayed and create new table in html
			$('#getAllFilms-xml-display').empty();
    		$('#getAllFilms-xml-display').html(table); 
    		
		}
	});
}

//Get all films in Text format, request data from servlet using ajax, formats the data into table and displays in html
function getAllFilmsText(){
	var rawStringData;
	var stringRow;
	var stringCell;
	
	var table = tableHeadings;
	console.log("getAllFilmsText function started");
		
	$.ajax({
        type: "GET",
        url: "GetAllFilms",
        dataType: "text",
        data: "format=text",
        
        complete: function(data) {	
        	
        	// get long string as response
        	rawStringData = data.responseText;
        	// split string into rows
        	stringRow = rawStringData.split('----');
        	var noRows = (stringRow.length - 1);
        	console.log(noRows);
        	//split rows into cells 
        	for (i=0; i<noRows;i++){
        		stringCell = stringRow[i].split('#');
        		
        		table+="<tr><td>"+stringCell[0]+"</td>"+
        		"<td>"+stringCell[1]+"</td>"+
        		"<td>"+stringCell[2]+"</td>"+
        		"<td>"+stringCell[3]+"</td>"+
        		"<td>"+stringCell[4]+"</td>"+
        		"<td>"+stringCell[5]+"</td></tr>"
        		}
        	
        	table+="</table>";
        	//empty where message may currently be displayed and create new message in html, jquery UI effect when message appears
        	$('#GetAllFilmsTextMessage').empty();	
        	$('#GetAllFilmsTextMessage').html(noRows + " records found!"); $( "#GetAllFilmsTextMessage" ).effect( "bounce", "slow" );
        	
        	//empty where table may currently be displayed and create new table in html
        	$('#getAllFilms-text-display').empty();
        	$('#getAllFilms-text-display').html(table); 
        	
			}
		
	});
}

//Get films in JSON format that contain title in form, request data from servlet using ajax, format data into table and displays html
function getFilmJSON() {
	var films;
	var title = $('#getFilm-Search').val(); 
	console.log("getFilmJSON function running using title: " + title);
	var table = tableHeadings;
	
	$.ajax({
        type: "GET",
        url: "GetFilm",
        dataType: "json",
        data: "format=json&title=" + title,
                
        complete: function(data) {	
        	films=data.responseJSON;
        	
        	for(i=0;i<films.length;i++){
        		table+="<tr><td>"+films[i].id+"</td>"+
        		"<td>"+films[i].title+"</td>"+
        		"<td>"+films[i].year+"</td>"+
        		"<td>"+films[i].director+"</td>"+
        		"<td>"+films[i].stars+"</td>"+
        		"<td>"+films[i].review+"</td></tr>"        		
        	}
        	
        	table+="</table>"
        	
        	//clear the message and table placeholders
			$('#GetFilmJSONMessage').empty();	
			$('#getFilm-json-display').empty();
			
			// if a film exists create message and table in html
			if (films.length > 0) {
				$('#GetFilmJSONMessage').html(films.length + " records found!"); $( "#GetFilmJSONMessage" ).effect( "bounce", "slow" );
				$('#getFilm-json-display').html(table); 
			} else {
				$('#GetFilmJSONMessage').html("No records exist with the title: " + title);
			}
        	
        }
	});
}

//Get films in XML format that contain title in form, request data from servlet using ajax, format data into table and displays html
function getFilmXML() {
	var films;
	var table = tableHeadings;
	var title = $('#getFilm-Search').val();
	console.log("getFilmXML function running using title: " + title);
		
	$.ajax({
		type: 'GET',
		url: 'GetFilm',
		dataType: 'xml',
		data: 'format=xml&title=' + title,
		
		complete: function(data) {
			filmlist=data.responseXML;
			var nofilms = $(filmlist).find("film").length; 
			console.log("number of films: " + nofilms);
			
			$(filmlist).find('film').each(function() {
				table+= "<tr><td>"
					+ $(this).find('id').text() + "</td><td>"
					+ $(this).find('title').text() + "</td><td>"
					+ $(this).find('year').text() + "</td><td>"
					+ $(this).find('director').text() + "</td><td>"
					+ $(this).find('stars').text() + "</td><td>"
					+ $(this).find('review').text() + "</td></tr>"
			})
			
			table += "</table>";
			
			//clear the message and table placeholders
			$('#GetFilmXMLMessage').empty();	
			$('#getFilm-xml-display').empty();
			// if a film exists create message and table in html
			if (nofilms > 0) {
				$('#GetFilmXMLMessage').html(nofilms + " records found!"); $( "#GetFilmXMLMessage" ).effect( "bounce", "slow" );
				$('#getFilm-xml-display').html(table); 
			} else {
				$('#GetFilmXMLMessage').html("No records exist with the title: " + title);
			}
        		
		}
	});
}

//Get films in Text format that contain title in form, request data from servlet using ajax, format data into table and displays html
function getFilmText(){
	var rawStringData;
	var stringRow;
	var stringCell;
	var title = $('#getFilm-Search').val(); 
	
	var table = tableHeadings;
	console.log("getFilmText function started");
		
	$.ajax({
        type: "GET",
        url: "GetFilm",
        dataType: "text",
        data: "format=text&title=" + title,
        
        complete: function(data) {	
        	rawStringData = data.responseText;
        	
        	stringRow = rawStringData.split('----');
        	var noRows = (stringRow.length - 1);
        	
        	for (i=0; i<noRows;i++){
        		stringCell = stringRow[i].split('#');
        		
        		table+="<tr><td>"+stringCell[0]+"</td>"+
        		"<td>"+stringCell[1]+"</td>"+
        		"<td>"+stringCell[2]+"</td>"+
        		"<td>"+stringCell[3]+"</td>"+
        		"<td>"+stringCell[4]+"</td>"+
        		"<td>"+stringCell[5]+"</td></tr>"
        		}
        	
        	table+="</table>";
        	
        	//clear the message and table placeholders
			$('#GetFilmTextMessage').empty();	
			$('#getFilm-text-display').empty();
			// if a film exists create message and table in html
			if (noRows > 0) {
				$('#GetFilmTextMessage').html(noRows + " records found!"); $( "#GetFilmTextMessage" ).effect( "bounce", "slow" );
				$('#getFilm-text-display').html(table);
			} else {
				$('#GetFilmTextMessage').html("No records exist with the title: " + title);
			}
        	        	
        }
	});
}

// Take the parameters entered into form and insert new record into database
function insertFilm() {
	var newID = $('#insertFilmID').val(); console.log(newID);
	var newTitle = $('#insertFilmTitle').val(); console.log(newTitle);
	var newYear = $('#insertFilmYear').val(); console.log(newYear);
	var newDirector = $('#insertFilmDirector').val(); console.log(newDirector);
	var newStars= $('#insertFilmStars').val(); console.log(newStars);
	var newReview = $('#insertFilmReview').val(); console.log(newReview);
	
		
	//if id and year are both numbers, and none of the fields are null, completes request, otherwise alert error message.
	if (($.isNumeric(newID) && $.isNumeric(newYear)) && (newID  && newTitle && newYear && newDirector && newStars && newReview != "")) {
		// request data from servlet
		$.ajax({
	        type: "POST",
	        url: "InsertFilm",
	        //dataType: "text",
	        data: "id=" + newID + "&title=" + newTitle + "&year=" + newYear + "&director=" + newDirector + "&stars=" + newStars + "&review=" + newReview,
	        	        
	        complete: function(data) {	
	           	$('#insertFilmMessage').html("New record inserted!");$( "#insertFilmMessage" ).effect( "bounce", "slow" );
	        }
		});
	} else {
		alert("Something went wrong! Make sure none of the fields are empty and 'ID' & 'Year' fields are entered as numbers!");
	}
}

//Take the parameters entered into form and update record in database
function updateFilm() {
	var updateID = $('#updateFilmID').val(); console.log(updateID);
	var updateTitle = $('#updateFilmTitle').val(); console.log(updateTitle);
	var updateYear = $('#updateFilmYear').val(); console.log(updateYear);
	var updateDirector = $('#updateFilmDirector').val(); console.log(updateDirector);
	var updateStars= $('#updateFilmStars').val(); console.log(updateStars);
	var updateReview = $('#updateFilmReview').val(); console.log(updateReview);
	
	//if id and year are both numbers, and none of the fields are null, completes request, otherwise alert error message.
	if (($.isNumeric(updateID) && $.isNumeric(updateYear)) && (updateID  && updateTitle && updateYear && updateDirector && updateStars && updateReview != "")) {
		// request data from servlet
		$.ajax({
	        type: "POST",
	        url: "UpdateFilm",
	        //dataType: "text",
	        data: "id=" + updateID + "&title=" + updateTitle + "&year=" + updateYear + "&director=" + updateDirector + "&stars=" + updateStars + "&review=" + updateReview,
	        	        
	        complete: function(data) {	
	        	$('#updateFilmMessage').html("Records with ID " + updateID + " have been updated!");$( "#updateFilmMessage" ).effect( "bounce", "slow" );
	        }
		});
		
	} else {
		alert("Something went wrong! Make sure none of the fields are empty and 'ID' & 'Year' fields are entered as numbers!");
	}
}

// Find ID entered into form and delete that record from database 
function deleteFilm() {
	var deleteID = $('#deleteFilmID').val(); console.log(deleteID);
	// check that number has been entered and field is not empty
	if (($.isNumeric(deleteID)) && (deleteID != "")) {
		$.ajax({
	        type: "POST",
	        url: "DeleteFilm",
	        //dataType: "text",
	        data: "id=" + deleteID,

	        complete: function(data) {	
	        	$('#deleteFilmMessage').html("Records with ID: " + deleteID + " have been deleted from database!");$( "#deleteFilmMessage" ).effect( "bounce", "slow" );
	        }
	    });
		
	} else {
		alert("Something went wrong! Make sure you have entered a number!");
	}
}








