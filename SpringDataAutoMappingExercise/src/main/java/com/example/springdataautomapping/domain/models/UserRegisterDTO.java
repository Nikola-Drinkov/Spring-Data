package com.example.springdataautomapping.domain.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static com.example.springdataautomapping.constants.ErrorMessage.*;
import static com.example.springdataautomapping.constants.Validation.EMAIL_PATTERN;
import static com.example.springdataautomapping.constants.Validation.PASSWORD_PATTERN;

@Getter
@Setter
@NoArgsConstructor
public class UserRegisterDTO {
    private String email;
    private String password;
    private String confirmPassword;
    private String fullName;

    public UserRegisterDTO(String email, String password, String confirmPassword, String fullName) {
        this.email = email;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.fullName = fullName;
        validate();
    }
    private void validate(){
        if(!password.equals(confirmPassword)){
            throw new IllegalStateException(PASSWORD_MISMATCH);
        }
        if(!email.matches(EMAIL_PATTERN)){
            throw new IllegalStateException(INVALID_EMAIL);
        }
        if(!password.matches(PASSWORD_PATTERN)){
            throw new IllegalStateException((INVALID_PASSWORD));
        }
    }
}
