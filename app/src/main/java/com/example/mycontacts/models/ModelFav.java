package com.example.mycontacts.models;

import java.util.ArrayList;
import java.util.List;

public class ModelFav {

    private List<ModelContacts> favList;

    public ModelFav() {
        favList = new ArrayList<>();
    }

    public void addContacttoList(ModelContacts modelContacts){
        favList.add(modelContacts);
    }

    public List<ModelContacts> getFavList() {
        return favList;
    }

    public void setFavList(List<ModelContacts> favList) {
        this.favList = favList;
    }
}
