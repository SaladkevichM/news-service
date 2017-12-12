package com.news.core;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.news.beans.Article;
import com.news.util.Utility;
import java.io.IOException;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * For pulling news
 */
public class HeadlineService implements ServiceURL {

    private Logger logger = Logger.getLogger(HeadlineService.class.getName());

    /**
     * Fetch all items of source. Do pagination by @page & @pageSize
     */
    public List<Article> getNews(Map<String, String> params, Integer pageSize, Integer page) {

        List<Article> roll = new ArrayList<>();
        String plainJson = Utility.getResponse(populateURL(params));

        try {

            JSONObject objectJson = (JSONObject) new JSONParser().parse(plainJson);
            String articles = objectJson.get("articles").toString();
            roll = new ObjectMapper().readValue(articles, new TypeReference<List<Article>>() {});

        } catch (ParseException | IOException e) {
            logger.log(Level.SEVERE, "HeadlineService.getNews(). JSON parse.", e);
        }

        // newest posts are shown on top
        if(!roll.isEmpty()) {
            roll.sort((a1, a2) -> a2.getPublishedAt().compareTo(a1.getPublishedAt()));    
        }        

        return Utility.getPage(roll, page, pageSize);
    }

    /**
     * Create API request URL from .properties file
     * 
     * @return String API_URL
     */
    public String getServiceURL() {
        return Utility.getProperty("headlines_url") + Utility.getProperty("apikey_prefix")
                + Utility.getProperty("apikey_token");
    }

}
