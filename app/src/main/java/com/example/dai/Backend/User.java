package com.example.dai.Backend;

public class User {
    private int id_user;
    private String username;
    private String password;
    private String e_mail;

    public User() {}

    public User(String e_mail, int id_user) {
        this.id_user = id_user;
        this.e_mail = e_mail;
    }

    public User(String username, String password, String e_mail) {
        this.username = username;
        this.password = password;
        this.e_mail = e_mail;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getE_mail() {
        return e_mail;
    }

    public void setE_mail(String e_mail) {
        this.e_mail = e_mail;
    }

    @Override
    public String toString() {
        return "User: " + "username = " + username +
                ", password = " + password +
                ", e_mail = " + e_mail;
    }
}
