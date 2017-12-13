#HOW TO USE

GET http://localhost:8080/service/news?sources=bbc-news
    http://localhost:8080/service/news?sources=bbc-news,abc-news&page=2&page_size=3

	- fetch news from some source	
	
	Required parameters:
		- @sources. default - "bbc-news"	

GET http://localhost:8080/service/sources
    http://localhost:8080/service/sources?page=2&page_size=5

	- fetch all news sources