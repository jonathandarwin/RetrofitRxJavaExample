package com.example.retrofitexample.app;

import android.arch.lifecycle.Observer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListAdapter;

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
        requestMovie("social");
    }

    @Override
    protected void setListener() {
        super.setListener();
        getBinding().txtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                requestMovie(s.toString());
            }
        });
    }

    @Override
    protected void setAdapter() {
        super.setAdapter();
        listMovie = new ArrayList<>();
        adapter = new MainAdapter(this, listMovie);
        getBinding().recyclerView.setLayoutManager(new LinearLayoutManager(this));
        getBinding().recyclerView.setHasFixedSize(true);
        getBinding().recyclerView.setAdapter(adapter);
    }

    private void requestMovie(String search){
        getBinding().loading.setVisibility(View.VISIBLE);
        listMovie.clear();
        adapter.notifyDataSetChanged();
        getViewModel().getListMovie(search).observe(this, new Observer<List<Movie>>() {
            @Override
            public void onChanged(@Nullable List<Movie> movies) {
                listMovie.clear();
                getBinding().loading.setVisibility(View.GONE);
                getBinding().setResult("Show " + movies.size() + " result(s)");
                Log.d("masuksingia","size : " + movies.size());
                if(movies.size() > 0){
                    listMovie.addAll(movies);
                    adapter.notifyDataSetChanged();
                }
            }
        });
    }
}