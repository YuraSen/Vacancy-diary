package com.inmost.task.service;

import com.inmost.task.dto.User;
import org.springframework.data.domain.Page;

import java.util.List;

public interface UserService {

    User registration(User user);

    User edit(Long id, User user);

    void deleteById(Long id);

    User findById(Long id);

    User findUser(String login, String password);

    Page<User> getPageUsers(int currentPage, int pageSize);

    User usersVacancy(String nameVacancy);
}
