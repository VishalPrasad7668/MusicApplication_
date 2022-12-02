package com.niit.UserTrackService.rabbitMQProducer;

public class CommonUser {
    private int userId;
    private String userName;
    private String password;
    private String phoneNo;

    public CommonUser() {
    }

    public CommonUser(int userId, String userName, String password, String phoneNo) {
        this.userId = userId;
        this.userName = userName;
        this.password = password;
        this.phoneNo = phoneNo;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "CommonUser{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", phoneNo='" + phoneNo + '\'' +
                '}';
    }
}
