package com.passwordvault;

//this class sets the blueprint for a single saved password
public class PasswordEntry {
    private String platform;
    private String username;
    private String password;

    //constructor
    public PasswordEntry(String platform, String username, String password) {
        this.platform = platform;
        this.username = username;
        this.password = password;
    }

    //getters to get the inputs
    public String getPlatform() {
        return platform;
    }
    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "Platform = " + platform + "\nUsername = " + username + "\nPassword = " + password;
    }
}

