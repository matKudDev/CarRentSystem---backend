package com.carrentsystem.carrentsystem.config;

import com.carrentsystem.carrentsystem.config.token.JwtTokenFactory;
import com.carrentsystem.carrentsystem.model.repository.EmployeeRepository;
import com.carrentsystem.carrentsystem.model.repository.UserRepository;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.context.annotation.Bean;


import static com.carrentsystem.carrentsystem.config.SecurityConstants.SIGN_UP_URL;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled=true)
public class WebSecurity extends WebSecurityConfigurerAdapter {
    private UserDetailsService userDetailsService;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private UserRepository repository;
    private EmployeeRepository employeeRepository;

    public WebSecurity(UserDetailsService userDetailsService,
                       BCryptPasswordEncoder bCryptPasswordEncoder,
                       UserRepository repository,
                       EmployeeRepository employeeRepository) {
        this.userDetailsService = userDetailsService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.repository = repository;
        this.employeeRepository = employeeRepository;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable().authorizeRequests()
                .antMatchers(HttpMethod.POST, SIGN_UP_URL).permitAll()
                .anyRequest().authenticated()
                .and()
                .addFilter(new JWTAuthenticationFilter(authenticationManager()
                        ,new  JwtTokenFactory(repository,employeeRepository)))
                .addFilter(new JWTAuthorizationFilter(authenticationManager()))
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.applyPermitDefaultValues().addAllowedMethod(HttpMethod.DELETE.name());


        source.registerCorsConfiguration("/**", config);
        return source;
    }
}