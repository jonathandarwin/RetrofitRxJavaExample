package com.example.retrofitexample.datamodel.user;

import com.example.retrofitexample.model.User;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class UserResponse{
    @SerializedName("data")
    protected List<User> listUser;

    public List<User> getListUser() {
        return listUser;
    }

    public void setListUser(List<User> listUser) {
        this.listUser = listUser;
    }
}
