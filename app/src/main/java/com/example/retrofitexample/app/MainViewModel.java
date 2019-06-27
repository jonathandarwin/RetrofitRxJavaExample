package com.example.retrofitexample.app;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import com.example.retrofitexample.common.APIHandler;
import com.example.retrofitexample.datamodel.movie.MovieResponse;
import com.example.retrofitexample.model.Movie;
import com.example.retrofitexample.repository.MovieRepository;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainViewModel extends ViewModel {

    private Gson gson;

    public MainViewModel(){
        GsonBuilder builder = new GsonBuilder();
        gson = builder.create();
    }

    public LiveData<List<Movie>> getListMovie(String search){
        final MutableLiveData<List<Movie>> result = new MutableLiveData<>();
        MovieRepository.getInstance(new APIHandler() {
            @Override
            public void onResponse(JSONObject response) {
                try{
                    MovieResponse listMovie = gson.fromJson(response.toString(), MovieResponse.class);
                    result.setValue(listMovie.getListMovie() != null ? listMovie.getListMovie() : new ArrayList<Movie>());
                }
                catch(Exception e){
                    result.setValue(new ArrayList<Movie>());
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(String call) {
                result.setValue(new ArrayList<Movie>());
            }
        }).getListMovie(search);

        return result;
    }
}
