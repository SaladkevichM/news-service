package com.news.rest.server;

import com.news.beans.Article;
import com.news.beans.Source;
import com.news.core.HeadlinesFetcher;
import com.news.core.SourcesFetcher;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/")
public class RSServer {

    public static final HeadlinesFetcher HEADLINES = new HeadlinesFetcher();
    public static final SourcesFetcher SOURCES = new SourcesFetcher();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/news") 
    public Response getNews(@QueryParam("sources") String sources,
            @QueryParam("page") Integer page, @QueryParam("page_size") Integer pageSize,
            @QueryParam("sort_by") String sortBy) {

        Map<String, String> params = new HashMap<>();
        params.put("sortBy", sortBy);
        params.put("sources", sources);
        
        List<Article> articles = HEADLINES.getNews(params, pageSize, pageSize);        
        GenericEntity<List<Article>> entity = new GenericEntity<List<Article>>(articles) {};
        
        return Response.ok(entity).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/sources") 
    public Response getSources(@QueryParam("page") Integer page, @QueryParam("page_size") Integer pageSize,
            @QueryParam("sort_by") String sortBy) {

        Map<String, String> params = new HashMap<>();
        params.put("sortBy", sortBy);
        
        List<Source> sources = SOURCES.getSources(params, pageSize, pageSize);        
        GenericEntity<List<Source>> entity = new GenericEntity<List<Source>>(sources) {};
        
        return Response.ok(entity).build();
    }

}
