package com.carrentsystem.carrentsystem.common.Dto;

import java.util.Date;

public class SearchDto {
    private String pickupCity;
    private String returnCity;
    private String carClass;
    private Date pickupDate;
    private Date returnDate;

    public SearchDto(String pickupCity,String returnCity,String carClass,Date pickupDate,Date returnDate){
        this.pickupCity = pickupCity;
        this.returnCity = returnCity;
        this.carClass = carClass;
        this.pickupDate = pickupDate;
        this.returnDate = returnDate;
    }



    public String getPickupCity() {
        return pickupCity;
    }

    public void setPickupCity(String pickupCity) {
        this.pickupCity = pickupCity;
    }

    public String getReturnCity() {
        return returnCity;
    }

    public void setReturnCity(String returnCity) {
        this.returnCity = returnCity;
    }

    public String getCarClass() {
        return carClass;
    }

    public void setCarClass(String carClass) {
        this.carClass = carClass;
    }

    public Date getPickupDate() {
        return pickupDate;
    }

    public void setPickupDate(Date pickupDate) {
        this.pickupDate = pickupDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }
}
