package com.trustly.api.paymentapiintegration.controller;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.trustly.api.paymentapiintegration.tools.RestTemplateWithSSL;
import com.trustly.api.paymentapiintegration.models.Attributes;
import com.trustly.api.paymentapiintegration.models.Deposit;
import com.trustly.api.paymentapiintegration.models.TrustlyAPIDepositCredentials;
import com.trustly.api.paymentapiintegration.models.TrustlyAPIDepositWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CallApiController {

    @Autowired
    TrustlyAPIDepositCredentials trustlyAPIDepositCredentials;

    final String trustlyApiUrl = "https://test.trustly.com/api/1";

    @RequestMapping(value = "/call", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public String call(Deposit deposit, Attributes attributes) {
        deposit.setAttributes(attributes);
        trustlyAPIDepositCredentials.setData(deposit);

        String result;
        try {
            result = RestTemplateWithSSL
                    .getRestTemplateWithSSL(trustlyAPIDepositCredentials.getSignature())
                    .postForObject(
                            trustlyApiUrl,
                            makeJsonObject().toString(),
                            String.class);
        } catch (Exception e) {
            result = e.getMessage();
        }

        return result;
    }

    private JsonObject makeJsonObject() {
        Gson gson = new Gson();
        JsonObject wrapper = gson.toJsonTree(new TrustlyAPIDepositWrapper()).getAsJsonObject();

        Gson gsonBuilderUpperCamelCase = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE).create();
        JsonObject content = gsonBuilderUpperCamelCase.toJsonTree(trustlyAPIDepositCredentials).getAsJsonObject();

        wrapper.add("params", content);

        return wrapper;
    }
}