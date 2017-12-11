package com.news.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Some utility functions
 * 
 * @author Mikita_Saladkevich
 *
 */
public class Utility {

    public static String getProperty(String name) {

        Properties prop = new Properties();
        try (InputStream input =
                Utility.class.getClassLoader().getResourceAsStream("newsapi.properties")) {

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
