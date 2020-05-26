package com.cinema.dao;

import com.cinema.lib.Dao;
import com.cinema.model.User;
import java.util.Optional;

@Dao
public interface UserDao {
    User add(User user);

    Optional<User> findByEmail(String email);
}
