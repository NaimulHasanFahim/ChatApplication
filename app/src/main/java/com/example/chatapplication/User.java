package com.example.chatapplication;

public class User {

    private String uid;
    private String username;
    private String imageURL;

    public User(String uid, String username, String imageURL) {
        this.uid = uid;
        this.username = username;
        this.imageURL = imageURL;
    }
    public  User( ){

    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }
}
