package com.example.retrofitexample.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.example.retrofitexample.BR;

public class Movie extends BaseObservable {
    protected String Title;
    protected String Year;
    protected String imbdID;
    protected String Type;
    protected String Poster;

    @Bindable
    public String getTitle() {
        return Title;
    }

    public Movie setTitle(String title) {
        Title = title;
        notifyPropertyChanged(BR.title);
        return this;
    }

    @Bindable
    public String getYear() {
        return Year;
    }

    public Movie setYear(String year) {
        Year = year;
        notifyPropertyChanged(BR.year);
        return this;
    }

    @Bindable
    public String getImbdID() {
        return imbdID;
    }

    public Movie setImbdID(String imbdID) {
        this.imbdID = imbdID;
        notifyPropertyChanged(BR.imbdID);
        return this;
    }

    @Bindable
    public String getType() {
        return Type;
    }

    public Movie setType(String type) {
        Type = type;
        notifyPropertyChanged(BR.type);
        return this;
    }

    @Bindable
    public String getPoster() {
        return Poster;
    }

    public Movie setPoster(String poster) {
        Poster = poster;
        notifyPropertyChanged(BR.poster);
        return this;
    }
}
