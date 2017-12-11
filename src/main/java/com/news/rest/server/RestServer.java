package com.news.rest.server;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.news.core.NewsAPIOrgPuller;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/")
public class RestServer {

    public static final NewsAPIOrgPuller API_INSTANCE = new NewsAPIOrgPuller();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/news/{source}/{sortBy}/{page}/{pageSize}")
    public String getNews(@PathParam("source") String source, @PathParam("page") String page,
            @PathParam("pageSize") String pageSize, @PathParam("sortBy") String sortBy) {

        Map<String, String> queryParams = new HashMap<>();
        queryParams.put("sources", source);
        queryParams.put("page", page);
        queryParams.put("pageSize", page);
        queryParams.put("sortBy", sortBy);

        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(API_INSTANCE.getRoll(queryParams));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return "";

    }

}
