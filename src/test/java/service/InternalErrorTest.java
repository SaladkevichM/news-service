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

public class InternalErrorTest {

    HttpUriRequest request;

    @Before
    public void setUp() throws HttpException {
        request = new HttpGet(Utility.getProperty("headlines_test_url") + "?sources="
                + RandomStringUtils.randomAlphabetic(3));
    }

    @Test
    public void responseApi_InvQuery_InternalError() throws ClientProtocolException, IOException {
        HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);

        Assert.assertEquals(httpResponse.getStatusLine().getStatusCode(),
                HttpStatus.SC_INTERNAL_SERVER_ERROR);
    }

}
