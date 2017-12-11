package com.news.core;

import com.news.beans.Article;

import java.util.List;
import java.util.Map;

/**
 * contract for all NewsPuller's implementations
 * 
 * @author Mikita_Saladkevich
 *
 */
public interface NewsPuller {
    List<Article> getRoll(Map<String, String> queryParams);
    String createAPIRequestURL(Map<String, String> queryParams);
}
