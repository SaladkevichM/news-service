package com.news.ext;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import org.apache.http.HttpException;

import javax.ws.rs.core.MediaType;

/**
 * Calling another REST service
 * 
 * @author Mikita_Saladkevich
 *
 */
public class ServiceCaller {

    /**
     * Call service & get JSON string
     * 
     * @param url
     * @return json String
     * @throws HttpException
     */
    public String sendRequest(String url) throws HttpException {

        Client client = new Client();

        WebResource resource = client.resource(url);
        ClientResponse response =
                resource.accept(MediaType.APPLICATION_JSON).get(ClientResponse.class);

        if (response.getStatus() != 200) {
            throw new HttpException(response.toString());
        }

        return response.getEntity(String.class);
    }

}
