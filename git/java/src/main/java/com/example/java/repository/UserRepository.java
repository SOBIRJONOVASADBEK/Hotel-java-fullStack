package com.example.java.repository;

import com.example.java.models.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity,Long> {
    UserEntity findByEmail(String email);
    UserEntity findByUserName(String userName);
}
