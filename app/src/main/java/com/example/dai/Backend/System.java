package com.example.dai.Backend;

public class System {
    private User user_connected;

    public System() {}

    public System(User user_connected) {
        this.user_connected = user_connected;
    }

    public User getUser_connected() {
        return user_connected;
    }

    public void setUser_connected(User user_connected) {
        this.user_connected = user_connected;
    }

    //Login user
}
