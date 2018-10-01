package com.carrentsystem.carrentsystem.controller;


import com.carrentsystem.carrentsystem.model.entity.ApplicationUser;
import com.carrentsystem.carrentsystem.model.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin/")
public class EmployeeController {

    @Autowired
    UserRepository userRepository;

    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN_USER')")
    @RequestMapping("/users/")
    public List<ApplicationUser> findAllUsers() {
        return userRepository.findAll();

    }
}
