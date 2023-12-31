package com.example.java.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class RegistrationDto {
    private Long id;
    @NotEmpty
    private String userName;
    @NotEmpty
    private String email;
    @NotEmpty
    private String password;

}
