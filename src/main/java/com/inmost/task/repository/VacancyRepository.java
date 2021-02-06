package com.inmost.task.repository;

import com.inmost.task.domain.VacancyEntity;
import com.inmost.task.dto.StatusVacancy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VacancyRepository extends JpaRepository<VacancyEntity, Long> {
    List<VacancyEntity> findByNameCompany(String nameCompany);

    List<VacancyEntity> findByStatusVacancy(StatusVacancy statusVacancy);

}
