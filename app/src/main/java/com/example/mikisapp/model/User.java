package com.example.mikisapp.model;

public class User {
    private String id;
    private String imageURL;
    private String username;
    private String email;
    private String password;
    public User(String id, String username, String email, String password, String imageURL) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.username = username;
        this.imageURL = imageURL;
    }

    public User(){

    }
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String kullaniciadi) {
        this.username = kullaniciadi;
    }
}
