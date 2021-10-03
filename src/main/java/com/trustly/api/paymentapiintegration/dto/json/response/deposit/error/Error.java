package com.trustly.api.paymentapiintegration.dto.json.response.deposit.error;

public class Error {
    private String message;
    private DepositeError depositeError;
    private Integer code;
    private String name;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public DepositeError getDepositeError() {
        return depositeError;
    }

    public void setDepositeError(DepositeError depositeError) {
        this.depositeError = depositeError;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
