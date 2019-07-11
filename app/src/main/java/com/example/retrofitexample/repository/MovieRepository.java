package com.example.retrofitexample.repository;

import com.example.retrofitexample.base.RetrofitUtil;
import com.example.retrofitexample.common.APIRoute;
import com.example.retrofitexample.datamodel.movie.MovieResponse;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class MovieRepository{

    private static MovieRepository instance;

    public static MovieRepository getInstance(){
        if(instance == null)
            instance = new MovieRepository();
        return instance;
    }

    public interface IMovieRepository{
        @GET("/")
        Observable<MovieResponse> getListMovie(
                @Query("apikey") String key,
                @Query("s") String query
        );
    }


    public Observable<MovieResponse> getListMovie(String param){
        return RetrofitUtil.getInstance().createOMBDService().getListMovie(APIRoute.API_KEY, param);
    }
}
