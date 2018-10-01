package com.carrentsystem.carrentsystem.controller;

import com.carrentsystem.carrentsystem.model.entity.ApplicationUser;
import com.carrentsystem.carrentsystem.model.entity.Branch;
import com.carrentsystem.carrentsystem.model.repository.BranchRepository;
import com.carrentsystem.carrentsystem.model.repository.EmployeeRepository;
import com.carrentsystem.carrentsystem.model.repository.UserRepository;
import com.carrentsystem.carrentsystem.model.repository.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private UserRepository applicationUserRepository;

    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    UserRoleRepository repository;

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    BranchRepository branchRepository;

    public UserController(UserRepository applicationUserRepository,
                          BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.applicationUserRepository = applicationUserRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @PostMapping("/sign-up")
    public void signUp(@RequestBody ApplicationUser user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRoles(repository.findOne(1l));
        applicationUserRepository.save(user);
    }


/*   @PostMapping("/sign-up")
    public void signUp(@RequestBody Employee user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRoles(repository.findOne(2l));
        user.setBranch(branchRepository.findOne(3l));
        employeeRepository.save(user);
    }*/

    @GetMapping(value = "/{id}")
    @PreAuthorize("hasAnyAuthority('STANDARD_USER')")
    public ApplicationUser findUser(@PathVariable("id") Long id) {
        System.out.println(applicationUserRepository.findOne(id));
        return applicationUserRepository.findOne(id);

    }

    @PostMapping
    @PreAuthorize("hasAnyAuthority('STANDARD_USER','ADMIN_USER')")
    public void updateUser(@RequestBody ApplicationUser user) {
        applicationUserRepository.save(user);

    }


    @GetMapping
    @PreAuthorize("hasAnyAuthority('ADMIN_USER')")
    public List<ApplicationUser> findAllUsers() {
        return applicationUserRepository.findAll();

    }

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @PreAuthorize("hasAnyAuthority('ADMIN_USER')")
    public void deleteUser(@PathVariable("id") Long id) {
        applicationUserRepository.delete(id);
        System.out.println("Usuniety" + id);
    }

    @RequestMapping("/cities")
    @GetMapping
    @PreAuthorize("hasAnyAuthority('STANDARD_USER')")
    public List<Branch> findAllBranches() {
        return branchRepository.findAll();
    }
}