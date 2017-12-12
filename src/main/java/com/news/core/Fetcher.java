package com.news.core;

import com.news.util.Utility;
import java.util.Map;

public abstract class Fetcher {

    public String API_URL = getRequestURL();

    public abstract String getRequestURL();

    /**
     * Populate API_URL with query params
     * 
     * @return String URL
     */
    public String populateURL(Map<String, String> params) {
        StringBuilder appendix = new StringBuilder();
        for (Map.Entry<String, String> entry : params.entrySet()) {
            appendix.append("&").append(entry.getKey()).append("=")
                    .append(entry.getValue() != null ? entry.getValue()
                            : Utility.getProperty("default_" + entry.getKey()));
        }
        return API_URL.concat(appendix.toString());
    }

}
