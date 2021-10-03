package com.trustly.api.paymentapiintegration.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.trustly.api.paymentapiintegration.dto.json.response.deposit.SuccessOrError;
import com.trustly.api.paymentapiintegration.tools.RSA;
import com.trustly.api.paymentapiintegration.tools.RestTemplateMaker;
import com.trustly.api.paymentapiintegration.models.Attributes;
import com.trustly.api.paymentapiintegration.models.Deposit;
import com.trustly.api.paymentapiintegration.models.TrustlyAPIDepositCredentials;
import com.trustly.api.paymentapiintegration.models.TrustlyAPIDepositWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.crypto.Cipher;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.PublicKey;

@RestController
public class CallApiController {

    @Autowired
    TrustlyAPIDepositCredentials trustlyAPIDepositCredentials;

    final String trustlyApiUrl = "https://test.trustly.com/api/1";

    @RequestMapping(value = "/call", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public String call(Deposit deposit, Attributes attributes) throws IOException {
        deposit.setAttributes(attributes);
        trustlyAPIDepositCredentials.setData(deposit);

        generateSignature(deposit);

        SuccessOrError SOE = RestTemplateMaker
                .getRestTemplate()
                .postForObject(
                        trustlyApiUrl,
                        makeJsonObject().toString(),
                        SuccessOrError.class);

        String result;

        if (SOE.getResult() != null) {
            //TODO: redirect to another page
            result = "<iframe src=\"" + SOE.getResult().getData().getUrl() + "\"></iframe>";
        } else {
            ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
            result = ow.writeValueAsString(SOE.getError());
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

    //TODO: fix max length
    private void generateSignature(Deposit deposit) {
        RSA rsa = new RSA();
        try
        {
            PublicKey publicKey = rsa.readPublicKey("src/main/resources/public.der");

            Cipher encryptCipher = Cipher.getInstance("RSA");
            encryptCipher.init(Cipher.ENCRYPT_MODE, publicKey);

            byte[] secretMessageBytes = deposit.toString().getBytes(StandardCharsets.UTF_8);
            byte[] encryptedMessageBytes = encryptCipher.doFinal(secretMessageBytes);

            trustlyAPIDepositCredentials.setSignature(new String(encryptedMessageBytes, StandardCharsets.UTF_8));
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}