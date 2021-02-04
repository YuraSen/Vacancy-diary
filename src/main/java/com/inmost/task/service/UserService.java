package com.inmost.task.service;

import com.inmost.task.dto.User;
import org.springframework.data.domain.Page;

import java.util.List;

public interface UserService {

    User edit(Long id, User user);

    User save(User user);

    void deleteById(Long id);

    User findById(Long id);

    List<User> findAll();

    Page<User> getPageUsers(int currentPage, int pageSize);

}
