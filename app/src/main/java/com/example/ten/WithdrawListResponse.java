package com.example.ten;

public class WithdrawListResponse {
    String gateway_type,receiver_number;
    Integer amount;
    String date,Img;

    public WithdrawListResponse( String withdraw_gateway_name,String withdraw_number, Integer amount,String created_at,String status){
        this.gateway_type = withdraw_gateway_name;
        this.receiver_number = withdraw_number;
        this.amount = amount;
        this.date = created_at;
        this.Img = status;
    }

    public String getGateway_type() {
        return gateway_type;
    }

    public void setGateway_type(String gateway_type) {
        this.gateway_type = gateway_type;
    }

    public String getReceiver_number() {
        return receiver_number;
    }

    public void setReceiver_number(String receiver_number) {
        this.receiver_number = receiver_number;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getImg() {
        return Img;
    }

    public void setImg(String img) {
        Img = img;
    }
}
