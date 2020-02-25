package com.easyride.easyridebookingservice.model;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;

@Document("Driver")
public class Driver {

    @Id
    private int driverId;
    private String driverName;
    private float rating;
    private float driverprice;
    private ArrayList<String> languages;
    public Driver(int driverId, String driverName, float rating, ArrayList<String> languages) {
        super();
        this.driverId = driverId;
        this.driverName = driverName;
        this.rating = rating;
        this.languages = languages;
    }
    public Driver() {
        super();
    }

    public float getDriverprice() {
        return driverprice;
    }

    public void setDriverprice(float driverprice) {
        this.driverprice = driverprice;
    }

    public int getDriverId() {
        return driverId;
    }
    public void setDriverId(int driverId) {
        this.driverId = driverId;
    }
    public String getDriverName() {
        return driverName;
    }
    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }
    public float getRating() {
        return rating;
    }
    public void setRating(float rating) {
        this.rating = rating;
    }
    public ArrayList<String> getLanguages() {
        return languages;
    }
    public void setLanguages(ArrayList<String> languages) {
        this.languages = languages;
    }
}
