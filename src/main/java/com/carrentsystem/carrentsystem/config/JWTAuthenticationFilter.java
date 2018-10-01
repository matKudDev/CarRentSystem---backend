package com.carrentsystem.carrentsystem.config;


import com.carrentsystem.carrentsystem.common.Dto.EmployeeDto;
import com.carrentsystem.carrentsystem.common.Dto.UserDto;
import com.carrentsystem.carrentsystem.common.LoginCredentials;
import com.carrentsystem.carrentsystem.config.token.JwtTokenFactory;
import com.carrentsystem.carrentsystem.model.entity.ApplicationUser;
import com.carrentsystem.carrentsystem.model.entity.Employee;
import com.carrentsystem.carrentsystem.model.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;


public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository repository;

    @Autowired
    private JwtTokenFactory factory;


    public JWTAuthenticationFilter(AuthenticationManager authenticationManager, JwtTokenFactory factory) {
        this.authenticationManager = authenticationManager;
        this.factory = factory;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest req,
                                                HttpServletResponse res) throws AuthenticationException {
        try {
            LoginCredentials creds = new ObjectMapper()
                    .readValue(req.getInputStream(), LoginCredentials.class);

            return authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            creds.getUsername(),
                            creds.getPassword(),
                            new ArrayList<>())
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest req,
                                            HttpServletResponse res,
                                            FilterChain chain,
                                            Authentication auth) throws IOException, ServletException {

        String token = factory.createAccessJwtToken(auth);
        ApplicationUser user = factory.getUser(auth);
        if (null == user) {
            Employee employee = factory.getEmployee(auth);
            boolean isAdmin = false;
            if (employee.getRole().getRoleName().equals("ADMIN_USER")) {
                isAdmin = true;
            }
            EmployeeDto resUser = new EmployeeDto(employee.getId(), employee.getUsername(), employee.getPassword(), isAdmin,employee.getBranch(), token);
            String json = new Gson().toJson(resUser);
            res.setContentType("application/json");
            res.getWriter().write(json);
        } else {


            boolean isAdmin = false;
            if (user.getRole().getRoleName().equals("ADMIN_USER")) {
                isAdmin = true;
            }
            UserDto resUser = new UserDto(user.getId(), user.getUsername(), user.getPassword(), isAdmin, token);
            String json = new Gson().toJson(resUser);
            res.setContentType("application/json");
            res.getWriter().write(json);
        }
    }
}
