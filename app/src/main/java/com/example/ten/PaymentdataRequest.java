package com.example.ten;

public class PaymentdataRequest {
    private String un_id;
    private Integer amount;

    public PaymentdataRequest(String un_id,Integer amount){
        this.un_id = un_id;
        this.amount = amount;
    }

    public String getUn_id() {
        return un_id;
    }

    public void setUn_id(String un_id) {
        this.un_id = un_id;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
}
