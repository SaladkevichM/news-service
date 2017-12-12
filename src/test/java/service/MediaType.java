package service;

import static org.junit.Assert.*;

import com.news.util.Utility;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

public class MediaType {

    HttpUriRequest request;

    @Before
    public void setUp() {
        String random = RandomStringUtils.randomAlphabetic(8);
        request = new HttpGet(Utility.getProperty("rest_test_url") + random);
    }

    @Test
    public void test() throws ClientProtocolException, IOException {

        // Given
        String jsonMimeType = "application/json";

        // When
        HttpResponse response = HttpClientBuilder.create().build().execute(request);

        // Then
        String mimeType = ContentType.getOrDefault(response.getEntity()).getMimeType();
        assertEquals(jsonMimeType, mimeType);
    }

}
