package com.trustly.api.paymentapiintegration.controller;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.trustly.api.paymentapiintegration.models.Attributes;
import com.trustly.api.paymentapiintegration.models.Deposit;
import com.trustly.api.paymentapiintegration.models.TrustlyAPIDepositCredentials;
import com.trustly.api.paymentapiintegration.models.TrustlyAPIDepositWrapper;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class CallApiController {

    @RequestMapping(value = "/call", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public String call(Deposit deposit, Attributes attributes) {
        deposit.setAttributes(attributes);

        final String trustlyApiUrl = "https://test.trustly.com/api/1";

        HttpClient httpClient = HttpClientBuilder.create().build();
        ClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory(httpClient);
        RestTemplate restTemplate = new RestTemplate(requestFactory);

        String result = restTemplate.getForObject(trustlyApiUrl, String.class, makeJsonObject(deposit));

        return result;
    }

    private JsonObject makeJsonObject(Deposit deposit) {
        Gson gson = new Gson();
        JsonObject wrapper = gson.toJsonTree(new TrustlyAPIDepositWrapper(null)).getAsJsonObject();

        Gson gsonBuilderUpperCamelCase = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE).create();
        JsonObject content = gsonBuilderUpperCamelCase.toJsonTree(new TrustlyAPIDepositCredentials(deposit)).getAsJsonObject();

        wrapper.add("params", content);

        return wrapper;
    }
}