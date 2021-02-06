package com.inmost.task.controller;

import com.inmost.task.dto.User;
import com.inmost.task.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@RestController
@AllArgsConstructor
@RequestMapping("/user/rest")
public class UserControllerImpl{
    private static final int PAGE_SIZE = 10;
    private final UserService userService;

    @GetMapping("/")
    public Page<User> showUsers(Integer current) {
        return addPagination(current);
    }

    @PostMapping("/login")
    public User login(@RequestBody User user){
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

    @PostMapping("/editUser/{id}")
    public User editUser(@PathVariable Long id,@RequestBody User user) {
        return userService.edit(id, user);
    }

    @PostMapping("/deleteUser/{id}")
    public void delete(@PathVariable Long id) {
        userService.deleteById(id);
    }

    private Page<User> addPagination(Integer current) {
        int currentPage = current == null ? 1 : current;
        return userService.getPageUsers(currentPage, PAGE_SIZE);
    }
}
