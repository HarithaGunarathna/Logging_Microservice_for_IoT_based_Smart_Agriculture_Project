package com.example.co227_project.User;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserRequest {

    @JsonProperty("UserName")
    private String UserName;

    @JsonProperty("Password")
    private String Password;

    @JsonProperty("Serverity")
    private String Serverity;

    public UserRequest() {
    }

    public UserRequest(String userName, String password , String serverity) {
        Serverity = serverity;
        UserName = userName;
        Password = password;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getServerity() {
        return Serverity;
    }

    public void setServerity(String serverity) {
        Serverity = serverity;
    }
}
