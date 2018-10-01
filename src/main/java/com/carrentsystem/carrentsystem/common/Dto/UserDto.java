package com.carrentsystem.carrentsystem.common.Dto;

public class UserDto {

    public UserDto(Long id,String username, String password, boolean isAdmin, String token) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.isAdmin = isAdmin;
        this.token = token;
    }
private Long id;
    private String username;
    private String password;
    private boolean isAdmin;
    private String token;
}


