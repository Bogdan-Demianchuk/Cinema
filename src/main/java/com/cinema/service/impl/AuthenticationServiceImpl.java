package com.cinema.service.impl;

import com.cinema.exeption.AuthenticationException;
import com.cinema.lib.Inject;
import com.cinema.lib.Service;
import com.cinema.model.User;
import com.cinema.service.AuthenticationService;
import com.cinema.service.UserService;
import com.cinema.util.HashUtil;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    @Inject
    private UserService userService;

    @Override
    public User login(String email, String password) throws AuthenticationException {
        User userFromDb = userService.findByEmail(email)
                .orElseThrow(() -> new AuthenticationException("Incorrect email"));
        if (HashUtil.hashPassword(password, userFromDb.getSalt())
                .equals(userFromDb.getPassword())) {
            return userFromDb;
        }
        throw new AuthenticationException("Incorrect password");
    }

    @Override
    public User register(String email, String password) {
        User user = new User(email, password);
        byte[] salt = HashUtil.getSalt();
        user.setPassword(HashUtil.hashPassword(user.getPassword(), salt));
        user.setSalt(salt);
        return userService.add(user);
    }
}
