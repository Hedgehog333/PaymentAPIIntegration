package com.trustly.api.paymentapiintegration.tools;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

public class RestTemplateMaker {

    public static RestTemplate getRestTemplate() {
        CloseableHttpClient httpClient = HttpClients.custom()
                .build();

        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory(httpClient);

        return new RestTemplate(factory);
    }
}