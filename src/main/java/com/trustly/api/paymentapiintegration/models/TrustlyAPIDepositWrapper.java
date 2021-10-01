package com.trustly.api.paymentapiintegration.models;

public class TrustlyAPIDepositWrapper {
    final String method = "Deposit";
    TrustlyAPIDepositCredentials params;
    final String version = "1.1";

    public TrustlyAPIDepositWrapper() {
    }

    public TrustlyAPIDepositWrapper(TrustlyAPIDepositCredentials trustlyAPIDepositCredentials) {
        this.params = trustlyAPIDepositCredentials;
    }
}
