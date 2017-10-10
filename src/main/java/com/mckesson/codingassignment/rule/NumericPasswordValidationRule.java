package com.mckesson.codingassignment.rule;


import com.mckesson.codingassignment.exception.PasswordValidationException;
import org.springframework.stereotype.Component;

/**
 * Validation rule related to numberics in password
 */
@Component("NUMERIC")
public class NumericPasswordValidationRule implements PasswordValidationRule {

    private static final String VALIDATION_MESSAGE = "password must contain at least one number";
    private static final String REGEX = "[^\\p{Digit}]*[\\p{Digit}]+[^\\p{Digit}]*";

    /**
     * This method checks whether provided password contains atleast digit
     *
     * @param password - password to be validated
     * @throws PasswordValidationException if the password does not contain any digit
     */
    @Override
    public void validate(String password) {
        boolean foundADigit = password.matches(REGEX);
        if (!foundADigit)
            throw new PasswordValidationException(VALIDATION_MESSAGE);
    }
}
