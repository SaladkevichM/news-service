package service;

import com.news.util.Utility;
import junit.framework.Assert;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.http.HttpException;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

/**
 * Test case for 404 status
 * 
 * @author Mikita_Saladkevich
 *
 */
public class NotFoundTest {

    HttpUriRequest request;

    @Before
    public void setUp() throws HttpException {
        request = new HttpGet(
                Utility.getProperty("root_test_url") + RandomStringUtils.randomAlphabetic(8));
    }

    @Test
    public void response_BaseUrl_NotFound() throws ClientProtocolException, IOException {
        HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);
        Assert.assertEquals(httpResponse.getStatusLine().getStatusCode(), HttpStatus.SC_NOT_FOUND);
    }

}
