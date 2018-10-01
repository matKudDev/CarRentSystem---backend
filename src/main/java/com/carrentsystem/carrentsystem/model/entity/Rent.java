package com.carrentsystem.carrentsystem.model.entity;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "rent", schema = "public", catalog = "postgres")
public class Rent {
    private Long id;
    private Date rentDate;
    private Date returnDate;
    private Branch pickupBranch;
    private Branch returnBranch;
    private int cost;
    private String state;
    private ApplicationUser appUser;
    private Car car;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "rent_sequence")
    @SequenceGenerator(name = "rent_sequence", sequenceName = "rent_sequence", allocationSize = 1)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "rent_date")
    public Date getRentDate() {
        return rentDate;
    }

    public void setRentDate(Date rentDate) {
        this.rentDate = rentDate;
    }

    @Basic
    @Column(name = "return_date")
    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    @Basic
    @Column(name = "cost")
    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    @Basic
    @Column(name = "state")
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }


    @ManyToOne
    @JoinColumn(name = "vehicle_id")
    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    @ManyToOne
    @JoinColumn(name="ID_CUSTOMER")
    public ApplicationUser getAppUser() {
        return appUser;
    }

    public void setAppUser(ApplicationUser appUser) {
        this.appUser = appUser;
    }

    @ManyToOne
    @JoinColumn(name="pickup_branch_id")
    public Branch getPickupBranch() {
        return pickupBranch;
    }

    public void setPickupBranch(Branch pickupBranch) {
        this.pickupBranch = pickupBranch;
    }

    @ManyToOne
    @JoinColumn(name="return_branch_id")
    public Branch getReturnBranch() {
        return returnBranch;
    }

    public void setReturnBranch(Branch returnBranch) {
        this.returnBranch = returnBranch;
    }





}
