package com.example.ten;

public class AppinfoResponse {
    private Boolean response_status;
    private String response_reason;
    private String current_app_version;

    public AppinfoResponse(Boolean response_status, String response_reason, String current_app_version){
        this.response_status = response_status;
        this.response_reason = response_reason;
        this.current_app_version = current_app_version;
    }

    public boolean isResponse_status() {
        return response_status;
    }

    public void setResponse_status(boolean response_status) {
        this.response_status = response_status;
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

    public String getCurrent_app_version() {
        return current_app_version;
    }

    public void setCurrent_app_version(String current_app_version) {
        this.current_app_version = current_app_version;
    }
}
