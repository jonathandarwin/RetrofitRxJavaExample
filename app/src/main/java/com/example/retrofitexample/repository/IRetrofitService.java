package com.example.retrofitexample.repository;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface IRetrofitService {

    @GET("/")
    Call<ResponseBody> getListMovie(
            @Query("apikey") String key,
            @Query("s") String query
    );
}
