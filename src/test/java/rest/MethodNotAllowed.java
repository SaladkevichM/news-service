package rest;

import com.news.rest.Server;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.test.framework.JerseyTest;
import junit.framework.Assert;
import org.apache.http.HttpStatus;
import org.junit.Test;

public class MethodNotAllowed extends JerseyTest {

    public MethodNotAllowed() {
        super(Server.class.getPackage().getName());
    }
    
    @Test
    public void response_BaseUrl_NotAllowed() {
        ClientResponse response = resource().path("/")
                .get(ClientResponse.class);
        Assert.assertEquals(response.getStatus(), HttpStatus.SC_METHOD_NOT_ALLOWED);
    }

}
