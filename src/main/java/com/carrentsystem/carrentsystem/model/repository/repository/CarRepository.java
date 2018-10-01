package com.carrentsystem.carrentsystem.model.repository.repository;


import com.carrentsystem.carrentsystem.model.entity.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
@Transactional
public class CarRepository {

    @Autowired
    EntityManager entityManager;


    public List<Car> findAllCarsFromCurrentBranch(Long id) {
        List<Car> list = new ArrayList<Car>();
        list.addAll((List<Car>) entityManager.createQuery(
                "from Car c where c.branch.id =:id AND c.active =:isActive "
        ).setParameter("id", id).setParameter("isActive","true").getResultList());
        return list;
    }

    public void create(Car car) {
        car.setActive("true");
        entityManager.persist(car);

    }

    public List<Car> findAll(){
        return entityManager.createQuery("Select from c Car c",Car.class).getResultList();
    }

    public void delete(Long id) {


        Car car = entityManager.find(Car.class, id);
      car.setActive("false");
      entityManager.merge(car);
    }

    public List<Car> findAllAvailableCarsByCityAndDate(Long pickupCityId, String carClass, String startDate, String returnDate) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSX");
        startDate = startDate.substring(1, startDate.length() - 1);
        returnDate = returnDate.substring(1, returnDate.length() - 1);

        Date rentCarDate = null;
        Date returnCarDate = null;
        try {
            rentCarDate = df.parse(startDate);
        } catch (ParseException e) {

            e.printStackTrace();
        }

        try {
            returnCarDate = df.parse(returnDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        List<Car> list = new ArrayList<Car>();


        list.addAll((List<Car>) entityManager.createQuery("from Car c WHERE NOT EXISTS (SELECT 1 FROM Rent r " +
                " WHERE r.car.id = c.id" +
                " AND r.rentDate <= :returnDate" +
                " AND r.returnDate >= :startDate )" +
                " AND c.branch.id = :id AND c.type =:carType AND c.active=:active")
                .setParameter("returnDate", returnCarDate)
                .setParameter("startDate", rentCarDate)
                .setParameter("id", pickupCityId)
                .setParameter("carType", carClass)
                .setParameter("active","true").getResultList());

        return list;


    }

    public Car find(Long id){
        return entityManager.find(Car.class,id);
    }

    public void update(Car car){
        entityManager.merge(car);
    }



}
