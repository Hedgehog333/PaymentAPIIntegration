package com.trustly.api.paymentapiintegration.models;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class TrustlyAPIDepositCredentials {
    @Value("${trustly.signature}")
    public String signature;

    @Value("${trustly.uuid}")
    private String UUID;

    private Deposit data;

    public TrustlyAPIDepositCredentials(Deposit data) {

        this.data = data;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getUUID() {
        return UUID;
    }

    public void setUUID(String UUID) {
        this.UUID = UUID;
    }

    public Deposit getData() {
        return data;
    }

    public void setData(Deposit data) {
        this.data = data;
    }
}
