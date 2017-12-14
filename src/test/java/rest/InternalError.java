package rest;

import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.test.framework.JerseyTest;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.http.HttpStatus;
import org.junit.Assert;
import org.junit.Test;

public class InternalError extends JerseyTest {

    public InternalError() {
        super("com.news.rest");
    }

    @Test
    public void responseApi_InvQuery_InternalError() {
        ClientResponse response = resource().path("news")
                .queryParam("sources", RandomStringUtils.randomAlphabetic(3))
                .get(ClientResponse.class);
        Assert.assertEquals(HttpStatus.SC_INTERNAL_SERVER_ERROR, response.getStatus());
    }

}
