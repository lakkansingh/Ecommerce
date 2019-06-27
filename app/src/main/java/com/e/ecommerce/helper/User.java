package com.e.ecommerce.helper;

class User {
    private String userName;
    private String userFisrtName;
    private String userLastName;
    private String email;
    private String mobile;
    private String userId;
    private String apiKey;
    private int role;

    public User() {
    }

    public User(String userName, String email, String mobile, String apiKey, String userId,String userFisrtName,String userLastName,int role) {
        this.userName = userName;
        this.email = email;
        this.mobile = mobile;
        this.apiKey = apiKey;
        this.userId = userId;
        this.userFisrtName = userFisrtName;
        this.userLastName = userLastName;
        this.role=role;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserFisrtName() {
        return userFisrtName;
    }

    public void setUserFisrtName(String userFisrtName) {
        this.userFisrtName = userFisrtName;
    }

    public String getUserLastName() {
        return userLastName;
    }

    public void setUserLastName(String userLastName) {
        this.userLastName = userLastName;
    }

    public String getName() {
        return userName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setName(String name) {
        this.userName = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }
}
