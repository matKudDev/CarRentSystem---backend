package com.carrentsystem.carrentsystem.model.repository.repository;

import com.carrentsystem.carrentsystem.model.entity.Rent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional
public class RentRepository {

    @Autowired
    EntityManager entityManager;


    public void create(Rent rent){
        entityManager.persist(rent);
    }

    public List<Rent> getAllPendingRents(Long id){

        List<Rent> list = new ArrayList<>();
        list.addAll( (List<Rent>) entityManager.createQuery("FROM Rent r where r.state=:state AND r.pickupBranch.id=:id")
                .setParameter("state","pending")
                .setParameter("id",id)
                .getResultList());
        return list;
    }

    public List<Rent> getAllLoanedRents(Long id){

        List<Rent> list = new ArrayList<>();
        list.addAll( (List<Rent>) entityManager.createQuery("FROM Rent r where r.state=:state AND r.returnBranch.id =:id")
                .setParameter("state","Loaned")
                .setParameter("id",id)
                .getResultList());
        return list;
    }

    public List<Rent> getUserRentsHistory(Long id){
        List<Rent> list = new ArrayList<>();
        list.addAll( (List<Rent>) entityManager.createQuery("from Rent r where r.appUser.id=:id").setParameter("id",id).getResultList());
        return list;


    }

    public List<Rent> findAll(){
        return new ArrayList<>();
    }

    public void changeToLoaned(Rent rent){
          rent.setState("Loaned");
          entityManager.merge(rent);

    }

    public void changeToReturned(Rent rent){
        rent.setState("Returned");
        entityManager.merge(rent);

    }

}
