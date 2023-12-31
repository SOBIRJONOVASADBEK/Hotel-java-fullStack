package com.example.java.service.impl;

import com.example.java.dto.RegistrationDto;
import com.example.java.models.Role;
import com.example.java.models.UserEntity;
import com.example.java.repository.RoleRepository;
import com.example.java.repository.UserRepository;
import com.example.java.service.UserService;
import org.springframework.stereotype.Service;

import java.util.Arrays;
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository repository;

    public UserServiceImpl(UserRepository userRepository, RoleRepository repository) {
        this.userRepository = userRepository;
        this.repository = repository;
    }

    @Override
    public void saveUSer(RegistrationDto registrationDto) {
        UserEntity user=new UserEntity();
        user.setUsername(registrationDto.getUserName());
        user.setEmail(registrationDto.getEmail());
        user.setPassword(registrationDto.getPassword());
        Role role=repository.findByName("USER");
        user.setRoles(Arrays.asList(role));
        userRepository.save(user);
    }

    @Override
    public UserEntity finByEmail(String email) {
        UserEntity user=userRepository.findByEmail(email);
        return user;
    }

    @Override
    public UserEntity finByUserName(String userName) {
        UserEntity user=userRepository.findByUserName(userName);
        return user;
    }
}
