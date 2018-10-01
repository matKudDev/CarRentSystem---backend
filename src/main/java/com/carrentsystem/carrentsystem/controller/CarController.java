package com.carrentsystem.carrentsystem.controller;
import com.carrentsystem.carrentsystem.model.entity.Car;
import com.carrentsystem.carrentsystem.model.repository.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/cars")
class CarController {

    @Autowired
    CarRepository carRepository;




    @GetMapping
    @PreAuthorize("hasAuthority('STANDARD_USER')")
    public List<Car> findAllAvailableCars(@RequestParam Long pickupCity,
                                          String carClass,
                                          String pickupDate,
                                          String returnDate) {


        return carRepository.findAllAvailableCarsByCityAndDate(pickupCity,carClass,pickupDate,returnDate);

    }

    @GetMapping(value = "/{id}")
    @PreAuthorize("hasAuthority('ADMIN_USER')")
    public List<Car> findAllCarsFromBranch(@PathVariable("id") Long id) {


return carRepository.findAllCarsFromCurrentBranch(id);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN_USER')")
    public void addCar(@RequestBody Car car){
        carRepository.create(car);

    }


    @DeleteMapping(value = "/{id}")
    @PreAuthorize("hasAuthority('ADMIN_USER')")
    public void deleteCar(@PathVariable Long id){
        carRepository.delete(id);

    }

 /*   @GetMapping
    @PreAuthorize("hasAuthority('ADMIN_USER')")
    public List<Car> findAllCarsInCurrentBranch(@RequestParam Branch branch){
        return carDao.findAllCarsFromCurrentBranch(branch);
    }*/




}
