package com.niit.UserTrackService.repository;

import com.niit.UserTrackService.domain.Users;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<Users,Integer> {
    Users findByUserId(int userId);
}
