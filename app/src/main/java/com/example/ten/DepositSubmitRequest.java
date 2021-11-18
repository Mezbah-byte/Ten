package com.example.ten;

public class DepositSubmitRequest {
    String un_id,deposit_number,trx;
    Integer token_quantity,gateway_id;

    public DepositSubmitRequest(String un_id,String deposit_number, String trx,Integer token_quantity, Integer gateway_id){
        this.un_id = un_id;
        this.deposit_number = deposit_number;
        this.trx = trx;
        this.token_quantity = token_quantity;
        this.gateway_id = gateway_id;
    }

    public String getUn_id() {
        return un_id;
    }

    public void setUn_id(String un_id) {
        this.un_id = un_id;
    }

    public String getDeposit_number() {
        return deposit_number;
    }

    public void setDeposit_number(String deposit_number) {
        this.deposit_number = deposit_number;
    }

    public String getTrx() {
        return trx;
    }

    public void setTrx(String trx) {
        this.trx = trx;
    }

    public Integer getToken_quantity() {
        return token_quantity;
    }

    public void setToken_quantity(Integer token_quantity) {
        this.token_quantity = token_quantity;
    }

    public Integer getGateway_id() {
        return gateway_id;
    }

    public void setGateway_id(Integer gateway_id) {
        this.gateway_id = gateway_id;
    }
}
