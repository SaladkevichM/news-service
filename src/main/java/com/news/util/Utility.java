package com.news.util;

import com.google.gson.Gson;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import org.apache.http.HttpException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ws.rs.core.MediaType;

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
     * @throws HttpException
     */
    public static <T> List<T> getPage(List<T> sourceList, Integer page, Integer pageSize)
            throws HttpException {
        if (page == null || pageSize == null) {
            return sourceList;
        }

        if (pageSize >= sourceList.size()) {
            return sourceList;
        }

        if (pageSize <= 0 || page <= 0) {
            throw new HttpException("IllegalArgument. Invalid page or pageSize.");
        }

        int fromIndex = (page - 1) * pageSize;
        if (sourceList.size() < fromIndex) {            
            throw new HttpException("IllegalArgument. Invalid page.");
        }

        // toIndex exclusive
        return sourceList.subList(fromIndex, Math.min(fromIndex + pageSize, sourceList.size()));
    }

    /**
     * JSON string that is described an error.
     * 
     * @param code
     * @param message
     * @return json String
     */
    public static String createError(Integer code, String message) {
        Map<String, String> error = new HashMap<>();
        error.put("code", code.toString());
        error.put("message", message);
        return new Gson().toJson(error);
    }

    /**
     * Call some REST service
     * 
     * @param url
     * @return json String
     * @throws HttpException
     */
    public static String sendRequest(String url) throws HttpException {

        Client client = new Client();

        WebResource resource = client.resource(url);
        ClientResponse response =
                resource.accept(MediaType.APPLICATION_JSON).get(ClientResponse.class);

        if (response.getStatus() != 200) {
            throw new HttpException(response.toString());
        }

        return response.getEntity(String.class);
    }

    /**
     * Get property value from api.properties
     * 
     * @param name
     * @return String
     * @throws HttpException
     * @throws IOException
     */
    public static String getProperty(String name) throws HttpException {

        Properties prop = new Properties();
        try (InputStream input =
                Utility.class.getClassLoader().getResourceAsStream("api.properties")) {

            // load a properties file
            prop.load(input);

            if(prop.getProperty(name) == null) {
                throw new HttpException("Property value not found: " + name);
            }
            
            // get the property value and print it out
            return prop.getProperty(name);

        } catch (IOException ex) {
            logger.log(Level.INFO, "Property file not found", ex);
            throw new HttpException(ex.getMessage());
        }

    }

}
