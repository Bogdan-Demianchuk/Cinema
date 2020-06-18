package com.cinema.service.impl;

import com.cinema.exeption.AuthenticationException;
import com.cinema.model.Role;
import com.cinema.model.User;
import com.cinema.service.AuthenticationService;
import com.cinema.service.ShoppingCartService;
import com.cinema.service.UserService;
import java.util.Set;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final ShoppingCartService shoppingCartService;

    public AuthenticationServiceImpl(UserService userService,
                                     PasswordEncoder passwordEncoder,
                                     ShoppingCartService shoppingCartService) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.shoppingCartService = shoppingCartService;
    }

    @Override
    public User login(String email, String password) throws AuthenticationException {
        User userFromDb = userService.findByEmail(email)
                .orElseThrow(() -> new AuthenticationException("Incorrect email"));
        if (passwordEncoder.matches(password, userFromDb.getPassword())) {
            return userFromDb;
        }
        throw new AuthenticationException("Incorrect password");
    }

    @Override
    public User register(String email, String password, Set<Role> roles) {
        User user = new User();
        user.setRoles(roles);
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));
        user = userService.add(user);
        shoppingCartService.registerNewShoppingCart(user);
        return user;
    }
}
