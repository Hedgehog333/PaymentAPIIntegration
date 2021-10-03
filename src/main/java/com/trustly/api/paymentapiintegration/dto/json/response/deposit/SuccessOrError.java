package com.trustly.api.paymentapiintegration.dto.json.response.deposit;

import com.trustly.api.paymentapiintegration.dto.json.response.deposit.error.Error;
import com.trustly.api.paymentapiintegration.dto.json.response.deposit.success.Result;

public class SuccessOrError {
    private Error error;
    private Result result;
    private String version;

    public Error getError() {
        return error;
    }

    public void setError(Error error) {
        this.error = error;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
