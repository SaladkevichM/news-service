package com.news.util;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.ws.http.HTTPException;

/**
 * Some utility functions
 * 
 * @author Mikita_Saladkevich
 *
 */
public final class Utility {

    private Utility() {
        // prevent to create instance of Utility
    }

    private static Logger logger = Logger.getLogger(Utility.class.getName());

    /**
     * returns a view (not a new list) of the sourceList for the range based on page and pageSize
     * 
     * @param sourceList
     * @param page
     * @param pageSize
     * @return List<T>
     */
    public static <T> List<T> getPage(List<T> sourceList, Integer page, Integer pageSize) {
        if (page == null || pageSize == null) {
            return sourceList;
        }

        if (pageSize >= sourceList.size()) {
            return sourceList;
        }

        if (pageSize <= 0 || page <= 0) {
            throw new IllegalArgumentException("invalid page size: " + pageSize);
        }

        int fromIndex = (page - 1) * pageSize;
        if (sourceList.size() < fromIndex) {
            return Collections.emptyList();
        }

        // toIndex exclusive
        return sourceList.subList(fromIndex, Math.min(fromIndex + pageSize, sourceList.size()));
    }

    /**
     * Call some REST service
     * 
     * @param url
     * @return json String
     */
    public static String sendRequest(String url) {

        Client client = new Client();

        WebResource resource = client.resource(url);
        ClientResponse response = resource.accept("application/json").get(ClientResponse.class);

        if (response.getStatus() != 200) {
            throw new HTTPException(response.getStatus());
        }

        return response.getEntity(String.class);
    }

    /**
     * Get property value from api.properties
     * 
     * @param name
     * @return String
     */
    public static String getProperty(String name) {

        Properties prop = new Properties();
        try (InputStream input =
                Utility.class.getClassLoader().getResourceAsStream("api.properties")) {

            // load a properties file
            prop.load(input);

            // get the property value and print it out
            return prop.getProperty(name);

        } catch (IOException ex) {
            logger.log(Level.INFO, "Property file not found", ex);
        }

        return null;
    }

}
