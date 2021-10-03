package com.trustly.api.paymentapiintegration.tools;

import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustAllStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.SSLContext;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;

public class RestTemplateWithSSL {

    public static RestTemplate getRestTemplateWithSSL(String signature) throws CertificateException, NoSuchAlgorithmException, KeyStoreException, IOException, KeyManagementException {
        SSLContext sslContext = new SSLContextBuilder()
                .loadTrustMaterial(
                        new LoadPemKeyStore().load(signature),
                        TrustAllStrategy.INSTANCE
                )
                .build();

        SSLConnectionSocketFactory socketFactory = new SSLConnectionSocketFactory(sslContext);

        CloseableHttpClient httpClient = HttpClients.custom()
                .setSSLSocketFactory(socketFactory)
                .build();

        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory(httpClient);

        return new RestTemplate(factory);
    }
}