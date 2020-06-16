package com.cinema.validation;

import com.cinema.model.dto.UserRequestDto;
import java.util.Objects;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordsEqualsValidator
        implements ConstraintValidator<PasswordsEquals, UserRequestDto> {

    @Override
    public boolean isValid(UserRequestDto value, ConstraintValidatorContext context) {
        return Objects.equals(value.getPassword(), value.getRepeatPassword());
    }
}
