package com.carrentsystem.carrentsystem.service;

import com.carrentsystem.carrentsystem.model.entity.ApplicationUser;
import com.carrentsystem.carrentsystem.model.entity.Employee;
import com.carrentsystem.carrentsystem.model.repository.EmployeeRepository;
import com.carrentsystem.carrentsystem.model.repository.UserRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import static java.util.Collections.emptyList;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private UserRepository userRepository;
    private EmployeeRepository employeeRepository;

    public UserDetailsServiceImpl(UserRepository applicationUserRepository,EmployeeRepository employeeRepository) {
        this.userRepository = applicationUserRepository;
        this.employeeRepository = employeeRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        ApplicationUser applicationUser = userRepository.findByUsername(username);
        if (applicationUser == null) {
            Employee employee = employeeRepository.findByUsername(username);
            if(employee == null) {
                throw new UsernameNotFoundException(username);
            }
            return new User(employee.getUsername(), employee.getPassword(), emptyList());
        }
        return new User(applicationUser.getUsername(), applicationUser.getPassword(), emptyList());
    }
}
