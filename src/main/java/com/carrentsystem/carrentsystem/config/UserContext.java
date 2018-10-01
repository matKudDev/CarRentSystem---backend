package com.carrentsystem.carrentsystem.config;

import org.springframework.security.core.GrantedAuthority;

import java.util.List;

public class UserContext {
    private final String username;
    private final GrantedAuthority authoritie;

    private UserContext(String username, GrantedAuthority authoritie) {
        this.username = username;
        this.authoritie = authoritie;
    }

    public static UserContext create(String username, GrantedAuthority authoritie) {
        if (username==null) throw new IllegalArgumentException("Username is blank: " + username);
        return new UserContext(username, authoritie);
    }

    public String getUsername() {
        return username;
    }

    public GrantedAuthority getAuthorities() {
        return authoritie;
    }
}
