package service;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.news.beans.Article;
import com.news.core.HeadlineService;
import com.news.core.ServiceCaller;
import org.apache.http.HttpException;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SortingListTest {

    String articles = "";
    ServiceCaller mockedCaller;
    ObjectMapper mapper = new ObjectMapper();

    Map<String, String> params = new HashMap<>();

    @Before
    public void setUp() throws Exception {
        mockedCaller = mock(ServiceCaller.class);
        articles =
                "{\"articles\":[{\"title\":\"2\",\"publishedAt\":\"2017-10-13T05:55:30Z\"},{\"title\":\"1\",\"publishedAt\":\"2017-11-13T07:33:42Z\"}]}";
    }

    @Test
    public void sort() throws HttpException, JsonParseException, JsonMappingException, IOException {

        HeadlineService service = new HeadlineService(mockedCaller);
        when(mockedCaller.sendRequest(service.getServiceURL())).thenReturn(articles);
        service = new HeadlineService(mockedCaller);

        Map<String, String> map = service.getNews(params, null, null);
        List<Article> roll =
                mapper.readValue(map.get("result"), new TypeReference<List<Article>>() {});

        // top article must be the newest
        assertEquals("1", roll.get(0).getTitle());
    }


}
