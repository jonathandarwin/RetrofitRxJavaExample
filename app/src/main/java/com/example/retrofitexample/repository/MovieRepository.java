package com.example.retrofitexample.repository;

import retrofit2.Retrofit;

public class MovieRepository {

    private final static String BASE_URL = "http://www.omdbapi.com";
    private static Retrofit retrofit;

    public static Retrofit getInstance(){
        if(retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .build();
        }
        return retrofit;
    }
}
