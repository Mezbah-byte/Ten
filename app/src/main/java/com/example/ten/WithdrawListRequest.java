package com.example.ten;

public class WithdrawListRequest {
    String un_id,type;

    public WithdrawListRequest(String un_id,String type){
        this.un_id = un_id;
        this.type = type;
    }

    public String getUn_id() {
        return un_id;
    }

    public void setUn_id(String un_id) {
        this.un_id = un_id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
