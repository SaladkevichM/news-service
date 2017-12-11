package com.news.core;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.news.beans.Article;
import com.news.rest.client.RestClient;
import com.news.util.Utility;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class NewsApiOrg implements NewsPuller {

    /**
     * Getting the news roll
     */
    public List<Article> getRoll(Map<String, String> queryParams) {
        List<Article> roll = new ArrayList<>();

        String requestURL = createAPIRequestURL(queryParams);
        String json = RestClient.doGet(requestURL);

        if (json != null) {
            try {
                roll = new ObjectMapper().readValue(json, new TypeReference<List<Article>>() {});
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return roll;
    }

    /**
     * Create API request URL
     * 
     * @param queryParams
     * @return
     */
    public String createAPIRequestURL(Map<String, String> queryParams) {
        String url = Utility.getProperty("api_url") + Utility.getProperty("apikey_prefix")
        + Utility.getProperty("apikey_token");

        for (Entry<String, String> entry : queryParams.entrySet()) {
            url += "&".concat(entry.getKey());
            url += "=".concat(entry.getValue());
        }
        return url;
    }

}
