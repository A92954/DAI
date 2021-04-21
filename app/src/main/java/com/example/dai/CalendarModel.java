package com.example.dai;

public class CalendarModel {
    String day;
    String acti_name;

    public CalendarModel(){

    }

    public CalendarModel(String day, String acti_name){
        this.day = day;
        this.acti_name = acti_name;
    }

    public String getDay() {
        return day;
    }

    public String getActi_name() {
        return acti_name;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public void setActi_name(String acti_name) {
        this.acti_name = acti_name;
    }
}
