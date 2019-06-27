package com.example.retrofitexample.app;

import android.arch.lifecycle.Observer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.example.retrofitexample.R;
import com.example.retrofitexample.base.BaseActivity;
import com.example.retrofitexample.databinding.MainActivityBinding;
import com.example.retrofitexample.model.Movie;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity<MainViewModel, MainActivityBinding> {

    MainAdapter adapter;
    List<Movie> listMovie;

    public MainActivity(){
        super(MainViewModel.class, R.layout.main_activity);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setAdapter();
        requestMovie();
    }

    private void setAdapter(){
        listMovie = new ArrayList<>();
        adapter = new MainAdapter(this, listMovie);
        getBinding().recyclerView.setLayoutManager(new LinearLayoutManager(this));
        getBinding().recyclerView.setHasFixedSize(true);
        getBinding().recyclerView.setAdapter(adapter);
    }

    private void requestMovie(){
        getBinding().loading.setVisibility(View.VISIBLE);
        getViewModel().getListMovie().observe(this, new Observer<List<Movie>>() {
            @Override
            public void onChanged(@Nullable List<Movie> movies) {
                getBinding().loading.setVisibility(View.GONE);
                listMovie.clear();
                if(movies.size() > 0){
                    listMovie.addAll(movies);
                    adapter.notifyDataSetChanged();
                }
            }
        });
    }
}