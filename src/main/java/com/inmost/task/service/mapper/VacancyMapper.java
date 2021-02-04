package com.inmost.task.service.mapper;

import com.inmost.task.domain.VacancyEntity;
import com.inmost.task.dto.Vacancy;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class VacancyMapper {
    public Vacancy vacancyEntityToVacancy(VacancyEntity vacancyEntity) {
        if (Objects.isNull(vacancyEntity)) {
            return null;
        }

        return Vacancy.builder()
                .id(vacancyEntity.getId())
                .nameCompany(vacancyEntity.getNameCompany())
                .position(vacancyEntity.getPosition())
                .salaryExpected(vacancyEntity.getSalaryExpected())
                .linkToVacancy(vacancyEntity.getLinkToVacancy())
                .recruitersContacts(vacancyEntity.getRecruitersContacts())
                .statusVacancy(vacancyEntity.getStatusVacancy())
                .lastChange(vacancyEntity.getLastChange())
                .build();
    }

    public VacancyEntity vacancyToVacancyEntity(Vacancy vacancy) {
        if (Objects.isNull(vacancy)) {
            return null;
        }

        return VacancyEntity.builder()
                .id(vacancy.getId())
                .nameCompany(vacancy.getNameCompany())
                .position(vacancy.getPosition())
                .salaryExpected(vacancy.getSalaryExpected())
                .linkToVacancy(vacancy.getLinkToVacancy())
                .recruitersContacts(vacancy.getRecruitersContacts())
                .statusVacancy(vacancy.getStatusVacancy())
                .lastChange(vacancy.getLastChange())
                .build();
    }
}
