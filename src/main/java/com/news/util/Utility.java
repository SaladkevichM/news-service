package com.news.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

/**
 * Some utility functions
 * 
 * @author Mikita_Saladkevich
 *
 */
public class Utility {

    /**
     * returns a view (not a new list) of the sourceList for the range based on page and pageSize
     * 
     * @param sourceList
     * @param page
     * @param pageSize
     * @return
     */
    public static <T> List<T> getPage(List<T> sourceList, int page, int pageSize) {
        if (pageSize <= 0 || page <= 0) {
            throw new IllegalArgumentException("invalid page size: " + pageSize);
        }

        int fromIndex = (page - 1) * pageSize;
        if (sourceList == null || sourceList.size() < fromIndex) {
            return Collections.emptyList();
        }

        // toIndex exclusive
        return sourceList.subList(fromIndex, Math.min(fromIndex + pageSize, sourceList.size()));
    }

    public static String getProperty(String name) {

        Properties prop = new Properties();
        try (InputStream input =
                Utility.class.getClassLoader().getResourceAsStream("api.properties")) {

            // load a properties file
            prop.load(input);

            // get the property value and print it out
            return prop.getProperty(name);

        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return null;
    }

}
