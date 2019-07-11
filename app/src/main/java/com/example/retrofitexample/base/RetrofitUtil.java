package com.example.retrofitexample.base;

import com.example.retrofitexample.common.APIRoute;
import com.example.retrofitexample.repository.MovieRepository;
import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitUtil {

    private static RetrofitUtil instance;

    public static RetrofitUtil getInstance(){
        if(instance == null)
            instance = new RetrofitUtil();
        return instance;
    }

    public MovieRepository.IMovieRepository createOMBDService(){
        return new Retrofit.Builder()
                .baseUrl(APIRoute.OMBD_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(new OkHttpClient
                        .Builder()
                        .addInterceptor((new HttpLoggingInterceptor()).setLevel(HttpLoggingInterceptor.Level.BODY))
                        .readTimeout(10, TimeUnit.SECONDS)
                        .connectTimeout(30, TimeUnit.SECONDS)
                        .build())
                .build()
                .create(MovieRepository.IMovieRepository.class);
    }
}
