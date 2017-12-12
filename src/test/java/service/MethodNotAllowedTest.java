package service;

import com.news.util.Utility;
import junit.framework.Assert;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

public class MethodNotAllowedTest {

    HttpUriRequest request;

    @Before
    public void setUp() {
        request = new HttpGet(Utility.getProperty("root_test_url"));
    }

    @Test
    public void test() throws ClientProtocolException, IOException {
        // When
        HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);

        // Then
        Assert.assertEquals(httpResponse.getStatusLine().getStatusCode(), HttpStatus.SC_METHOD_NOT_ALLOWED);
    }

}
