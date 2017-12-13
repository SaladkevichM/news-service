package com.news.core;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.news.beans.Article;
import com.news.beans.Source;
import com.news.util.Utility;
import java.io.IOException;

import org.apache.http.HttpException;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ws.rs.core.Response;

/**
 * Pulling sources service
 */
public class SourceService implements ServiceURL {

    private Logger logger = Logger.getLogger(SourceService.class.getName());
    private ObjectMapper mapper = new ObjectMapper();
    private ServiceCaller serviceCaller;

    /**
     * Constructor receives ServiceCaller object
     * 
     * @param caller
     */
    public SourceService(ServiceCaller caller) {
        serviceCaller = caller;
    }

    /**
     * Fetch all sources. Do pagination by @page & @pageSize
     */
    public Map<String, String> getSources(Integer pageSize, Integer page) {

        Map<String, String> result = new HashMap<>();
        result.put("code", String.valueOf(Response.Status.OK.getStatusCode()));

        try {

            String json = serviceCaller.sendRequest(getServiceURL());
            JSONObject objectJson = (JSONObject) new JSONParser().parse(json);
            String articles = objectJson.get("sources").toString();

            List<Article> roll = mapper.readValue(articles, new TypeReference<List<Source>>() {});
            result.put("result", mapper.writeValueAsString(Utility.getPage(roll, page, pageSize)));

        } catch (ParseException | IOException | HttpException internalException) {

            logger.log(Level.SEVERE, "SourceService.getSources()", internalException);

            result.put("code",
                    String.valueOf(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode()));
            result.put("result", Utility.createError(500, internalException.getMessage()));

        }

        return result;
    }

    /**
     * Create API request URL from .properties file
     * 
     * @return String API_URL
     */
    public String getServiceURL() throws HttpException {
        return Utility.getProperty("sources_url") + Utility.getProperty("apikey_prefix")
                + Utility.getProperty("apikey_token");
    }

}
