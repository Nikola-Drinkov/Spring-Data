package com.example.springdataautomapping.domain.models;

import lombok.Getter;
import lombok.Setter;

import static com.example.springdataautomapping.constants.ErrorMessage.INCORRECT_PASSWORD;

@Getter
@Setter
public class UserLoginDTO {
    private String email;
    private String password;

    public UserLoginDTO(String email, String password) {
        this.email = email;
        this.password = password;
    }
    public void validate(String realPassword){
        if(!password.equals(realPassword)){
            throw new IllegalStateException(INCORRECT_PASSWORD);
        }
    }
}
