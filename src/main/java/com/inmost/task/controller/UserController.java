package com.inmost.task.controller;

import com.inmost.task.dto.User;
import com.inmost.task.dto.Vacancy;
import com.inmost.task.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/user")
public class UserController {
    private static final int USER_PAGE_SIZE = 10;
    private final UserService userService;

    @GetMapping("/")
    public Page<User> showUsers(Integer current) {
        return addPagination(current);
    }

    @PostMapping("/login")
    public User login(@RequestBody User user) {
        return userService.findUser(user.getEmail(), user.getPassword());
    }

    @GetMapping("/{id}")
    public User showUser(@PathVariable Long id) {
        return userService.findById(id);
    }

    @PostMapping("/registration")
    public User createUser(@RequestBody User user) {
        return userService.registration(user);
    }

    @PostMapping("/edit")
    public User editUser(@RequestBody User user) {
        return userService.edit(user);
    }

    @PostMapping("/delete/{id}")
    public void delete(@PathVariable Long id) {
        userService.deleteById(id);
    }

    @PostMapping("/getVacancy")
    public List<Vacancy> getUserVacancy(@RequestBody User user) {
        return userService.getUserVacancy(user);
    }

    @GetMapping("/email/{id}")
    public boolean sendEmail(@PathVariable Long id, String text) {
        return userService.sendTheEmail(text, id);
    }

    private Page<User> addPagination(Integer current) {
        int currentPage = current == null ? 1 : current;
        return userService.getPageUsers(currentPage, USER_PAGE_SIZE);
    }

}
