package com.news.core;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.news.beans.Source;
import com.news.rest.client.RSClient;
import com.news.util.Utility;
import java.io.IOException;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Fetcher extension for pulling sources
 * 
 * @author Mikita_Saladkevich
 *
 */
public class SourcesFetcher extends Fetcher {

    /**
     * Fetch all items of source. Do pagination by @page & @pageSize
     */
    public List<Source> getSources(Map<String, String> params, Integer pageSize, Integer page) {

        List<Source> roll = new ArrayList<>();
        String plainJson = RSClient.getResponse(populateURL(params));

        try {

            JSONObject objectJson = (JSONObject) new JSONParser().parse(plainJson);
            String articles = objectJson.get("sources").toString();
            roll = new ObjectMapper().readValue(articles, new TypeReference<List<Source>>() {});

        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }

        return roll;
    }

    /**
     * Create API request URL from .properties file & query params
     * 
     * @return String API_URL
     */
    public String getRequestURL() {
        return Utility.getProperty("sources_url") + Utility.getProperty("apikey_prefix")
                + Utility.getProperty("apikey_token");
    }

    

}
