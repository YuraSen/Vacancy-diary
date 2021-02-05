package com.inmost.task.controller;

import com.inmost.task.dto.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface UserController {
    @GetMapping("/")
    List<User> showUsers();

    @GetMapping("/{id}")
    User showUser(@PathVariable("id") Long id);

    @PostMapping("/registration")
    User createUser(@RequestBody User user);

    @PostMapping("/editUser/{id}")
    User editUser(@PathVariable("id") Long id,@RequestBody User user);

    @PostMapping("/deleteUser/{id}")
    void deleteUser(@PathVariable("id") Long id);

}
