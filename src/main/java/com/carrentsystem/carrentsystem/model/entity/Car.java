package com.carrentsystem.carrentsystem.model.entity;

import javax.persistence.*;

@Entity
@Table(name = "vehicle", schema = "public", catalog = "postgres")
public class Car {
    private Long id;
    private String brand;
    private String model;
    private String type;
    private String prodYear;
    private String color;
    private String photo;
    private String description;
    private Branch branch;
    private String active;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "vehicle_sequence")
    @SequenceGenerator(name = "vehicle_sequence", sequenceName = "vehicle_sequence", allocationSize = 1)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "brand")
    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    @Basic
    @Column(name = "model")
    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @Basic
    @Column(name = "type")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Basic
    @Column(name = "prod_year")
    public String getProdYear() {
        return prodYear;
    }

    public void setProdYear(String prodYear) {
        this.prodYear = prodYear;
    }

    @Basic
    @Column(name = "color")
    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }


    @Basic
    @Column(name = "photo")
    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    @Basic
    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    @ManyToOne
    @JoinColumn(name="branch_id")
    public Branch getBranch() {
        return branch;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }


    @Basic
    @Column(name = "active")
    public String getActive() {
        return active;
    }


    public void setActive(String active) {
        this.active = active;
    }





    public void setDescription(String description) {
        this.description = description;
    }



}
