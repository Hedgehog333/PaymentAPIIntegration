package com.trustly.api.paymentapiintegration.dto.json.response.deposit.success;

public class Data {
    private String orderid;
    private String url;

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
