package com.news.core;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.news.beans.Source;
import com.news.util.Utility;
import java.io.IOException;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * For pulling sources
 */
public class SourceService implements ServiceURL {

    private Logger logger = Logger.getLogger(SourceService.class.getName());

    /**
     * Fetch all items of source. Do pagination by @page & @pageSize
     */
    public List<Source> getSources(Integer pageSize, Integer page) {

        List<Source> roll = new ArrayList<>();
        String plainJson = Utility.getResponse(getServiceURL());

        try {

            JSONObject objectJson = (JSONObject) new JSONParser().parse(plainJson);
            String articles = objectJson.get("sources").toString();
            roll = new ObjectMapper().readValue(articles, new TypeReference<List<Source>>() {});

        } catch (ParseException | IOException e) {
            logger.log(Level.SEVERE, "SourceService.getSources(). JSON parse.", e);
        }

        return Utility.getPage(roll, page, pageSize);
    }

    /**
     * Create API request URL from .properties file
     * 
     * @return String API_URL
     */
    public String getServiceURL() {
        return Utility.getProperty("sources_url") + Utility.getProperty("apikey_prefix")
                + Utility.getProperty("apikey_token");
    }



}
