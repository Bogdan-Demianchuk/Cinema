package com.cinema.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EmailValidator implements ConstraintValidator<EmailValidate, String> {
    private static final String EMAIL_REGEX = "[A-z0-9._%+-]+@[A-z0-9.-]+\\.[A-z]{2,}";

    public boolean isValid(String email, ConstraintValidatorContext context) {
        return email != null && email.matches(EMAIL_REGEX);
    }
}
