package com.example.ten;

public class DepositSubmitResponse {
    Boolean response_status;
    String response_reason;

    public DepositSubmitResponse(Boolean response_status,String response_reason){
        this.response_status = response_status;
        this.response_reason = response_reason;
    }

    public Boolean getResponse_status() {
        return response_status;
    }

    public void setResponse_status(Boolean response_status) {
        this.response_status = response_status;
    }

    public String getResponse_reason() {
        return response_reason;
    }

    public void setResponse_reason(String response_reason) {
        this.response_reason = response_reason;
    }
}
