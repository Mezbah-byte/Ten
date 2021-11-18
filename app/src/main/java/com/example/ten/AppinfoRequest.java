package com.example.ten;

public class AppinfoRequest {
    private String app_version;

    public AppinfoRequest(String app_version){
        this.app_version = app_version;
    }

    public String getApp_version() {
        return app_version;
    }

    public void setApp_version(String app_version) {
        this.app_version = app_version;
    }
}
