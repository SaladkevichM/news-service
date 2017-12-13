package com.news.rest;

import com.news.core.ServiceCaller;
import com.news.core.HeadlineService;
import com.news.core.SourceService;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Controller of the REST service
 * 
 * @author Mikita_Saladkevich
 *
 */
@Path("/")
public class Server {

    public static final HeadlineService HEADLINES = new HeadlineService(new ServiceCaller());
    public static final SourceService SOURCES = new SourceService(new ServiceCaller());

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/news")
    public Response getNews(@QueryParam("sources") String sources, @QueryParam("page") Integer page,
            @QueryParam("page_size") Integer pageSize) {

        Map<String, String> params = new HashMap<>();
        params.put("sources", sources);

        Map<String, String> response = HEADLINES.getNews(params, pageSize, page);
        return Response.ok(response.get("result")).status(Integer.valueOf(response.get("code")))
                .build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/sources")
    public Response getSources(@QueryParam("page") Integer page,
            @QueryParam("page_size") Integer pageSize) {

        Map<String, String> response = SOURCES.getSources(pageSize, page);
        return Response.ok(response.get("result")).status(Integer.valueOf(response.get("code")))
                .build();
    }

}
