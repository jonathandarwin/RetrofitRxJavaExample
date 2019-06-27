package com.example.retrofitexample.app;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;
import com.example.retrofitexample.common.APIHandler;
import com.example.retrofitexample.datamodel.movie.MovieResponse;
import com.example.retrofitexample.model.Movie;
import com.example.retrofitexample.repository.MovieRepository;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.JSONObject;

import java.util.List;

public class MainViewModel extends ViewModel {

    private Gson gson;

    public MainViewModel(){
        GsonBuilder builder = new GsonBuilder();
        gson = builder.create();
    }

    private final static String BASE_URL = "http://www.omdbapi.com";

    public LiveData<List<Movie>> getListMovie(){
        final MutableLiveData<List<Movie>> result = new MutableLiveData<>();
        MovieRepository.getInstance(new APIHandler() {
            @Override
            public void onResponse(JSONObject response) {
                try{
                    MovieResponse listMovie = gson.fromJson(response.toString(), MovieResponse.class);
                    result.setValue(listMovie.getListMovie());
                }
                catch(Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(String call) {
                Log.d("masuksiniga", call);
            }
        }).getListMovie("social");

        return result;
    }
}
