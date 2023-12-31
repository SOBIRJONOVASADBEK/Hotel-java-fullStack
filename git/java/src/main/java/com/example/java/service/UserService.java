package com.example.java.service;

import com.example.java.dto.RegistrationDto;
import com.example.java.models.UserEntity;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    void saveUSer(RegistrationDto registrationDto);

    UserEntity finByEmail(String email);

    UserEntity finByUserName(String userName);
}
