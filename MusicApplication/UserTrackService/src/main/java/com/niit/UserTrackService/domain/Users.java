package com.niit.UserTrackService.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
public class Users {
    @Id
    private int userId;
    private String userName;
    private String phoneNo;
    private List<Tracks> tracks;

    public Users() {
    }

    public Users(int userId, String userName, String phoneNo, List<Tracks> tracks) {
        this.userId = userId;
        this.userName = userName;
        this.phoneNo = phoneNo;
        this.tracks = tracks;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public List<Tracks> getTracks() {
        return tracks;
    }

    public void setTracks(List<Tracks> tracks) {
        this.tracks = tracks;
    }

    @Override
    public String toString() {
        return "Users{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", phoneNo='" + phoneNo + '\'' +
                ", tracks=" + tracks +
                '}';
    }
}
