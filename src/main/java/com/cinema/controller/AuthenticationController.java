package com.cinema.controller;

import com.cinema.model.dto.UserRequestDto;
import com.cinema.service.AuthenticationService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class AuthenticationController {
    private final AuthenticationService authenticationService;
    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/register")
    public void register(UserRequestDto userRequestDto) {
        authenticationService.register(userRequestDto.getEmail(),
                userRequestDto.getPassword());
        ;
    }
}
