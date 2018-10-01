package com.carrentsystem.carrentsystem.config.token;

import com.carrentsystem.carrentsystem.config.UserContext;
import com.carrentsystem.carrentsystem.model.entity.ApplicationUser;
import com.carrentsystem.carrentsystem.model.entity.Employee;
import com.carrentsystem.carrentsystem.model.repository.EmployeeRepository;
import com.carrentsystem.carrentsystem.model.repository.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import java.util.Date;

import static com.carrentsystem.carrentsystem.config.SecurityConstants.EXPIRATION_TIME;
import static com.carrentsystem.carrentsystem.config.SecurityConstants.SECRET;

@Component
public class JwtTokenFactory {


    private UserRepository repository;
    private EmployeeRepository employeeRepository;

    @Autowired
    public JwtTokenFactory(UserRepository repository, EmployeeRepository employeeRepository) {
        this.repository = repository;
        this.employeeRepository = employeeRepository;
    }

    public ApplicationUser getUser(Authentication auth){
        User appUser = (User) auth.getPrincipal();
        ApplicationUser user = repository.findByUsername(appUser.getUsername());
        return user;
    }

    public Employee getEmployee(Authentication auth){
        User appUser = (User) auth.getPrincipal();
        Employee user = employeeRepository.findByUsername(appUser.getUsername());
        return user;
    }


    public String createAccessJwtToken(Authentication auth) {

        User appUser = (User) auth.getPrincipal();
        ApplicationUser user = repository.findByUsername(appUser.getUsername());
        if(null == user){
            Employee employee = employeeRepository.findByUsername(appUser.getUsername());
            GrantedAuthority authoritie = new SimpleGrantedAuthority(employee.getRole().getRoleName());
            UserContext userContext = UserContext.create(employee.getUsername(), authoritie);
            Claims claims = Jwts.claims().setSubject(((User) auth.getPrincipal()).getUsername());
            claims.put("scopes", userContext.getAuthorities());


            String token = Jwts.builder()
                    .setClaims(claims)
                    .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                    .signWith(SignatureAlgorithm.HS512, SECRET.getBytes())
                    .compact();
            return token;

        }
        GrantedAuthority authoritie = new SimpleGrantedAuthority(user.getRole().getRoleName());
        UserContext userContext = UserContext.create(user.getUsername(), authoritie);
        Claims claims = Jwts.claims().setSubject(((User) auth.getPrincipal()).getUsername());
        claims.put("scopes", userContext.getAuthorities());


        String token = Jwts.builder()
                .setClaims(claims)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, SECRET.getBytes())
                .compact();
        return token;
    }


}
