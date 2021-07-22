package com.example.test_project;

public class User {

    public String userName;
    public String passWd;
    public String phonenum;

    public User() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public User(String userName, String passWd, String phonenum) {
        this.userName = userName;
        this.passWd = passWd;
        this.phonenum = phonenum;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWd() {
        return passWd;
    }

    public void setPassWd(String passWd) {
        this.passWd = passWd;
    }

    public String getPhonenum(){return phonenum;}

    public void setPhonenum(String phonenum){this.phonenum = phonenum;}
    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", passWd='" + passWd + '\'' +
                ", phone='" + phonenum + '\'' +
                '}';
    }
}