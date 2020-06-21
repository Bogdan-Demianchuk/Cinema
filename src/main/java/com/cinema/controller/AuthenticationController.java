package com.cinema.controller;

import com.cinema.model.Role;
import com.cinema.model.dto.UserRequestDto;
import com.cinema.service.AuthenticationService;
import com.cinema.service.RoleService;
import java.util.Set;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class AuthenticationController {
    private final AuthenticationService authenticationService;
    private final RoleService roleService;

    public AuthenticationController(AuthenticationService authenticationService,
                                    RoleService roleService) {
        this.authenticationService = authenticationService;
        this.roleService = roleService;
    }

    @PostMapping("/register")
    public void register(@RequestBody @Valid UserRequestDto userRequestDto) {
        Role role = roleService.getRoleByName("USER");
        authenticationService.register(userRequestDto.getEmail(),
                userRequestDto.getPassword(), Set.of(role));
    }
}
