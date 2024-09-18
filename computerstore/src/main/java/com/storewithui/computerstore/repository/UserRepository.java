package com.storewithui.computerstore.repository;

import com.storewithui.computerstore.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
    User findByVerificationToken(String token);
}

