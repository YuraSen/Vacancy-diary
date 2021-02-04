package com.inmost.task.service;

import com.inmost.task.dto.Vacancy;

import java.util.List;

public interface VacancyService {

    List<Vacancy> findAll();

    Vacancy editVacancy(Long id);

    Vacancy findByStatus();

    Vacancy findByNameCompany();

    Vacancy sendTheEmail();

}
