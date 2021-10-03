package com.trustly.api.paymentapiintegration.tools;

import de.dentrassi.crypto.pem.PemKeyStoreProvider;

import java.io.IOException;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;

public class LoadPemKeyStore {
    private String keystorePath = "/private.pem";

    public KeyStore load(String signature) throws KeyStoreException, CertificateException, IOException, NoSuchAlgorithmException {
        KeyStore keyStore = KeyStore.getInstance("PEM", new PemKeyStoreProvider());
        InputStream stream = this.getClass().getResourceAsStream(keystorePath);
        keyStore.load(stream, signature.toCharArray());

        return keyStore;
    }
}