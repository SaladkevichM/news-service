#HOW TO USE

GET http://localhost:8080/service/news?sources=bbc-news
    http://localhost:8080/service/news?sources=bbc-news,abc-news&page=2&page_size=3

	- fetch news from some source	
	
	Required parameters:
		- @sources. default - "bbc-news"	

GET http://localhost:8080/service/sources
    http://localhost:8080/service/sources?page=2&page_size=5

	- fetch all news sources
	
#ABOUT ERRORS

If internal appication exception occured - you'd get HTTP code 500 with JSON explanation of the problem.
	ex. http://localhost:8080/service/news?sources=bbc-news&page=-1&page_size=2
	
	{
		"code": "500",
		"message": "IllegalArgument. Invalid page or pageSize."
	}
	
If you tried to GET from unknown path - you'd get HTTP 40X response.	
	ex. http://localhost:8080/service/T%T%T% - 400 Bad Request
		http://localhost:8080/service        - 405 Method Not Allowed
		http://localhost:8080/service/SDFSDF - 404 Not Found