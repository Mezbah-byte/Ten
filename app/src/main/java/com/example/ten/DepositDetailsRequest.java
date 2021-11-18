package com.example.ten;

public class DepositDetailsRequest {
    Integer payment_type;
    Integer token_quantity;
    public DepositDetailsRequest(Integer payment_type, Integer token_quantity){
        this.payment_type = payment_type;
        this.token_quantity = token_quantity;
    }

    public Integer getPayment_type() {
        return payment_type;
    }

    public void setPayment_type(Integer payment_type) {
        this.payment_type = payment_type;
    }

    public Integer getToken_quantity() {
        return token_quantity;
    }

    public void setToken_quantity(Integer token_quantity) {
        this.token_quantity = token_quantity;
    }
}
