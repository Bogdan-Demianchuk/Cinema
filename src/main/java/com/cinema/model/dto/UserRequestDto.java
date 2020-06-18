package com.cinema.model.dto;

import com.cinema.validation.EmailValidate;
import com.cinema.validation.PasswordsEquals;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@PasswordsEquals
public class UserRequestDto {
    @EmailValidate(message = "email invalid")
    @NotNull(message = "email can't be null")
    private String email;
    @NotNull(message = "password can't be null")
    @Size(min = 3, message = "password length should be 3 or more symbols")
    private String password;
    private String repeatPassword;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRepeatPassword() {
        return repeatPassword;
    }

    public void setRepeatPassword(String repeatPassword) {
        this.repeatPassword = repeatPassword;
    }
}
