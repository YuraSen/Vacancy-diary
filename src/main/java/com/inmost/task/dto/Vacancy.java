package com.inmost.task.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.util.List;

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

    private Integer salaryExpected;

    @NotEmpty
    private String linkToVacancy;

    @NotEmpty
    private String recruitersContacts;

    @NotEmpty
    private StatusVacancy statusVacancy;

    @NotEmpty
    private LocalDate lastChange;
}
