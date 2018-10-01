package com.carrentsystem.carrentsystem.common.Dto;

import com.carrentsystem.carrentsystem.model.entity.Branch;

public class EmployeeDto {

    public EmployeeDto(Long id, String username, String password, boolean isAdmin,Branch branch, String token) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.isAdmin = isAdmin;
        this.token = token;
        this.branch = branch;
    }
    private Long id;
    private String username;
    private String password;
    private boolean isAdmin;
    private String token;
    private Branch branch;
}