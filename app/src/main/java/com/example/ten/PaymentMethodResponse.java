package com.example.ten;

public class PaymentMethodResponse {
    public Boolean response_status;
    public String response_reason;
    public String payment_type;

    public PaymentMethodResponse(Boolean response_status,String response_reason,String payment_type){
        this.response_status = response_status;
        this.response_reason = response_reason;
        this.payment_type = payment_type;
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

    public String getPayment_type() {
        return payment_type;
    }

    public void setPayment_type(String payment_type) {
        this.payment_type = payment_type;
    }
}
