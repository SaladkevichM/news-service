package rest;

import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.test.framework.JerseyTest;
import junit.framework.Assert;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.junit.Test;

import java.io.IOException;

public class NotFound extends JerseyTest {

    public NotFound() {
        super("com.news.rest");
    }

    @Test
    public void response_BaseUrl_NotFound() throws ClientProtocolException, IOException {
        ClientResponse response = resource().path("/" + RandomStringUtils.randomAlphabetic(3))
                .get(ClientResponse.class);
        Assert.assertEquals(response.getStatus(), HttpStatus.SC_NOT_FOUND);
    }

}
