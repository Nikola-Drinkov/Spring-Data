package com.example.springdataautomapping.constants;

public enum ErrorMessage {
    ;
    public static final String PASSWORD_MISMATCH = "Passwords doesn't match!";
    public static final String INVALID_EMAIL = "Invalid email!";
    public static final String INVALID_PASSWORD = "Invalid password!";
    public static final String EMAIL_PRESENT = "The email already exists!";
    public static final String INCORRECT_EMAIL = "Incorrect email!";
    public static final String  INCORRECT_PASSWORD = "Incorrect password!";
    public static final String USER_ALREADY_LOGGED = "User is already logged!";
    public static final String NO_USER_TO_LOG_OUT = "Cannot log out. No user was logged in.";
    public static final String NO_SUCH_GAME_EXISTING = "No such game exists.";
    public static final String NON_ADMIN_GAME_ADD = "You are not authorized to add games! Admin login requiredEditGame|1|price=80.00|size=12.0";
    public static final String NON_ADMIN_GAME_EDIT = "You are not authorized to edit games! Admin login required";
    public static final String NON_ADMIN_GAME_DELETE = "You are not authorized to delete games! Admin login required";
}
