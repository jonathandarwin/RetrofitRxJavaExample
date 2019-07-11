package com.example.retrofitexample.repository;

import com.example.retrofitexample.base.RetrofitUtil;
import com.example.retrofitexample.common.APIHandler;
import com.example.retrofitexample.common.APIRoute;
import com.example.retrofitexample.datamodel.movie.MovieResponse;
import com.example.retrofitexample.model.Movie;

import org.json.JSONObject;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Single;

public class MovieRepository implements Callback<ResponseBody>{

    private static MovieRepository instance;
    private static APIHandler listener;

    public static MovieRepository getInstance(){
        if(instance == null)
            instance = new MovieRepository();
        return instance;
    }

    public static MovieRepository getInstance(APIHandler param){
        if(instance == null)
            instance = new MovieRepository();
        listener = param;
        return instance;
    }

    public interface IMovieRepositoryObservable{
        @GET("/")
        Observable<MovieResponse> getListMovie(
                @Query("apikey") String key,
                @Query("s") String query
        );
    }


    public Observable<MovieResponse> getListMovie(String param){
        return RetrofitUtil.
                getInstance().
                createOMBDService().
                getListMovie(APIRoute.API_KEY, param);
    }

    @Override
    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
        try{
            listener.onResponse(new JSONObject(response.body().string()));
        }
        catch (Exception e){
            listener.onFailure(e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void onFailure(Call<ResponseBody> call, Throwable t) {
        listener.onFailure(t.getMessage());
    }
}
