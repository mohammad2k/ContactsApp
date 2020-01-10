package com.example.mycontacts.models;

import android.widget.Button;

public class ModelCalls {

    private String number,duration,date,time,name;

    public ModelCalls(String number, String duration, String date, String time, String name) {
        this.number = number;
        this.duration = duration;
        this.date = date;
        this.time = time;
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}