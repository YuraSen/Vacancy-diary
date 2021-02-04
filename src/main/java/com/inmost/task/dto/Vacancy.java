package com.inmost.task.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Vacancy {

    private Long id;

    @NotEmpty(message = "Enter the company name")
    private String nameCompany;

    @NotEmpty
    private String position;

    @NotEmpty
    private Integer salaryExpected;

    @NotEmpty
    private String linkToVacancy;

    @NotEmpty
    private String recruitersContacts;

    private StatusVacancy statusVacancy;

    @NotEmpty
    private LocalDate lastChange;
}
