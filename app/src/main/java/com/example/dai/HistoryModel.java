package com.example.dai;

//import java.util.Date;

public class HistoryModel {

    String acti_name;
    String local;
    String data;

    public HistoryModel(){

    }

    public HistoryModel(String acti_name,String local, String data){
        this.local = local;
        this.acti_name = acti_name;
        this.data = data;
    }

    public String getActi_name() {
        return acti_name;
    }

    public void setActi_name(String acti_name) {
        this.acti_name = acti_name;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
