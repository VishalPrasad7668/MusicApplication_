package com.niit.UserAuthenticationService.repository;

import com.niit.UserAuthenticationService.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    public User findUserByUserIdAndPassword(int userId, String password);
}
