package rest;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.news.beans.Article;
import com.news.beans.Source;
import com.news.core.HeadlineService;
import com.news.core.SourceService;
import com.news.ext.ServiceCaller;
import org.apache.http.HttpException;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CorrectBehavior {

    String articles = "";
    String sources = "";

    ServiceCaller mockedCaller = mock(ServiceCaller.class);

    HeadlineService head = new HeadlineService(mockedCaller);
    SourceService source = new SourceService(mockedCaller);

    ObjectMapper mapper = new ObjectMapper();
    Map<String, String> params = new HashMap<>();

    @Before
    public void setUp() throws Exception {
        articles =
                "{\"articles\":[{\"title\":\"ArticleOne\",\"publishedAt\":\"2017-10-13T05:55:30Z\"}]}";

        sources = "{\"sources\":[{\"id\":\"bbc-news\",\"name\":\"BBC\"}]}";
    }

    @Test
    public void getNews_ValidJson_CorrectResult()
            throws JsonParseException, JsonMappingException, IOException, HttpException {
        when(mockedCaller.sendRequest(head.getServiceURL())).thenReturn(articles);
        head = new HeadlineService(mockedCaller);

        Map<String, String> map = head.getNews(params, null, null);
        List<Article> roll =
                mapper.readValue(map.get("result"), new TypeReference<List<Article>>() {});

        // first article must be the newest
        assertEquals("ArticleOne", roll.get(0).getTitle());
    }

    @Test
    public void getSources_ValidJson_CorrectResult()
            throws JsonParseException, JsonMappingException, IOException, HttpException {
        when(mockedCaller.sendRequest(source.getServiceURL())).thenReturn(sources);
        source = new SourceService(mockedCaller);

        Map<String, String> map = source.getSources(null, null);
        List<Source> roll =
                mapper.readValue(map.get("result"), new TypeReference<List<Source>>() {});

        // first article must be the newest
        assertEquals("BBC", roll.get(0).getName());
    }

}
