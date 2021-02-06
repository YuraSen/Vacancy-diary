package com.inmost.task.service;

import com.inmost.task.dto.StatusVacancy;
import com.inmost.task.dto.Vacancy;

import java.util.List;

public interface VacancyService {

    List<Vacancy> findAll();

    Vacancy editVacancy(Vacancy vacancy);

    List<Vacancy> findByStatus(StatusVacancy statusVacancy);

    List<Vacancy> findByNameCompany(String nameCompany);

    Vacancy findById(Long id);

    void deleteById(Long id);

    Vacancy save(Vacancy vacancy);

}
