package com.example.retrofitexample.repository;

import com.example.retrofitexample.base.RetrofitUtil;
import com.example.retrofitexample.common.APIHandler;

import org.json.JSONObject;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class UserRepository implements Callback<ResponseBody> {

    private static UserRepository instance;
    public static APIHandler listener;


    public static UserRepository getInstance(APIHandler param){
        if(instance == null){
            instance = new UserRepository();
        }
        listener = param;
        return instance;
    }

    public interface IUserRepository{
        @GET("/api/users")
        Call<ResponseBody> getUser(
                @Query("per_page") int per_page
        );
    }

    public void getUser(){
        Call<ResponseBody> call = RetrofitUtil.getInstance().createReqresService().getUser(12);
        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
        try{
            listener.onResponse(new JSONObject(response.body().string()));
        }
        catch (Exception e){
            e.printStackTrace();
            listener.onFailure(e.getMessage());
        }

    }

    @Override
    public void onFailure(Call<ResponseBody> call, Throwable t) {
        listener.onFailure(t.getMessage());
    }
}
