package com.inmost.task.service.impl;

import com.inmost.task.dto.Vacancy;
import com.inmost.task.repository.VacancyRepository;
import com.inmost.task.service.VacancyService;
import com.inmost.task.service.mapper.VacancyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VacancyServiceImpl implements VacancyService {

    private static final String USER_BY_THIS_ID_NOT_EXIST = "User by this id not exist";
    private static final String ID_MUST_BE_POSITIVE = "Id must be positive";
    private static final int THE_SMALLEST_POSSIBLE_ID = 0;
    private final VacancyRepository vacancyRepository;
    private final VacancyMapper vacancyMapper;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public VacancyServiceImpl(VacancyRepository vacancyRepository, VacancyMapper vacancyMapper, PasswordEncoder passwordEncoder) {
        this.vacancyRepository = vacancyRepository;
        this.vacancyMapper = vacancyMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<Vacancy> findAll() {
        return null;
    }

    @Override
    public Vacancy editVacancy(Long id) {
        return null;
    }

    @Override
    public Vacancy findByStatus() {
        return null;
    }

    @Override
    public Vacancy findByNameCompany() {
        return null;
    }

    @Override
    public Vacancy sendTheEmail() {
        return null;
    }
}
