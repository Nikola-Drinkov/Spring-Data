package com.example.springdataautomapping.services;

public interface UserService {
    String registerUser(String[] args);
    String loginUser(String[] args);
    String logout();
    Boolean isLoggedAdmin();
}
