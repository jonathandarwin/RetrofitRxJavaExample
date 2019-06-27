package com.example.retrofitexample.repository;

import com.example.retrofitexample.base.RetrofitUtil;
import com.example.retrofitexample.common.APIHandler;
import com.example.retrofitexample.common.APIRoute;
import org.json.JSONObject;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieRepository{

    private static MovieRepository instance;
    private static APIHandler listener;

    public static MovieRepository getInstance(APIHandler param){
        if(instance == null)
            instance = new MovieRepository();
        listener = param;
        return instance;
    }

    public void getListMovie(String param){
        Call<ResponseBody> call = RetrofitUtil.getInstance().createOMBDService().getListMovie(APIRoute.API_KEY, param);
        call.enqueue(new Callback<ResponseBody>() {
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
        });
    }
}
