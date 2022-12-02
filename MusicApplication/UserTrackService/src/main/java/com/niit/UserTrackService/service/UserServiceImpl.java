package com.niit.UserTrackService.service;

import com.niit.UserTrackService.domain.Tracks;
import com.niit.UserTrackService.domain.Users;
import com.niit.UserTrackService.exception.TrackAlreadyExistException;
import com.niit.UserTrackService.exception.TrackNotFoundException;
import com.niit.UserTrackService.exception.UserAlreadyExistException;
import com.niit.UserTrackService.exception.UserNotFoundException;
import com.niit.UserTrackService.rabbitMQProducer.CommonUser;
import com.niit.UserTrackService.rabbitMQProducer.Producer;
import com.niit.UserTrackService.rabbitMQProducer.UserDTO;
import com.niit.UserTrackService.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
@Service
public class UserServiceImpl implements UserService{
    private UserRepository userRepository;
    private Producer producer;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, Producer producer) {
        this.userRepository = userRepository;
        this.producer = producer;
    }

    @Override
    public Users addUser(Users users) throws UserAlreadyExistException {
        if (userRepository.findById(users.getUserId()).isPresent()){
            throw new UserAlreadyExistException();
        }
        return userRepository.insert(users);
    }

    @Override
    public Users addUsers(CommonUser commonUser) {
        UserDTO userDTO = new UserDTO(commonUser.getUserId(),commonUser.getPassword());
        producer.sendDtoToQueue(userDTO);
        Users users = new Users(commonUser.getUserId(),commonUser.getUserName(),commonUser.getPhoneNo(),new ArrayList<>());
        return userRepository.insert(users);
    }

    @Override
    public Users addTrackForUser(int userId, Tracks tracks) throws UserNotFoundException, TrackAlreadyExistException {
        if (userRepository.findById(userId).isEmpty()){
            throw new  UserNotFoundException();
        }
        Users user = userRepository.findByUserId(userId);
        if (user.getTracks()==null){
            user.setTracks(Arrays.asList(tracks));
        }
        List<Tracks> tracksList = user.getTracks();
        Iterator<Tracks> iterator = tracksList.iterator();
        while (iterator.hasNext()){
            Tracks track1 = iterator.next();
            if(track1.getTrackId()==tracks.getTrackId()){
                throw new TrackAlreadyExistException();
            }
        }

        tracksList.add(tracks);
        user.setTracks(tracksList);
        return userRepository.save(user);
    }

    @Override
    public List<Tracks> getAllTracksOfUser(int userId) throws UserNotFoundException {
        if (userRepository.findById(userId).isEmpty()){
            throw new UserNotFoundException();
        }
        return userRepository.findById(userId).get().getTracks();
    }

    @Override
    public String deleteTrackOfUser(int userId, int trackId) throws TrackNotFoundException, UserNotFoundException {
        if (userRepository.findById(userId).isEmpty()){
            throw new UserNotFoundException();
        }
        Users user = userRepository.findById(userId).get();
        List<Tracks> tracksList = user.getTracks();
        tracksList.removeIf(obj-> obj.getTrackId()==trackId);

        if(userRepository.findById(trackId).isPresent()){
            throw new TrackNotFoundException();
        }
        user.setTracks(tracksList);
        userRepository.save(user);
        return "Track with track Id = "+trackId+" is deleted";
    }

    @Override
    public Users updateTrackOfUser(int userId, Tracks tracks) throws UserNotFoundException {
        if (userRepository.findById(userId).isEmpty()){
            throw new UserNotFoundException();
        }
        Users user = userRepository.findById(userId).get();
        List<Tracks> tracksList = user.getTracks();
        Iterator<Tracks> iterator = tracksList.iterator();
        while (iterator.hasNext()){
            Tracks track1 = iterator.next();
            if(track1.getTrackId()==tracks.getTrackId()){
                track1.setTrackName(tracks.getTrackName());
                track1.setArtist(tracks.getArtist());
            }
        }

        user.setTracks(tracksList);
        return userRepository.save(user);
    }
}
