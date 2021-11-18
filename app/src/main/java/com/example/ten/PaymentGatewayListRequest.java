package com.example.ten;

public class PaymentGatewayListRequest {
    private String un_id,payment_type;

    public PaymentGatewayListRequest(String un_id, String payment_type){
        this.un_id = un_id;
        this.payment_type = payment_type;
    }

    public String getUn_id() {
        return un_id;
    }

    public void setUn_id(String un_id) {
        this.un_id = un_id;
    }

    public String getPayment_type() {
        return payment_type;
    }

    public void setPayment_type(String payment_type) {
        this.payment_type = payment_type;
    }
}
