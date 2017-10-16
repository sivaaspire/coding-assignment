package com.mckesson.codingassignment.rule;

import com.mckesson.codingassignment.exception.PasswordValidationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;

/**
 * Validation rule related to password length
 */
@Component("LENGTH")
public class PasswordLengthValidationRule implements PasswordValidationRule {

    private static final Logger LOGGER = LoggerFactory.getLogger(PasswordLengthValidationRule.class);

    private int passwordMinLength;

    private int passwordMaxLength;

    private String passwordLengthValidationMessage;

    @Autowired
    public PasswordLengthValidationRule(@Value("${password.validation.min.length:5}") int passwordMinLength,
                                        @Value("${password.validation.max.length:12}") int passwordMaxLength) {
        this.passwordMinLength = passwordMinLength;
        this.passwordMaxLength = passwordMaxLength;
        passwordLengthValidationMessage = MessageFormat.format("password length must be between {0} and {1} characters", passwordMinLength, passwordMaxLength);
    }

    /**
     * This method checks whether provided password length is between min and max length
     *
     * @param password - password to be validated
     * @throws PasswordValidationException if the password length is not between min and max length
     */
    @Override
    public void validate(String password) {
        LOGGER.info("Validating password with password length validation rule");
        int passwordLength = password.length();
        if (passwordLength < passwordMinLength || passwordLength > passwordMaxLength) {
            LOGGER.warn(passwordLengthValidationMessage);
            throw new PasswordValidationException(passwordLengthValidationMessage);
        }
    }
}
