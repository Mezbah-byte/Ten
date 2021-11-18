package com.example.ten;

public class UserdataRequest {
    private Integer id;
    private String un_id;
    private String name;
    private String email;
    private String phone_number;
    private String password;
    private String address;
    private String referedBy;
    private String createdAt;


    public UserdataRequest(String un_id){
        this.un_id = un_id;
        this.name = name;
        this.email = email;
        this.phone_number = phone_number;
        this.password = password;
        this.address = address;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUn_id() {
        return un_id;
    }

    public void setUn_id(String un_id) {
        this.un_id = un_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getReferedBy() {
        return referedBy;
    }

    public void setReferedBy(String referedBy) {
        this.referedBy = referedBy;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
}
