package com.example.ten;

public class AdRequest {
    private String un_id;
    private String screen;

    public AdRequest(String un_id,String screen){
        this.un_id = un_id;
        this.screen = screen;
    }

    public String getUn_id() {
        return un_id;
    }

    public void setUn_id(String un_id) {
        this.un_id = un_id;
    }

    public String getScreen() {
        return screen;
    }

    public void setScreen(String screen) {
        this.screen = screen;
    }
}
