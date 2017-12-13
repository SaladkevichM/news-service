package com.news.core;

import com.news.util.Utility;
import java.util.Map;

/**
 * Contract to get service URL
 * 
 * @author Mikita_Saladkevich
 *
 */
public interface ServiceURL {

    public abstract String getServiceURL();

    /**
     * Populate API_URL with query params
     * 
     * @return String URL with query params
     */
    default String populateURL(Map<String, String> params) {
        StringBuilder appendix = new StringBuilder();
        for (Map.Entry<String, String> entry : params.entrySet()) {
            appendix.append("&").append(entry.getKey()).append("=")
                    .append(entry.getValue() != null ? entry.getValue()
                            : Utility.getProperty("default_" + entry.getKey()));
        }
        return getServiceURL().concat(appendix.toString());
    }

}
