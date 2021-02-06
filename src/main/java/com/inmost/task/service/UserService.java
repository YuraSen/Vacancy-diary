package com.inmost.task.service;

import com.inmost.task.dto.StatusVacancy;
import com.inmost.task.dto.User;
import com.inmost.task.dto.Vacancy;
import org.springframework.data.domain.Page;

import java.util.List;

public interface UserService {

    User registration(User user);

    User edit(User user);

    void deleteById(Long id);

    User findById(Long id);

    User findUser(String login, String password);

    Page<User> getPageUsers(int currentPage, int pageSize);

    List<Vacancy> getUserVacancy(User user);

    boolean sendTheEmail(String text, Long id);

    User changeStatus(Long idVacancy, Long idUser, StatusVacancy statusVacancy);

}
