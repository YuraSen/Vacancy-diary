package com.inmost.task.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {

    private Long id;

    private String firstName;

    private String lastname;

    private LocalDate birthday;

    @NotEmpty(message = "Enter your email")
    @Email
    private String email;

    @NotEmpty(message = "Please input the password")
    private String password;

    @NotEmpty
    private String description;
}
