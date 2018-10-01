package com.carrentsystem.carrentsystem.controller;


import com.carrentsystem.carrentsystem.model.entity.Rent;
import com.carrentsystem.carrentsystem.model.repository.repository.CarRepository;
import com.carrentsystem.carrentsystem.model.repository.repository.RentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.IntStream;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
@RequestMapping("/rent")
public class RentController {

    @Autowired
    RentRepository rentRepository;

    @Autowired
    CarRepository carRepository;

    @PostMapping
    @PreAuthorize("hasAnyAuthority('STANDARD_USER')")
    public void book(@RequestBody Rent rent) {
       carRepository.update(rent.getCar());
       rentRepository.create(rent);

    }



    @PreAuthorize("hasAnyAuthority('ADMIN_USER')")
    @RequestMapping(value = "/pending/{id}", method = GET)
    public List<Rent> getAllPendingRents(@PathVariable Long id){

        return rentRepository.getAllPendingRents(id);
    }


    @GetMapping(value = "/{id}")
    @PreAuthorize("hasAnyAuthority('STANDARD_USER')")
    public List<Rent> getUserRentsHistory(@PathVariable Long id){

        return rentRepository.getUserRentsHistory(id);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN_USER')")
    @RequestMapping(value = "/loaned/{id}",method = GET)
    public List<Rent> getAllLoanedRents(@PathVariable Long id){

        int z = 3;
        long number = IntStream.range(2,11).filter(i -> i % z == 0).count();
        return rentRepository.getAllLoanedRents(id);
    }

    @PostMapping
    @PreAuthorize("hasAnyAuthority('ADMIN_USER')")
    @RequestMapping("/pending")
    public void changeToLoaned(@RequestBody Rent rent){

        rentRepository.changeToLoaned(rent);
    }

    @PostMapping
    @PreAuthorize("hasAnyAuthority('ADMIN_USER')")
    @RequestMapping("/loaned")
    public void changeToReturned(@RequestBody Rent rent){
        rentRepository.changeToReturned(rent);
    }









}
