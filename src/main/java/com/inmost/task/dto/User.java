package com.inmost.task.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {

    private Long id;

    private String firstName;

    private String lastName;

    private LocalDate birthday;

    @NotEmpty(message = "Enter your email")
    @Email
    private String email;

    @NotEmpty(message = "Please input the password")
    private String password;

    @NotEmpty
    private String description;

    private List<Vacancy> vacancyList;

}
