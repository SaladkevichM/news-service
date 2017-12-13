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

public class ApiAccessTest {

    HttpUriRequest request;

    @Before
    public void setUp() {
        request = new HttpGet(Utility.getProperty("sources_url")
                + Utility.getProperty("apikey_prefix") + Utility.getProperty("apikey_token"));
    }

    @Test
    public void test() throws ClientProtocolException, IOException {
        HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);

        Assert.assertEquals(httpResponse.getStatusLine().getStatusCode(),
                HttpStatus.SC_OK);
    }

}
