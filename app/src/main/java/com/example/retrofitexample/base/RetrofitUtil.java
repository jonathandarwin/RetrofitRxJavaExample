package com.example.retrofitexample.base;

import com.example.retrofitexample.common.APIRoute;
import com.example.retrofitexample.repository.IRetrofitService;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitUtil {

    private static RetrofitUtil instance;

    public static RetrofitUtil getInstance(){
        if(instance == null)
            instance = new RetrofitUtil();
        return instance;
    }

    public IRetrofitService createOMBDService(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(APIRoute.OMBD_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().create()))
                .build();

        return retrofit.create(IRetrofitService.class);
    }
}
