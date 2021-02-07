package com.inmost.task.domain;

import com.inmost.task.dto.StatusVacancy;
import com.inmost.task.dto.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

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

    @Enumerated(EnumType.STRING)
    @Column(name = "statusVacancy")
    private StatusVacancy statusVacancy;

    @Column(name = "lastChange")
    private LocalDate lastChange;

    @JoinColumn(name = "id_user", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private UserEntity userEntity;
}
