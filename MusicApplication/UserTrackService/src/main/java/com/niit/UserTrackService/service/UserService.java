package com.niit.UserTrackService.service;

import com.niit.UserTrackService.domain.Tracks;
import com.niit.UserTrackService.domain.Users;
import com.niit.UserTrackService.exception.TrackAlreadyExistException;
import com.niit.UserTrackService.exception.TrackNotFoundException;
import com.niit.UserTrackService.exception.UserAlreadyExistException;
import com.niit.UserTrackService.exception.UserNotFoundException;
import com.niit.UserTrackService.rabbitMQProducer.CommonUser;

import java.util.List;

public interface UserService {
    public Users addUser(Users users) throws UserAlreadyExistException;
    Users addUsers(CommonUser commonUser);
    public Users addTrackForUser(int userId, Tracks tracks) throws UserNotFoundException, TrackAlreadyExistException;
    public List<Tracks> getAllTracksOfUser(int userId) throws UserNotFoundException;
    public String deleteTrackOfUser(int userId, int trackId) throws TrackNotFoundException,UserNotFoundException;
    public Users updateTrackOfUser(int userId,Tracks tracks) throws UserNotFoundException;
}
