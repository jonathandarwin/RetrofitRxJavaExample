package com.example.retrofitexample.app;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;

import com.example.retrofitexample.model.Movie;
import com.example.retrofitexample.repository.IRetrofitService;
import com.example.retrofitexample.repository.MovieRepository;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainViewModel extends ViewModel {

    private final static String BASE_URL = "http://www.omdbapi.com";

    public LiveData<String> getListMovie(){
        final MutableLiveData<String> result = new MutableLiveData<>();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .build();

        IRetrofitService service = retrofit.create(IRetrofitService.class);
        Call<ResponseBody> call = service.getListMovie("97e4d484", "social");
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try{
                    result.setValue(response.body().string());
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                result.setValue("Error. Please try again.");
            }
        });
        return result;
    }
}
