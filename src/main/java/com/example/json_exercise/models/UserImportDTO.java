package com.example.json_exercise.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserImportDTO {
    private String firstName;
    private String lastName;
    private int age;
}
