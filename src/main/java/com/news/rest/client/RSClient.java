package com.news.rest.client;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class RSClient {

    public static String getResponse(String url) {

        Client client = Client.create();

        WebResource resource = client.resource(url);
        ClientResponse response = resource.accept("application/json").get(ClientResponse.class);

        if (response.getStatus() != 200) {
            throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
        }

        return response.getEntity(String.class);
    }

}
