package com.inmost.task.service.impl;

import com.inmost.task.domain.VacancyEntity;
import com.inmost.task.dto.StatusVacancy;
import com.inmost.task.dto.Vacancy;
import com.inmost.task.exceprion.EntityNotExistRuntimeException;
import com.inmost.task.exceprion.InvalidDataRuntimeException;
import com.inmost.task.repository.VacancyRepository;
import com.inmost.task.service.VacancyService;
import com.inmost.task.service.mapper.VacancyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class VacancyServiceImpl implements VacancyService {

    private static final String VACANCY_BY_THIS_ID_NOT_EXIST = "Vacancy by this id not exist";
    private static final String ID_MUST_BE_POSITIVE = "Id must be positive";
    private static final int THE_SMALLEST_POSSIBLE_ID = 0;
    private final VacancyRepository vacancyRepository;
    private final VacancyMapper vacancyMapper;

    @Autowired
    public VacancyServiceImpl(VacancyRepository vacancyRepository, VacancyMapper vacancyMapper) {
        this.vacancyRepository = vacancyRepository;
        this.vacancyMapper = vacancyMapper;
    }

    @Override
    public List<Vacancy> findAll() {
        List<VacancyEntity> vacancyEntities = vacancyRepository.findAll();
        return vacancyEntities
                .stream()
                .map(vacancyMapper::vacancyEntityToVacancy)
                .collect(Collectors.toList());
    }

    @Override
    public Vacancy editVacancy(Vacancy vacancy) {
        return save(vacancy);
    }

    @Override
    public List<Vacancy> findByStatus(StatusVacancy statusVacancy) {
        if (Objects.isNull(statusVacancy)) {
            throw new InvalidDataRuntimeException("Status vacancy is uncorrected");
        }
        List<VacancyEntity> vacancyEntity = vacancyRepository.findByStatusVacancy(statusVacancy);
        return vacancyEntity.stream()
                .map(vacancyMapper::vacancyEntityToVacancy)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    @Override
    public List<Vacancy> findByNameCompany(String nameCompany) {
        if (Objects.isNull(nameCompany)) {
            throw new InvalidDataRuntimeException("Uncorrected name company");
        }
        List<VacancyEntity> vacancyEntity = vacancyRepository.findByNameCompany(nameCompany);
        return vacancyEntity.stream()
                .map(vacancyMapper::vacancyEntityToVacancy)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    @Override
    public Vacancy findById(Long id) {
        checkCorrectIdVacancy(id);
        VacancyEntity vacancyEntity = vacancyRepository
                .findById(id)
                .orElseThrow(() -> new EntityNotExistRuntimeException(VACANCY_BY_THIS_ID_NOT_EXIST));
        return vacancyMapper.vacancyEntityToVacancy(vacancyEntity);
    }

    @Override
    public Vacancy save(Vacancy vacancy) {
        VacancyEntity vacancyEntity = vacancyMapper.vacancyToVacancyEntity(vacancy);
        vacancyEntity = vacancyRepository.save(vacancyEntity);
        return vacancyMapper.vacancyEntityToVacancy(vacancyEntity);
    }


    @Override
    public void deleteById(Long id) {
        checkUserExist(id);
        vacancyRepository.deleteById(id);
    }

    private void checkUserExist(Long id) {
        if (vacancyNotExistsById(id)) {
            throw new EntityNotExistRuntimeException();
        }
    }

    private void checkCorrectIdVacancy(Long id) {
        if (id < THE_SMALLEST_POSSIBLE_ID) {
            throw new EntityNotExistRuntimeException(ID_MUST_BE_POSITIVE);
        }
    }

    private boolean vacancyNotExistsById(Long id) {
        checkCorrectIdVacancy(id);

        return !vacancyRepository.existsById(id);
    }
}
