package com.example.ten;

public class DepositDetailsResponse {
    Boolean response_status;
    String response_reason,gateway_type_details,gateway_desc,gateway_number;
    String total_price;
    public DepositDetailsResponse(Boolean response_status,String response_reason, String gateway_type_details, String gateway_desc, String gateway_number, String total_price){
        this.response_status = response_status;
        this.response_reason = response_reason;
        this.gateway_type_details  = gateway_type_details;
        this.gateway_desc = gateway_desc;
        this.gateway_number = gateway_number;
        this.total_price = total_price;
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

    public String getGateway_type_details() {
        return gateway_type_details;
    }

    public void setGateway_type_details(String gateway_type_details) {
        this.gateway_type_details = gateway_type_details;
    }

    public String getGateway_desc() {
        return gateway_desc;
    }

    public void setGateway_desc(String gateway_desc) {
        this.gateway_desc = gateway_desc;
    }

    public String getGateway_number() {
        return gateway_number;
    }

    public void setGateway_number(String gateway_number) {
        this.gateway_number = gateway_number;
    }

    public String getTotal_price() {
        return total_price;
    }

    public void setTotal_price(String total_price) {
        this.total_price = total_price;
    }
}
