package com.example.springdataautomapping.constants;

public class Validation {
    public static final String EMAIL_PATTERN = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
    public static final String PASSWORD_PATTERN = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{6,}$";
    public static final String GAME_TITLE_PATTERN = "^[A-Z].{2,99}$";
    public static final String GAME_TRAILER_PATTERN = ".{11}";
    public static final String GAME_THUMBNAIL_PATTERN = "^(http:\\/\\/|https:\\/\\/).*$";
    public static final String  GAME_DESCRIPTION_PATTERN = "^.{20,}$";
}
