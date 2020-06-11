package com.cinema.controller;

import com.cinema.model.dto.UserResponceDto;
import com.cinema.service.UserService;
import com.cinema.util.mapper.UserMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users/byemail")
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;

    public UserController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @GetMapping
    public UserResponceDto getUserByEmail(@RequestParam String email) {
        return userMapper.parsingUserToDto(userService.findByEmail(email).get());
    }
}
