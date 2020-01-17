package com.example.mycontacts.models;

import android.widget.Button;

public class ModelContacts {

    private String name,number;
    private Boolean star ;

    public ModelContacts(String name, String number , Boolean star) {
        this.name = name;
        this.number = number;
        this.star = star;
    }

    public Boolean getStar() {
        return star;
    }

    public void setStar(Boolean star) {
        this.star = star;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

}
