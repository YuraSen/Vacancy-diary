package com.inmost.task.repository;

import com.inmost.task.domain.VacancyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VacancyRepository extends JpaRepository<VacancyEntity, Long> {
}
