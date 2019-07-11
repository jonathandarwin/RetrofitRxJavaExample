package com.example.retrofitexample.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.example.retrofitexample.BR;

public class User extends BaseObservable {
    protected int id;
    protected String email;
    protected String first_name;
    protected String last_name;
    protected String avatar;

    @Bindable
    public String getDisplayName(){
        return getFirst_name() + " " + getLast_name();
    }

    @Bindable
    public int getId() {
        return id;
    }

    public User setId(int id) {
        this.id = id;
        notifyPropertyChanged(BR.id);
        return this;
    }

    @Bindable
    public String getEmail() {
        return email;
    }

    public User setEmail(String email) {
        this.email = email;
        notifyPropertyChanged(BR.email);
        return this;
    }

    @Bindable
    public String getFirst_name() {
        return first_name;
    }

    public User setFirst_name(String first_name) {
        this.first_name = first_name;
        notifyPropertyChanged(BR.first_name);
        return this;
    }

    @Bindable
    public String getLast_name() {
        return last_name;
    }

    public User setLast_name(String last_name) {
        this.last_name = last_name;
        notifyPropertyChanged(BR.last_name);
        return this;
    }

    @Bindable
    public String getAvatar() {
        return avatar;
    }

    public User setAvatar(String avatar) {
        this.avatar = avatar;
        notifyPropertyChanged(BR.avatar);
        return this;
    }
}
