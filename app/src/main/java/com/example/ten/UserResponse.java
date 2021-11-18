package com.example.ten;

import java.text.DecimalFormat;
import java.util.Date;

public class UserResponse {

    private Integer id;
    private String un_id;
    private String name;
    private String img;
    private String email;
    private String phone_number;
    private String password;
    private String address;
    private Integer level;
    private String current_balance;
    private Integer plan;
    private Integer total_token;
    private Integer total_withdraw;
    private Integer verify_status;
    private String referedBy;
    private String createdAt;
    private String type;
    private String approved;
    private Boolean response_status;
    private String reason;
    private String status;

    private Integer token_price;



    public UserResponse(int id, String un_id, String name, String img, String email, String phone_number, String password,
                        String address, Integer level,String current_balance, Integer plan, Integer total_token,
                        Integer total_withdraw, Integer verify_status,String type, String approved,
                        Boolean response_status, String reason, String status, Integer token_price){
        this.id = id;
        this.un_id = un_id;
        this.name = name;
        this.img = img;
        this.email = email;
        this.phone_number = phone_number;
        this.password = password;
        this.address = address;
        this.level = level;
        this.current_balance = current_balance;
        this.plan = plan;
        this.total_token = total_token;
        this.total_withdraw = total_withdraw;
        this.verify_status = verify_status;
        this.type = type;
        this.approved = approved;
        this.response_status = response_status;
        this.reason = reason;
        this.status = status;
        this.token_price = token_price;
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

    public String getphone_number() {
        return phone_number;
    }

    public void setphone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getaddress() {
        return address;
    }

    public void setaddress(String address) {
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getApproved() {
        return approved;
    }

    public void setApproved(String approved) {
        this.approved = approved;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Boolean getResponse_status() {
        return response_status;
    }

    public void setResponse_status(Boolean response_status) {
        this.response_status = response_status;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getCurrent_balance() {
        return current_balance;
    }

    public void setCurrent_balance(String current_balance) {
        this.current_balance = current_balance;
    }

    public Integer getPlan() {
        return plan;
    }

    public void setPlan(Integer plan) {
        this.plan = plan;
    }

    public Integer getTotal_token() {
        return total_token;
    }

    public void setTotal_token(Integer total_token) {
        this.total_token = total_token;
    }

    public Integer getVerify_status() {
        return verify_status;
    }

    public void setVerify_status(Integer verify_status) {
        this.verify_status = verify_status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getTotal_withdraw() {
        return total_withdraw;
    }

    public void setTotal_withdraw(Integer total_withdraw) {
        this.total_withdraw = total_withdraw;
    }

    public Integer getToken_price() {
        return token_price;
    }

    public void setToken_price(Integer token_price) {
        this.token_price = token_price;
    }
}