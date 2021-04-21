package com.example.dai.Backend;

import java.text.DateFormat;
import java.util.Date;

public class Activity {
    private int id_activity;
    private String name;
    private String address;
    private String city;
    private DateFormat hours;
    private Date beginning_date;
    private Date end_date;

    public Activity() {}

    public Activity(int id_activity, String name, String address, String city, DateFormat hours, Date beginning_date, Date end_date) {
        this.id_activity = id_activity;
        this.name = name;
        this.address = address;
        this.city = city;
        this.hours = hours;
        this.beginning_date = beginning_date;
        this.end_date = end_date;
    }

    public int getId_activity() {
        return id_activity;
    }

    public void setId_activity(int id_activity) {
        this.id_activity = id_activity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public DateFormat getHours() {
        return hours;
    }

    public void setHours(DateFormat hours) {
        this.hours = hours;
    }

    public Date getBeginning_date() {
        return beginning_date;
    }

    public void setBeginning_date(Date beginning_date) {
        this.beginning_date = beginning_date;
    }

    public Date getEnd_date() {
        return end_date;
    }

    public void setEnd_date(Date end_date) {
        this.end_date = end_date;
    }

    @Override
    public String toString() {
        return "Activity: " +
                "id = " + id_activity +
                ", name = " + name +
                ", address = " + address +
                ", city = " + city +
                ", hours = " + hours +
                ", beginning_date = " + beginning_date +
                ", end_date = " + end_date;
    }
}
