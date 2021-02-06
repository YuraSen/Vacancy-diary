package com.inmost.task.controller;

import com.inmost.task.dto.StatusVacancy;
import com.inmost.task.dto.User;
import com.inmost.task.dto.Vacancy;
import com.inmost.task.service.UserService;
import com.inmost.task.service.VacancyService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/vacancy")
public class VacancyController {
    private final VacancyService vacancyService;

    @GetMapping("/")
    public List<Vacancy> showVacancy() {
        return vacancyService.findAll();
    }

    @GetMapping("/{id}")
    public Vacancy showVacancy(@PathVariable Long id) {
        return vacancyService.findById(id);
    }

    @PostMapping("/create")
    public Vacancy saveVacancy(@RequestBody Vacancy vacancy) {
        return vacancyService.save(vacancy);
    }

    @PostMapping("/edit")
    public Vacancy editVacancy(@RequestBody Vacancy vacancy) {
        return vacancyService.editVacancy(vacancy);
    }

    @PostMapping("/delete/{id}")
    public void delete(@PathVariable Long id) {
        vacancyService.deleteById(id);
    }

    @PostMapping("/{companyName}")
    public List<Vacancy> getByCompanyName(@PathVariable String companyName) {
        return vacancyService.findByNameCompany(companyName);
    }

    @PostMapping("/status")
    public List<Vacancy> findByStatus(@RequestBody StatusVacancy statusVacancy) {
        return vacancyService.findByStatus(statusVacancy);
    }

}
