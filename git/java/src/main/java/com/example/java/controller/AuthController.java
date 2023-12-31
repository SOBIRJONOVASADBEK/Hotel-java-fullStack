package com.example.java.controller;

import com.example.java.dto.RegistrationDto;
import com.example.java.models.UserEntity;
import com.example.java.service.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class AuthController {
    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping("register")
    public String  getRegisterForm(Model model){
        RegistrationDto user=new RegistrationDto();
        model.addAttribute("user",user);
        return "register";
    }
    @GetMapping("/login")
    public String loginPage(){
        return "login";
    }

    public String register(@Valid @ModelAttribute("user") RegistrationDto user,
                           BindingResult result,Model model){
        UserEntity existingUserEmail=userService.finByEmail(user.getEmail());
        if (existingUserEmail !=null && existingUserEmail.getEmail() != null && !existingUserEmail.getEmail().isEmpty()){
           return "redirect:/register?fail";
        }
        UserEntity existingUsername=userService.finByUserName(user.getUserName());
        if (existingUsername !=null && existingUsername.getUsername() != null && !existingUsername.getUsername().isEmpty()){
            return "redirect:/register?fail";
        }
        if (result.hasErrors()){
            model.addAttribute("user",user);
            return "register";
        }
        userService.saveUSer(user);
        return "redirect:/clubs?succes";
    }
}
