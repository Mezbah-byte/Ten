package com.example.ten;

public class AdResponse {
    private Boolean response_status;
    private String response_reason;
    private Integer remaining_ad;
    private String ad_network;
    private String ad_api;

    public AdResponse(Boolean response_status, String response_reason,Integer remaining_ad,String ad_network,String ad_api){
        this.response_status = response_status;
        this.response_reason = response_reason;
        this.remaining_ad = remaining_ad;
        this.ad_network = ad_network;
        this.ad_api = ad_api;
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

    public Integer getRemaining_ad() {
        return remaining_ad;
    }

    public void setRemaining_ad(Integer remaining_ad) {
        this.remaining_ad = remaining_ad;
    }

    public String getAd_network() {
        return ad_network;
    }

    public void setAd_network(String ad_network) {
        this.ad_network = ad_network;
    }

    public String getAd_api() {
        return ad_api;
    }

    public void setAd_api(String ad_api) {
        this.ad_api = ad_api;
    }
}
