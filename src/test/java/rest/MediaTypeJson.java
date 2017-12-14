package rest;

import static org.junit.Assert.*;

import com.news.rest.Server;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.test.framework.JerseyTest;
import org.apache.http.HttpStatus;
import org.junit.Test;

import javax.ws.rs.core.MediaType;

public class MediaTypeJson extends JerseyTest {

    public MediaTypeJson() {
        super(Server.class.getPackage().getName());
    }

    @Test
    public void mediaType_Headlines_Json() {
        ClientResponse response = resource().path("news").accept(MediaType.APPLICATION_JSON)
                .get(ClientResponse.class);
        assertEquals(HttpStatus.SC_OK, response.getStatus());
    }

    @Test
    public void mediaType_Sources_Json() {
        ClientResponse response =
                resource().path("sources").queryParam("page", "1").queryParam("page_size", "1")
                        .accept(MediaType.APPLICATION_JSON).get(ClientResponse.class);
        assertEquals(HttpStatus.SC_OK, response.getStatus());
    }

}
