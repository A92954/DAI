package com.example.dai;

public class CalendarModel {
    String day;
    String acti_name;
    String id_calendar;

    public CalendarModel(){

    }

    public CalendarModel(String day, String acti_name, String id_calendar){
        this.day = day;
        this.acti_name = acti_name;
        this.id_calendar = id_calendar;
    }

    public String getId_calendar() {
        return id_calendar;
    }

    public void setId_calendar(String id_calendar) {
        this.id_calendar = id_calendar;
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
