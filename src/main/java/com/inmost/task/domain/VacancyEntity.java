package com.inmost.task.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "vacancy")
public class VacancyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @Column(name = "nameCompany")
    private String nameCompany;
    @Column(name = "position")
    private String position;
    @Column(name = "salaryExpected")
    private Integer salaryExpected;
    @Column(name = "linkToVacancy")
    private String linkToVacancy;
    @Column(name = "recruitersContacts")
    private String recruitersContacts;
    @Column(name = "statusVacancy")
    private enum statusVacancy{
        Submitted,
        GaveTest,
        WaitingForFeedback,
        Screening,
        TechnicalInterview,
        Offer,
        Refused,
        NoResponse
    }
    @Column(name = "lastChange")
    private LocalDate lastChange;
}
